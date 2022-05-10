package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class DejavuOre extends OreBlock implements IIdentifiableBlock {
    Identifier id;

    public DejavuOre(Settings settings) {
        super(settings);
    }

    public DejavuOre(Settings settings, UniformIntProvider experienceDropped) {
        super(settings, experienceDropped);
    }

    @Override
    public Item getItem() {
        return null;
    }

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public DejavuOre setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    @Override
    public DejavuOre setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }

    @Override
    public BlockItem createItem() {
        return new BlockItem(getBlock(), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    }
}
