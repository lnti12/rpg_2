package ua.agravaine.rpginventory.inventory.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import ua.agravaine.rpginventory.inventory.GuiInventoryRpg;

public class SlotArmor extends Slot{

	private EntityPlayer owner;
	private byte armorType;

	public SlotArmor(IInventory inventory, EntityPlayer player, int id, int x, int y, int armorIndex){
		super(inventory, id, x, y);
		owner = player;
		armorType = (byte)armorIndex;
	}

	public int getSlotStackLimit(){
		return 1;
	}

	public boolean isItemValid(ItemStack itemStack){
		if(itemStack == null){
			return false;
		}
		return itemStack.getItem().isValidArmor(itemStack, armorType, owner);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex(){
		return GuiInventoryRpg.slotIcons[8 + armorType];
	}
}