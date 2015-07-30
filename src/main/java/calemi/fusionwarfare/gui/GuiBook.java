package calemi.fusionwarfare.gui;

import java.awt.event.KeyListener;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.book.IPage;
import calemi.fusionwarfare.gui.button.GuiArrowButton;
import calemi.fusionwarfare.gui.button.GuiFusionButton;
import calemi.fusionwarfare.gui.button.GuiTabButton;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitBookPages;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class GuiBook extends GuiScreenBase {

	private GuiArrowButton backButton;
	private GuiArrowButton forwardButton;
	private GuiFusionButton doneButton;
	
	private GuiTabButton[] tabs = new GuiTabButton[3];
	
	public int currentPage;
	
	private EntityPlayer player;
	
	public GuiBook(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public String getGuiTextures() {
		return "book_textures";
	}

	@Override
	public int getGuiSizeX() {
		return 256;
	}

	@Override
	public int getGuiSizeY() {
		return 159;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		backButton = new GuiArrowButton(0, getScreenX() + 11, getScreenY() + 138, true);
		buttonList.add(backButton);
		forwardButton = new GuiArrowButton(1, getScreenX() + 227, getScreenY() + 138, false);
		buttonList.add(forwardButton);
		doneButton = new GuiFusionButton(2, getScreenX() + 103, getScreenY() + 160, 50, "Done");
		buttonList.add(doneButton);
		
		for (int id = 0; id < tabs.length; id++) {
			tabs[id] = new GuiTabButton(3 + id, getScreenX() - 20, getScreenY() + (id * 23) + 5);
			buttonList.add(tabs[id]);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == doneButton.id) {
			player.closeScreen();
		}
		
		if (button.id == 0 && currentPage > 0) {
			currentPage--;
		}

		if (button.id == 1 && currentPage < InitBookPages.getList().size() - 1) {
			currentPage++;
		}
		
		if (button.id == tabs[0].id) {
			currentPage = 0;
		}
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		drawCenteredString(fontRendererObj, currentPage + 1 + "/" + InitBookPages.getList().size(), getScreenX() + getGuiSizeX() / 2, getScreenY() - 10, 0xffffff);
		
		if (getCurrentPage() != null) {
			
			getCurrentPage().render(this, getScreenX(), getScreenY());	
		}
		
		//ItemStack drawingStack = new ItemStack(InitBlocks.network_controller_3);
		
		//drawLimitedCenteredStringBox(drawingStack.getDisplayName(), 64, 80, 100);
	
		//drawParagraphString("", 137, 10, 125);
		
		//GL11.glPushMatrix();
        //GL11.glTranslated(0, 4, 0);
            
        //GL11.glTranslated(0, 35, 0);
        
        //GL11.glPopMatrix();
	}

	private IPage getCurrentPage() {
		return InitBookPages.getList().get(currentPage);
	}

	private IPage getCurrentPage(int index) {
		try {
			return InitBookPages.getList().get(currentPage + index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}	
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {}
}
