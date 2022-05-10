package io.cacahuetedev.dejavu.block;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.item.BlockItem;

public interface IIdentifiableBlock extends IIdentifiable {
    BlockItem createItem();
}
