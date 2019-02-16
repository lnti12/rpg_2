package ua.agravaine.rpginventory.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ua.agravaine.rpginventory.entity.RpgPlayer;
import ua.agravaine.rpginventory.inventory.slot.SlotArmor;
import ua.agravaine.rpginventory.inventory.slot.SlotBauble;
import ua.agravaine.rpginventory.item.EnumBaubleType;
import ua.agravaine.rpginventory.item.IBauble;
import ua.agravaine.rpginventory.item.ItemBackpack;

public class ContainerPlayerRpg extends Container{

	private final EntityPlayer thePlayer;
	private final RpgPlayer rpgPlayer;
	public final InventoryBasic inventoryBackpack;

	public ContainerPlayerRpg(InventoryPlayer inventory, EntityPlayer player){
		thePlayer = player;
		rpgPlayer = RpgPlayer.forPlayer(player);
		inventoryBackpack = new InventoryBasic("Backpack", true, 16);

		for(int i = 0; i < 4; ++i){
			addSlotToContainer(new SlotArmor(inventory, thePlayer, inventory.getSizeInventory() - 1 - i, 10, 20 + i * 17, i));
		}

		for(int i = 0; i < 4; ++i){
			for(int j = 0; j < 5; ++j){
				addSlotToContainer(new Slot(inventory, j + (i + 1) * 5 + 4, 82 + j * 18, 21 + i * 18));
			}
		}

		for(int i = 0; i < 9; ++i){
			addSlotToContainer(new Slot(inventory, i, 10 + i * 18, 107));
		}

		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 0, 61, 20, EnumBaubleType.AMULET));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 1, 61, 37, EnumBaubleType.RING));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 2, 61, 54, EnumBaubleType.RING));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 3, 61, 71, EnumBaubleType.BACKPACK));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 5, 27, 88, EnumBaubleType.SHOULDER));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 6, 44, 88, EnumBaubleType.GLOVE));
		addSlotToContainer(new SlotBauble(this, player, rpgPlayer.baublesInventory, 7, 10, 88, EnumBaubleType.PAT));
		addSlotToContainer(new Slot(rpgPlayer.baublesInventory, 4, 61, 88){
			public boolean isItemValid(ItemStack itemStack){
				return itemStack != null && itemStack.getItem() == Items.arrow;
			}
		});
		
		for(int i = 0; i < 4; ++i){
			for(int j = 0; j < 4; ++j){
				
				addSlotToContainer(new Slot(inventoryBackpack, j + i * 4, Integer.MAX_VALUE, Integer.MAX_VALUE));
			}
		}
		
		ItemBackpack.loadItems(player, inventorySlots.subList(41, 41 + 16), inventoryBackpack, rpgPlayer.baublesInventory.getStackInSlot(3));
	}

	public boolean canInteractWith(EntityPlayer p_75145_1_){
		return true;
	}

	public void onContainerClosed(EntityPlayer player){
		super.onContainerClosed(player);
		ItemBackpack.saveItems(player, null, inventoryBackpack, rpgPlayer.baublesInventory.getStackInSlot(3), true);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex){
		ItemStack itemStack = null;
		Slot slot = getSlot(slotIndex);

		if(slot != null && slot.getHasStack()){
			ItemStack slotStack = slot.getStack();
			itemStack = slotStack.copy();

			if(slotIndex >= 0 && slotIndex < 4){
				if(!mergeItemStack(slotStack, 4, 33, false)){
					return null;
				}
			}else if(slotIndex >= 33 && slotIndex < 40){
				if(!mergeItemStack(slotStack, 4, 33, false)){
					return null;
				}
			}else if(itemStack.getItem() == Items.arrow){
				if(!mergeItemStack(slotStack, 39, 40, false)){
					return null;
				}
			}else if(itemStack.getItem() instanceof ItemArmor){
				ItemArmor armor = (ItemArmor)itemStack.getItem();
				Slot armorSlot = getSlot(armor.armorType);
				if(!armorSlot.getHasStack()){
					if(!mergeItemStack(slotStack, armor.armorType, armor.armorType + 1, false)){
						return null;
					}
				}
			}else if(itemStack.getItem() instanceof IBauble){
				IBauble armor = (IBauble)itemStack.getItem();
				int index = -1;
				for(int i = 33; i < 40; i++){
					SlotBauble baubleSlot = (SlotBauble)getSlot(i);
					if(!baubleSlot.getHasStack() && armor.getType(itemStack) == baubleSlot.getType()){
						index = i;
						break;
					}
				}
				if(index != -1){
					Slot armorSlot = getSlot(index);
					if(!armorSlot.getHasStack()){
						if(!mergeItemStack(slotStack, index, index + 1, false)){
							return null;
						}
					}
				}
			}else if(slotIndex >= 4 && slotIndex < 24){
				if(!mergeItemStack(slotStack, 24, 33, false)){
					return null;
				}
			}else if(slotIndex >= 24 && slotIndex < 33){
				if(!mergeItemStack(slotStack, 4, 24, false)){
					return null;
				}
			}else if(!mergeItemStack(slotStack, 4, 33, false)){
				return null;
			}

			// if(slotIndex > 3){
			// if(!mergeItemStack(slotStack, 4, 33, false)){
			// return null;
			// }
			// }

			if(slotStack.stackSize == 0){
				slot.putStack(null);
			}else{
				slot.onSlotChanged();
			}

			if(slotStack.stackSize == itemStack.stackSize){
				return null;
			}

			slot.onPickupFromSlot(player, slotStack);
		}

		return itemStack;
	}

