package ua.agravaine.rpginventory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface IProtectableBauble extends IBauble{

	public int getProtection(EntityPlayer player, ItemStack itemStack, DamageSource source, double damage);

	public int getDisplayProtection(EntityPlayer player, ItemStack itemStack);
}