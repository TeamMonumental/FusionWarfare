package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerBase;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.inventory.ContainerReactorCore;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityAdvancedHydroReactorCore;

public class GuiReactorCore extends GuiContainerBase {

	private String name;
	
	public GuiReactorCore(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerReactorCore(player, tileEntity), player, tileEntity);
		this.name = name;
	}

	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "reactor_core";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		if (tileEntity instanceof TileEntityAdvancedHydroReactorCore) {
        	drawRightInfoTextBar("Capsules: " + ((TileEntityAdvancedHydroReactorCore)tileEntity).capsules + "/4", 0);
        }		
		
		drawLargeFuelBar(63, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
	}
}
	
	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/reactor_core.png");
		
	private EntityPlayer player;
	private TileEntityBase tileentity;
	private String name;

	public GuiReactorCore(EntityPlayer player, TileEntityBase tileentity, String name) {
		super(new ContainerReactorCore(player, tileentity));
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
		        
        drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
        
        if (tileentity instanceof TileEntityAdvancedHydroReactorCore) {
        	drawCreativeTabHoveringText("Capsules: " + ((TileEntityAdvancedHydroReactorCore)tileentity).capsules + "/4", k + 79 + 90, l + 18 + 20);        	        	
        }
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();
				
		mc.fontRenderer.drawString(name, 8, 6, 4210752);
	}
}*/