package calemi.fusionwarfare.model;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.util.EnumColorUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAuraBase extends ModelBase {
	
    public ModelRenderer base;
    public ModelRenderer top;
    public ModelRenderer ball;
    public ModelRenderer middle;

    public ModelAuraBase() {
    	
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

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float r, int red, int green, int blue, boolean layer) { 
       
        this.middle.render(f5);
        this.base.render(f5);                
 
        GL11.glPushMatrix();
                
        float s = 1.0F / 255.0F;
        
        if (layer) GL11.glColor3f(s * red, s * green, s * blue);
        this.ball.render(f5); 
        this.top.render(f5);
        
        GL11.glPopMatrix();
        
        ball.rotateAngleY = (float) Math.toRadians(r);
        ball.rotateAngleX = (float) Math.toRadians(r / 2);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
