package io.cacahuetedev.dejavu.item.momo;

import io.cacahuetedev.dejavu.item.DejavuItem;
import io.cacahuetedev.dejavu.item.DejavuItemWithDescription;
import io.cacahuetedev.dejavu.item.DejavuItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MomobibleItem extends DejavuItemWithDescription {
    public MomobibleItem(Settings settings) {
        super(new TranslatableText("item.dejavu.momo_bible.desc"), settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getInventory().insertStack(new ItemStack(DejavuItems.MOMO_BIBLE_PAGE.getItem(), 1));
        user.getItemCooldownManager().set(this, 1200); // 1 minute counter
        world.playSound(user, user.getBlockPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1, 1);

        if (user instanceof ServerPlayerEntity) {
            user.getInventory().getMainHandStack().damage(1, user, p -> p.sendToolBreakStatus(hand));
        }

        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
