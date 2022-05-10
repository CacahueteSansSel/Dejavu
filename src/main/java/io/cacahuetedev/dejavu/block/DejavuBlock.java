package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;

import java.util.List;

public class DejavuBlock extends Block implements IIdentifiableBlock {
    Identifier id;

    public DejavuBlock(Block.Settings settings) {
        super(settings);
    }

    @Override
    public Item getItem() {
        return null;
    }

    @Override
    public Block getBlock() {
        return this;
    }

    public Identifier getIdentifier() {
        return id;
    }

    public DejavuBlock setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    public DejavuBlock setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }

    public BlockItem createItem() {
        return new BlockItem(this, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    }
}
