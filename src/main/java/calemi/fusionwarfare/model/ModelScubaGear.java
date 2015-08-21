package calemi.fusionwarfare.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScubaGear extends ModelBiped {
	
    public ModelRenderer side1;
    public ModelRenderer side2;
    public ModelRenderer tank1;
    public ModelRenderer tank2;
    public ModelRenderer tankbase;
    public ModelRenderer tanktube1;
    public ModelRenderer tanktube2;
    public ModelRenderer mouthpiece;
    public ModelRenderer flipper1;
    public ModelRenderer flipper2;

    public ModelScubaGear(float expand) {
    	
    	super(expand, 0, 64, 64);
    	
        this.tanktube2 = new ModelRenderer(this, 40, 32);
        this.tanktube2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tanktube2.addBox(-2.5F, 1.0F, 2.0F, 1, 1, 2, 0.0F);
        
        this.side1 = new ModelRenderer(this, 0, 32);
        this.side1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.side1.addBox(4.0F, -5.0F, -3.0F, 1, 5, 6, 0.0F);
        
        this.flipper1 = new ModelRenderer(this, 8, 43);
        this.flipper1.setRotationPoint(0F, 0F, 0F);
        this.flipper1.addBox(-2.0F, 11.5F, -8.0F, 4, 1, 10, 0.0F);
                
        this.flipper2 = new ModelRenderer(this, 8, 43);
        this.flipper2.setRotationPoint(0F, 0F, 0F);
        this.flipper2.addBox(-2.0F, 11.5F, -8.0F, 4, 1, 10, 0.0F);
        
        this.tanktube1 = new ModelRenderer(this, 40, 32);
        this.tanktube1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tanktube1.addBox(1.5F, 1.0F, 2.0F, 1, 1, 2, 0.0F);
        
        this.tankbase = new ModelRenderer(this, 14, 32);
        this.tankbase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tankbase.addBox(-4.0F, 2.0F, 2.0F, 8, 9, 2, 0.0F);
        
        this.mouthpiece = new ModelRenderer(this, 0, 43);
        this.mouthpiece.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.mouthpiece.addBox(0.0F, -3.0F, -5.0F, 3, 3, 1, 0.0F);        
        this.setRotateAngle(mouthpiece, 0.0F, 0.0F, -0.7853981633974483F);
        
        this.tank2 = new ModelRenderer(this, 34, 32);
        this.tank2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tank2.addBox(-3.0F, 2.0F, 4.0F, 2, 9, 1, 0.0F);
        
        this.tank1 = new ModelRenderer(this, 34, 32);
        this.tank1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tank1.addBox(1.0F, 2.0F, 4.0F, 2, 9, 1, 0.0F);
        
        this.side2 = new ModelRenderer(this, 0, 32);
        this.side2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.side2.addBox(-5.0F, -5.0F, -3.0F, 1, 5, 6, 0.0F);
       
        this.bipedHead.addChild(side1);
        this.bipedHead.addChild(side2);
        this.bipedHead.addChild(mouthpiece);
        
        this.bipedBody.addChild(tankbase);
        this.bipedBody.addChild(tank1);
        this.bipedBody.addChild(tank2);
        this.bipedBody.addChild(tanktube1);
        this.bipedBody.addChild(tanktube2);
        
        this.bipedLeftLeg.addChild(flipper1);
        this.bipedRightLeg.addChild(flipper2);
    }   
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
