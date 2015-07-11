package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBreachingMissile - Undefined Created using Tabula 5.0.0
 */
public class ModelBreachingMissile extends ModelBase {

	public ModelRenderer shaft;
	public ModelRenderer head;
	public ModelRenderer cone1;
	public ModelRenderer cone2;
	public ModelRenderer base;
	public ModelRenderer fin1;
	public ModelRenderer fin2;
	public ModelRenderer fin3;
	public ModelRenderer fin4;
	public ModelRenderer wing1;
	public ModelRenderer wing2;
	public ModelRenderer wing3;
	public ModelRenderer wing4;
	public ModelRenderer wing5;
	public ModelRenderer wing6;
	public ModelRenderer wing7;
	public ModelRenderer wing8;

	public ModelBreachingMissile() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.wing1 = new ModelRenderer(this, 16, 18);
		this.wing1.setRotationPoint(-0.5F, -6.6F, -5.0F);
		this.wing1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
		this.wing5 = new ModelRenderer(this, 32, 18);
		this.wing5.setRotationPoint(-0.5F, -11.8F, -2.0F);
		this.wing5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
		this.setRotateAngle(wing5, -0.5235987755982988F, 0.0F, 0.0F);
		this.wing2 = new ModelRenderer(this, 16, 18);
		this.wing2.setRotationPoint(-0.5F, -6.6F, 2.0F);
		this.wing2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
		this.wing7 = new ModelRenderer(this, 32, 18);
		this.wing7.setRotationPoint(-2.0F, -11.8F, 0.5F);
		this.wing7.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
		this.setRotateAngle(wing7, -0.5235987755982988F, 1.5707963267948966F, 0.0F);
		this.wing6 = new ModelRenderer(this, 32, 18);
		this.wing6.setRotationPoint(0.5F, -11.8F, 2.0F);
		this.wing6.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
		this.setRotateAngle(wing6, -0.5235987755982988F, 3.141592653589793F, 0.0F);
		this.shaft = new ModelRenderer(this, 0, 0);
		this.shaft.setRotationPoint(-2.0F, -16.0F, -2.0F);
		this.shaft.addBox(0.0F, 0.0F, 0.0F, 4, 25, 4, 0.0F);
		this.wing4 = new ModelRenderer(this, 24, 18);
		this.wing4.setRotationPoint(2.0F, -6.6F, -0.5F);
		this.wing4.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
		this.fin1 = new ModelRenderer(this, 54, 0);
		this.fin1.setRotationPoint(1.0F, 12.0F, -1.0F);
		this.fin1.addBox(0.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
		this.setRotateAngle(fin1, 0.0F, 0.0F, -0.2792526803190927F);
		this.head = new ModelRenderer(this, 0, 29);
		this.head.setRotationPoint(-3.0F, -24.0F, -3.0F);
		this.head.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6, 0.0F);
		this.fin3 = new ModelRenderer(this, 54, 0);
		this.fin3.setRotationPoint(-1.0F, 12.0F, -1.0F);
		this.fin3.addBox(-5.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
		this.setRotateAngle(fin3, 0.0F, 0.0F, 0.2792526803190927F);
		this.cone2 = new ModelRenderer(this, 0, 51);
		this.cone2.setRotationPoint(-1.0F, -32.0F, -1.0F);
		this.cone2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		this.base = new ModelRenderer(this, 16, 0);
		this.base.setRotationPoint(-3.0F, 9.0F, -3.0F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 6, 12, 6, 0.0F);
		this.wing3 = new ModelRenderer(this, 24, 18);
		this.wing3.setRotationPoint(-5.0F, -6.6F, -0.5F);
		this.wing3.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
		this.fin2 = new ModelRenderer(this, 40, 0);
		this.fin2.setRotationPoint(-1.0F, 12.0F, 1.0F);
		this.fin2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 5, 0.0F);
		this.setRotateAngle(fin2, 0.2792526803190927F, 0.0F, 0.0F);
		this.cone1 = new ModelRenderer(this, 0, 43);
		this.cone1.setRotationPoint(-2.0F, -28.0F, -2.0F);
		this.cone1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		this.fin4 = new ModelRenderer(this, 40, 0);
		this.fin4.setRotationPoint(-1.0F, 12.0F, -1.0F);
		this.fin4.addBox(0.0F, 0.0F, -5.0F, 2, 10, 5, 0.0F);
		this.setRotateAngle(fin4, -0.2792526803190927F, 0.0F, 0.0F);
		this.wing8 = new ModelRenderer(this, 32, 18);
		this.wing8.setRotationPoint(2.0F, -11.8F, -0.5F);
		this.wing8.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
		this.setRotateAngle(wing8, -0.5235987755982988F, -1.5707963267948966F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.wing1.render(f5);
		this.wing5.render(f5);
		this.wing2.render(f5);
		this.wing7.render(f5);
		this.wing6.render(f5);
		this.shaft.render(f5);
		this.wing4.render(f5);
		this.fin1.render(f5);
		this.head.render(f5);
		this.fin3.render(f5);
		this.cone2.render(f5);
		this.base.render(f5);
		this.wing3.render(f5);
		this.fin2.render(f5);
		this.cone1.render(f5);
		this.fin4.render(f5);
		this.wing8.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
