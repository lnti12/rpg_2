package ua.agravaine.rpginventory;

import ua.agravaine.rpginventory.item.ItemHealtRing;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class RpgTab extends CreativeTabs {

	public RpgTab(String lable) {
		 super(lable);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
		 return ElterionRPG.ringOfSpeed;
		}

}
