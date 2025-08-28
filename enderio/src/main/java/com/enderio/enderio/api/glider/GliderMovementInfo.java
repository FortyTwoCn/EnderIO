package com.enderio.enderio.api.glider;

import com.enderio.legacy_layout.base.api.integration.Integration;

public record GliderMovementInfo(double acceleration, double maxSpeed, double fallSpeed, Integration cause) {
}
