package ua.agravaine.rpginventory.weapon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import ua.agravaine.rpginventory.ElterionRPG;

import java.util.List;

public class sword_bastard_lg extends ItemSword {



    public sword_bastard_lg(Item.ToolMaterial p_i45356_1_) {
        super(Item.ToolMaterial.IRON);
        setUnlocalizedName("sword_bastard_lg");
        setTextureName("elterionrpg:sword_bastard");
        setCreativeTab(ElterionRPG.RpgTab);
        setMaxStackSize(1);
        setMaxDamage(470);
        setFull3D();

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
        lines.add("Качество:§dлегендарный");
        lines.add("Тип:двуручный");
        lines.add("Урон:§d4§8-§16");
        lines.add("Прочность:§1470");
        lines.add("");
		lines.add("Доп.прочность:§d70");
		lines.add("Доп.макс :§d1");
		lines.add("Доп.макс урон:§11");
        lines.add("Урон крита:§b+50%");
        lines.add("Доп урон крита:§b15%");
    }



}
