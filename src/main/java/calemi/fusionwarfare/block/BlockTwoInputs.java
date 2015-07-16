package calemi.fusionwarfare.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;

public class BlockTwoInputs extends BlockBasicMachineBase {

	public EnumRecipeType recipeType;	
	
	public BlockTwoInputs(String imagePath, EnumRecipeType recipeType, int guiID, String topImage, String sideImage1, String sideImage2, String sideImage3, String sideImage4) {
		super(imagePath, null, guiID, recipeType == EnumRecipeType.INFUSION_FOUNDRY, true, topImage, "steel_casing", sideImage1, sideImage2, sideImage3, sideImage4, false);
		this.recipeType = recipeType;
	}
		
	public BlockTwoInputs(String imagePath, EnumRecipeType recipeType, int guiID, String topImage, String sideImage) {
		this(imagePath, recipeType, guiID, topImage, sideImage, sideImage, sideImage, sideImage);
	}	
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
			
		TileEntityTwoInputs tileEntity = new TileEntityTwoInputs();
		
		tileEntity.recipeType = recipeType;
		
		return tileEntity;
	}
}
