package io.cacahuetedev.dejavu;

import io.cacahuetedev.dejavu.item.DejavuItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public interface IIdentifiable {
    Item getItem();
    Block getBlock();

    Identifier getIdentifier();

    IIdentifiable setIdentifier(Identifier id);

    IIdentifiable setIdentifier(String id);
}
