package io.cacahuetedev.dejavu.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HornItem extends DejavuItem {
    public HornItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(user.getInventory().getMainHandStack().getItem(), 60);
        user.sendMessage(new TranslatableText("ui.dejavu.horn"), true);

        return super.use(world, user, hand);
    }
}
