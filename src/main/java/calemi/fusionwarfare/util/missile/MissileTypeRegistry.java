package calemi.fusionwarfare.util.missile;

import calemi.fusionwarfare.model.ModelBreachingMissile;
import calemi.fusionwarfare.model.ModelEMPMissile;
import calemi.fusionwarfare.model.ModelMissile;
import calemi.fusionwarfare.model.ModelPyroblastMissile;
import calemi.fusionwarfare.renderer.item.ItemRenderBreachingMissile;
import calemi.fusionwarfare.renderer.item.ItemRenderEMPMissile;
import calemi.fusionwarfare.renderer.item.ItemRenderVelocityMissile;
import calemi.fusionwarfare.util.explosion.BreachingEvent;
import calemi.fusionwarfare.util.explosion.DudleyEvent;
import calemi.fusionwarfare.util.explosion.EMPEvent;
import calemi.fusionwarfare.util.explosion.PyroBlastEvent;
import calemi.fusionwarfare.util.explosion.VelocityEvent;

public class MissileTypeRegistry {

	private static String path = "textures/models/missiles/";
	
	public static PackedModel velocity_model_1 = new PackedModel(new ModelMissile(), path + "velocity_missile_1");
	public static PackedModel velocity_model_2 = new PackedModel(new ModelMissile(), path + "velocity_missile_2");
	public static PackedModel velocity_model_3 = new PackedModel(new ModelMissile(), path + "velocity_missile_3");
	
	public static PackedModel breaching_model_1 = new PackedModel(new ModelBreachingMissile(), path + "breaching_missile_1");
	public static PackedModel breaching_model_2 = new PackedModel(new ModelBreachingMissile(), path + "breaching_missile_2");
	public static PackedModel breaching_model_3 = new PackedModel(new ModelBreachingMissile(), path + "breaching_missile_3");
	
	public static PackedModel emp_model_1 = new PackedModel(new ModelEMPMissile(), path + "emp_missile_1");
	public static PackedModel emp_model_2 = new PackedModel(new ModelEMPMissile(), path + "emp_missile_2");
	public static PackedModel emp_model_3 = new PackedModel(new ModelEMPMissile(), path + "emp_missile_3");
	
	public static PackedModel pyroblast_model = new PackedModel(new ModelPyroblastMissile(), path + "pyroblast");
	public static PackedModel splinter_model = new PackedModel(new ModelMissile(), path + "splinter");
	public static PackedModel missile404_model = new PackedModel(new ModelMissile(), path + "missile404");	
	public static PackedModel theEnd_model = new PackedModel(new ModelMissile(), path + "theEnd");		
	public static PackedModel dudley_model = new PackedModel(new ModelMissile(), path + "velocity_missile_1");
	
	//--------------------------------------------------------------------------------------------------------------------------------\\
	
	public static MissileType velocity_1 = new MissileType(velocity_model_1, new ItemRenderVelocityMissile(), new VelocityEvent(1), "velocity");
	public static MissileType velocity_2 = new MissileType(velocity_model_2, new ItemRenderVelocityMissile(), new VelocityEvent(2), "velocity");
	public static MissileType velocity_3 = new MissileType(velocity_model_3, new ItemRenderVelocityMissile(), new VelocityEvent(3), "velocity");
	
	public static MissileType breaching_1 = new MissileType(breaching_model_1, new ItemRenderBreachingMissile(), new BreachingEvent(1), "breaching");
	public static MissileType breaching_2 = new MissileType(breaching_model_2, new ItemRenderBreachingMissile(), new BreachingEvent(2), "breaching");
	public static MissileType breaching_3 = new MissileType(breaching_model_3, new ItemRenderBreachingMissile(), new BreachingEvent(3), "breaching");
	
	public static MissileType emp_1 = new MissileType(emp_model_1, new ItemRenderEMPMissile(), new EMPEvent(1), "emp");
	public static MissileType emp_2 = new MissileType(emp_model_2, new ItemRenderEMPMissile(), new EMPEvent(2), "emp");
	public static MissileType emp_3 = new MissileType(emp_model_3, new ItemRenderEMPMissile(), new EMPEvent(3), "emp");
	
	public static MissileType pyroblast = new MissileType(pyroblast_model, new ItemRenderBreachingMissile(), new PyroBlastEvent(), "pyroblast");
	public static MissileType splinter = new MissileType(splinter_model, new ItemRenderBreachingMissile(), new EMPEvent(1), "splinter");
	public static MissileType missile404 = new MissileType(missile404_model, new ItemRenderBreachingMissile(), new EMPEvent(1), "missile404");
	public static MissileType theEnd = new MissileType(theEnd_model, new ItemRenderBreachingMissile(), new EMPEvent(1), "theEnd");
	public static MissileType dudley = new MissileType(dudley_model, new ItemRenderVelocityMissile(), new DudleyEvent(), "dudley");
}
