package com.enderio.enderio.api.dark_steel;

import java.util.Collection;
import java.util.Optional;

public interface IDarkSteelCapability {

    void addUpgrade(IDarkSteelUpgrade upgrade);

    void removeUpgrade(String name);

    boolean hasUpgrade(String upgrade);

    boolean canApplyUpgrade(IDarkSteelUpgrade upgrade);

    Collection<IDarkSteelUpgrade> getUpgrades();

    Optional<IDarkSteelUpgrade> getUpgrade(String upgrade);

    <T extends IDarkSteelUpgrade> Optional<T> getUpgradeAs(String upgradeName, Class<T> as);

    Collection<IDarkSteelUpgrade> getUpgradesApplicable();

}
