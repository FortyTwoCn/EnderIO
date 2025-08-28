package com.enderio.enderio.common.registration.legacy_regilite.conduits;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.EnderIORegistries;
import com.enderio.enderio.api.conduits.ConduitType;
import com.enderio.enderio.api.conduits.connection.config.ConnectionConfigType;
import com.enderio.enderio.api.conduits.network.ConduitNetworkContextType;
import com.enderio.enderio.api.conduits.network.node.NodeDataType;
import com.enderio.enderio.api.conduits.network.node.legacy.ConduitDataType;
import com.enderio.enderio.common.content.conduits.legacy.LegacyFluidConduitData;
import com.enderio.enderio.common.content.conduits.legacy.LegacyItemConduitData;
import com.enderio.enderio.common.content.conduits.legacy.LegacyRedstoneConduitData;
import com.enderio.enderio.common.content.conduits.types.energy.EnergyConduit;
import com.enderio.enderio.common.content.conduits.types.energy.EnergyConduitConnectionConfig;
import com.enderio.enderio.common.content.conduits.types.energy.EnergyConduitNetworkContext;
import com.enderio.enderio.common.content.conduits.types.fluid.FluidConduit;
import com.enderio.enderio.common.content.conduits.types.fluid.FluidConduitConnectionConfig;
import com.enderio.enderio.common.content.conduits.types.fluid.FluidConduitNetworkContext;
import com.enderio.enderio.common.content.conduits.types.item.ItemConduit;
import com.enderio.enderio.common.content.conduits.types.item.ItemConduitConnectionConfig;
import com.enderio.enderio.common.content.conduits.types.item.ItemConduitNodeData;
import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduit;
import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduitConnectionConfig;
import com.enderio.enderio.common.content.conduits.types.redstone.RedstoneConduitNetworkContext;
import java.util.function.Supplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ConduitTypes {
    private static final DeferredRegister<ConduitType<?>> CONDUIT_TYPES = DeferredRegister
            .create(EnderIORegistries.CONDUIT_TYPE, EnderIOAPI.NAMESPACE);

    public static final Supplier<ConduitType<EnergyConduit>> ENERGY = CONDUIT_TYPES.register("energy",
            () -> ConduitType.builder(EnergyConduit.CODEC).exposeCapability(Capabilities.EnergyStorage.BLOCK).build());

    public static final Supplier<ConduitType<RedstoneConduit>> REDSTONE = CONDUIT_TYPES.register("redstone",
            () -> ConduitType.of(RedstoneConduit.CODEC));

    public static final Supplier<ConduitType<FluidConduit>> FLUID = CONDUIT_TYPES.register("fluid",
            () -> ConduitType.of(FluidConduit.CODEC));

    public static final Supplier<ConduitType<ItemConduit>> ITEM = CONDUIT_TYPES.register("item",
            () -> ConduitType.of(ItemConduit.CODEC));

    public static class Data {
        private static final DeferredRegister<ConduitDataType<?>> CONDUIT_DATA_TYPES = DeferredRegister
                .create(EnderIORegistries.CONDUIT_DATA_TYPE, EnderIOAPI.NAMESPACE);

        public static final Supplier<ConduitDataType<LegacyItemConduitData>> ITEM = CONDUIT_DATA_TYPES.register("item",
                () -> new ConduitDataType<>(LegacyItemConduitData.CODEC, LegacyItemConduitData.STREAM_CODEC,
                        LegacyItemConduitData::new));

        public static final Supplier<ConduitDataType<LegacyFluidConduitData>> FLUID = CONDUIT_DATA_TYPES
                .register("fluid", () -> new ConduitDataType<>(LegacyFluidConduitData.CODEC,
                        LegacyFluidConduitData.STREAM_CODEC, LegacyFluidConduitData::new));

        public static final Supplier<ConduitDataType<LegacyRedstoneConduitData>> REDSTONE = CONDUIT_DATA_TYPES
                .register("redstone", () -> new ConduitDataType<>(LegacyRedstoneConduitData.CODEC,
                        LegacyRedstoneConduitData.STREAM_CODEC, LegacyRedstoneConduitData::new));
    }

    public static class ConnectionTypes {
        private static final DeferredRegister<ConnectionConfigType<?>> CONNECTION_TYPES = DeferredRegister
                .create(EnderIORegistries.CONDUIT_CONNECTION_CONFIG_TYPE, EnderIOAPI.NAMESPACE);

        public static final Supplier<ConnectionConfigType<ItemConduitConnectionConfig>> ITEM = CONNECTION_TYPES
                .register("item", () -> ItemConduitConnectionConfig.TYPE);

        public static final Supplier<ConnectionConfigType<EnergyConduitConnectionConfig>> ENERGY = CONNECTION_TYPES
                .register("energy", () -> EnergyConduitConnectionConfig.TYPE);

        public static final Supplier<ConnectionConfigType<RedstoneConduitConnectionConfig>> REDSTONE = CONNECTION_TYPES
                .register("redstone", () -> RedstoneConduitConnectionConfig.TYPE);

        public static final Supplier<ConnectionConfigType<FluidConduitConnectionConfig>> FLUID = CONNECTION_TYPES
                .register("fluid", () -> FluidConduitConnectionConfig.TYPE);
    }

    public static class NodeData {
        private static final DeferredRegister<NodeDataType<?>> NODE_DATA_TYPES = DeferredRegister
                .create(EnderIORegistries.CONDUIT_NODE_DATA_TYPE, EnderIOAPI.NAMESPACE);

        public static final Supplier<NodeDataType<ItemConduitNodeData>> ITEM = NODE_DATA_TYPES.register("item",
                () -> ItemConduitNodeData.TYPE);
    }

    public static class ContextTypes {
        public static final DeferredRegister<ConduitNetworkContextType<?>> CONDUIT_NETWORK_CONTEXT_TYPES = DeferredRegister
                .create(EnderIORegistries.CONDUIT_NETWORK_CONTEXT_TYPE, EnderIOAPI.NAMESPACE);

        public static final Supplier<ConduitNetworkContextType<EnergyConduitNetworkContext>> ENERGY = CONDUIT_NETWORK_CONTEXT_TYPES
                .register("energy", () -> EnergyConduitNetworkContext.TYPE);

        public static final Supplier<ConduitNetworkContextType<RedstoneConduitNetworkContext>> REDSTONE = CONDUIT_NETWORK_CONTEXT_TYPES
                .register("redstone", () -> RedstoneConduitNetworkContext.TYPE);

        public static final Supplier<ConduitNetworkContextType<FluidConduitNetworkContext>> FLUID = CONDUIT_NETWORK_CONTEXT_TYPES
                .register("fluid", () -> FluidConduitNetworkContext.TYPE);
    }

    public static void register(IEventBus bus) {
        CONDUIT_TYPES.register(bus);
        Data.CONDUIT_DATA_TYPES.register(bus);
        ConnectionTypes.CONNECTION_TYPES.register(bus);
        NodeData.NODE_DATA_TYPES.register(bus);
        ContextTypes.CONDUIT_NETWORK_CONTEXT_TYPES.register(bus);
    }
}
