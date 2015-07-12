package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemArmorBase extends ItemArmor {
	
	private String imagePath;
	private String imageKey;

	public ItemArmorBase(String imagePath, String imageKey, ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(armorMaterial, renderIndex, armorType);
		this.imagePath = imagePath;
		this.imageKey = imageKey;
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return Reference.MOD_ID + ":textures/models/armor/" + imageKey + "_" + (armorType == 2 ? 2 : 1) + ".png";
	}
		
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

		if (world.isRemote) {
			
			/*if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode())) {
				
				player.capabilities.setPlayerWalkSpeed(15.0F);
				player.motionY += 0.2D;
				player.fallDistance = 0;
				FusionWarfare.network.sendToServer(new ServerPacketHandler("updatePos%" + player.posX + "%" + player.posY + "%" + player.posZ));				
			}
			
			if (player.onGround) {
				player.capabilities.setPlayerWalkSpeed(10.0F);
			}*/
		}		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tools/" + imagePath);
	}
}
