package io.cacahuetedev.dejavu.item;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class DejavuItem extends Item implements IIdentifiable {
    Identifier id;

    public DejavuItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public Block getBlock() {
        return null;
    }

    public Identifier getIdentifier() {
        return id;
    }

    public DejavuItem setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    public DejavuItem setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }
}
