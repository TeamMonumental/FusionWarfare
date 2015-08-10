package calemi.fusionwarfare.item.tool;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.item.ItemRocketLauncher;
import calemi.fusionwarfare.proxy.ClientProxy;
import calemi.fusionwarfare.renderer.RenderNetworkCable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemScubaGear extends ItemArmorBase {

	private boolean canRemove = false;

	public ItemScubaGear(String imagePath, String imageKey, ArmorMaterial armorMaterial, int renderIndex, int armorType) {
		super(imagePath, imageKey, "", armorMaterial, renderIndex, armorType, false);
	}

	@Override
	public boolean hasEffect(ItemStack p_77636_1_) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

		boolean hasFullSuit = true;

		for (int i = 0; i < 4; i++) {

			if (player.inventory.armorInventory[i] == null || !(player.inventory.armorInventory[i].getItem() instanceof ItemScubaGear)) {

				hasFullSuit = false;
			}
		}

		if (hasFullSuit) {

			if (player.isInWater()) {

				boolean nonePressed = true;

				float rotationPitch = player.rotationPitch;
				float rotationYaw = player.rotationYaw;

				if (world.isRemote) {
					
					if (Minecraft.getMinecraft().currentScreen == null) {

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode())) {

							double motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
							double motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);

							motionX *= 0.01F;
							motionZ *= 0.01F;

							player.addVelocity(motionX, 0, motionZ);
						}

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode())) {

							rotationYaw -= 90;

							double motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
							double motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);

							motionX *= 0.01F;
							motionZ *= 0.01F;

							player.addVelocity(motionX, 0, motionZ);
						}

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode())) {

							rotationYaw += 90;

							double motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
							double motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);

							motionX *= 0.01F;
							motionZ *= 0.01F;

							player.addVelocity(motionX, 0, motionZ);
						}

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode())) {

							double motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
							double motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);

							motionX *= 0.01F;
							motionZ *= 0.01F;

							player.addVelocity(-motionX, 0, -motionZ);
						}

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode())) {

							player.setVelocity(player.motionX, 0.3F, player.motionZ);
							nonePressed = false;
						}

						if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) {

							player.setVelocity(player.motionX, -0.3F, player.motionZ);
							nonePressed = false;
						}
					}

					if (nonePressed) {
						player.setVelocity(player.motionX, 0, player.motionZ);
					}
				}
			}

			canRemove = true;

			player.addPotionEffect(new PotionEffect(13, 20, 0, true));
			player.addPotionEffect(new PotionEffect(16, 300, 0, true));
		}

		else if (canRemove) {

			canRemove = false;

			player.removePotionEffect(13);
			player.removePotionEffect(16);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase e, ItemStack is, int armorSlot) {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		ModelBiped armorModel = ClientProxy.armorModels.get(this);

		if (armorModel != null) {

			armorModel.bipedHead.showModel = armorSlot == 0;
			armorModel.bipedHeadwear.showModel = false;
			armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
			armorModel.bipedRightArm.showModel = armorSlot == 1;
			armorModel.bipedLeftArm.showModel = armorSlot == 1;
			armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
			armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

			armorModel.isSneak = e.isSneaking();
			armorModel.isRiding = e.isRiding();
			armorModel.isChild = e.isChild();

			armorModel.heldItemRight = 0;
			armorModel.aimedBow = false;

			EntityPlayer player = (EntityPlayer) e;

			ItemStack heldItem = player.getEquipmentInSlot(0);

			if (heldItem != null) {

				armorModel.heldItemRight = 1;

				if (heldItem.getItem() instanceof ItemFusionGun || heldItem.getItem() instanceof ItemRocketLauncher) {
					armorModel.aimedBow = true;
				}

				if (player.getItemInUseCount() > 0) {

					EnumAction enumaction = heldItem.getItemUseAction();

					if (enumaction == EnumAction.bow) {
						armorModel.aimedBow = true;
					}

					else if (enumaction == EnumAction.block) {
						armorModel.heldItemRight = 3;
					}
				}
			}
		}

		return armorModel;
	}
}