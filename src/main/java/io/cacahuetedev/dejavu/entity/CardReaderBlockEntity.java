package io.cacahuetedev.dejavu.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class CardReaderBlockEntity extends BlockEntity {
    public Level currentLevel = Level.WHITE;

    public CardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(DejavuBlockEntities.CARD_READER_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putByte("level", currentLevel.value);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        currentLevel = Level.fromByte(nbt.getByte("level"));
    }

    public void setLevel(Level level) {
        currentLevel = level;
        markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public enum Level {
        WHITE(0),
        RED(1),
        BLACK(2);

        byte value;

        Level(int level) {
            this.value = (byte)level;
        }

        public boolean supports(Level level) {
            return level.value >= this.value;
        }

        public Level increment() {
            if (this == BLACK) return WHITE;

            return fromByte((byte)(value+1));
        }

        public static Level fromByte(byte v) {
            switch (v) {
                case 0:
                    return WHITE;
                case 1:
                    return RED;
                case 2:
                    return BLACK;
            }

            return WHITE;
        }
    }
}
