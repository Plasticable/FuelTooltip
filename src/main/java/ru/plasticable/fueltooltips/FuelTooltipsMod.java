package ru.plasticable.fueltooltips;

import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FuelTooltipsMod.MODID)
public class FuelTooltipsMod {
    public static final String MODID = "fuel_tooltips";

    public FuelTooltipsMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTooltipRender(ItemTooltipEvent event) {
        final int burnTime = FurnaceTileEntity.getBurnTimes().getOrDefault(event.getItemStack().getItem(), 0);

        if (burnTime < 1) {
            return;
        }

        final List<ITextComponent> lines = event.getToolTip();

        final TranslationTextComponent textHeader = new TranslationTextComponent("gui." + MODID + ".header");
        textHeader.getStyle().setColor(TextFormatting.GRAY);

        final TranslationTextComponent textBurningTime = new TranslationTextComponent(
                "gui." + MODID + ".burning_time",
                burnTime / 20f,
                burnTime
        );
        textBurningTime.getStyle().setColor(TextFormatting.GOLD);

        final TranslationTextComponent textNumberOfOperations = new TranslationTextComponent(
                "gui." + MODID + ".number_of_operations",
                burnTime / 200f
        );
        textNumberOfOperations.getStyle().setColor(TextFormatting.GOLD);

        lines.add(new StringTextComponent(""));
        lines.add(textHeader);
        lines.add(textBurningTime);
        lines.add(textNumberOfOperations);
    }
}
