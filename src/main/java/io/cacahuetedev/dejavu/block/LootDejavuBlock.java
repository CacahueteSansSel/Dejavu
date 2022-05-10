package io.cacahuetedev.dejavu.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

import java.util.ArrayList;
import java.util.List;

public class LootDejavuBlock extends DejavuBlock {
    ArrayList<ItemStack> items = new ArrayList<>();

    public LootDejavuBlock(Settings settings) {
        super(settings);
    }

    public LootDejavuBlock addLoot(Item item, int count) {
        items.add(new ItemStack(item, count));

        return this;
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return items;
    }
}
