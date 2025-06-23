package com.enderio.enderio.common.foundation.network.menu;

import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.common.foundation.network.menu.payload.SlotPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ServerboundSetSyncSlotDataPacket(int containerId, short index, SlotPayload payload)
        implements CustomPacketPayload {

    public static final Type<ServerboundSetSyncSlotDataPacket> TYPE = new Type<>(EnderIO.loc("set_slot_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundSetSyncSlotDataPacket> STREAM_CODEC = StreamCodec
            .composite(ByteBufCodecs.INT, ServerboundSetSyncSlotDataPacket::containerId, ByteBufCodecs.SHORT,
                    ServerboundSetSyncSlotDataPacket::index, SlotPayload.STREAM_CODEC,
                    ServerboundSetSyncSlotDataPacket::payload, ServerboundSetSyncSlotDataPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
