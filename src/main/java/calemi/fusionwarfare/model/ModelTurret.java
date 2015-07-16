package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurret extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer top;
    public ModelRenderer ball;
    public ModelRenderer middle;

    public ModelTurret() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.top = new ModelRenderer(this, 30, 0);
        this.top.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.top.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 8, 0.0F);
        this.setRotateAngle(top, 0.0F, 0.7853981633974483F, 0.0F);
        this.middle = new ModelRenderer(this, 0, 14);
        this.middle.setRotationPoint(-2.0F, 12.0F, -2.0F);
        this.middle.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-5.0F, 20.0F, -5.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 10, 4, 10, 0.0F);
        this.ball = new ModelRenderer(this, 16, 14);
        this.ball.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.ball.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float r) { 
        this.top.render(f5);
        this.middle.render(f5);
        this.base.render(f5);
        this.ball.render(f5);
        ball.rotateAngleY = (float) Math.toRadians(r);
        ball.rotateAngleX = (float) Math.toRadians(r / 2);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
