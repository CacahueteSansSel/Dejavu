package io.cacahuetedev.dejavu.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class TorchPackBlock extends DejavuBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 10, 15);

    public TorchPackBlock(Settings settings) {
        super(settings.luminance((state) -> 15));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockItem createItem() {
        return new BlockItem(this, new Item.Settings().group(ItemGroup.DECORATIONS));
    }
}
