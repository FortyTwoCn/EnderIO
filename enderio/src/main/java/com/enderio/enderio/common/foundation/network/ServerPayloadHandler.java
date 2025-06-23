package com.enderio.enderio.common.foundation.network;

import com.enderio.enderio.common.foundation.blocks.entities.EnderBlockEntity;
import com.enderio.enderio.common.foundation.menu.BaseEnderMenu;
import com.enderio.enderio.common.foundation.network.menu.ServerboundSetSyncSlotDataPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {

    private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

    public static ServerPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleDataSlotChange(ClientboundDataSlotChange change, IPayloadContext context) {
        context.enqueueWork(() -> {
            var level = context.player().level();
            BlockEntity be = level.getBlockEntity(change.pos());
            if (be instanceof EnderBlockEntity enderBlockEntity) {
                var buf = new RegistryFriendlyByteBuf(Unpooled.wrappedBuffer(change.updateData()),
                        level.registryAccess());
                enderBlockEntity.serverHandleBufferChange(buf);
                buf.release();
            }
        });
    }

    public void handleSetSyncSlotDataPacket(ServerboundSetSyncSlotDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player().containerMenu.containerId == packet.containerId()) {
                if (context.player().containerMenu instanceof BaseEnderMenu enderMenu) {
                    enderMenu.serverHandleIncomingPayload(packet.index(), packet.payload());
                }
            }
        });
    }
}
