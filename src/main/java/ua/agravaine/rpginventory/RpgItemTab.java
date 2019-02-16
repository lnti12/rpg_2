package ua.agravaine.rpginventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class RpgItemTab extends CreativeTabs {

	public RpgItemTab(String lable) {
		 super(lable);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
		 return ElterionRPG.helmetMorgul;
		}
}
