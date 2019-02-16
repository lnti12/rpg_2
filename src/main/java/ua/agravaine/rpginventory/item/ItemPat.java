package ua.agravaine.rpginventory.item;

import ua.agravaine.rpginventory.ElterionRPG;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;

public class ItemPat extends ItemBauble {

	public ItemPat(String itemName, String... itemNames){
		super(itemName, EnumBaubleType.PAT, itemNames);
	}

	
	

}
