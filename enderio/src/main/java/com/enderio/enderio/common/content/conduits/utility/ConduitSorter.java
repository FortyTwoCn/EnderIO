package com.enderio.enderio.common.content.conduits.utility;

import com.enderio.enderio.api.EnderIORegistries;
import com.enderio.enderio.api.conduits.bundle.ConduitBundle;
import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.common.foundation.util.ThrowableUtil;
import com.enderio.enderio.api.conduits.Conduit;
import com.enderio.enderio.api.conduits.ConduitType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import org.joml.Vector2i;
import org.slf4j.Logger;

/**
 * This class is used to sort conduit types for display.
 * This is needed, so upgrading conduits doesn't require shifting of types, but just recalculating the current connection
 */
@SuppressWarnings("unused")
@EventBusSubscriber(modid = EnderIO.MOD_ID)
public class ConduitSorter {
    private static final List<Holder<Conduit<?, ?>>> SORTED_CONDUITS = new ArrayList<>();

    @SubscribeEvent
    public static void serverSortTypes(ServerStartedEvent event) {
        var conduitRegistry = event.getServer()
                .registryAccess()
                .registryOrThrow(EnderIORegistries.Keys.CONDUIT);
        sortTypes(conduitRegistry);
    }

    @SubscribeEvent
    public static void clientSortTypes(ClientPlayerNetworkEvent.LoggingIn event) {
        var conduitRegistry = event.getPlayer()
                .registryAccess()
                .registryOrThrow(EnderIORegistries.Keys.CONDUIT);
        sortTypes(conduitRegistry);
    }

    private static void sortTypes(Registry<Conduit<?, ?>> registry) {
        SORTED_CONDUITS.clear();

        // Group like types together.
        List<ConduitType<?>> conduitTypes = EnderIORegistries.CONDUIT_TYPE.stream()
                .sorted(Comparator.comparing(
                        i -> Objects.requireNonNull(EnderIORegistries.CONDUIT_TYPE.getKey(i)).toString()))
                .toList();

        List<Holder<Conduit<?, ?>>> sortedConduits = new ArrayList<>();
        for (ConduitType<?> conduitType : conduitTypes) {
            sortedConduits.addAll(gatherConduitsForType(registry, conduitType));
        }
        SORTED_CONDUITS.addAll(sortedConduits);
    }

    private static <T extends Conduit<T, ?>> List<Holder<Conduit<?, ?>>> gatherConduitsForType(
            Registry<Conduit<?, ?>> registry, ConduitType<T> conduitType) {
        return registry.holders()
                .filter(i -> i.value().type() == conduitType)
                // Group by tier, then by name
                .sorted(new Comparator<Holder<Conduit<?, ?>>>() {
                    @Override
                    public int compare(Holder<Conduit<?, ?>> o1, Holder<Conduit<?, ?>> o2) {
                        return ((T) o1.value()).compareTo((T) o2.value());
                    }
                }.thenComparing(Holder::getRegisteredName))
                .map(i -> (Holder<Conduit<?, ?>>) i)
                .toList();
    }

    public static int getSortIndex(Holder<Conduit<?, ?>> conduit) {
        return SORTED_CONDUITS.indexOf(conduit);
    }

    public static class OffsetHelper {

        private static final Logger LOGGER = LogUtils.getLogger();

        /**
         *
         * Creates an offset based on the Schema
         *
         * @param typeIndex of the type you want to query
         * @param maxTypes for that Direction
         * @return the offset
         * <p>
         *  ###
         *  #1#
         *  ###
         * <p>
         *  #1#
         *  ###
         *  #2#
         * <p>
         *  1##
         *  #2#
         *  ##3
         * <p>
         *  all after 3:
         *  615
         *  294
         *  738
         */

        public static final Map<Integer, Vector2i> positions = Util.make(() -> {
            Map<Integer, Vector2i> positions = new HashMap<>();
            positions.put(1, new Vector2i(0, -1));
            positions.put(2, new Vector2i(-1, 0));
            positions.put(3, new Vector2i(0, 1));
            positions.put(4, new Vector2i(1, 0));
            positions.put(5, new Vector2i(1, -1));
            positions.put(6, new Vector2i(-1, -1));
            positions.put(7, new Vector2i(-1, 1));
            positions.put(8, new Vector2i(1, 1));
            positions.put(9, new Vector2i(0, 0));
            return positions;
        });

        public static Vector2i offsetConduit(int conduitIndex, int maxConduits) {
            if (conduitIndex >= maxConduits) {
                LOGGER.warn("Higher index than existing conduits in OffsetHelper#offsetConduit",
                        ThrowableUtil.addStackTrace(new IndexOutOfBoundsException("higher index than existing conduits")));
                return new Vector2i();
            }

            if (conduitIndex < 0) {
                LOGGER.warn("Negative index in OffsetHelper#offsetConduit",
                        ThrowableUtil.addStackTrace(new IndexOutOfBoundsException("negative index")));
                return new Vector2i();
            }

            if (maxConduits == 1) {
                return new Vector2i();
            }

            if (maxConduits == 2) {
                return conduitIndex == 0 ? new Vector2i(0, -1) : new Vector2i(0, 1);
            }

            if (maxConduits == 3) {
                switch (conduitIndex) {
                case 0 -> {
                    return new Vector2i(-1, -1);
                }
                case 1 -> {
                    return new Vector2i();
                }
                case 2 -> {
                    return new Vector2i(1, 1);
                }
                default -> throw new IllegalStateException();
                }
            }

            if (maxConduits < 9) {
                Vector2i vector2i = positions.get(conduitIndex + 1);
                if (vector2i != null) {
                    return vector2i;
                }
            }

            LOGGER.warn("Fallback was applied in OffsetHelper#offsetConduit",
                    ThrowableUtil.addStackTrace(new IndexOutOfBoundsException("fallback was applied")));
            return new Vector2i();
        }

        public static Vec3i translationFor(Direction.Axis axis, Vector2i offset) {
            return switch (axis) {
            case X -> new Vec3i(0, offset.y(), offset.x());
            case Y -> new Vec3i(offset.x(), 0, offset.y());
            case Z -> new Vec3i(offset.x(), offset.y(), 0);
            };
        }

        public static Direction.Axis findMainAxis(ConduitBundle bundle) {
            List<Direction> connectedDirs = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                if (!bundle.getConnectedConduits(dir).isEmpty()) {
                    connectedDirs.add(dir);
                }
            }

            if (connectedDirs.isEmpty()) {
                return Direction.Axis.Z;
            }

            // get Last as MainAxis, because those are the horizontal ones
            return connectedDirs.get(connectedDirs.size() - 1).getAxis();
        }
    }
}
