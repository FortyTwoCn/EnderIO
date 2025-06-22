package com.enderio.enderio.client.legacy_to_move.gui.widget;

import com.enderio.core.client.gui.widgets.BaseEnumPickerWidget;
import com.enderio.enderio.client.icon.EIOEnumIcons;
import com.enderio.enderio.common.features.filters.item.general.DamageFilterMode;
import com.enderio.enderio.common.registration.legacy_modules.base.EIOEnumLang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DamageFilterModePickerWidget extends BaseEnumPickerWidget<DamageFilterMode> {

    public DamageFilterModePickerWidget(int pX, int pY, Supplier<DamageFilterMode> getter,
            Consumer<DamageFilterMode> setter, Component optionName) {
        super(pX, pY, 16, 16, DamageFilterMode.class, getter, setter, false, optionName);
    }

    @Override
    @Nullable
    public Component getValueTooltip(DamageFilterMode value) {
        return EIOEnumLang.DAMAGE_FILTER_MODE.get(value);
    }

    @Override
    public ResourceLocation getValueIcon(DamageFilterMode value) {
        return EIOEnumIcons.DAMAGE_FILTER_MODE.get(value);
    }
}
