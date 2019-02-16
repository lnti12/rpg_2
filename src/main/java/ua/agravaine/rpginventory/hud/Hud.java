package ua.agravaine.rpginventory.hud;

import cpw.mods.fml.common.eventhandler.EventPriority;

import org.lwjgl.opengl.GL11;



import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeHooks;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.*;

@SideOnly(Side.CLIENT)
public class Hud {

	Minecraft mc = Minecraft.getMinecraft();
	 String stg;
     int wi;


	
	@SubscribeEvent
    public void RenderHud(RenderGameOverlayEvent.Post e) {
        int width = e.resolution.getScaledWidth();
        int height= e.resolution.getScaledHeight();
	    EntityClientPlayerMP mcp = mc.thePlayer;
	    if(!mcp.capabilities.isCreativeMode) {
		GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glScaled(1, 1, 1);
        GL11.glScalef(1F, 1F, 1F);
        mc.getTextureManager().bindTexture(new ResourceLocation("elterionrpg", "textures/gui/hud_hp.png"));
        int hpBarWidth = MathHelper.ceiling_float_int(((mcp.getHealth() / mcp.getMaxHealth()) * 120));
        mc.ingameGUI.drawTexturedModalRect(width/2-123, height-35, 0, 0, 120, 10);
        mc.ingameGUI.drawTexturedModalRect(width/2-123 , height-34, 0, 11, hpBarWidth, 8);

        mc.getTextureManager().bindTexture(new ResourceLocation("elterionrpg", "textures/gui/hud_food.png"));
        int foodBarWidth = (int)(((float)mc.thePlayer.getFoodStats().getFoodLevel() / 20F) * 120F);
        mc.ingameGUI.drawTexturedModalRect(width/2 +3, height-35, 0, 0, 120, 10);
        mc.ingameGUI.drawTexturedModalRect(width/2 +3, height-34, 0, 12, foodBarWidth, 8);
        GL11.glPopMatrix();
        }
	}
	@SubscribeEvent
    public void RenderText(RenderGameOverlayEvent.Post e) {
	    EntityClientPlayerMP mcp = mc.thePlayer;
	    if(!mcp.capabilities.isCreativeMode) {
            int width = e.resolution.getScaledWidth();
            int height= e.resolution.getScaledHeight();
	    int health = (int) mcp.getHealth();
	    int healthmax = (int) mcp.getMaxHealth();
		int food = mcp.getFoodStats().getFoodLevel();
		int armor = ForgeHooks.getTotalArmorValue(mcp);
		GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(2.0F, 2.0F, 0.0F);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix(); 
		this.mc.ingameGUI.drawString(this.mc.fontRenderer, + health +"/"+ healthmax, width/2-70, height-35, 16777215);
        this.mc.ingameGUI.drawString(this.mc.fontRenderer, + food + "/20", width/2+50, height-35, 16777215);
        this.mc.ingameGUI.drawString(this.mc.fontRenderer, + armor + " / 28", width/2+95, height-10, 16777215);
	    }
        String[] fps1 = this.mc.debug.split(" ");
    	GL11.glPushMatrix();
        this.mc.ingameGUI.drawString(this.mc.fontRenderer, "ElterionRPG fps " + Integer.parseInt(fps1[0]), e.resolution.getScaledWidth() / 2 * 2 - 90, 1, 16777215);
        this.mc.ingameGUI.drawString(this.mc.fontRenderer, I18n.format("text.coord", new Object[0]) + (int)this.mc.thePlayer.posX + ", " + (int)this.mc.thePlayer.posZ, e.resolution.getScaledWidth() / 2 * 2 - 90, 11, 16777215);
        GL11.glPopMatrix();




	}
    @SubscribeEvent
    public void setCanceled(RenderGameOverlayEvent.Pre event) {
        switch(event.type) {
            case FOOD:
            case HEALTH:
            case ARMOR:
            case EXPERIENCE:
                event.setCanceled(true);
                break;
            default:
                break;
        }
    }

}