package calemi.fusionwarfare.init;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.book.BookStrings;
import calemi.fusionwarfare.book.StackInfoPage;
import calemi.fusionwarfare.book.IPage;
import calemi.fusionwarfare.book.ImagePage;
import calemi.fusionwarfare.book.InfoPage;
import net.minecraft.item.ItemStack;

public class InitBookPages {

	private static List<IPage> list = new ArrayList<IPage>();
	
	private static void add(IPage page) {		
		list.add(page);		
	}
	
	public static void init() {
		
		add(new ImagePage("logo", BookStrings.DESC_1, BookStrings.DESC_2));
		add(new InfoPage(BookStrings.DESC_3, ""));
		
		add(new StackInfoPage("A thing.", new ItemStack(InitItems.infused_crystal), false, 0, 0, 0, 0, 0));
		add(new StackInfoPage("A thing.", new ItemStack(InitBlocks.infused_crystal_ore), true, 0, 0, 0, 0, 0));
	}
	
	public static List<IPage> getList() {
		return list;
	}
}
