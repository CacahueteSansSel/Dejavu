package io.cacahuetedev.dejavu.item.momo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class SleepRitualItem extends RitualItem {
    public SleepRitualItem(Settings settings) {
        super(new TranslatableText("item.dejavu.sleep_ritual_passage.desc"), settings);
    }

    @Override
    public boolean activateRitual(PlayerEntity player, BlockPos pos, World world) {
        if (player.isCreative()) return false;
        if (!world.getGameRules().getBoolean(GameRules.DO_INSOMNIA)) return false;

        if (!(player instanceof ServerPlayerEntity)) return true;

        // Reset the phantom counter thing
        ServerStatHandler serverStatHandler = ((ServerPlayerEntity)player).getStatHandler();
        serverStatHandler.setStat(player, Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST), 0);
        return true;
    }
}
