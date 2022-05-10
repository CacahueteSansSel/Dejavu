package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.block.momo.MomoRitualTableBlock;
import io.cacahuetedev.dejavu.block.momo.MomoRose;
import io.cacahuetedev.dejavu.item.DejavuItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class DejavuBlocks {
    public static final IIdentifiableBlock RUBIDIUM_BLOCK = new DejavuBlock(AbstractBlock.Settings.of(Material.METAL).strength(4f))
            .setIdentifier("rubidium_block");
    public static final IIdentifiableBlock RUBIDIUM_ORE = new DejavuOre(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f), UniformIntProvider.create(3, 7))
            .setIdentifier("rubidium_ore");
    public static final IIdentifiableBlock DEEPSLATE_RUBIDIUM_ORE = new DejavuOre(AbstractBlock.Settings.of(Material.STONE).sounds(BlockSoundGroup.DEEPSLATE).requiresTool().strength(3.0f, 3.0f))
            .setIdentifier("deepslate_rubidium_ore");
    public static final IIdentifiableBlock CYAN_DOOR = new LockedDoor(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.0f, 3.0f), DejavuItems.CYAN_KEY.getItem())
            .setIdentifier("cyan_door");
    public static final IIdentifiableBlock BROWN_DOOR = new LockedDoor(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.0f, 3.0f), DejavuItems.BROWN_KEY.getItem())
            .setIdentifier("brown_door");
    public static final IIdentifiableBlock MOMO_ROSE = new MomoRose(StatusEffects.LUCK, 20, AbstractBlock.Settings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().dynamicBounds().noCollision())
            .setIdentifier("momo_rose");
    public static final IIdentifiableBlock MOMO_PILLAR = new DejavuBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(2f))
            .setIdentifier("momo_pillar");
    public static final IIdentifiableBlock MOMO_BRICKS = new DejavuBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(2f))
            .setIdentifier("momo_bricks");
    public static final IIdentifiableBlock MOMO_BRICKS_STOP = new DejavuBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(2f))
            .setIdentifier("momo_bricks_stop");
    public static final IIdentifiableBlock MOMO_RITUAL_TABLE = new MomoRitualTableBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(2f))
            .setIdentifier("momo_ritual_table");
    public static final IIdentifiableBlock CARD_READER = new CardReaderBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(2f))
            .setIdentifier("card_reader");
    public static final IIdentifiableBlock TORCH_PACK = new TorchPackBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakInstantly())
            .setIdentifier("torch_pack");
    public static final IIdentifiableBlock COLORFUL_LAMP = new DejavuBlock(AbstractBlock.Settings.of(Material.GLASS).sounds(BlockSoundGroup.GLASS).strength(2f).luminance((state) -> 15))
            .setIdentifier("colorful_lamp");
    public static final IIdentifiableBlock DEJAVU_TABLE = new DejavuBlock(AbstractBlock.Settings.of(Material.AMETHYST).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(2f).luminance((state) -> 5))
            .setIdentifier("dejavu_table");

    public static IIdentifiableBlock[] all() {
        return new IIdentifiableBlock[] {
                RUBIDIUM_BLOCK,
                RUBIDIUM_ORE,
                DEEPSLATE_RUBIDIUM_ORE,
                CYAN_DOOR,
                BROWN_DOOR,
                MOMO_ROSE,
                MOMO_PILLAR,
                MOMO_BRICKS,
                MOMO_RITUAL_TABLE,
                CARD_READER,
                TORCH_PACK,
                COLORFUL_LAMP,
                MOMO_BRICKS_STOP,
                DEJAVU_TABLE
        };
    }

    public static BlockItem[] allItems() {
        ArrayList<BlockItem> list = new ArrayList<>();

        IIdentifiableBlock[] blocks = all();
        for (IIdentifiableBlock block : blocks) {
            list.add(block.createItem());
        }

        BlockItem[] array = new BlockItem[list.size()];
        list.toArray(array);

        return array;
    }

    public static void register() {
        for (IIdentifiableBlock block : all()) {
            Registry.register(Registry.BLOCK, block.getIdentifier(), block.getBlock());
            Registry.register(Registry.ITEM, block.getIdentifier(), block.createItem());
        }

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BROWN_DOOR.getBlock());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), MOMO_ROSE.getBlock());
    }
}
