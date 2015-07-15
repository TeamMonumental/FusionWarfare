package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelRocket - Undefined
 * Created using Tabula 5.0.0
 */
public class ModelRocket extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer tip;
    public ModelRenderer shaft;
    public ModelRenderer fin1;
    public ModelRenderer fin2;
    public ModelRenderer fin3;
    public ModelRenderer fin4;

    public ModelRocket() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.fin1 = new ModelRenderer(this, 0, 6);
        this.fin1.setRotationPoint(-4.0F, 1.5F, -0.5F);
        this.fin1.addBox(-4.0F, -1.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(fin1, 0.0F, 0.0F, 0.2792526803190927F);
        this.fin2 = new ModelRenderer(this, 0, 6);
        this.fin2.setRotationPoint(-4.0F, 0.5F, -0.5F);
        this.fin2.addBox(-4.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(fin2, 0.0F, 0.0F, -0.2792526803190927F);
        this.tip = new ModelRenderer(this, 0, 8);
        this.tip.setRotationPoint(6.0F, 0.5F, -0.5F);
        this.tip.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.fin3 = new ModelRenderer(this, 0, 6);
        this.fin3.setRotationPoint(-4.0F, 1.5F, -0.5F);
        this.fin3.addBox(-4.0F, -1.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(fin3, 0.0F, 0.2792526803190927F, 0.0F);
        this.fin4 = new ModelRenderer(this, 0, 6);
        this.fin4.setRotationPoint(-3.8F, 1.5F, -0.5F);
        this.fin4.addBox(-4.0F, -1.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(fin4, 0.0F, -0.2792526803190927F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 4);
        this.shaft.setRotationPoint(-5.0F, 0.5F, -0.5F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.fin1.render(f5);
        this.fin2.render(f5);
        this.tip.render(f5);
        this.fin3.render(f5);
        this.fin4.render(f5);
        this.head.render(f5);
        this.shaft.render(f5);
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
