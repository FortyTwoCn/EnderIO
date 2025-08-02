package com.enderio.enderio.common.content.conduits.packets;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.api.conduits.Conduit;
import com.enderio.enderio.common.content.conduits.bundle.ConduitBundleBlockEntity;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record S2CConduitListPacket(int containerId, List<Holder<Conduit<?, ?>>> conduits)
        implements CustomPacketPayload {

    public static final Type<S2CConduitListPacket> TYPE = new Type<>(EnderIOAPI.loc("conduit_list"));

    public static final StreamCodec<RegistryFriendlyByteBuf, S2CConduitListPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, S2CConduitListPacket::containerId,
            Conduit.STREAM_CODEC.apply(ByteBufCodecs.list(ConduitBundleBlockEntity.MAX_CONDUITS)),
            S2CConduitListPacket::conduits, S2CConduitListPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
