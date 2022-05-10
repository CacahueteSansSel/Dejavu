package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class LockedDoor extends DoorBlock implements IIdentifiableBlock {
    Item keyItem;
    Identifier id;

    public LockedDoor(Settings settings, Item keyItem) {
        super(settings);

        this.keyItem = keyItem;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getInventory().getMainHandStack().getItem() != keyItem) {
            player.sendMessage(new TranslatableText("ui.dejavu.doorlocked"), true);

            return ActionResult.PASS;
        }

        state = state.cycle(OPEN);
        world.setBlockState(pos, state, Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
        world.syncWorldEvent(player, state.get(OPEN) ? WorldEvents.IRON_DOOR_OPENS : WorldEvents.IRON_DOOR_CLOSES, pos, 0);
        world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {

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
    public LockedDoor setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    @Override
    public LockedDoor setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }

    @Override
    public BlockItem createItem() {
        return new TallBlockItem(this, new Item.Settings().group(ItemGroup.REDSTONE));
    }
}
