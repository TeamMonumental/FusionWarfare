package calemi.fusionwarfare.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;

public class BlockTwoInputs extends BlockBasicMachineBase {

	public final EnumRecipeType recipeType;	
	
	public BlockTwoInputs(String imagePath, EnumRecipeType recipeType) {
		super(imagePath, null);
		this.recipeType = recipeType;
		if (recipeType == EnumRecipeType.INFUSION_FOUNDRY) setDirectional();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {			
		return new TileEntityTwoInputs(recipeType);
	}
}
