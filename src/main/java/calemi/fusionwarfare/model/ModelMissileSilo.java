package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMissileSilo extends ModelBase {
    
	public ModelRenderer base1;
    public ModelRenderer base2;
    public ModelRenderer base3;
    public ModelRenderer base4;
    public ModelRenderer door;
    public ModelRenderer ring1;
    public ModelRenderer ring2;
    public ModelRenderer ring3;
    public ModelRenderer ring4;

    public ModelMissileSilo() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.door = new ModelRenderer(this, 0, 88);
        this.door.setRotationPoint(-10.0F, 22.5F, -10.0F);
        this.door.addBox(0.0F, 0.0F, 0.0F, 20, 0, 20, 0.0F);
        this.ring2 = new ModelRenderer(this, 80, 0);
        this.ring2.setRotationPoint(-12.0F, 21.0F, -8.0F);
        this.ring2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 20, 0.0F);
        this.ring1 = new ModelRenderer(this, 80, 21);
        this.ring1.setRotationPoint(8.0F, 21.0F, -12.0F);
        this.ring1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 20, 0.0F);
        this.base4 = new ModelRenderer(this, 0, 74);
        this.base4.setRotationPoint(-20.0F, 22.0F, 8.0F);
        this.base4.addBox(0.0F, 0.0F, 0.0F, 28, 2, 12, 0.0F);
        this.base3 = new ModelRenderer(this, 0, 60);
        this.base3.setRotationPoint(-8.0F, 22.0F, -20.0F);
        this.base3.addBox(0.0F, 0.0F, 0.0F, 28, 2, 12, 0.0F);
        this.base1 = new ModelRenderer(this, 0, 0);
        this.base1.setRotationPoint(-20.0F, 22.0F, -20.0F);
        this.base1.addBox(0.0F, 0.0F, 0.0F, 12, 2, 28, 0.0F);
        this.ring4 = new ModelRenderer(this, 80, 47);
        this.ring4.setRotationPoint(-8.0F, 21.0F, 8.0F);
        this.ring4.addBox(0.0F, 0.0F, 0.0F, 20, 1, 4, 0.0F);
        this.base2 = new ModelRenderer(this, 0, 30);
        this.base2.setRotationPoint(8.0F, 22.0F, -8.0F);
        this.base2.addBox(0.0F, 0.0F, 0.0F, 12, 2, 28, 0.0F);
        this.ring3 = new ModelRenderer(this, 80, 42);
        this.ring3.setRotationPoint(-12.0F, 21.0F, -12.0F);
        this.ring3.addBox(0.0F, 0.0F, 0.0F, 20, 1, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.door.render(f5);
        this.ring2.render(f5);
        this.ring1.render(f5);
        this.base4.render(f5);
        this.base3.render(f5);
        this.base1.render(f5);
        this.ring4.render(f5);
        this.base2.render(f5);
        this.ring3.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
