package io.cacahuetedev.dejavu;

import io.cacahuetedev.dejavu.block.DejavuBlocks;
import io.cacahuetedev.dejavu.entity.DejavuBlockEntities;
import io.cacahuetedev.dejavu.gen.DejavuFeatures;
import io.cacahuetedev.dejavu.item.DejavuItems;
import io.cacahuetedev.dejavu.sound.DejavuSounds;
import io.cacahuetedev.dejavu.villager.DejavuVillagerProfessions;
import net.fabricmc.api.ModInitializer;

public class Dejavu implements ModInitializer {
    @Override
    public void onInitialize() {
        DejavuSounds.register();
        DejavuItems.register();
        DejavuBlocks.register();
        DejavuBlockEntities.register();
        DejavuVillagerProfessions.register();

        DejavuEvents.registerEvents();

        DejavuFeatures.register();
    }
}
