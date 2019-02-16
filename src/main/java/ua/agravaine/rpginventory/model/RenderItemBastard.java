package ua.agravaine.rpginventory.model;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.lwjgl.opengl.GL11;

import ua.agravaine.rpginventory.ElterionRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderItemBastard implements IItemRenderer {

	public static final IModelCustom model = 
			AdvancedModelLoader.loadModel(new ResourceLocation("elterionrpg", "obj/sword_bastard.obj"));
	public static final ResourceLocation texture = new ResourceLocation("elterionrpg", "textures/items/sword_bastartd_obj.png");
    private RenderPlayer renderplayer;


    @Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type) {
        switch (type) {
        case ENTITY:
            return true;
        case EQUIPPED:
            return true;
        case EQUIPPED_FIRST_PERSON:
            return true;
        default:
            return false;
        }
    }

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
        switch (type) {
        case ENTITY:
            return true;
        case EQUIPPED:
            return true;
        case EQUIPPED_FIRST_PERSON:
            return true;
        default:
            return false;
        }
	}

	@Override
    public void renderItem(ItemRenderType type, ItemStack is, Object... data) {

      
        switch (type) {
        case EQUIPPED_FIRST_PERSON: {

                GL11.glPushMatrix();



                GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(25F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-65F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-1.2F, -1.45F, 1.1F);
                GL11.glScalef(2F, 2F, 2F);
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                model.renderAll();
                 GL11.glPopMatrix();
            if (is != null) {
                GL11.glPushMatrix();
                GL11.glRotatef(-30F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(60F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-55F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.5F, -1.2F, 0.3F);
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                EntityClientPlayerMP entityclientplayermp = Minecraft.getMinecraft().thePlayer;
                Minecraft.getMinecraft().getTextureManager().bindTexture(entityclientplayermp.getLocationSkin());
                renderplayer = (RenderPlayer) RenderManager.instance.getEntityRenderObject(Minecraft.getMinecraft().thePlayer);
                renderplayer.renderFirstPersonArm(Minecraft.getMinecraft().thePlayer);
                GL11.glPopMatrix();
                }

        }
        break;
        case EQUIPPED: {
        	GL11.glPushMatrix();
            GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-50F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-25F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.9F, -2F, 0.7F);
	        GL11.glScalef(2.3F, 3.6F, 2F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
	        model.renderAll();
	        GL11.glPopMatrix();

        }
        break;
        case ENTITY: {
        	GL11.glPushMatrix();
        	GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
	        GL11.glRotatef(0F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
	        GL11.glTranslatef(0.0F, 0.0F, 0.0F);
	        GL11.glScalef(1F, 1F, 1F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
	        model.renderAll();
	        GL11.glPopMatrix();
        }
        break;
            case INVENTORY:
                break;
            case FIRST_PERSON_MAP:
                break;
            default:
            break;
       }
      }
	    
}
	


