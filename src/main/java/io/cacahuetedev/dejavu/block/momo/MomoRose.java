package io.cacahuetedev.dejavu.block.momo;

import io.cacahuetedev.dejavu.IIdentifiable;
import io.cacahuetedev.dejavu.block.IIdentifiableBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.Random;

public class MomoRose extends FlowerBlock implements IIdentifiableBlock {
    Identifier id;

    public MomoRose(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(floor, world, pos)
                || floor.isOf(Blocks.COBBLESTONE)
                || floor.isOf(Blocks.STONE)
                || floor.isOf(Blocks.GRASS_BLOCK)
                || floor.isOf(Blocks.DIRT);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        VoxelShape voxelShape = this.getOutlineShape(state, world, pos, ShapeContext.absent());
        Vec3d vec3d = voxelShape.getBoundingBox().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;
        for (int i = 0; i < 3; ++i) {
            if (!random.nextBoolean()) continue;
            world.addParticle(ParticleTypes.ELECTRIC_SPARK, d + random.nextDouble() / 5.0, (double)pos.getY() + (0.5 - random.nextDouble()), e + random.nextDouble() / 5.0, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        LivingEntity livingEntity;
        if (world.isClient || world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (entity instanceof LivingEntity && !(livingEntity = (LivingEntity)entity).isInvulnerableTo(DamageSource.WITHER)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 10));
        }
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
    public MomoRose setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    @Override
    public MomoRose setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }

    @Override
    public BlockItem createItem() {
        return new BlockItem(this, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    }
}
