package io.cacahuetedev.dejavu.item.momo;

import io.cacahuetedev.dejavu.item.DejavuItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class SpeculoseRitualItem extends RitualItem {
    public SpeculoseRitualItem(Settings settings) {
        super(new TranslatableText("item.dejavu.speculose_ritual_passage.desc"), settings);
    }

    @Override
    public boolean activateRitual(PlayerEntity player, BlockPos pos, World world) {
        if (!(player instanceof ServerPlayerEntity)) return true;

        player.getInventory().insertStack(new ItemStack(DejavuItems.SPECULOSE.getItem(), world.random.nextInt(1, 5)));
        return true;
    }
}
