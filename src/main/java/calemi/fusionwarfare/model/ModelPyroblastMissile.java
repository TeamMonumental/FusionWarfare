package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPyroblastMissile extends ModelBase {
    public ModelRenderer shaft;
    public ModelRenderer cone1;
    public ModelRenderer cone2;
    public ModelRenderer fin1;
    public ModelRenderer fin2;
    public ModelRenderer fin3;
    public ModelRenderer fin4;
    public ModelRenderer head;
    public ModelRenderer base;
    public ModelRenderer fire1;
    public ModelRenderer fire2;
    public ModelRenderer fire3;
    public ModelRenderer fire4;
    public ModelRenderer con2;
    public ModelRenderer con3;
    public ModelRenderer con1;
    public ModelRenderer con3_1;

    public ModelPyroblastMissile() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.fin1 = new ModelRenderer(this, 54, 0);
        this.fin1.setRotationPoint(1.0F, 12.0F, -1.0F);
        this.fin1.addBox(0.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
        this.setRotateAngle(fin1, 0.0F, 0.0F, -0.2792526803190927F);
        this.cone2 = new ModelRenderer(this, 0, 51);
        this.cone2.setRotationPoint(-1.0F, -32.0F, -1.0F);
        this.cone2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.fire1 = new ModelRenderer(this, 68, 0);
        this.fire1.setRotationPoint(-2.0F, -6.0F, -6.0F);
        this.fire1.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 0);
        this.shaft.setRotationPoint(-2.0F, -16.0F, -2.0F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 4, 25, 4, 0.0F);
        this.fire4 = new ModelRenderer(this, 68, 0);
        this.fire4.setRotationPoint(-2.0F, -6.0F, 2.0F);
        this.fire4.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.con2 = new ModelRenderer(this, 84, 0);
        this.con2.setRotationPoint(-2.0F, -12.0F, -1.0F);
        this.con2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 2, 0.0F);
        this.setRotateAngle(con2, 0.0F, 0.0F, 0.45378560551852565F);
        this.fire2 = new ModelRenderer(this, 68, 0);
        this.fire2.setRotationPoint(2.0F, -6.0F, -2.0F);
        this.fire2.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.fin4 = new ModelRenderer(this, 40, 0);
        this.fin4.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.fin4.addBox(0.0F, 0.0F, -5.0F, 2, 10, 5, 0.0F);
        this.setRotateAngle(fin4, -0.2792526803190927F, 0.0F, 0.0F);
        this.cone1 = new ModelRenderer(this, 0, 43);
        this.cone1.setRotationPoint(-2.0F, -28.0F, -2.0F);
        this.cone1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.fire3 = new ModelRenderer(this, 68, 0);
        this.fire3.setRotationPoint(-6.0F, -6.0F, -2.0F);
        this.fire3.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.fin3 = new ModelRenderer(this, 54, 0);
        this.fin3.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.fin3.addBox(-5.0F, 0.0F, 0.0F, 5, 10, 2, 0.0F);
        this.setRotateAngle(fin3, 0.0F, 0.0F, 0.2792526803190927F);
        this.head = new ModelRenderer(this, 0, 29);
        this.head.setRotationPoint(-3.0F, -24.0F, -3.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6, 0.0F);
        this.base = new ModelRenderer(this, 16, 0);
        this.base.setRotationPoint(-3.0F, 9.0F, -3.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 6, 12, 6, 0.0F);
        this.con3_1 = new ModelRenderer(this, 94, 0);
        this.con3_1.setRotationPoint(-1.0F, -11.0F, -1.0F);
        this.con3_1.addBox(0.0F, 0.0F, 0.0F, 2, 7, 3, 0.0F);
        this.setRotateAngle(con3_1, 0.45378560551852565F, 0.0F, 0.0F);
        this.fin2 = new ModelRenderer(this, 40, 0);
        this.fin2.setRotationPoint(-1.0F, 12.0F, 1.0F);
        this.fin2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 5, 0.0F);
        this.setRotateAngle(fin2, 0.2792526803190927F, 0.0F, 0.0F);
        this.con3 = new ModelRenderer(this, 94, 0);
        this.con3.setRotationPoint(-1.0F, -12.0F, -2.0F);
        this.con3.addBox(0.0F, 0.0F, 0.0F, 2, 7, 3, 0.0F);
        this.setRotateAngle(con3, -0.45378560551852565F, 0.0F, 0.0F);
        this.con1 = new ModelRenderer(this, 84, 0);
        this.con1.setRotationPoint(-1.0F, -11.0F, -1.0F);
        this.con1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 2, 0.0F);
        this.setRotateAngle(con1, 0.0F, 0.0F, -0.45378560551852565F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.fin1.render(f5);
        this.cone2.render(f5);
        this.fire1.render(f5);
        this.shaft.render(f5);
        this.fire4.render(f5);
        this.con2.render(f5);
        this.fire2.render(f5);
        this.fin4.render(f5);
        this.cone1.render(f5);
        this.fire3.render(f5);
        this.fin3.render(f5);
        this.head.render(f5);
        this.base.render(f5);
        this.con3_1.render(f5);
        this.fin2.render(f5);
        this.con3.render(f5);
        this.con1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
