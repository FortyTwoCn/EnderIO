package com.enderio.enderio.common.legacy_to_move.network;

import com.enderio.enderio.api.travel.TravelTargetApi;
import com.enderio.enderio.common.features.travel.TravelTargetSavedData;
import com.enderio.enderio.common.features.travel.network.SyncTravelDataPacket;
import com.enderio.enderio.common.features.travel.network.TravelTargetRemovedPacket;
import com.enderio.enderio.common.features.travel.network.TravelTargetUpdatedPacket;
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
