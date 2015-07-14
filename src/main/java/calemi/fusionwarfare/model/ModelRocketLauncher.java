package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRocketLauncher extends ModelBase {
    public ModelRenderer barrel1;
    public ModelRenderer corner1;
    public ModelRenderer corner2;
    public ModelRenderer corner3;
    public ModelRenderer corner4;
    public ModelRenderer box;
    public ModelRenderer ring1;
    public ModelRenderer ring2;
    public ModelRenderer scope1;
    public ModelRenderer handle;
    public ModelRenderer scope2;
    public ModelRenderer handleBase;
    public ModelRenderer trigger;
    public ModelRenderer shoulderRest1;
    public ModelRenderer trigger2;
    public ModelRenderer barrel2;
    public ModelRenderer shoulderRest2;
    public ModelRenderer ring3;

    public ModelRocketLauncher() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.ring2 = new ModelRenderer(this, 24, 18);
        this.ring2.setRotationPoint(16.0F, 11.0F, -3.0F);
        this.ring2.addBox(-7.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        this.handle = new ModelRenderer(this, 0, 30);
        this.handle.setRotationPoint(1.0F, 16.5F, -1.6F);
        this.handle.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.shoulderRest2 = new ModelRenderer(this, 44, 18);
        this.shoulderRest2.setRotationPoint(5.0F, 15.5F, -1.0F);
        this.shoulderRest2.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1, 0.0F);
        this.box = new ModelRenderer(this, 0, 18);
        this.box.setRotationPoint(-1.0F, 10.5F, -3.5F);
        this.box.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.scope2 = new ModelRenderer(this, 36, 22);
        this.scope2.setRotationPoint(2.3F, 9.0F, -4.7F);
        this.scope2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(scope2, -0.5585053606381855F, 0.0F, 0.0F);
        this.ring1 = new ModelRenderer(this, 24, 18);
        this.ring1.setRotationPoint(15.0F, 11.0F, -3.0F);
        this.ring1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        this.corner3 = new ModelRenderer(this, 0, 16);
        this.corner3.setRotationPoint(-9.5F, 15.0F, 0.3F);
        this.corner3.addBox(0.0F, 0.0F, 0.0F, 29, 1, 1, 0.0F);
        this.setRotateAngle(corner3, 0.7853981633974483F, 0.0F, 0.0F);
        this.handleBase = new ModelRenderer(this, 0, 37);
        this.handleBase.setRotationPoint(0.5F, 21.5F, -2.1F);
        this.handleBase.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.ring3 = new ModelRenderer(this, 24, 18);
        this.ring3.setRotationPoint(1.0F, 11.0F, -3.0F);
        this.ring3.addBox(-7.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        this.scope1 = new ModelRenderer(this, 36, 18);
        this.scope1.setRotationPoint(3.0F, 11.5F, -3.0F);
        this.scope1.addBox(0.0F, -1.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(scope1, -0.5585053606381855F, 0.0F, 0.0F);
        this.corner2 = new ModelRenderer(this, 0, 16);
        this.corner2.setRotationPoint(-9.5F, 12.0F, -2.7F);
        this.corner2.addBox(0.0F, 0.0F, 0.0F, 29, 1, 1, 0.0F);
        this.setRotateAngle(corner2, 0.7853981633974483F, 0.0F, 0.0F);
        this.barrel1 = new ModelRenderer(this, 0, 0);
        this.barrel1.setRotationPoint(-10.0F, 11.0F, -2.0F);
        this.barrel1.addBox(0.0F, 0.0F, 0.0F, 30, 5, 3, 0.0F);
        this.corner4 = new ModelRenderer(this, 0, 16);
        this.corner4.setRotationPoint(-9.5F, 15.0F, -2.7F);
        this.corner4.addBox(0.0F, 0.0F, 0.0F, 29, 1, 1, 0.0F);
        this.setRotateAngle(corner4, 0.7853981633974483F, 0.0F, 0.0F);
        this.trigger2 = new ModelRenderer(this, 16, 32);
        this.trigger2.setRotationPoint(-1.8F, 15.5F, -1.0F);
        this.trigger2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.barrel2 = new ModelRenderer(this, 0, 8);
        this.barrel2.setRotationPoint(-10.0F, 12.0F, -3.0F);
        this.barrel2.addBox(0.0F, 0.0F, 0.0F, 30, 3, 5, 0.0F);
        this.corner1 = new ModelRenderer(this, 0, 16);
        this.corner1.setRotationPoint(-9.5F, 12.0F, 0.3F);
        this.corner1.addBox(0.0F, 0.0F, 0.0F, 29, 1, 1, 0.0F);
        this.setRotateAngle(corner1, 0.7853981633974483F, 0.0F, 0.0F);
        this.trigger = new ModelRenderer(this, 16, 30);
        this.trigger.setRotationPoint(-1.0F, 17.5F, -1.0F);
        this.trigger.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.shoulderRest1 = new ModelRenderer(this, 44, 21);
        this.shoulderRest1.setRotationPoint(2.3F, 16.4F, -1.0F);
        this.shoulderRest1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(shoulderRest1, 0.0F, 0.0F, -0.3141592653589793F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.ring2.render(f5);
        this.handle.render(f5);
        this.shoulderRest2.render(f5);
        this.box.render(f5);
        this.scope2.render(f5);
        this.ring1.render(f5);
        this.corner3.render(f5);
        this.handleBase.render(f5);
        this.ring3.render(f5);
        this.scope1.render(f5);
        this.corner2.render(f5);
        this.barrel1.render(f5);
        this.corner4.render(f5);
        this.trigger2.render(f5);
        this.barrel2.render(f5);
        this.corner1.render(f5);
        this.trigger.render(f5);
        this.shoulderRest1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
