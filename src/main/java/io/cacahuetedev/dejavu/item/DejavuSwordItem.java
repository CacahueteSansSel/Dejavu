package io.cacahuetedev.dejavu.item;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

public class DejavuSwordItem extends SwordItem implements IIdentifiable {
    Identifier id;

    protected DejavuSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public Block getBlock() {
        return null;
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public IIdentifiable setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    @Override
    public IIdentifiable setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }
}
