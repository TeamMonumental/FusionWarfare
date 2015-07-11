package calemi.fusionwarfare.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;

public class GuiEXPFabricator extends GuiContainerBase {

	private GuiFusionButton button1;
	private GuiFusionButton button2;
	private GuiFusionButton button3;
	
	private GuiFusionButton[] buttons = new GuiFusionButton[]{button1, button2, button3};
	
	public GuiEXPFabricator(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
	}
	
	@Override
	public void initGui() {		
		super.initGui();
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new GuiFusionButton(i, getScreenX() + 20, (getScreenY() + 21) + (i * 21), 64, "Add " + (10 + (i * 20)) + " EXP");
			buttonList.add(buttons[i]);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {		
		super.actionPerformed(button);
		
		for (int i = 0; i < buttons.length; i++) {
			
			if (button.id == i) {
				FusionWarfare.network.sendToServer(new ServerPacketHandler("addEXP%" + (10 + (i * 20)) + "%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
			}			
		}		
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "exp_fabricator";
	}

	@Override
	public String getGuiTitle() {
		return "EXP Fabricator";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawLargeFuelBar(99, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(99, 80, mouseX, mouseY);
	}
}
	
	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/exp_fabricator.png");
	
	private GuiFusionButton button1;
	private GuiFusionButton button2;
	private GuiFusionButton button3;
	
	private EntityPlayer player;
	private TileEntityEXPFabricator tileentity;

	public GuiEXPFabricator(EntityPlayer player, TileEntityEXPFabricator tileentity) {
		super(new ContainerEnergyTank(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		ySize += 15;
	}	
	
	@Override
	public void initGui() {		
		super.initGui();
		
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		button1 = new GuiFusionButton(0, k + 20, l + 21, 64, "Add 10 EXP");
		buttonList.add(button1);
		
		button2 = new GuiFusionButton(1, k + 20, l + 42, 64, "Add 30 EXP");
		buttonList.add(button2);
		
		button3 = new GuiFusionButton(2, k + 20, l + 63, 64, "Add 50 EXP");
		buttonList.add(button3);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		super.actionPerformed(button);
		
		if (button.id == 0) {
			FusionWarfare.network.sendToServer(new ServerPacketHandler("addEXP%10%" + tileentity.xCoord + "%" + tileentity.yCoord + "%" + tileentity.zCoord));
		}
		
		if (button.id == 1) {
			FusionWarfare.network.sendToServer(new ServerPacketHandler("addEXP%30%" + tileentity.xCoord + "%" + tileentity.yCoord + "%" + tileentity.zCoord));
		}
		
		if (button.id == 2) {		
			FusionWarfare.network.sendToServer(new ServerPacketHandler("addEXP%50%" + tileentity.xCoord + "%" + tileentity.yCoord + "%" + tileentity.zCoord));
		}
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
        this.drawTexturedModalRect(k + 99, l + 81 - i1, 176, 60 - i1 + 1, 49, i1);        
        
        this.drawTexturedModalRect(k + 99, l + 26, 176, 61, 12, 50);
		        
        drawCreativeTabHoveringText("Energy: " + tileentity.energy  + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);       
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();
				
		mc.fontRenderer.drawString("EXP Fabricator", 8, 6, 4210752);
	}
}*/