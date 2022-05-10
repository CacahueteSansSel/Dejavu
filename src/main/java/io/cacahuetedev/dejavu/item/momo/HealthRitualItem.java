package io.cacahuetedev.dejavu.item.momo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HealthRitualItem extends RitualItem {
    public HealthRitualItem(Settings settings) {
        super(new TranslatableText("item.dejavu.health_ritual_passage.desc"), settings);
    }

    @Override
    public boolean activateRitual(PlayerEntity player, BlockPos pos, World world) {
        if (player.isCreative()) return false;

        player.heal(10f);
        return true;
    }
}
