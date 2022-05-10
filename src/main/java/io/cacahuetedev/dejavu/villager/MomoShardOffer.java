package io.cacahuetedev.dejavu.villager;

import io.cacahuetedev.dejavu.item.DejavuItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.Random;

class MomoShardOffer implements TradeOffers.Factory {
    private final Item offer;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public MomoShardOffer(ItemConvertible item, int price, int maxUses, int experience) {
        this.offer = item.asItem();
        this.price = price;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    public TradeOffer create(Entity entity, Random random) {
        ItemStack itemStack = new ItemStack(this.offer, 1);
        return new TradeOffer(new ItemStack(DejavuItems.MOMO_SHARD.getItem(), this.price), itemStack, this.maxUses, this.experience, this.multiplier);
    }
}
