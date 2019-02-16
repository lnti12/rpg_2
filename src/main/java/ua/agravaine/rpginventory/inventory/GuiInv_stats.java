package ua.agravaine.rpginventory.inventory;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import io.netty.handler.codec.http.multipart.Attribute;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeHooks;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ua.agravaine.rpginventory.ElterionRPG;
import ua.agravaine.rpginventory.RPGButton;


public class GuiInv_stats extends GuiScreen {

	private static final ResourceLocation TEXTURE = new ResourceLocation("elterionrpg", "textures/gui/Inv_stat.png");

	private static int guiWidth = 200;
	private static int guiHeight = 140;
	private Object RPGButton;
	String stg;
	int w;
	private Object GuiButton;


	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks) {
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		int k = width / 2;
		int l = height / 2;
		mc.getTextureManager().bindTexture(TEXTURE);
		GL11.glColor4f(1, 1, 1, 1);
		drawDefaultBackground();
		drawTexturedModalRect(guiX, guiY, 0, 0, 226, guiHeight);
		EntityClientPlayerMP mcp = mc.thePlayer;
		int healthMax = (int) mcp.getMaxHealth();
		int armor = ForgeHooks.getTotalArmorValue(mcp);
		int d = (int) SharedMonsterAttributes.attackDamage.getDefaultValue();
		//право
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Здоровье:" + healthMax, guiX + 150, guiY + 40, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Регенерация:1", guiX + 150, guiY + 50, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Скорость:70", guiX + 150, guiY + 60, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Броня:" + armor, guiX + 150, guiY + 70, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Урон:1", guiX + 150, guiY + 80, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Мана:0", guiX + 150, guiY + 90, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Рег. маны:0", guiX + 150, guiY + 100, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Доп.опыт:" + "0%", guiX + 150, guiY + 110, 0x808080);
		//лево
		String nickname = Minecraft.getMinecraft().getSession().getUsername();
		int lvl = mcp.experienceLevel + 1;
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, nickname+ " : " +lvl , guiX + 7, guiY + 40, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Сила:0", guiX + 7, guiY + 70, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Ловкость:0", guiX + 7, guiY + 80, 0x808080);
		//this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Интелект:0", guiX + 7, guiY + 75, 0x808080);
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, "Доступно очков:0", guiX + 7, guiY + 100, 0x808080);


		func_147046_a(k + 14, l + 30, 30, (float) (k + 15) - mouseX, (float) (l + 60 - 70) - mouseY, mc.thePlayer);


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

	public void initGui() {
		buttonList.clear();
		buttonList.add(GuiButton = new GuiButton(21, this.width / 2 - 75, this.height / 2 - 66, 15, 15, ""));
		if (mc.playerController.isInCreativeMode()) {
			mc.displayGuiScreen(new GuiContainerCreative(mc.thePlayer));
		} else {
			super.initGui();
		}
	}


	@SubscribeEvent
	public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInv_stats());
		}
	}

	public void actionPerformed(GuiButton button) {
		if (button.id == 21) {
			mc.displayGuiScreen(new GuiInventoryRpg(mc.thePlayer));

		    }
		
		if (button.id == 22) {

		}
	}
}