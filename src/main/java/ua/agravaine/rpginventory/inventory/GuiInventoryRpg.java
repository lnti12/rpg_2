package ua.agravaine.rpginventory.inventory;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.RPGButton;
import ua.agravaine.rpginventory.inventory.container.ContainerPlayerRpg;

@SideOnly(Side.CLIENT)
public class GuiInventoryRpg extends InventoryEffectRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation("elterionrpg", "textures/gui/inventory.png");

	public static IIcon[] slotIcons = new IIcon[12];
	private Object RPGButton;

	public GuiInventoryRpg(EntityPlayer player) {
		super(new ContainerPlayerRpg(player.inventory, player));
		allowUserInput = true;
		xSize = 176;
		ySize = 121;
	}

	public void updateScreen() {
		if (mc.playerController.isInCreativeMode()) {
			mc.displayGuiScreen(new GuiContainerCreative(mc.thePlayer));
		}
	}

	public void initGui() {
		buttonList.clear();
		buttonList.add(RPGButton = new RPGButton(20, this.width/2 - 43, this.height/2 -56, 15, 15, ""));

		if (mc.playerController.isInCreativeMode()) {
			mc.displayGuiScreen(new GuiContainerCreative(mc.thePlayer));
		} else {
			super.initGui();
		}

		if (!mc.thePlayer.getActivePotionEffects().isEmpty()) {
			guiLeft = (width - xSize) / 2;
		}
	}

	public void drawGuiContainerBackgroundLayer(float ticks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		int k = guiLeft;
		int l = guiTop;
		drawTexturedModalRect(k, l, 0, 0, 176, 134);
		if (inventorySlots.getSlot(36).getHasStack()) {
			xSize = 176 + 80;
			ySize = 121 + 78;
			drawTexturedModalRect(k + 176 + 2, l, 0, 134, 78, 78);
		} else {
			xSize = 176;
			ySize = 121;
		}
		func_147046_a(k + 44, l + 83, 30, (float) (k + 51) - mouseX, (float) (l + 75 - 50) - mouseY, mc.thePlayer);
	}

	public static void func_147046_a(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, 50.0F);
		GL11.glScalef((float) (-scale), (float) scale, (float) scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = entity.renderYawOffset;
		float f3 = entity.rotationYaw;
		float f4 = entity.rotationPitch;
		float f5 = entity.prevRotationYawHead;
		float f6 = entity.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan((double) (yaw / 40.0F)) * 20.0F;
		entity.rotationYaw = (float) Math.atan((double) (yaw / 40.0F)) * 40.0F;
		entity.rotationPitch = -((float) Math.atan((double) (pitch / 40.0F))) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		entity.renderYawOffset = f2;
		entity.rotationYaw = f3;
		entity.rotationPitch = f4;
		entity.prevRotationYawHead = f5;
		entity.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}


	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			mc.displayGuiScreen(new GuiAchievements(this, mc.thePlayer.getStatFileWriter()));
		} else if (button.id == 1) {
			mc.displayGuiScreen(new GuiStats(this, mc.thePlayer.getStatFileWriter()));
		} else if (button.id == 20) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInv_stats());
		}

	}
}