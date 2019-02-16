package ua.agravaine.rpginventory.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemBaubleArmor extends ItemBauble implements IProtectableBauble{

	public ItemBaubleArmor(String itemName, EnumBaubleType baubleType, String... itemNames){
		super(itemName, baubleType, itemNames);
	}

	public int getProtection(EntityPlayer player, ItemStack itemStack, DamageSource source, double damage){
		return getBaubleProtection(itemStack);
	}

	public int getDisplayProtection(EntityPlayer player, ItemStack itemStack){
		return getBaubleProtection(itemStack);
	}
    
	public int getBaubleProtection(ItemStack itemStack){
		switch(itemStack.getItemDamage()){
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		case 3:
			return 4;
		default:
			return 0;
		}
	}
}