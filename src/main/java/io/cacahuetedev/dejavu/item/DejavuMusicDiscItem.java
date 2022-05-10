package io.cacahuetedev.dejavu.item;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class DejavuMusicDiscItem extends MusicDiscItem implements IIdentifiable {
    Identifier id;

    protected DejavuMusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
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
    public DejavuMusicDiscItem setIdentifier(Identifier id) {
        this.id = id;

        return this;
    }

    @Override
    public DejavuMusicDiscItem setIdentifier(String id) {
        this.id = new Identifier("dejavu", id);

        return this;
    }
}
