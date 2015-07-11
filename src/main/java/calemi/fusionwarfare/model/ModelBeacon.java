package calemi.fusionwarfare.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeacon extends ModelBase {
    
	public ModelRenderer base;
    public ModelRenderer shaft;
    public ModelRenderer top;
    public ModelRenderer ring2;
    public ModelRenderer ring1;

    public ModelBeacon() {
    	
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.top = new ModelRenderer(this, 0, 38);
        this.top.setRotationPoint(-1.5F, -1.0F, -1.5F);
        this.top.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-7.0F, 22.0F, -7.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 14, 2, 14, 0.0F);
        this.shaft = new ModelRenderer(this, 0, 16);
        this.shaft.setRotationPoint(-1.0F, 2.0F, -1.0F);
        this.shaft.addBox(0.0F, 0.0F, 0.0F, 2, 20, 2, 0.0F);
        this.ring1 = new ModelRenderer(this, 8, 31);
        this.ring1.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.ring1.addBox(-7.0F, 0.0F, -2.0F, 14, 1, 4, 0.0F);
        this.ring2 = new ModelRenderer(this, 8, 16);
        this.ring2.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.ring2.addBox(-2.0F, 0.0F, -7.0F, 4, 1, 14, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float r) { 
        this.top.render(f5);
        this.base.render(f5);
        this.shaft.render(f5);
        
        GL11.glPushMatrix();
        GL11.glRotatef(r, 0, 1, 0);
        this.ring1.render(f5);
        GL11.glPopMatrix(); 
        
        GL11.glPushMatrix();
        GL11.glRotatef(-r, 0, 1, 0);
        this.ring2.render(f5);
        GL11.glPopMatrix();        
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
