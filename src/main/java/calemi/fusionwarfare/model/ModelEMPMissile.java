package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * NewModelEMPMissile - Undefined
 * Created using Tabula 5.0.0
 */
public class ModelEMPMissile extends ModelBase {
    public ModelRenderer shaft;
    public ModelRenderer head;
    public ModelRenderer cone1;
    public ModelRenderer cone2;
    public ModelRenderer base;
    public ModelRenderer fin1;
    public ModelRenderer fin2;
    public ModelRenderer fin3;
    public ModelRenderer fin4;
    public ModelRenderer shaft2;
    public ModelRenderer shaft3;
    public ModelRenderer shaft4;
    public ModelRenderer shaft5;
    public ModelRenderer shaft6;
    public ModelRenderer shaft7;
    public ModelRenderer top;
    public ModelRenderer coil1;
    public ModelRenderer coil2;
    public ModelRenderer coil3;
    public ModelRenderer coil4;
    public ModelRenderer top2;

    public ModelEMPMissile() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.top = new ModelRenderer(this, 24, 27);
        this.top.setRotationPoint(-4.0F, -25.0F, -4.0F);
        this.top.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8, 0.0F);
        this.cone1 = new ModelRenderer(this, 0, 43);
        this.cone1.setRotationPoint(-2.0F, -32.0F, -2.0F);
        this.cone1.addBox(0.0F, 0.0F, 0.0F, 4, 7, 4, 0.0F);
        this.fin3 = new ModelRenderer(this, 54, 0);
        this.fin3.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.fin3.addBox(-5.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
        this.setRotateAngle(fin3, 0.0F, 0.0F, 0.2792526803190927F);
        this.shaft3 = new ModelRenderer(this, 16, 18);
        this.shaft3.setRotationPoint(-5.0F, -6.6F, -1.0F);
        this.shaft3.addBox(0.0F, 0.0F, 0.0F, 10, 2, 2, 0.0F);
        this.shaft6 = new ModelRenderer(this, 68, 0);
        this.shaft6.setRotationPoint(-1.0F, -12.0F, -7.0F);
        this.shaft6.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.shaft2 = new ModelRenderer(this, 30, 15);
        this.shaft2.setRotationPoint(-1.0F, -6.6F, -5.0F);
        this.shaft2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 10, 0.0F);
        this.cone2 = new ModelRenderer(this, 0, 54);
        this.cone2.setRotationPoint(-1.0F, -37.0F, -1.0F);
        this.cone2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 29);
        this.head.setRotationPoint(-3.0F, -24.0F, -3.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6, 0.0F);
        this.shaft5 = new ModelRenderer(this, 68, 0);
        this.shaft5.setRotationPoint(-7.0F, -12.0F, -1.0F);
        this.shaft5.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 0);
        this.shaft.setRotationPoint(-2.0F, -16.0F, -2.0F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 4, 25, 4, 0.0F);
        this.shaft4 = new ModelRenderer(this, 68, 0);
        this.shaft4.setRotationPoint(5.0F, -12.0F, -1.0F);
        this.shaft4.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.coil1 = new ModelRenderer(this, 16, 43);
        this.coil1.setRotationPoint(2.0F, -32.0F, -3.0F);
        this.coil1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.top2 = new ModelRenderer(this, 27, 30);
        this.top2.setRotationPoint(-2.5F, -33.0F, -2.5F);
        this.top2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5, 0.0F);
        this.fin1 = new ModelRenderer(this, 54, 0);
        this.fin1.setRotationPoint(1.0F, 12.0F, -1.0F);
        this.fin1.addBox(0.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
        this.setRotateAngle(fin1, 0.0F, 0.0F, -0.2792526803190927F);
        this.shaft7 = new ModelRenderer(this, 68, 0);
        this.shaft7.setRotationPoint(-1.0F, -12.0F, 5.0F);
        this.shaft7.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.fin2 = new ModelRenderer(this, 40, 0);
        this.fin2.setRotationPoint(-1.0F, 12.0F, 1.0F);
        this.fin2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 5, 0.0F);
        this.setRotateAngle(fin2, 0.2792526803190927F, 0.0F, 0.0F);
        this.coil3 = new ModelRenderer(this, 16, 43);
        this.coil3.setRotationPoint(-3.0F, -32.0F, 2.0F);
        this.coil3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.coil4 = new ModelRenderer(this, 16, 43);
        this.coil4.setRotationPoint(2.0F, -32.0F, 2.0F);
        this.coil4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.base = new ModelRenderer(this, 16, 0);
        this.base.setRotationPoint(-3.0F, 9.0F, -3.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 6, 12, 6, 0.0F);
        this.coil2 = new ModelRenderer(this, 16, 43);
        this.coil2.setRotationPoint(-3.0F, -32.0F, -3.0F);
        this.coil2.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.fin4 = new ModelRenderer(this, 40, 0);
        this.fin4.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.fin4.addBox(0.0F, 0.0F, -5.0F, 2, 10, 5, 0.0F);
        this.setRotateAngle(fin4, -0.2792526803190927F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.top.render(f5);
        this.cone1.render(f5);
        this.fin3.render(f5);
        this.shaft3.render(f5);
        this.shaft6.render(f5);
        this.shaft2.render(f5);
        this.cone2.render(f5);
        this.head.render(f5);
        this.shaft5.render(f5);
        this.shaft.render(f5);
        this.shaft4.render(f5);
        this.coil1.render(f5);
        this.top2.render(f5);
        this.fin1.render(f5);
        this.shaft7.render(f5);
        this.fin2.render(f5);
        this.coil3.render(f5);
        this.coil4.render(f5);
        this.base.render(f5);
        this.coil2.render(f5);
        this.fin4.render(f5);
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
