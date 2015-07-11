package calemi.fusionwarfare.util.missile;

import net.minecraftforge.client.IItemRenderer;
import calemi.fusionwarfare.util.explosion.BlastEvent;

public class MissileType {

	public PackedModel packedModel;
	public IItemRenderer itemRenderer;
	public BlastEvent event;
	public String name;
	
	public MissileType(PackedModel packedModel, IItemRenderer itemRenderer, BlastEvent event, String name) {
		this.packedModel = packedModel;
		this.itemRenderer = itemRenderer;
		this.event = event;
		this.name = name;
	}
	
	public String getItemName() {		
		return name + "_missile_" + event.tier;
	}
}
