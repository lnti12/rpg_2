package ua.agravaine.rpginventory.event;

import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.entity.RpgPlayer;
import ua.agravaine.rpginventory.inventory.container.ContainerPlayerRpg;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class EventPat {

	@SubscribeEvent 
	public void playerTick(TickEvent.PlayerTickEvent e) { 
      if(e.player.inventoryContainer.getSlot(1).getStack()== new ItemStack(ElterionRPG.pat,0)) {


	}
	}
}
