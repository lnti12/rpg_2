package ua.agravaine.rpginventory.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import ua.agravaine.rpginventory.inventory.container.ContainerPlayerRpg;

public class ItemBackpack extends ItemBauble{

	@SideOnly(Side.CLIENT)
	public static ModelBase MODEL;

	public ItemBackpack(String name, String... names){
		super(name, EnumBaubleType.BACKPACK, names);
	}

	public void onEquipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		if(!(container instanceof ContainerPlayerRpg)){
			return;
		}

		ContainerPlayerRpg playerContainer = (ContainerPlayerRpg)container;

		loadItems(player, playerContainer.inventorySlots.subList(40, 40 + 16), playerContainer.inventoryBackpack, itemStack);
	}

	public void onUnequipped(Container container, IInventory inventory, ItemStack itemStack, EntityPlayer player){
		if(!(container instanceof ContainerPlayerRpg)){
			return;
		}

		ContainerPlayerRpg playerContainer = (ContainerPlayerRpg)container;

		saveItems(player, playerContainer.inventorySlots.subList(40, 40 + 16), playerContainer.inventoryBackpack, itemStack, false);
	}

	public static void saveItems(EntityPlayer player, List<Slot> slots, IInventory inventory, ItemStack itemStack, boolean isContainerClosed){
		if(isContainerClosed){
			if(itemStack == null){
				return;
			}

			if(!itemStack.hasTagCompound()){
				itemStack.setTagCompound(new NBTTagCompound());
			}

			NBTTagCompound nbt = itemStack.getTagCompound();
			NBTTagList listItems = new NBTTagList();
			for(int i = 0; i < inventory.getSizeInventory(); i++){
				ItemStack slotStack = inventory.getStackInSlot(i);
				if(slotStack != null){
					NBTTagCompound stackTag = new NBTTagCompound();
					stackTag.setByte("slot", (byte)i);
					slotStack.writeToNBT(stackTag);
					listItems.appendTag(stackTag);
					inventory.setInventorySlotContents(i, null);
				}
			}
			nbt.setTag("items", listItems);
		}else{
			for(int i = 0; i < inventory.getSizeInventory(); i++){
				ItemStack slotStack = inventory.getStackInSlot(i);
				if(slotStack != null){
					player.dropPlayerItemWithRandomChoice(slotStack, false);
					inventory.setInventorySlotContents(i, null);
				}
			}
		}

		if(slots != null){
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++){
					Slot slot = slots.get(j + i * 4);
					slot.xDisplayPosition = Integer.MAX_VALUE;
					slot.yDisplayPosition = Integer.MAX_VALUE;
				}
			}
		}
	}

	public static void loadItems(EntityPlayer player, List<Slot> slots, IInventory inventory, ItemStack itemStack){
		if(slots != null){
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++){
					Slot slot = slots.get(j + i * 4);
					slot.xDisplayPosition = j * 18 + 182;
					slot.yDisplayPosition = 4 + i * 18;
				}
			}
		}

		if(itemStack == null || !itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("items")){
			return;
		}

		NBTTagCompound nbt = itemStack.getTagCompound();
		NBTTagList listItems = nbt.getTagList("items", NBT.TAG_COMPOUND);
		for(int i = 0; i < listItems.tagCount(); ++i){
			NBTTagCompound stackTag = listItems.getCompoundTagAt(i);
			int slot = stackTag.getByte("slot") & 255;
			ItemStack slotStack = ItemStack.loadItemStackFromNBT(stackTag);
			if(itemStack != null){
				if(slot >= 0 && slot < inventory.getSizeInventory()){
					inventory.setInventorySlotContents(slot, slotStack);
				}
			}
		}
		nbt.removeTag("items");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advancedTooltips) {
		switch(itemStack.getItemDamage()){
		case 0:
			list.add("Тип:§bобычный");
			list.add("Дополнительные слоты:+16");
			break;
		
	
}
	
}
}