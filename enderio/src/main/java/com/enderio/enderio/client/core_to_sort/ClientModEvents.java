package com.enderio.enderio.client.core_to_sort;

import com.enderio.enderio.client.core_to_sort.model.EitherModelLoader;
import com.enderio.enderio.common.EnderIO;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(EnderIO.loc("modloaded"), new EitherModelLoader());
    }
}
