package com.enderio.enderio.common.registration.legacy_regilite.conduits;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.common.content.conduits.crafting.ConduitIngredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ConduitIngredientTypes {
    private static final DeferredRegister<IngredientType<?>> INGREDIENT_TYPES = DeferredRegister
            .create(NeoForgeRegistries.Keys.INGREDIENT_TYPES, EnderIOAPI.NAMESPACE);

    public static final DeferredHolder<IngredientType<?>, IngredientType<ConduitIngredient>> CONDUIT_INGREDIENT_TYPE = INGREDIENT_TYPES
            .register("conduit", () -> new IngredientType<>(ConduitIngredient.CODEC));

    public static void register(IEventBus bus) {
        INGREDIENT_TYPES.register(bus);
    }
}
