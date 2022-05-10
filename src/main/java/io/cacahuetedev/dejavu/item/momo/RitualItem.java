package io.cacahuetedev.dejavu.item.momo;

import io.cacahuetedev.dejavu.item.DejavuItem;
import io.cacahuetedev.dejavu.item.DejavuItemWithDescription;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RitualItem extends DejavuItemWithDescription {
    Text ritualDesc;

    public RitualItem(Text ritualDescription, Settings settings) {
        super(new TranslatableText("item.dejavu.ritual.desc"), settings.rarity(Rarity.RARE));

        ritualDesc = ritualDescription;
    }

    public List<String> getRequirements() {
        return new ArrayList<>();
    }

    public boolean isMeetingRequirements(PlayerEntity player, BlockPos pos, World world) {
        return true;
    }

    public boolean activateRitual(PlayerEntity player, BlockPos pos, World world) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.addAll(ritualDesc.getWithStyle(Style.EMPTY.withItalic(true)));
        tooltip.add(new TranslatableText("item.dejavu.rituals.requirements"));
        List<String> reqs = getRequirements();

        if (reqs.size() == 0) {
            tooltip.add(new TranslatableText("item.dejavu.rituals.requirements.none"));
        } else {
            for (String req : reqs) {
                tooltip.add(new LiteralText(req).fillStyle(Style.EMPTY.withItalic(true)));
            }
        }
    }
}
