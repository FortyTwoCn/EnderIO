package com.enderio.legacy_layout.conduits.common.integrations;

import com.enderio.enderio.common.EnderIO;
import com.enderio.legacy_layout.base.api.integration.IntegrationManager;
import com.enderio.legacy_layout.base.api.integration.IntegrationWrapper;
import com.enderio.legacy_layout.conduits.common.integrations.cctweaked.CCIntegration;

public class Integrations {

    public static final IntegrationWrapper<CCIntegration> CC_INTEGRATION = IntegrationManager.wrapper("computercraft",
            () -> CCIntegration::new, EnderIO.modEventBus);

    public static void register() {
    }
}
