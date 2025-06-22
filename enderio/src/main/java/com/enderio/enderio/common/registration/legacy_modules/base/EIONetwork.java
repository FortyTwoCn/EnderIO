package com.enderio.enderio.common.registration.legacy_modules.base;

import com.enderio.enderio.common.features.filters.fluid.C2SSetFluidFilterSlot;
import com.enderio.enderio.common.features.filters.item.C2SSetItemFilterSlot;
import com.enderio.enderio.common.legacy_to_move.network.ClientPayloadHandler;
import com.enderio.enderio.common.features.travel.network.RequestTravelPacket;
import com.enderio.enderio.common.legacy_to_move.network.ServerPayloadHandler;
import com.enderio.enderio.common.features.travel.network.SyncTravelDataPacket;
import com.enderio.enderio.common.features.travel.network.TravelTargetRemovedPacket;
import com.enderio.enderio.common.features.travel.network.TravelTargetUpdatedPacket;
import com.enderio.enderio.common.features.equipment.coordinate_selector.UpdateCoordinateSelectionNameMenuPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber
public class EIONetwork {
    private static final String PROTOCOL_VERSION = "2";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);

        registrar.playToClient(SyncTravelDataPacket.TYPE, SyncTravelDataPacket.STREAM_CODEC,
                ClientPayloadHandler.getInstance()::handleSyncTravelDataPacket);

        registrar.playToClient(TravelTargetUpdatedPacket.TYPE, TravelTargetUpdatedPacket.STREAM_CODEC,
                ClientPayloadHandler.getInstance()::handleAddTravelTarget);

        registrar.playToClient(TravelTargetRemovedPacket.TYPE, TravelTargetRemovedPacket.STREAM_CODEC,
                ClientPayloadHandler.getInstance()::handleRemoveTravelTarget);

        registrar.playToServer(UpdateCoordinateSelectionNameMenuPacket.TYPE,
                UpdateCoordinateSelectionNameMenuPacket.STREAM_CODEC,
                ServerPayloadHandler.getInstance()::handleCoordinateSelectionName);

        registrar.playToServer(RequestTravelPacket.TYPE, RequestTravelPacket.STREAM_CODEC,
                ServerPayloadHandler.getInstance()::handleTravelRequest);

        registrar.playToServer(C2SSetFluidFilterSlot.TYPE, C2SSetFluidFilterSlot.STREAM_CODEC,
                ServerPayloadHandler.getInstance()::handleSetFluidFilterSlot);

        registrar.playToServer(C2SSetItemFilterSlot.TYPE, C2SSetItemFilterSlot.STREAM_CODEC,
                ServerPayloadHandler.getInstance()::handleSetItemFilterSlot);
    }

}
