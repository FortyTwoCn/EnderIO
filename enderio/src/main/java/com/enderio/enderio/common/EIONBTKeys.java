package com.enderio.enderio.common;

/**
 * Common NBT Keys.
 * This helps us keep consistency.
 * Names are purposely generic, but shouldn't conflict.
 * NOTE: If you have a highly specific NBT tag, store the keys in the class.
 * For example LootCapacitorData does this.
 */
public class EIONBTKeys {

    // region Standard Keys

    public static final String BLOCK_POS = "BlockPos";
    public static final String ITEM = "Item";
    public static final String ITEMS = "Items";
    public static final String FLUID = "Fluid";
    public static final String FLUIDS = "Fluids";
    public static final String TANKS = "Tanks";
    public static final String ENERGY = "Energy";

    // endregion

    // region Energy Storage

    public static final String ENERGY_STORED = "EnergyStored";
    public static final String ENERGY_MAX_STORED = "MaxEnergyStored";

    // endregion

    // region Capability Serialized Names

    public static final String ENTITY_STORAGE = "EntityStorage";
    public static final String PAINT = "Paint";
    public static final String PAINT_2 = "Paint2";

    // endregion

    // region Misc task

    public static final String ACTIVE = "Active";

    // endregion
}
