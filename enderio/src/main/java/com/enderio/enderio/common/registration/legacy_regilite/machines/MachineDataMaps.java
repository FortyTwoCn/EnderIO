package com.enderio.enderio.common.registration.legacy_regilite.machines;

import com.enderio.enderio.common.EnderIO;
import com.enderio.enderio.common.content.machines.upgrades.RangeExtender;
import com.enderio.enderio.common.content.machines.upgrades.VatReagent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = EnderIO.MOD_ID)
public class MachineDataMaps {
    @SubscribeEvent
    public static void registerDataMap(RegisterDataMapTypesEvent event) {
        event.register(VatReagent.DATA_MAP);
        event.register(RangeExtender.DATA_MAP);
    }
}
