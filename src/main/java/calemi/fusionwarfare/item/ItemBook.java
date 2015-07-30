package calemi.fusionwarfare.item;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.gui.GuiBook;
import calemi.fusionwarfare.gui.GuiTwoInputsRecipe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.GuiBackupFailed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBook extends ItemBase {

	public ItemBook() {
		super("book");
	}

	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {
		FMLClientHandler.instance().displayGuiScreen(p, new GuiBook(p));
        return is;
    }	
}
