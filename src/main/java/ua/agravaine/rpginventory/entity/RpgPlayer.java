package ua.agravaine.rpginventory.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import ua.agravaine.rpginventory.ElterionRPG;


public class RpgPlayer implements IExtendedEntityProperties{

	private EntityPlayer player;
	
	public InventoryBasic baublesInventory;
	
	//public ItemStack[] baublesInventory;
	
	public RpgPlayer(EntityPlayer thePlayer){
		player = thePlayer;
		//baublesInventory = new ItemStack[7];
		baublesInventory = new InventoryBasic("Baubles", true,8);
	}
	
	public static RpgPlayer forPlayer(EntityPlayer player){
		return (RpgPlayer)player.getExtendedProperties(ElterionRPG.MOD_ID);
	}
	
	public void saveNBTData(NBTTagCompound compound){}

	public void loadNBTData(NBTTagCompound compound){}

	public void init(Entity entity, World world){}
}