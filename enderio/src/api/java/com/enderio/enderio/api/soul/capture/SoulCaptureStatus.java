package com.enderio.enderio.api.soul.capture;

import com.enderio.enderio.api.EnderIOAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.Nullable;

public enum SoulCaptureStatus implements StringRepresentable {
    DEAD("dead", true),
    CAPTURABLE("capturable", true),
    BOSS("boss", true),
    BLACKLISTED("blacklisted", true),
    INCOMPATIBLE("incompatible", true),
    ;

    private final String name;

    @Nullable
    private final String errorMessageId;

    SoulCaptureStatus(String name, boolean hasErrorMessage) {
        this.name = name;

        if (hasErrorMessage) {
            errorMessageId = EnderIOAPI.MOD_ID + ".message.soul_capture.error." + this.name;
        } else {
            errorMessageId = null;
        }
    }

    @Nullable
    public String errorMessageId() {
        return errorMessageId;
    }

    public Component errorMessage() {
        if (errorMessageId == null) {
            return Component.empty();
        }

        return Component.translatable(errorMessageId);
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