//	protected boolean mergeItemStack(ItemStack itemStack, int fromIndex, int toIndex, boolean reverse){
//		boolean changed = false;
//		int index = fromIndex;
//
//		if(reverse){
//			index = toIndex - 1;
//		}
//
//		if(itemStack.isStackable()){
//			while(itemStack.stackSize > 0 && (!reverse && index < toIndex || reverse && index >= fromIndex)){
//				Slot slot = getSlot(index);
//				ItemStack slotStack = slot.getStack();
//
//				if(slotStack != null && slotStack.getItem() == itemStack.getItem() && (!itemStack.getHasSubtypes() || itemStack.getItemDamage() == slotStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(itemStack, slotStack) || (slot instanceof SlotBauble && ((IBauble)slotStack.getItem()).getType(slotStack) == ((SlotBauble)slot).getType())){
//					int l = slotStack.stackSize + itemStack.stackSize;
//
//					if(l <= itemStack.getMaxStackSize()){
//						itemStack.stackSize = 0;
//						slotStack.stackSize = l;
//						slot.onSlotChanged();
//						changed = true;
//					}else if(slotStack.stackSize < itemStack.getMaxStackSize()){
//						itemStack.stackSize -= itemStack.getMaxStackSize() - slotStack.stackSize;
//						slotStack.stackSize = itemStack.getMaxStackSize();
//						slot.onSlotChanged();
//						changed = true;
//					}
//				}
//
//				if(reverse){
//					--index;
//				}else{
//					++index;
//				}
//			}
//		}
//
//		if(itemStack.stackSize > 0){
//			if(reverse){
//				index = toIndex - 1;
//			}else{
//				index = fromIndex;
//			}
//
//			while(!reverse && index < toIndex || reverse && index >= fromIndex){
//				Slot slot = getSlot(index);
//				ItemStack slotStack = slot.getStack();
//
//				if(slotStack == null){
//					slot.putStack(itemStack.copy());
//					slot.onSlotChanged();
//					itemStack.stackSize = 0;
//					changed = true;
//					break;
//				}
//
//				if(reverse){
//					--index;
//				}else{
//					++index;
//				}
//			}
//		}
//
//		return changed;
//	}
}