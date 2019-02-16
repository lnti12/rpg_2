package ua.agravaine.rpginventory.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldServer;
import ua.agravaine.rpginventory.ElterionRPG;

public class RpgInvPacketHandler implements IPacketHandler{
	
	public static final byte OPEN_GUI = 0;
	
	@SideOnly(Side.CLIENT)
	public void handleClientSide(PacketBuffer buf, byte id, Minecraft mc, WorldClient world, EntityClientPlayerMP player){
		
	}

	public void handleServerSide(PacketBuffer buf, byte id, WorldServer world, EntityPlayerMP player){
		if(id == OPEN_GUI){
			int guiId = buf.readInt();
			player.openGui(ElterionRPG.instance, guiId, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}

	public String getChannel(){
		return ElterionRPG.MOD_ID;
	}
}