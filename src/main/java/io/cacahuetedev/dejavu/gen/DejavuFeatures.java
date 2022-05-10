package io.cacahuetedev.dejavu.gen;

import io.cacahuetedev.dejavu.block.DejavuBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Arrays;

public class DejavuFeatures {
    private static ConfiguredFeature<?, ?> RUBIDIUM_ORE_CONFIGURED_FEATURE = new ConfiguredFeature
            (Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    DejavuBlocks.RUBIDIUM_ORE.getBlock().getDefaultState(),
                    6)); // vein size

    public static PlacedFeature RUBIDIUM_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(RUBIDIUM_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(5), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(24))
            )); // height

    private static ConfiguredFeature<?, ?> DEEPSLATE_RUBIDIUM_ORE_CONFIGURED_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    DejavuBlocks.DEEPSLATE_RUBIDIUM_ORE.getBlock().getDefaultState(),
                    6)); // vein size

    public static PlacedFeature DEEPSLATE_RUBIDIUM_ORE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(DEEPSLATE_RUBIDIUM_ORE_CONFIGURED_FEATURE),
            Arrays.asList(
                    CountPlacementModifier.of(5), // number of veins per chunk
                    SquarePlacementModifier.of(), // spreading horizontally
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(24))
            )); // height

    public static final ConfiguredFeature<?, ?> MOMO_ROSE_CONFIGURED_FEATURE = new ConfiguredFeature(
            Feature.FLOWER,
            ConfiguredFeatures.createRandomPatchFeatureConfig(16, PlacedFeatures.createEntry(
                    Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(DejavuBlocks.MOMO_ROSE.getBlock())))));

    public static final PlacedFeature MOMO_ROSE_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(MOMO_ROSE_CONFIGURED_FEATURE),
            Arrays.asList(
                    RarityFilterPlacementModifier.of(2),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BiomePlacementModifier.of()
            ));

    public static void register() {
        // Registering

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier("dejavu", "rubidium_ore"), RUBIDIUM_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("dejavu", "rubidium_ore"),
                RUBIDIUM_ORE_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier("dejavu", "rubidium_ore")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier("dejavu", "deepslate_rubidium_ore"), DEEPSLATE_RUBIDIUM_ORE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("dejavu", "deepslate_rubidium_ore"),
                DEEPSLATE_RUBIDIUM_ORE_PLACED_FEATURE);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier("dejavu", "momo_rose"), MOMO_ROSE_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("dejavu", "momo_rose"),
                MOMO_ROSE_PLACED_FEATURE);

        // Generation

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier("dejavu", "deepslate_rubidium_ore")));

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier("dejavu", "momo_rose")));
    }
}
