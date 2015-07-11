package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFallingSupplyCrate extends ModelBase {
	
    public ModelRenderer crate;
    public ModelRenderer parachute;
    public ModelRenderer parachute2;
    public ModelRenderer parachute3;
    public ModelRenderer string1;
    public ModelRenderer string2;
    public ModelRenderer string3;
    public ModelRenderer string4;

    public ModelFallingSupplyCrate() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.parachute2 = new ModelRenderer(this, 0, 51);
        this.parachute2.setRotationPoint(11.0F, -21.0F, -9.0F);
        this.parachute2.addBox(0.0F, 0.0F, 0.0F, 18, 1, 18, 0.0F);
        this.setRotateAngle(parachute2, 0.0F, 0.0F, 0.2792526803190927F);
        this.string3 = new ModelRenderer(this, 0, 70);
        this.string3.setRotationPoint(-5.0F, 8.0F, 5.0F);
        this.string3.addBox(-1.0F, -30.0F, 0.0F, 1, 30, 1, 0.0F);
        this.setRotateAngle(string3, 0.0F, 0.0F, -0.47123889803846897F);
        this.string2 = new ModelRenderer(this, 0, 70);
        this.string2.setRotationPoint(5.0F, 8.0F, -6.0F);
        this.string2.addBox(0.0F, -30.0F, 0.0F, 1, 30, 1, 0.0F);
        this.setRotateAngle(string2, 0.0F, 0.0F, 0.47123889803846897F);
        this.crate = new ModelRenderer(this, 0, 0);
        this.crate.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.crate.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
        this.parachute3 = new ModelRenderer(this, 0, 51);
        this.parachute3.setRotationPoint(-11.0F, -21.0F, 8.0F);
        this.parachute3.addBox(-18.0F, 0.0F, -17.0F, 18, 1, 18, 0.0F);
        this.setRotateAngle(parachute3, 0.0F, 0.0F, -0.2792526803190927F);
        this.string4 = new ModelRenderer(this, 0, 70);
        this.string4.setRotationPoint(-5.0F, 8.0F, 5.0F);
        this.string4.addBox(-1.0F, -30.0F, -11.0F, 1, 30, 1, 0.0F);
        this.setRotateAngle(string4, 0.0F, 0.0F, -0.47123889803846897F);
        this.parachute = new ModelRenderer(this, 0, 32);
        this.parachute.setRotationPoint(-11.0F, -21.0F, -9.0F);
        this.parachute.addBox(0.0F, 0.0F, 0.0F, 22, 1, 18, 0.0F);
        this.string1 = new ModelRenderer(this, 0, 70);
        this.string1.setRotationPoint(5.0F, 8.0F, 5.0F);
        this.string1.addBox(0.0F, -30.0F, 0.0F, 1, 30, 1, 0.0F);
        this.setRotateAngle(string1, 0.0F, 0.0F, 0.47123889803846897F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.parachute2.render(f5);
        this.string3.render(f5);
        this.string2.render(f5);
        this.crate.render(f5);
        this.parachute3.render(f5);
        this.string4.render(f5);
        this.parachute.render(f5);
        this.string1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
