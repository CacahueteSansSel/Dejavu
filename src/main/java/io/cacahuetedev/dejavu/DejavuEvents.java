package io.cacahuetedev.dejavu;

import io.cacahuetedev.dejavu.item.DejavuItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class DejavuEvents {
    static final Identifier MINESHAFT_LOOTTABLE_ID = new Identifier("minecraft:chests/abandoned_mineshaft");

    public static void registerEvents() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (MINESHAFT_LOOTTABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(EmptyEntry.Serializer().weight(7))
                        .with(ItemEntry.builder(DejavuItems.MEGACRYSTAL.getItem()).weight(1));

                table.pool(poolBuilder);
            }
        });
    }
}
