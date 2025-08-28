package com.enderio.enderio.common.compat.jei;

import com.enderio.enderio.api.EnderIOAPI;
import com.enderio.enderio.common.compat.jei.category.AlloySmeltingCategory;
import com.enderio.enderio.common.compat.jei.category.EnchanterCategory;
import com.enderio.enderio.common.compat.jei.category.SagMillCategory;
import com.enderio.enderio.common.compat.jei.category.SlicingRecipeCategory;
import com.enderio.enderio.common.compat.jei.category.SoulBindingCategory;
import com.enderio.enderio.common.compat.jei.category.SoulEngineCategory;
import com.enderio.enderio.common.compat.jei.category.TankCategory;
import com.enderio.enderio.common.compat.jei.category.VATCategory;
import com.enderio.enderio.common.compat.jei.category.WeatherChangeCategory;
import com.enderio.enderio.common.compat.jei.transfer.CrafterRecipeTransferHandler;
import com.enderio.enderio.common.compat.jei.transfer.FluidTankTransferHelper;
import com.enderio.enderio.common.compat.jei.transfer.VATTransferHelper;
import com.enderio.enderio.common.compat.jei.transfer.WeatherObeliskTransferHelper;
import com.enderio.enderio.common.content.utility.glass.GlassBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.base.EIOItems;
import com.enderio.enderio.common.compat.jei.category.FireCraftingCategory;
import com.enderio.enderio.common.compat.jei.extension.ShapedEntityStorageCategoryExtension;
import com.enderio.enderio.common.compat.jei.subtype.SoulBindableSubtypeInterpreter;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineBlocks;
import com.enderio.enderio.common.registration.legacy_regilite.machines.MachineMenus;
import com.enderio.legacy_layout.base.common.item.misc.BrokenSpawnerItem;
import com.enderio.legacy_layout.base.common.recipe.ShapedEntityStorageRecipe;

import java.util.ArrayList;
import java.util.List;

import com.enderio.core.client.gui.screen.EnderContainerScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.AlloySmelterScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.EnchanterScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.FluidTankScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.SagMillScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.SlicerScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.SoulBinderScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.VatScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.WeatherObeliskScreen;
import com.enderio.legacy_layout.machines.client.gui.screen.base.MachineScreen;
import com.enderio.enderio.common.content.machines.blocks.alloy.AlloySmelterMenu;
import com.enderio.enderio.common.content.machines.blocks.enchanter.EnchanterMenu;
import com.enderio.enderio.common.content.machines.blocks.sag_mill.SagMillMenu;
import com.enderio.enderio.common.content.machines.blocks.slicer.SlicerMenu;
import com.enderio.enderio.common.content.machines.blocks.soul_binder.SoulBinderMenu;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

