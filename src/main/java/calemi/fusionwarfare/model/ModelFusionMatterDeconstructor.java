package calemi.fusionwarfare.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFusionMatterDeconstructor extends ModelBase {
	
    public ModelRenderer shaft;
    public ModelRenderer head;
    public ModelRenderer base;
    public ModelRenderer ring1;
    public ModelRenderer ring2;
    public ModelRenderer ring3;
    public ModelRenderer ring4;
    public ModelRenderer ring5;
    public ModelRenderer ring6;
    public ModelRenderer end1;
    public ModelRenderer end2;

    public ModelFusionMatterDeconstructor() {
    	
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        this.ring4 = new ModelRenderer(this, 12, 6);
        this.ring4.setRotationPoint(3.0F, 2.5F, -2.0F);
        this.ring4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.ring6 = new ModelRenderer(this, 4, 10);
        this.ring6.setRotationPoint(-1.0F, 13.0F, -1.0F);
        this.ring6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.ring5 = new ModelRenderer(this, 4, 10);
        this.ring5.setRotationPoint(-1.0F, 15.0F, -1.0F);
        this.ring5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.ring3 = new ModelRenderer(this, 12, 6);
        this.ring3.setRotationPoint(1.2F, 2.5F, -2.0F);
        this.ring3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.ring2 = new ModelRenderer(this, 12, 6);
        this.ring2.setRotationPoint(-2.2F, 2.5F, -2.0F);
        this.ring2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.base = new ModelRenderer(this, 0, 6);
        this.base.setRotationPoint(-1.0F, 22.0F, -1.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-5.0F, 3.0F, -1.5F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 10, 3, 3, 0.0F);
        this.end2 = new ModelRenderer(this, 4, 13);
        this.end2.setRotationPoint(4.4F, 3.5F, -1.0F);
        this.end2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.ring1 = new ModelRenderer(this, 12, 6);
        this.ring1.setRotationPoint(-4.0F, 2.5F, -2.0F);
        this.ring1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 10);
        this.shaft.setRotationPoint(-0.5F, 6.0F, -0.5F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.end1 = new ModelRenderer(this, 4, 13);
        this.end1.setRotationPoint(-5.5F, 3.5F, -1.0F);
        this.end1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int red, int green, int blue) { 
      
    	GL11.glColor3f(red, green, blue);
    	this.ring1.render(f5);
        this.ring2.render(f5);
        this.ring3.render(f5);
    	this.ring4.render(f5);
    	this.ring5.render(f5);
        this.ring6.render(f5);
        
        this.end1.render(f5);
        this.end2.render(f5);  
        this.base.render(f5);
        this.head.render(f5);             
        this.shaft.render(f5);        
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
