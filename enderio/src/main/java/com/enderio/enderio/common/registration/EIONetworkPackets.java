package com.enderio.enderio.common.registration;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.maven.artifact.versioning.ArtifactVersion;

public class EIONetworkPackets {
    private static String protocolVersion;

    public static void registerPackets(IEventBus modEventBus, ModContainer modContainer) {
        ArtifactVersion currentVersion = modContainer.getModInfo().getVersion();

        protocolVersion = String.format("%s.%s.%s", currentVersion.getMajorVersion(), currentVersion.getMinorVersion(),
            currentVersion.getIncrementalVersion());

        modEventBus.addListener(EIONetworkPackets::registerPayloadHandlers);
    }

    private static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(protocolVersion);

    }
}
