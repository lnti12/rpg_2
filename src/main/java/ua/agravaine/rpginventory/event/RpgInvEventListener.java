package ua.agravaine.rpginventory.event;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.entity.RpgPlayer;
import ua.agravaine.rpginventory.item.ItemBackpack;
import ua.agravaine.rpginventory.model.ModelBackpack;
import ua.agravaine.rpginventory.network.RpgInvGuiHandler;
import ua.agravaine.rpginventory.network.RpgInvPacketHandler;


public class RpgInvEventListener<ItemTooltipEvent>{

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiOpen(GuiOpenEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		if(e.gui instanceof GuiInventory && !mc.playerController.isInCreativeMode()){
			FMLProxyPacket packet = ElterionRPG.network.createPacket(RpgInvPacketHandler.OPEN_GUI, RpgInvGuiHandler.PLAYER_INVENTORY);
			ElterionRPG.network.sendToServer(packet);
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityEvent.EntityConstructing e){
		if(e.entity instanceof EntityPlayer && e.entity.getExtendedProperties(ElterionRPG.MOD_ID) == null){
			e.entity.registerExtendedProperties(ElterionRPG.MOD_ID, new RpgPlayer((EntityPlayer)e.entity));
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerIcons(TextureStitchEvent.Post e){
//		if(e.map.getTextureType() == 1){
//			GuiInventoryRpg.slotIcons[0] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_amulet");
//			GuiInventoryRpg.slotIcons[1] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_ring1");
//			GuiInventoryRpg.slotIcons[2] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_ring2");
//			GuiInventoryRpg.slotIcons[3] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_backpack");
//			GuiInventoryRpg.slotIcons[4] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_arrow");
//			GuiInventoryRpg.slotIcons[5] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_shoulder");
//			GuiInventoryRpg.slotIcons[6] = e.map.registerIcon(RpgInventoryMod.MOD_ID + ":slot_glove.png");
//		}
	}

	@SubscribeEvent
	public void onArrowNock(ArrowNockEvent e){
		EntityPlayer player = e.entityPlayer;
		ItemStack arrow = player.inventory.getStackInSlot(37);
		if(player.capabilities.isCreativeMode || arrow != null && arrow.getItem() == Items.arrow){
			player.setItemInUse(e.result, e.result.getMaxItemUseDuration());
		}
		e.setCanceled(true);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderBackpack(RenderPlayerEvent.Specials.Post e){
		EntityPlayer player = e.entityPlayer;
		if(player.inventory.getStackInSlot(36) != null){
			GL11.glColor4f(1F, 1F, 1F, 1F);
			if(ItemBackpack.MODEL == null){
				ItemBackpack.MODEL = new ModelBackpack();
			}
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("elterionrpg", "textures/models/backpack.png"));
			e.renderer.modelBipedMain.bipedBody.postRender(0.0625F);
			if(player.getCurrentArmor(2) != null){
				GL11.glTranslatef(0F, 0F, 0.05F);
			}
			ItemBackpack.MODEL.render(player, 0, 0, 0, 0, 0, 0.0625F);
			GL11.glPopMatrix();
		}
	}
	
}