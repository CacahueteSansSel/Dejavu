package io.cacahuetedev.dejavu.villager;

import com.google.common.collect.ImmutableMap;
import io.cacahuetedev.dejavu.block.DejavuBlocks;
import io.cacahuetedev.dejavu.item.DejavuItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class DejavuVillagerProfessions {

    public static final PointOfInterestType MOMOTHEIST_POI = PointOfInterestHelper.register(
            new Identifier("dejavu:momotheist_pointofinterest"),
            1,
            4,
            DejavuBlocks.MOMO_RITUAL_TABLE.getBlock());

    public static final VillagerProfession MOMOTHEIST_PROFESSION = VillagerProfessionBuilder.create()
            .id(new Identifier("dejavu:momotheist"))
            .workstation(MOMOTHEIST_POI)
            .workSound(SoundEvents.BLOCK_BEACON_POWER_SELECT)
            .build();


    public static void register() {
        // register the village profession
        Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("dejavu:momotheist"), MOMOTHEIST_PROFESSION);

        // register the momotheist trades
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(MOMOTHEIST_PROFESSION, copyToFastUtilMap(ImmutableMap.of(
                1,
                new TradeOffers.Factory[] {
                        new MomoShardOffer(DejavuItems.HEALTH_SEAL.getItem(), 20, 16, 1),
                        new MomoShardOffer(DejavuItems.SLEEP_SEAL.getItem(), 17, 13, 2),
                        new MomoShardOffer(DejavuItems.SPECULOSE.getItem(), 5, 24, 1)
                },
                2,
                new TradeOffers.Factory[] {
                        new MomoShardOffer(DejavuItems.GROUP_RECORD.getItem(), 35, 2, 5),
                },
                3,
                new TradeOffers.Factory[] {
                        new MomoShardOffer(DejavuItems.MOMO_BIBLE.getItem(), 64, 2, 10),
                        new MomoShardOffer(DejavuItems.BROWN_KEY.getItem(), 16, 1, 1)
                }
        )));
    }

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap<TradeOffers.Factory[]>(map);
    }
}
