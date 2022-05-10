package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.entity.CardReaderBlockEntity;
import io.cacahuetedev.dejavu.item.CardItem;
import io.cacahuetedev.dejavu.item.DejavuItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CardReaderBlock extends DejavuBlock implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty POWERED = Properties.POWERED;
    private static final VoxelShape NORTH_SHAPE
            = Block.createCuboidShape(4, 2, 15, 12, 14, 16);
    private static final VoxelShape SOUTH_SHAPE
            = Block.createCuboidShape(4, 2, 0, 12, 14, 1);
    private static final VoxelShape WEST_SHAPE
            = Block.createCuboidShape(15, 2, 4, 16, 14, 12);
    private static final VoxelShape EAST_SHAPE
            = Block.createCuboidShape(0, 2, 4, 1, 14, 12);

    public CardReaderBlock(Settings settings) {
        super(settings);

        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH).with(POWERED, false)));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity entity = world.getBlockEntity(pos);

        if (!(entity instanceof CardReaderBlockEntity cardEntity)) return ActionResult.FAIL;
        ItemStack curItemOfPlayer = player.getInventory().getMainHandStack();

        if (curItemOfPlayer.getItem() instanceof CardItem keycard) {
            if (cardEntity.currentLevel.supports(keycard.getAccessLevel())) {
                this.powerOn(state, world, pos);
                this.playClickSound(player, world, pos, true);
                world.emitGameEvent((Entity)player, GameEvent.BLOCK_PRESS, pos);

                player.sendMessage(new TranslatableText("ui.dejavu.card_reader.granted"), true);
            } else {
                player.sendMessage(new TranslatableText("ui.dejavu.card_reader.denied"), true);
            }
        } else if (curItemOfPlayer.getItem() == DejavuItems.CARD_PROGRAMMER) {
            cardEntity.setLevel(cardEntity.currentLevel.increment());
            player.sendMessage(new TranslatableText("ui.dejavu.card_reader.set_level", cardEntity.currentLevel), true);
        }

        return ActionResult.success(world.isClient);
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, (BlockState)state.with(POWERED, true), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        world.createAndScheduleBlockTick(pos, this, this.getPressTicks());
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, this.getClickSound(powered), SoundCategory.BLOCKS, 0.3f, powered ? 0.6f : 0.5f);
    }

    protected SoundEvent getClickSound(boolean isOn) {
        return isOn ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF : SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
    }

    private int getPressTicks() {
        return 20 * 10; // 10 seconds, 200 ticks
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(POWERED, false);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) != false ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(POWERED).booleanValue()) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(POWERED).booleanValue()) {
            return;
        }

        world.setBlockState(pos, (BlockState)state.with(POWERED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        this.playClickSound(null, world, pos, false);
        world.emitGameEvent(GameEvent.BLOCK_UNPRESS, pos);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Override
    public BlockItem createItem() {
        return new BlockItem(this, new FabricItemSettings().group(ItemGroup.REDSTONE));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CardReaderBlockEntity(pos, state);
    }
}
