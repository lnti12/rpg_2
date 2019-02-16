package ua.agravaine.rpginventory.item;

import java.util.List;
import java.util.UUID;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ua.agravaine.rpginventory.util.RpgInventoryUtils;

public class ItemSpeedRing extends ItemBauble{

	public static final UUID SPEED_BOOST_ID = UUID.fromString("40354ACA-AEBA-11E7-ABC4-CEC278B6B50A");
	public static final AttributeModifier SPEED_BOOST_ATTRIBUTE = new AttributeModifier(SPEED_BOOST_ID, "Ring health boost", 0.05D, 1).setSaved(true);

	public ItemSpeedRing(String itemName, String... itemNames){
		super(itemName, EnumBaubleType.RING, itemNames);
	}

	public void onEquipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

		if(attribute.getModifier(SPEED_BOOST_ID) == null){
			RpgInventoryUtils.setPrivateValue(AttributeModifier.class, SPEED_BOOST_ATTRIBUTE, getSpeedBoost(itemStack), "amount", "field_111174_a");
			attribute.applyModifier(SPEED_BOOST_ATTRIBUTE);
		}
	}

	public void onUnequipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

		if(attribute.getModifier(SPEED_BOOST_ID) != null){
			attribute.removeModifier(SPEED_BOOST_ATTRIBUTE);
		}
	}

	private double getSpeedBoost(ItemStack itemStack){
		switch(itemStack.getItemDamage()){
		case 0:
			return 0.05D;
		case 1:
			return 0.07D;
		case 2:
			return 0.1D;
		default:
			return 0.0D;
		}
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedTooltips) {
		switch(itemStack.getItemDamage()){
		case 0:
			list.add("Тип:§bобычный");
			list.add("Скорость:§3+5");
			break;
		case 1:
			list.add("Тип:§1редкий");
			list.add("Скорость:§3+7");
			break;
		case 2:
			list.add("Тип:§dлегендарный");
			list.add("Скорость:§3+10");
			break;
		}
	
}
	
}