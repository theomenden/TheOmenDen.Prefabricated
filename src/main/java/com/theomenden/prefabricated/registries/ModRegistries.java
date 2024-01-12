package com.theomenden.prefabricated.registries;

import lombok.Getter;

@Getter
public final class ModRegistries {
    private final LightSwitchRegistry lightSwitchRegistry;

    public ModRegistries() {
        this.lightSwitchRegistry = new LightSwitchRegistry();
    }

}
