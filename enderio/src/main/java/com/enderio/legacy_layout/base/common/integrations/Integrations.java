package com.enderio.legacy_layout.base.common.integrations;

import com.enderio.legacy_layout.base.api.integration.IntegrationManager;

public class Integrations {

    public static void register() {
        IntegrationManager.addIntegration(EnderIOSelfIntegration.INSTANCE);
    }
}
