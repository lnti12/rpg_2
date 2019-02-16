package ua.agravaine.rpginventory.weapon;

import java.util.List;

import ua.agravaine.rpginventory.ElterionRPG;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class dwarven_axe_lg extends ItemSword {

	public dwarven_axe_lg(ToolMaterial p_i45356_1_) {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("dwarven_axe_lg");
		setTextureName("elterionrpg:dwarven_axe");
		setCreativeTab(ElterionRPG.RpgTab);
		setMaxStackSize(1);
		setMaxDamage(470);
	
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("Качество:§dлегендарный");
		lines.add("Тип:одноручный");
    	lines.add("Урон:§d4§8-§16");
		lines.add("Прочность:§d470");
		lines.add("");
		lines.add("Доп.прочность:§d70");
		lines.add("Доп.макс :§d1");
		lines.add("Доп.макс урон:§11");
		
		

    	}

	}
