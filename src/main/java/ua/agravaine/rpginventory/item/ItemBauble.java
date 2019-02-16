package ua.agravaine.rpginventory.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.inventory.GuiInventoryRpg;

public class ItemBauble extends Item implements IBauble{

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	private String name;
	private final String[] items;

	private EnumBaubleType type;

	public ItemBauble(String itemName, EnumBaubleType baubleType, String... itemNames){
		super();
		name = itemName;
		type = baubleType;
		items = itemNames.length == 0 ? new String[]{name} : itemNames;
		setUnlocalizedName(name);
		setTextureName(ElterionRPG.MOD_ID + ":" + name);
		setCreativeTab(ElterionRPG.RpgTab);
		setMaxStackSize(1);
		setHasSubtypes(items.length > 0);
		GameRegistry.registerItem(this, name);
	}

	public String[] getItemsNames(){
		return items;
	}

	public ItemStack getItemStack(String item){
		return getItemStack(item, 1);
	}

	public String getUnlocalizedName(ItemStack iStack){
		int metadata = MathHelper.clamp_int(iStack.getItemDamage(), 0, items.length);
		return "item." + name + "_" + items[metadata];
	}

	public ItemStack getItemStack(String item, int count){
		for(int i = 0; i < items.length; i++){
			if(item.equals(items[i])){
				return new ItemStack(this, count, i);
			}
		}
		return null;
	}

	public IIcon getIconFromDamage(int metadata){
		return metadata < icons.length && metadata >= 0 ? icons[metadata] : itemIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir){
		icons = new IIcon[items.length];
		for(int i = 0; i < icons.length; i++){
			icons[i] = ir.registerIcon(ElterionRPG.MOD_ID + ":" + (items.length > 1 ? name + "_" + items[i] : name));
		}
		itemIcon = icons[0];

		GuiInventoryRpg.slotIcons[0] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_amulet");
		GuiInventoryRpg.slotIcons[1] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_ring1");
		GuiInventoryRpg.slotIcons[2] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_ring2");
		GuiInventoryRpg.slotIcons[3] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_backpack");
		GuiInventoryRpg.slotIcons[5] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_shoulder");
		GuiInventoryRpg.slotIcons[6] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_glove");
		GuiInventoryRpg.slotIcons[8] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_helmet");
		GuiInventoryRpg.slotIcons[9] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_chestplate");
		GuiInventoryRpg.slotIcons[10] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_leggings");
		GuiInventoryRpg.slotIcons[11] = ir.registerIcon(ElterionRPG.MOD_ID + ":slot_boots");
		
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item tabItem, CreativeTabs tab, List list){
		for(String item : items){
			list.add(getItemStack(item));
		}
	}

	public EnumBaubleType getType(ItemStack itemStack){
		return type;
	}
	
}