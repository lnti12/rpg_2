package ua.agravaine.rpginventory.item;

import java.util.ArrayList;

import ua.agravaine.rpginventory.ElterionRPG;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class random_amulet extends Item {

	 private ArrayList<DropsItem> list_drops = new ArrayList<DropsItem>();
	
	
	public random_amulet(){
		super();
		setUnlocalizedName("random_amulet");
		setTextureName("elterionrpg:random_amulet");
		setCreativeTab(ElterionRPG.RpgTab);
		setMaxStackSize(1);
	
		addItemWithChance(0.8F, new ItemStack(ElterionRPG.amulet, 1, 0));
		addItemWithChance(0.19F, new ItemStack(ElterionRPG.amulet, 1, 1));
		addItemWithChance(0.01F, new ItemStack(ElterionRPG.amulet, 1, 2));
        
    }
    

    
    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
        if (!world.isRemote) {
            float p = world.rand.nextFloat() * getChanceMax();
            ItemStack item_drop = null;
            if (list_drops != null)
                for (int i = 0; i < list_drops.size(); ++i) {
                    if (list_drops.get(i) != null) {
                        if (list_drops.get(i).getChanceA() <= p && list_drops.get(i).getChanceB() > p) {
                            item_drop = list_drops.get(i).getItem().copy();
                            //System.out.println("onItemRightClick - " + p + ",  " + item_drop);
                            break;
                        }
                    }
                    
                }
            if (item_drop != null) {
                player.func_146097_a(item_drop, false, false);
                player.inventory.addItemStackToInventory(item_drop);
                --is.stackSize;
            }
        }
        return is;
    }

    public void addItemWithChance(float chance, Object item) {
        float a = 0F;
        if (list_drops != null && list_drops.size() > 0) {
            float b = -1F;
            for (int i = 0; i < list_drops.size(); ++i) {
                if (list_drops.get(i) != null) {
                    if (b < list_drops.get(i).getChanceB()) b = list_drops.get(i).getChanceB();
                    else continue;
                } else continue;
            }
            if (b != -1F) a = b;
        }
        list_drops.add(new DropsItem(a, a + chance, item));
    }

    public float getChanceMax() {
        float a = 0F;
        if (list_drops != null && list_drops.size() > 0) {
            float b = -1;
            for (int i = 0; i < list_drops.size(); ++i) {
                if (list_drops.get(i) != null) {
                    if (b < list_drops.get(i).getChanceB()) b = list_drops.get(i).getChanceB();
                    else continue;
                } else continue;
            }
            if (b != -1) a = b;
        }
        return a;
    }

    private static class DropsItem {

        private ItemStack item;
        private float chanceA;
        private float chanceB;

        private DropsItem(float chanceA, float chanceB, Object item) {
            if (item == null) return;
            if (item != null) {
                if (item instanceof Block) this.item = new ItemStack((Block)item);
                else if (item instanceof Item) this.item = new ItemStack((Item)item);
                else if (item instanceof ItemStack) this.item = (ItemStack)item;
                //this.item = item;
            }
            this.chanceA = chanceA;
            this.chanceB = chanceB;
        }

        public ItemStack getItem() {
            return item;
        }

        public float getChanceA() {
            return chanceA;
        }

        public float getChanceB() {
            return chanceB;
        }

    }


	
	}
	

