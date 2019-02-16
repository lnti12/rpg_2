package ua.agravaine.rpginventory.inventory.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ua.agravaine.rpginventory.inventory.GuiInventoryRpg;
import ua.agravaine.rpginventory.item.EnumBaubleType;
import ua.agravaine.rpginventory.item.IBauble;

public class SlotBauble extends Slot{

	private final Container parent;
	private final EntityPlayer player;
	private final EnumBaubleType type;

	public SlotBauble(Container container, EntityPlayer thePlayer, IInventory inventory, int id, int x, int y, EnumBaubleType baubleType){
		super(inventory, id, x, y);
		parent = container;
		player = thePlayer;
		type = baubleType;
	}

	public EnumBaubleType getType(){
		return type;
	}

	public int getSlotStackLimit(){
		return 1;
	}

	public boolean isItemValid(ItemStack itemStack){
		if(itemStack == null || !(itemStack.getItem() instanceof IBauble)){
			return false;
		}
		IBauble item = (IBauble)itemStack.getItem();
		return item.getType(itemStack) == type;
	}

	public void onPickupFromSlot(EntityPlayer player, ItemStack stack){
		if(!getHasStack() && stack.getItem() instanceof IBauble){
			((IBauble)stack.getItem()).onUnequipped(parent, inventory, stack, player);
		}
		super.onPickupFromSlot(player, stack);
	}

	public void putStack(ItemStack itemStack){
		if(getHasStack() && getStack().getItem() instanceof IBauble && !ItemStack.areItemStacksEqual(itemStack, getStack())){
			((IBauble)getStack().getItem()).onUnequipped(parent, inventory, getStack(), player);
		}

		ItemStack oldStack = getStack() != null ? getStack().copy() : null;
		super.putStack(itemStack);

		if(getHasStack() && getStack().getItem() instanceof IBauble && !ItemStack.areItemStacksEqual(oldStack, getStack())){
			((IBauble)getStack().getItem()).onEquipped(parent, inventory, getStack(), player);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex(){
		int index = MathHelper.clamp_int(getSlotIndex(), 0, GuiInventoryRpg.slotIcons.length);
		return GuiInventoryRpg.slotIcons[index];
	}
}
