package com.enderio.legacy_layout.base.common.recipe;

import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;

public interface FluidRecipeInput extends RecipeInput {
    FluidStack getFluid(int slotIndex);
}
