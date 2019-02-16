package ua.agravaine.rpginventory.weapon;

import java.util.List;

import org.lwjgl.opengl.GL11;

import ua.agravaine.rpginventory.ElterionRPG;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class sword_bastard_st extends ItemSword  {


	
	public sword_bastard_st(ToolMaterial p_i45356_1_) {
		super(ToolMaterial.IRON);
		setUnlocalizedName("sword_bastard_st");
		setTextureName("elterionrpg:sword_bastard");
		setCreativeTab(ElterionRPG.RpgTab);
		setMaxStackSize(1);
		setMaxDamage(400);
		
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
		lines.add("Качество:§bобычный");
		lines.add("Тип:двуручный");
    	lines.add("Урон:3-5");
		lines.add("Прочность:400");
		lines.add("");
		lines.add("Критический урон:§b+50%");
		lines.add("Шанс критического урона:§b15%");
    	}	
	    
	  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack){
		 
		  return true;
	  }
	
	 }
	        
