package io.cacahuetedev.dejavu.item;

import io.cacahuetedev.dejavu.IIdentifiable;
import io.cacahuetedev.dejavu.entity.CardReaderBlockEntity;
import io.cacahuetedev.dejavu.item.momo.*;
import io.cacahuetedev.dejavu.sound.DejavuSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class DejavuItems {
    public static final IIdentifiable RUBIDIUM = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS))
            .setIdentifier("rubidium");
    public static final IIdentifiable BROWN_KEY = new DejavuItem(new FabricItemSettings().group(ItemGroup.MISC))
            .setIdentifier("brown_key");
    public static final IIdentifiable CYAN_KEY = new DejavuItem(new FabricItemSettings().group(ItemGroup.MISC))
            .setIdentifier("cyan_key");
    public static final IIdentifiable DIAMOND_SHARD = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS))
            .setIdentifier("diamond_shard");
    public static final IIdentifiable RUBIDIUM_HORN = new HornItem(new FabricItemSettings().group(ItemGroup.MATERIALS))
            .setIdentifier("rubidium_horn");
    public static final IIdentifiable MOMO_SHARD = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS))
            .setIdentifier("momo_shard");
    public static final IIdentifiable MOMO_RITUAL_OBJECT = new DejavuItemWithDescription(new TranslatableText("item.dejavu.momo_ritual_object.desc")
            , new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(1)).setIdentifier("momo_ritual_object");
    public static final IIdentifiable MOMO_BIBLE = new MomobibleItem(new FabricItemSettings().maxDamage(24).group(ItemGroup.MISC).rarity(Rarity.RARE))
            .setIdentifier("momo_bible");
    public static final IIdentifiable GROUP_RECORD = new DejavuMusicDiscItem(13, DejavuSounds.RECORD_GROUP, new FabricItemSettings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE))
            .setIdentifier("music_disc_group");
    public static final IIdentifiable MOMO_BIBLE_PAGE = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS))
            .setIdentifier("momo_bible_page");
    public static final IIdentifiable HEALTH_SEAL = new DejavuItemWithDescription(new TranslatableText("item.dejavu.health_seal.desc")
            , new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16)).setIdentifier("health_seal");
    public static final IIdentifiable SLEEP_SEAL = new DejavuItemWithDescription(new TranslatableText("item.dejavu.sleep_seal.desc")
            , new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16)).setIdentifier("sleep_seal");
    public static final IIdentifiable SPECULOSE_SEAL = new DejavuItemWithDescription(new TranslatableText("item.dejavu.speculose_seal.desc")
            , new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16)).setIdentifier("speculose_seal");
    public static final IIdentifiable HEALTH_RITUAL_PASSAGE = new HealthRitualItem(new FabricItemSettings().group(ItemGroup.MATERIALS).maxDamage(3))
            .setIdentifier("health_ritual_passage");
    public static final IIdentifiable SLEEP_RITUAL_PASSAGE = new SleepRitualItem(new FabricItemSettings().group(ItemGroup.MATERIALS).maxDamage(4))
            .setIdentifier("sleep_ritual_passage");
    public static final IIdentifiable SPECULOSE_RITUAL_PASSAGE = new SpeculoseRitualItem(new FabricItemSettings().group(ItemGroup.MATERIALS).maxDamage(5))
            .setIdentifier("speculose_ritual_passage");
    public static final IIdentifiable SPECULOSE = new DejavuItem(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).snack().statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 20), 0.5f).build()))
            .setIdentifier("speculose");
    public static final IIdentifiable KEYCARD_WHITE = new CardItem(CardReaderBlockEntity.Level.WHITE, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))
            .setIdentifier("keycard_white");
    public static final IIdentifiable KEYCARD_RED = new CardItem(CardReaderBlockEntity.Level.RED, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))
            .setIdentifier("keycard_red");
    public static final IIdentifiable KEYCARD_BLACK = new CardItem(CardReaderBlockEntity.Level.BLACK, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))
            .setIdentifier("keycard_black");
    public static final IIdentifiable CARD_PROGRAMMER = new DejavuItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1))
            .setIdentifier("card_programmer");
    public static final IIdentifiable RUBIDIUM_PICKAXE = new DejavuPickaxeItem(DejavuToolMaterials.RUBIDIUM, 1, -3f, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1))
            .setIdentifier("rubidium_pickaxe");
    public static final IIdentifiable RUBIDIUM_SHOVEL = new DejavuShovelItem(DejavuToolMaterials.RUBIDIUM, 1, -3f, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1))
            .setIdentifier("rubidium_shovel");
    public static final IIdentifiable RUBIDIUM_HOE = new DejavuHoeItem(DejavuToolMaterials.RUBIDIUM, -2, -1f, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1))
            .setIdentifier("rubidium_hoe");
    public static final IIdentifiable RUBIDIUM_SWORD = new DejavuSwordItem(DejavuToolMaterials.RUBIDIUM, 3, -3f, new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1))
            .setIdentifier("rubidium_sword");
    public static final IIdentifiable RUBIDIUM_AXE = new DejavuAxeItem(DejavuToolMaterials.RUBIDIUM, 6, -3f, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1))
            .setIdentifier("rubidium_axe");
    public static final IIdentifiable OSCILLATING_CRYSTAL = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(16))
            .setIdentifier("oscillating_crystal");
    public static final IIdentifiable UNDOCUMENTED_BALL = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(1).rarity(Rarity.RARE))
            .setIdentifier("undocumented_ball");
    public static final IIdentifiable MEGACRYSTAL = new DejavuItem(new FabricItemSettings().group(ItemGroup.MATERIALS).rarity(Rarity.RARE))
            .setIdentifier("megacrystal");
    public static final IIdentifiable MEGACRYSTAL_SWORD = new DejavuSwordItem(DejavuToolMaterials.MEGACRYSTAL, 4, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxCount(1))
            .setIdentifier("megacrystal_sword");

    public static IIdentifiable[] all() {
        return new IIdentifiable[] {
                RUBIDIUM,
                BROWN_KEY,
                CYAN_KEY,
                DIAMOND_SHARD,
                RUBIDIUM_HORN,
                MOMO_SHARD,
                MOMO_RITUAL_OBJECT,
                MOMO_BIBLE,
                GROUP_RECORD,
                MOMO_BIBLE_PAGE,
                HEALTH_SEAL,
                SLEEP_SEAL,
                SPECULOSE_SEAL,
                HEALTH_RITUAL_PASSAGE,
                SLEEP_RITUAL_PASSAGE,
                SPECULOSE_RITUAL_PASSAGE,
                SPECULOSE,
                KEYCARD_WHITE,
                KEYCARD_RED,
                KEYCARD_BLACK,
                CARD_PROGRAMMER,
                RUBIDIUM_PICKAXE,
                RUBIDIUM_SHOVEL,
                RUBIDIUM_HOE,
                RUBIDIUM_SWORD,
                RUBIDIUM_AXE,
                OSCILLATING_CRYSTAL,
                UNDOCUMENTED_BALL,
                MEGACRYSTAL,
                MEGACRYSTAL_SWORD
        };
    }

    public static void register() {
        for (IIdentifiable item : all()) {
            Registry.register(Registry.ITEM, item.getIdentifier(), item.getItem());
        }
    }
}
