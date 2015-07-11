package calemi.fusionwarfare.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurbine extends ModelBase {
    public ModelRenderer shaft;
    public ModelRenderer blade1;
    public ModelRenderer blade2;
    public ModelRenderer blade3;

    public ModelTurbine() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.blade2 = new ModelRenderer(this, 0, 0);
        this.blade2.setRotationPoint(0.0F, 0F, -15.0F);
        this.blade2.addBox(0.0F, -2.0F, 0.0F, 30, 4, 1, 0.0F);
        this.setRotateAngle(blade2, 0.2617993877991494F, 0.0F, 0.0F);
        this.blade1 = new ModelRenderer(this, 0, 0);
        this.blade1.setRotationPoint(0.0F, 0F, -15.0F);
        this.blade1.addBox(0.0F, -2.0F, 0.0F, 30, 4, 1, 0.0F);
        this.setRotateAngle(blade1, 0.2617993877991494F, 0.0F, 4.1887902047863905F);
        this.shaft = new ModelRenderer(this, 0, 5);
        this.shaft.setRotationPoint(0.0F, 0F, -16.0F);
        this.shaft.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(shaft, 0.0F, 0.0F, -0.5585053606381855F);
        this.blade3 = new ModelRenderer(this, 0, 0);
        this.blade3.setRotationPoint(0.0F, 0F, -15.0F);
        this.blade3.addBox(0.0F, -2.0F, 0.0F, 30, 4, 1, 0.0F);
        this.setRotateAngle(blade3, 0.2617993877991494F, 0.0F, 2.0943951023931953F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float r) { 
       
        GL11.glPushMatrix();
        GL11.glRotatef(r, 0, 0, 1);
        this.shaft.render(f5);
        this.blade1.render(f5);
        GL11.glPopMatrix(); 
        
        GL11.glPushMatrix();
        GL11.glRotatef(r + 120, 0, 0, 1);
        this.blade2.render(f5);
        GL11.glPopMatrix(); 
        
        GL11.glPushMatrix();
        GL11.glRotatef(r + 240, 0, 0, 1);
        this.blade3.render(f5);
        GL11.glPopMatrix(); 
        
        /*shaft.rotateAngleZ = (float) Math.toRadians(r);
        blade1.rotateAngleZ = (float) Math.toRadians(r);
        blade2.rotateAngleZ = (float) Math.toRadians(r + 120);
        blade3.rotateAngleZ = (float) Math.toRadians(r + 240);*/
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}