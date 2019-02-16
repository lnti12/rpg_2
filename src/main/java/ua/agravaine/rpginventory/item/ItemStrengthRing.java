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

public class ItemStrengthRing extends ItemBauble{

	public static final UUID STRENGTH_BOOST_ID = UUID.fromString("5D56FC24-AEBB-11E7-ABC4-CEC278B6B50A");
	public static final AttributeModifier STRENGTH_BOOST_ATTRIBUTE = new AttributeModifier(STRENGTH_BOOST_ID, "Ring strength boost", 1, 0).setSaved(true);

	public ItemStrengthRing(String itemName, String... itemNames){
		super(itemName, EnumBaubleType.RING, itemNames);
	}

	public void onEquipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.attackDamage);

		if(attribute.getModifier(STRENGTH_BOOST_ID) == null){
			RpgInventoryUtils.setPrivateValue(AttributeModifier.class, STRENGTH_BOOST_ATTRIBUTE, getStrengthBoost(itemStack), "amount", "field_111174_a");
			attribute.applyModifier(STRENGTH_BOOST_ATTRIBUTE);
		}
	}

	public void onUnequipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.attackDamage);

		if(attribute.getModifier(STRENGTH_BOOST_ID) != null){
			attribute.removeModifier(STRENGTH_BOOST_ATTRIBUTE);
		}
	}

	private int getStrengthBoost(ItemStack itemStack){
		switch(itemStack.getItemDamage()){
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		default:
			return 0;
		}
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedTooltips) {
		switch(itemStack.getItemDamage()){
		case 0:
			list.add("Тип:§bобычный");
			list.add("Урон:§4+1");
			break;
		case 1:
			list.add("Тип:§1редкий");
			list.add("Урон:§4+2");
			break;
		case 2:
			list.add("Тип:§dлегендарный");
			list.add("Урон:§4+3");
			break;
		}
	
}
}