package ua.agravaine.rpginventory.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBackpack extends ModelBase{

	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;

	public ModelBackpack(){
		textureWidth = 64;
		textureHeight = 32;

		shape1 = new ModelRenderer(this, 3, 20);
		shape1.addBox(0F, 0F, 0F, 4, 8, 1);
		shape1.setRotationPoint(-2F, 1F, 2F);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		shape2 = new ModelRenderer(this, 0, 0);
		shape2.addBox(0F, 0F, 0F, 6, 7, 2);
		shape2.setRotationPoint(-3F, 2F, 1.8F);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0349066F, 0F, 0F);
		shape3 = new ModelRenderer(this, 6, 0);
		shape3.addBox(0F, 0F, 0F, 1, 3, 2);
		shape3.setRotationPoint(3F, 5F, 1.7F);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0F, -0.2792527F, 0.0349066F);
		shape4 = new ModelRenderer(this, 0, 0);
		shape4.addBox(0F, 0F, 0F, 1, 3, 2);
		shape4.setRotationPoint(-4F, 5F, 2F);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0F, 0.2792527F, -0.0349066F);
		shape5 = new ModelRenderer(this, 0, 0);
		shape5.addBox(0F, 0F, 0F, 3, 2, 1);
		shape5.setRotationPoint(-1.5F, 4F, 3.6F);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0872665F, 0F, 0F);
		shape6 = new ModelRenderer(this, 0, 0);
		shape6.addBox(0F, 0F, 0F, 4, 2, 1);
		shape6.setRotationPoint(-2F, 5.7F, 4F);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		shape1.render(f5);
		shape2.render(f5);
		shape3.render(f5);
		shape4.render(f5);
		shape5.render(f5);
		shape6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}