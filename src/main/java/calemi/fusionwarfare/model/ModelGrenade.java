package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrenade extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public ModelGrenade() {
		
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 2, 5, 2);
		Shape1.setRotationPoint(-1F, 18.5F, -1F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 8, 0);
		Shape2.addBox(0F, 0F, 0F, 1, 4, 2);
		Shape2.setRotationPoint(0.5F, 19F, -0.9666666F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 8, 0);
		Shape6.addBox(0F, 0F, 0F, 1, 4, 2);
		Shape6.setRotationPoint(-1.5F, 19F, -1F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 8, 6);
		Shape7.addBox(0F, 0F, 0F, 2, 4, 1);
		Shape7.setRotationPoint(-1F, 19F, 0.5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 8, 6);
		Shape8.addBox(0F, 0F, 0F, 2, 4, 1);
		Shape8.setRotationPoint(-1F, 19F, -1.5F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 4, 11);
		Shape3.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape3.setRotationPoint(-0.5F, 17F, -0.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 7);
		Shape4.addBox(0F, 0F, 0F, 1, 1, 3);
		Shape4.setRotationPoint(-0.5F, 16.5F, -0.7F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 11);
		Shape5.addBox(0F, 0F, 0F, 1, 6, 1);
		Shape5.setRotationPoint(-0.5F, 16.5F, 2F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		if (entity == null) {		
			Shape4.render(f5);
			Shape5.render(f5);
		}
		
		Shape1.render(f5);
		Shape2.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape3.render(f5);		
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
