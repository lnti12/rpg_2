package ua.agravaine.rpginventory.event;

import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.armor.render.Explorer_glasses_model;
import ua.agravaine.rpginventory.armor.render.explorer_glasses_render;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventsArmor {
	
	@SubscribeEvent
	public void onRenderArmomForPlayer(RenderPlayerEvent.SetArmorModel event) {
		if (event.stack == null) return;
		Explorer_glasses_model acm = getModel(event.entityPlayer, event.stack, event.slot);
		if (acm == null) return;
		acm.partHead.set(event.renderer.modelBipedMain.bipedHead);
		acm.partBody.set(event.renderer.modelBipedMain.bipedBody);
		acm.partRightArm.set(event.renderer.modelBipedMain.bipedRightArm);
		acm.partLeftArm.set(event.renderer.modelBipedMain.bipedLeftArm);
		acm.partRightLeg.set(event.renderer.modelBipedMain.bipedRightLeg);
		acm.partLeftLeg.set(event.renderer.modelBipedMain.bipedLeftLeg);
		acm.render(event.entityPlayer);
	}

	private static final Explorer_glasses_model
	acm0 = new explorer_glasses_render(0);


	public static Explorer_glasses_model getModel(EntityLivingBase entityPlayer, ItemStack stack, int slot) {
		if (stack.getItem() == ElterionRPG.explorer_glasses) return acm0;

		else return null;
	}

}

