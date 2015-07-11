package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class GuiEnergyTank extends GuiContainerBase {

	private String name;
	
	public GuiEnergyTank(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
		this.name = name;
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}

	@Override
	public String getGuiTextures() {
		return "energy_tank";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawLargeFuelBar(63, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
	}
}
	
	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/energy_tank.png");
		
	private EntityPlayer player;
	private TileEntityBase tileentity;
	private String name;
	
	public GuiEnergyTank(EntityPlayer player, TileEntityBase tileentity, String name) {
		super(new ContainerEnergyTank(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.name = name;
		ySize += 15;
	}	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
			
		updateScreen();
		
		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 181);
        
        //(teleX, teleY, originX, originY, increaseWidth, increaseHeight)
        
        //Fuel Bar
        int i1 = tileentity.getCurrentEnergyScaled(61);
        this.drawTexturedModalRect(k + 63, l + 81 - i1, 176, 60 - i1 + 1, 49, i1);        
        
        this.drawTexturedModalRect(k + 63, l + 26, 176, 61, 12, 50);
		        
        drawCreativeTabHoveringText("Energy: " + tileentity.energy  + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);       
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();
				
		mc.fontRenderer.drawString(name, 8, 6, 4210752);
	}
}*/