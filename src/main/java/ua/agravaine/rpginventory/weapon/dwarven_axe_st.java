package ua.agravaine.rpginventory.weapon;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ua.agravaine.rpginventory.ElterionRPG;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class dwarven_axe_st extends ItemSword {

	public dwarven_axe_st(ToolMaterial p_i45356_1_) {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("dwarven_axe_st");
		setTextureName("elterionrpg:dwarven_axe");
		setCreativeTab(ElterionRPG.RpgTab);
		setMaxStackSize(1);
		setMaxDamage(400);
	
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("Качество:§bобычный");
		lines.add("Тип:одноручный");
    	lines.add("Урон:3-5");
		lines.add("Прочность:400");

    	}

	}

