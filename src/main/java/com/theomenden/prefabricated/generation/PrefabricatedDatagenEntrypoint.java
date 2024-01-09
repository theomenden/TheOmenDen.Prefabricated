package com.theomenden.prefabricated.generation;

import com.theomenden.prefabricated.generation.providers.PrefabricatedBlockTagProvider;
import com.theomenden.prefabricated.generation.providers.PrefabricatedItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class PrefabricatedDatagenEntrypoint implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        PrefabricatedBlockTagProvider blockTags = pack.addProvider(PrefabricatedBlockTagProvider::new);
        pack.addProvider(((output, registriesFuture) -> new PrefabricatedItemTagProvider(output, registriesFuture, blockTags)));
    }
}
