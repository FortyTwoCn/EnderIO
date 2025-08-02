package com.enderio.legacy_layout.base.common.network;

import com.enderio.enderio.common.content.travel.packets.SyncTravelDataPacket;
import com.enderio.enderio.common.content.travel.packets.TravelTargetRemovedPacket;
import com.enderio.enderio.common.content.travel.packets.TravelTargetUpdatedPacket;
import com.enderio.legacy_layout.base.api.travel.TravelTargetApi;
import com.enderio.enderio.common.content.travel.TravelTargetSavedData;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    private static final ClientPayloadHandler INSTANCE = new ClientPayloadHandler();

    public static ClientPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleSyncTravelDataPacket(SyncTravelDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            TravelTargetSavedData travelData = TravelTargetSavedData.getTravelData(context.player().level());
            travelData.loadNBT(context.player().registryAccess(), packet.data());
        });
    }

    public void handleAddTravelTarget(TravelTargetUpdatedPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            var level = context.player().level();
            TravelTargetApi.INSTANCE.set(level, packet.target());
        });
    }

    public void handleRemoveTravelTarget(TravelTargetRemovedPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            var level = context.player().level();
            TravelTargetApi.INSTANCE.removeAt(level, packet.pos());
        });
    }
}
