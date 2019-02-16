package ua.agravaine.rpginventory.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class explorer_glasses extends ItemArmor{

	public explorer_glasses(ArmorMaterial ArmorMaterial, int p_i45325_2_,int p_i45325_3_) {
		super(ArmorMaterial.IRON, p_i45325_2_, p_i45325_3_);

	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		ModelBiped model = new ModelBiped();
		model.bipedHead.showModel = 
				model.bipedHeadwear.showModel = 
				model.bipedBody.showModel = 
				model.bipedRightArm.showModel = 
				model.bipedLeftArm.showModel = 
				model.bipedRightLeg.showModel = 
				model.bipedLeftLeg.showModel = false;
		return model;
	}
	
}
