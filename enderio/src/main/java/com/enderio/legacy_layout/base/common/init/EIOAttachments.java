package com.enderio.legacy_layout.base.common.init;

import com.enderio.enderio.api.EnderIOAPI;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class EIOAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister
            .create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EnderIOAPI.NAMESPACE);

    public static void register(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
