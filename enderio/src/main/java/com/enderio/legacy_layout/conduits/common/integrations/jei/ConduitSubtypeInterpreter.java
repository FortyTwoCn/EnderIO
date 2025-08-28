package com.enderio.legacy_layout.conduits.common.integrations.jei;

import com.enderio.enderio.api.conduits.Conduit;
import com.enderio.enderio.common.registration.legacy_regilite.conduits.ConduitComponents;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;

public class ConduitSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
    @Override
    public String apply(ItemStack ingredient, UidContext context) {
        Holder<Conduit<?, ?>> conduit = ingredient.get(ConduitComponents.CONDUIT);
        if (conduit != null) {
            return conduit.getRegisteredName();
        }

        return IIngredientSubtypeInterpreter.NONE;
    }
}
