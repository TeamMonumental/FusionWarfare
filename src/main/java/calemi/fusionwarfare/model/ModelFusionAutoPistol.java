package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFusionAutoPistol extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ModelFusionAutoPistol() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 10, 2, 2);
		Shape1.setRotationPoint(-2F, 16F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 4);
		Shape2.addBox(0F, 0F, 0F, 2, 5, 1);
		Shape2.setRotationPoint(-2F, 16F, 0.5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, -0.0051637F, 0.2082002F);
		Shape3 = new ModelRenderer(this, 24, 2);
		Shape3.addBox(0F, 0F, 0F, 5, 1, 1);
		Shape3.setRotationPoint(2F, 18F, 0.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 10);
		Shape4.addBox(0F, 0F, 0F, 2, 2, 1);
		Shape4.setRotationPoint(-3F, 15F, 0.5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 15);
		Shape5.addBox(-1F, 0F, 0F, 1, 1, 1);
		Shape5.setRotationPoint(7F, 15F, 0.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 6, 4);
		Shape6.addBox(0F, 0F, 0F, 3, 4, 1);
		Shape6.setRotationPoint(3F, 19F, 0.5F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 24, 0);
		Shape7.addBox(0F, 0F, 0F, 7, 1, 1);
		Shape7.setRotationPoint(-1F, 15.7F, 0.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 13);
		Shape8.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape8.setRotationPoint(0F, 16.5F, -0.3F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 0, 13);
		Shape9.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape9.setRotationPoint(0F, 16.5F, 1.3F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}
