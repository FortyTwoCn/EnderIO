package com.enderio.enderio.api.travel;

import com.enderio.enderio.api.UseOnly;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import net.neoforged.fml.LogicalSide;

@UseOnly(LogicalSide.CLIENT)
public interface TravelRenderer<T extends TravelTarget> {
    void render(T travelData, LevelRenderer levelRenderer, PoseStack poseStack, double distanceSquared, boolean active, float partialTick);
}
