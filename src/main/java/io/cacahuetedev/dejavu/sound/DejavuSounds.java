package io.cacahuetedev.dejavu.sound;

import io.cacahuetedev.dejavu.IIdentifiable;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DejavuSounds {
    public static final SoundEvent RECORD_GROUP = new SoundEvent(new Identifier("dejavu:record.group"));

    public static SoundEvent[] all() {
        return new SoundEvent[] {
            RECORD_GROUP
        };
    }

    public static void register() {
        for (SoundEvent sound : all()) {
            Registry.register(Registry.SOUND_EVENT, sound.getId(), sound);
        }
    }
}
