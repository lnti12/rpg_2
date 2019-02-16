package ua.agravaine.rpginventory.armor;

import java.util.List;
import java.util.UUID;

import ua.agravaine.rpginventory.ElterionRPG;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class reinforcedArmor extends ItemArmor {

	public reinforcedArmor(ArmorMaterial ArmorMaterial, int p_i45325_2_,int p_i45325_3_) {
		super(ArmorMaterial.IRON, p_i45325_2_, p_i45325_3_);
		this.setCreativeTab(ElterionRPG.RpgTab);
		this.setMaxStackSize(1);
		this.setTextureName(iconString);


	
	}

	/* private static UUID uuid = UUID.fromString("42c8426d-7e85-4a61-a883-72e85c656fab");
	public static final AttributeModifier HEALTH_BOOST_ATTRIBUTE = new AttributeModifier(uuid, "health boost", 2, 0).setSaved(true);

	    @Override
	    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	        if(world.isRemote)return;
	        BaseAttributeMap map = player.getAttributeMap();
	        if(map.getAttributeInstance(SharedMonsterAttributes.maxHealth).getModifier(uuid) != null){
	            map.getAttributeInstance(SharedMonsterAttributes.maxHealth).removeModifier(HEALTH_BOOST_ATTRIBUTE);
	        }
	        
	        if(player.inventory.armorItemInSlot(0).getItem() == ElterionRPG.reinforcedhelmet) {
	            if (player.inventory.armorItemInSlot(1).getItem() == ElterionRPG.reinforcedplate) {
	                if (player.inventory.armorItemInSlot(2).getItem() == ElterionRPG.reinforcedpants) {
	                    if (player.inventory.armorItemInSlot(3).getItem() == ElterionRPG.reinforcedboots) {
	                        map.getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier(uuid, "Lol", 2F, 1));
	                    }
	                }
	          
	            }
	            }
	        }*/
	    
	    
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
		switch(armorType){
		case 0:
			list.add("Тип:§bобычный");
			list.add("Защита:+2");
			list.add("");
			list.add("Сет:§eтемный §eрыцарь");
			list.add("Полный сет дарует:");
			list.add("+ 2 §4здоровья");
			
			
		break;
		case 1:
			list.add("Тип:§bобычный");
			list.add("Защита:+6");
			list.add("");
			list.add("Сет:§eтемный §eрыцарь");
			list.add("Полный сет дарует:");
			list.add("+ 2 §4здоровья");
		break;
		case 2:
			list.add("Тип:§bобычный");
			list.add("Защита:+5");
			list.add("");
			list.add("Сет:§eтемный §eрыцарь");
			list.add("Полный сет дарует:");
			list.add("+ 2 §4здоровья");
		break;
		case 3:
			list.add("Тип:§bобычный");
			list.add("Защита:+2");
			list.add("");
			list.add("Сет:§eтемный §eрыцарь");
			list.add("Полный сет дарует:");
			list.add("+ 2 §4здоровья");
		break;
		}
	}
	
}

	