package ua.agravaine.rpginventory.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class MorgulArmor extends ItemArmor{

	private String texturePath = "elterionrpg:textures/models/morgul_helmet.png";
	
	public MorgulArmor(ArmorMaterial ArmorMaterial, int p_i45325_2_,int p_i45325_3_) {
		super(ArmorMaterial.IRON, p_i45325_2_, p_i45325_3_);
		// TODO Auto-generated constructor stub
	}
	public void setTextureName ()
	{
		if(armorType == 0){
			this.texturePath += "morgul_helmet" + ".png";
		}

}
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
		return this.texturePath;
	}
	}
