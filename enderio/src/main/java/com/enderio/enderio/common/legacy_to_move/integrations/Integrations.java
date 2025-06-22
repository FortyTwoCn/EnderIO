package com.enderio.enderio.common.legacy_to_move.integrations;

import com.enderio.enderio.api.integration.IntegrationManager;

public class Integrations {

    public static void register() {
        IntegrationManager.addIntegration(EnderIOSelfIntegration.INSTANCE);
    }
}
