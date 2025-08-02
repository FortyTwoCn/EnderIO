package com.enderio.legacy_layout.base.client.gui.widget;

import com.enderio.legacy_layout.base.client.gui.icon.EIOEnumIcons;
import com.enderio.enderio.common.content.filters.item.general.DamageFilterMode;
import com.enderio.legacy_layout.base.common.lang.EIOEnumLang;
import com.enderio.core.client.gui.widgets.BaseEnumPickerWidget;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

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
