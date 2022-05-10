package io.cacahuetedev.dejavu.block.momo;

import io.cacahuetedev.dejavu.block.DejavuBlock;
import io.cacahuetedev.dejavu.item.DejavuItems;
import io.cacahuetedev.dejavu.item.momo.RitualItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MomoRitualTableBlock extends DejavuBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(0, 0, 0, 16, 7, 16);
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            BASE_SHAPE, Block.createCuboidShape(0, 7, 9, 16, 14, 16));
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            BASE_SHAPE, Block.createCuboidShape(0, 7, 0, 16, 14, 7));
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            BASE_SHAPE, Block.createCuboidShape(9, 7, 0, 16, 14, 16));
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            BASE_SHAPE, Block.createCuboidShape(0, 7, 0, 7, 14, 16));

    public MomoRitualTableBlock(Settings settings) {
        super(settings);

        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getInventory().getMainHandStack().getItem() instanceof RitualItem ritual) {
            if (!player.getInventory().contains(new ItemStack(DejavuItems.MOMO_RITUAL_OBJECT.getItem(), 1))) {
                player.sendMessage(new TranslatableText("block.dejavu.momo_ritual_table.missing_obj"), true);
                world.playSound(player, pos, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1, 0.5f);
                return ActionResult.PASS;
            }

            if (!ritual.isMeetingRequirements(player, pos, world)) {
                player.sendMessage(new TranslatableText("block.dejavu.momo_ritual_table.missing_req"), true);
                world.playSound(player, pos, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1, 0.5f);
                return ActionResult.PASS;
            }

            if (ritual.activateRitual(player, pos, world)) {
                player.sendMessage(new TranslatableText("block.dejavu.momo_ritual_table.success"), true);

                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 50));

                if (player instanceof ServerPlayerEntity) {
                    player.getInventory().getMainHandStack().damage(1, player, p -> p.sendToolBreakStatus(hand));
                }

                if (world.random.nextFloat() > 0.5f) {
                    int ritualObjectSlot = player.getInventory().getSlotWithStack(new ItemStack(DejavuItems.MOMO_RITUAL_OBJECT.getItem(), 1));
                    player.getInventory().removeStack(ritualObjectSlot, 1);

                    world.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1, 1f);
                }

                world.playSound(player, pos, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1, 1f);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(new TranslatableText("block.dejavu.momo_ritual_table.failed"), true);
                world.playSound(player, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.BLOCKS, 1, 1f);
                return ActionResult.PASS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            case EAST -> {
                return EAST_SHAPE;
            }
            default -> {
                return NORTH_SHAPE;
            }
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockItem createItem() {
        return new BlockItem(this, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    }
}
