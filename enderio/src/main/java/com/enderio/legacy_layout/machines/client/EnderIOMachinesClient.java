package com.enderio.legacy_layout.machines.client;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.common.EnderIO;
import com.enderio.legacy_layout.base.api.travel.RegisterTravelRenderersEvent;
import com.enderio.legacy_layout.machines.client.rendering.item.FluidTankBEWLR;
import com.enderio.legacy_layout.machines.client.rendering.model.IOOverlayBakedModel;
import com.enderio.legacy_layout.machines.client.rendering.travel.EnderfaceRenderer;
import com.enderio.legacy_layout.machines.client.rendering.travel.TravelAnchorHud;
import com.enderio.legacy_layout.machines.client.rendering.travel.TravelAnchorRenderer;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineTravelTargets;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.util.Lazy;

@EventBusSubscriber(modid = EnderIO.MOD_ID, value = Dist.CLIENT)
@Mod(value = EnderIO.MOD_ID, dist = Dist.CLIENT)
public class EnderIOMachinesClient {

    public EnderIOMachinesClient(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerTravelRenderers(RegisterTravelRenderersEvent event) {
        event.register(MachineTravelTargets.TRAVEL_ANCHOR_TYPE.get(), TravelAnchorRenderer::new);
        event.register(MachineTravelTargets.ENDERFACE_TYPE.get(), EnderfaceRenderer::new);
    }

    @SubscribeEvent
    public static void customModelLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(EnderIOAPI.loc("io_overlay"), new IOOverlayBakedModel.Loader());
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, EnderIOAPI.loc("anchor_hud"), TravelAnchorHud.INSTANCE);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            // Minecraft can be null during datagen
            final Lazy<BlockEntityWithoutLevelRenderer> renderer = Lazy.of(() -> FluidTankBEWLR.INSTANCE);

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer.get();
            }
        }, MachineBlocks.FLUID_TANK.asItem(), MachineBlocks.PRESSURIZED_FLUID_TANK.asItem());
    }
}