@JeiPlugin
public class EnderIOJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return EnderIOAPI.loc("jei");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.ALLOY_SMELTER.get()), AlloySmeltingCategory.TYPE,
            RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.ENCHANTER.get()), EnchanterCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.SAG_MILL.get()), SagMillCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.SLICE_AND_SPLICE.get()), SlicingRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.SOUL_BINDER.get()), SoulBindingCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.FLUID_TANK.get()), TankCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.PRESSURIZED_FLUID_TANK.get()), TankCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.SOUL_ENGINE.get()), SoulEngineCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.VAT.get()), VATCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(MachineBlocks.WEATHER_OBELISK.get()), WeatherChangeCategory.TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new FireCraftingCategory(guiHelper));
        registration.addRecipeCategories(new AlloySmeltingCategory(guiHelper));
        registration.addRecipeCategories(new EnchanterCategory(guiHelper));
        registration.addRecipeCategories(new SagMillCategory(guiHelper));
        registration.addRecipeCategories(new SlicingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new SoulBindingCategory(guiHelper));
        registration.addRecipeCategories(new TankCategory(guiHelper));
        registration.addRecipeCategories(new SoulEngineCategory(guiHelper));
        registration.addRecipeCategories(new VATCategory(guiHelper));
        registration.addRecipeCategories(new WeatherChangeCategory(guiHelper));
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getCraftingCategory()
                .addExtension(ShapedEntityStorageRecipe.class, new ShapedEntityStorageCategoryExtension());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        EnderIOJEIRecipes recipes = new EnderIOJEIRecipes();
        registration.addRecipes(FireCraftingCategory.TYPE, recipes.getAllFireCraftingRecipes());

        List<ItemStack> spawners = BrokenSpawnerItem.getPossibleStacks();
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, spawners);

        List<ItemStack> glasses = new ArrayList<>();
        for (GlassBlocks glass : EIOBlocks.GLASS_BLOCKS.values()) {
            for (var color : glass.COLORS.values()) {
                glasses.add(new ItemStack(color.asItem()));
            }
        }
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, glasses);

        registration.addRecipes(AlloySmeltingCategory.TYPE, recipes.getAlloySmeltingRecipes());
        registration.addRecipes(EnchanterCategory.TYPE, recipes.getEnchanterRecipes());
        registration.addRecipes(SagMillCategory.TYPE, recipes.getSagMillingRecipes());
        registration.addRecipes(SlicingRecipeCategory.TYPE, recipes.getSlicingRecipes());
        registration.addRecipes(SoulBindingCategory.TYPE, recipes.getSoulBindingRecipes());
        registration.addRecipes(TankCategory.TYPE, recipes.getTankRecipes());
        registration.addRecipes(SoulEngineCategory.TYPE, recipes.getMobGeneratorRecipes());
        registration.addRecipes(VATCategory.TYPE, recipes.getVATRecipes());
        registration.addRecipes(WeatherChangeCategory.TYPE, recipes.getWeatherRecipes());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AlloySmelterMenu.class, MachineMenus.ALLOY_SMELTER.get(),
            AlloySmeltingCategory.TYPE, AlloySmelterMenu.INPUTS_INDEX, AlloySmelterMenu.INPUT_COUNT,
            AlloySmelterMenu.LAST_INDEX + 1, 36);

        registration.addRecipeTransferHandler(EnchanterMenu.class, MachineMenus.ENCHANTER.get(), EnchanterCategory.TYPE,
            EnchanterMenu.INPUTS_INDEX, EnchanterMenu.INPUT_COUNT, EnchanterMenu.LAST_INDEX + 1, 36);

        registration.addRecipeTransferHandler(SagMillMenu.class, MachineMenus.SAG_MILL.get(), SagMillCategory.TYPE,
            SagMillMenu.INPUTS_INDEX, SagMillMenu.INPUT_COUNT, SagMillMenu.LAST_INDEX + 1, 36);

        registration.addRecipeTransferHandler(SlicerMenu.class, MachineMenus.SLICE_N_SPLICE.get(),
            SlicingRecipeCategory.TYPE, SlicerMenu.INPUTS_INDEX, SlicerMenu.INPUT_COUNT, SlicerMenu.LAST_INDEX + 1,
            36);

        registration.addRecipeTransferHandler(SoulBinderMenu.class, MachineMenus.SOUL_BINDER.get(),
            SoulBindingCategory.TYPE, SoulBinderMenu.INPUTS_INDEX, SoulBinderMenu.INPUT_COUNT,
            SoulBinderMenu.LAST_INDEX + 1, 36);

        registration.addRecipeTransferHandler(new VATTransferHelper(registration.getTransferHelper()), VATCategory.TYPE);

        registration.addRecipeTransferHandler(new CrafterRecipeTransferHandler(registration.getTransferHelper()),
            RecipeTypes.CRAFTING);

        registration.addRecipeTransferHandler(new WeatherObeliskTransferHelper(registration.getTransferHelper()), WeatherChangeCategory.TYPE);

        registration.addRecipeTransferHandler(new FluidTankTransferHelper(registration.getTransferHelper()), TankCategory.TYPE);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(EIOItems.SOUL_VIAL.get(), new SoulBindableSubtypeInterpreter());
        registration.registerSubtypeInterpreter(EIOItems.BROKEN_SPAWNER.get(), new SoulBindableSubtypeInterpreter());
        registration.registerSubtypeInterpreter(MachineBlocks.POWERED_SPAWNER.asItem(), new SoulBindableSubtypeInterpreter());
        registration.registerSubtypeInterpreter(MachineBlocks.SOUL_ENGINE.asItem(), new SoulBindableSubtypeInterpreter());

        for (var solarPanel : MachineBlocks.SOLAR_PANELS.values()) {
            registration.registerSubtypeInterpreter(solarPanel.asItem(), new SoulBindableSubtypeInterpreter());
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlloySmelterScreen.class, 56, 56, 14, 14, AlloySmeltingCategory.TYPE);
        registration.addRecipeClickArea(AlloySmelterScreen.class, 104, 56, 14, 14, AlloySmeltingCategory.TYPE);

        // TODO: Where to put Crafter recipe area

        registration.addRecipeClickArea(EnchanterScreen.class, 111, 35, 24, 17, EnchanterCategory.TYPE);

        registration.addRecipeClickArea(FluidTankScreen.class, 62, 24, 15, 10, TankCategory.TYPE);
        registration.addRecipeClickArea(FluidTankScreen.class, 47, 40, 10, 9, TankCategory.TYPE);
        registration.addRecipeClickArea(FluidTankScreen.class, 98, 24, 15, 10, TankCategory.TYPE);
        registration.addRecipeClickArea(FluidTankScreen.class, 119, 40, 10, 9, TankCategory.TYPE);

        // TODO: Painting machine needs a viewer

        registration.addRecipeClickArea(SagMillScreen.class, 80, 47, 16, 24, SagMillCategory.TYPE);
        registration.addRecipeClickArea(SlicerScreen.class, 98, 61, 24, 16, SlicingRecipeCategory.TYPE);
        registration.addRecipeClickArea(SoulBinderScreen.class, 80, 34, 24, 17, SoulBindingCategory.TYPE);
        registration.addRecipeClickArea(VatScreen.class, 75, 33, 28, 30, VATCategory.TYPE);
        registration.addRecipeClickArea(WeatherObeliskScreen.class, 80, 27, 14, 34, WeatherChangeCategory.TYPE);

        registration.addGhostIngredientHandler(MachineScreen.class, new MachinesGhostSlotHandler());
        registration.addGhostIngredientHandler(EnderContainerScreen.class, new FilterGhostIngredientHandler());
    }

    // region Utilities

    public static ItemStack getResultItem(Recipe<?> recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registryAccess = level.registryAccess();
        return recipe.getResultItem(registryAccess);
    }

    // endregion
}
