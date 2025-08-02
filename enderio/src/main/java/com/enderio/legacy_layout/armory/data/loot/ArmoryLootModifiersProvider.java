package com.enderio.legacy_layout.armory.data.loot;

import com.enderio.legacy_layout.armory.common.item.darksteel.upgrades.direct.DirectUpgradeLootCondition;
import com.enderio.legacy_layout.armory.common.item.darksteel.upgrades.direct.DirectUpgradeLootModifier;
import com.enderio.enderio.api.EnderIOAPI;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public class ArmoryLootModifiersProvider extends GlobalLootModifierProvider {
    public ArmoryLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, EnderIOAPI.NAMESPACE);
    }

    @Override
    protected void start() {
        add("direct_upgrade",
                new DirectUpgradeLootModifier(new LootItemCondition[] { new DirectUpgradeLootCondition() }));
    }
}
