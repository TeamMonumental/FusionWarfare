package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMissileLauncher extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public ModelMissileLauncher() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 14, 4, 14);
		Shape1.setRotationPoint(-7F, 20F, -7F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 6, 18);
		Shape2.addBox(0F, -9F, 0F, 2, 9, 1);
		Shape2.setRotationPoint(-1F, 21F, -6F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, -0.1745329F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 18);
		Shape3.addBox(0F, -9F, 0F, 1, 9, 2);
		Shape3.setRotationPoint(5F, 21F, -1F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, -0.1745329F);
		Shape4 = new ModelRenderer(this, 0, 18);
		Shape4.addBox(0F, -9F, 0F, 1, 9, 2);
		Shape4.setRotationPoint(-6F, 21F, -1F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.1745329F);
		Shape5 = new ModelRenderer(this, 6, 18);
		Shape5.addBox(0F, -9F, 0F, 2, 9, 1);
		Shape5.setRotationPoint(-1F, 21F, 5F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0.1745329F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);		
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
