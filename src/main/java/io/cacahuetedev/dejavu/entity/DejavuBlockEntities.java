package io.cacahuetedev.dejavu.entity;

import io.cacahuetedev.dejavu.block.CardReaderBlock;
import io.cacahuetedev.dejavu.block.DejavuBlock;
import io.cacahuetedev.dejavu.block.DejavuBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class DejavuBlockEntities {
    public static BlockEntityType<CardReaderBlockEntity> CARD_READER_ENTITY;

    public static void register() {
        CARD_READER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "dejavu:card_reader_entity", FabricBlockEntityTypeBuilder.create(CardReaderBlockEntity::new, DejavuBlocks.CARD_READER.getBlock()).build());
    }
}
