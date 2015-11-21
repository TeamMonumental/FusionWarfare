package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNetworkBeacon extends ModelBase {
    
	public ModelRenderer base;
    public ModelRenderer shaft;
    public ModelRenderer ring;
    public ModelRenderer top;

    public ModelNetworkBeacon() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-4.0F, 23.0F, -4.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 20);
        this.shaft.setRotationPoint(-1.0F, 15.0F, -1.0F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.top = new ModelRenderer(this, 0, 14);
        this.top.setRotationPoint(-1.5F, 12.0F, -1.5F);
        this.top.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.ring = new ModelRenderer(this, 0, 9);
        this.ring.setRotationPoint(-2.0F, 18.0F, -2.0F);
        this.ring.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.base.render(f5);
        this.shaft.render(f5);
        this.top.render(f5);
        this.ring.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
