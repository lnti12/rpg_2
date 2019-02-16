package ua.agravaine.rpginventory.network;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ua.agravaine.rpginventory.inventory.GuiInventoryRpg;
import ua.agravaine.rpginventory.inventory.container.ContainerPlayerRpg;

public class RpgInvGuiHandler implements IGuiHandler{

	public static final int PLAYER_INVENTORY = 0;
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		if(id == PLAYER_INVENTORY){
			return new ContainerPlayerRpg(player.inventory, player);
		}
		return null;
	}

	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		if(id == PLAYER_INVENTORY){
			return new GuiInventoryRpg(player);
		}
		return null;
	}
}