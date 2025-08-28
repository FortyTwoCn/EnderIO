package com.enderio.legacy_layout.base;

import com.enderio.core.CoreNBTKeys;

/**
 * Common NBT Keys.
 * This helps us keep consistency.
 * Names are purposely generic, but shouldn't conflict.
 * NOTE: If you have a highly specific NBT tag, store the keys in the class.
 * For example LootCapacitorData does this.
 */
public class EIONBTKeys extends CoreNBTKeys {

    // region Capability Serialized Names

    public static final String ENTITY_STORAGE = "EntityStorage";
    public static final String PAINT = "Paint";
    public static final String PAINT_2 = "Paint2";

    // endregion

    // region Misc task

    public static final String ACTIVE = "Active";

    // endregion

    // region Machines

    // TODO: The next two should maybe go back into AlloySmelterBlockEntity.
    public static final String MACHINE_MODE = "Mode";
    public static final String PROCESSED_INPUTS = "ProcessedInputs";

    public static final String REDSTONE_CONTROL = "RedstoneControl";
    public static final String IO_CONFIG = "IOConfig";
    public static final String ACTION_RANGE = "ActionRange";
    public static final String IS_RANGE_VISIBLE = "IsRangeVisible";

    public static final String BURN_TIME = "BurnTime";
    public static final String BURN_DURATION = "BurnDuration";

    public static final String OWNER = "owner";

    // endregion
}
