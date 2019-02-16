package ua.agravaine.rpginventory.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ua.agravaine.rpginventory.ElterionRPG;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class bush extends Block {
	


    public bush() {
		super(Material.leaves);
		setBlockName("bush");
		setBlockTextureName("elterionrpg:bush");
		setCreativeTab(ElterionRPG.RpgItemTab);
	}
    

	
/*	@Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int p6, float p7, float p8, float p9) {
		 ItemStack item = new ItemStack(ElterionRPG.raspberry,1,0);

		 String nickname = Minecraft.getMinecraft().getSession().getUsername();
		 if(!world.isRemote ){
			
				
			 if (!player.inventory.addItemStackToInventory(item)){
				 Minecraft.getMinecraft().getSession().getUsername();
			 }
			 player.addChatMessage(new ChatComponentText(nickname + " собрал ягоды."));
		 }
	
		 return true;
	      
	
}*/


}