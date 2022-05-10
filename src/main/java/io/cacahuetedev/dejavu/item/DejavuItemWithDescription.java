package io.cacahuetedev.dejavu.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DejavuItemWithDescription extends DejavuItem {
    Text descriptionText;

    public DejavuItemWithDescription(Text desc, Settings settings) {
        super(settings);

        descriptionText = desc;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.addAll(descriptionText.getWithStyle(Style.EMPTY.withFormatting(Formatting.ITALIC)));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
