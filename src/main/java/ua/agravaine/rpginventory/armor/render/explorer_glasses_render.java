package ua.agravaine.rpginventory.armor.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class explorer_glasses_render extends Explorer_glasses_model {

	public static final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("elterionrpg", "obj/explorer_glasses_render.obj"));
	public static final ResourceLocation explorer_glasses = new ResourceLocation("elterionrpg","textures/models/explorer_glasses_render.png");
	private final int partType;

	/**armorType: 0 - head, 1 - body and arms, 2 - legs, 3 - feet.**/
	public explorer_glasses_render(int armorType) {
		partType = armorType;
	}

	@Override
	public void pre() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
	}

	@Override
	public void post() {
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void partHead() {
		if (partType == 0) {
		
			GL11.glTranslatef(0F, -1.5F, 0F);
			GL11.glRotatef(-90, 0, 1, 0);
			Minecraft.getMinecraft().renderEngine.bindTexture(explorer_glasses);
			model.renderPart("ojki_ojki_Cube.001");
		}
	}

	@Override
	public void partBody() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partRightArm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partLeftArm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partRightLeg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partLeftLeg() {
		// TODO Auto-generated method stub
		
	}


}