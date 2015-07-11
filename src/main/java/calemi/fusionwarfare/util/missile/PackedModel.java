package calemi.fusionwarfare.util.missile;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class PackedModel {

	private ModelBase model;
	private ResourceLocation location;
	
	public PackedModel(ModelBase model, String path) {
		this.model = model;
		this.location = new ResourceLocation(Reference.MOD_ID + ":" + path + ".png");
	}	

	public void render() {
		
		Minecraft.getMinecraft().renderEngine.bindTexture(location);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
	}	
}