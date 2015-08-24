package calemi.fusionwarfare.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import calemi.fusionwarfare.model.ModelFusionMatterDeconstructor;
import calemi.fusionwarfare.util.EnumColorUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRenderFusionMatterDeconstructor implements IItemRenderer {

	private final ModelFusionMatterDeconstructor model = new ModelFusionMatterDeconstructor();
	public final ResourceLocation texture;
	
	public ItemRenderFusionMatterDeconstructor() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/fusion_matter_deconstructor.png");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
			
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(0, 1.7F, 0);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(0.4F, 1.4F, -0.1F);
			GL11.glRotatef(25, 0, 0, 1);
			GL11.glRotatef(0, 0, 1, 0);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glRotatef(40, 0, 0, 1);
			GL11.glRotatef(-4F, 0, 1, 0);
			GL11.glTranslatef(0.75F, 1.35F, -0.05F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}	
		
		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glRotatef(-40, 0, 0, 1);
			GL11.glRotatef(15, 0, 1, 0);
			GL11.glRotatef(-60, 1, 0, 0);
			GL11.glTranslatef(0, 0.9F, 0.1F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}
		
		render(is);
	}
	
	public EnumColorUtil getEnumColor(ItemStack is) {
				
		NBTTagCompound nbt = ((ItemFusionMatterDeconstructor)is.getItem()).getNBT(is);
		
		if (nbt.hasKey("player")) {		
							
			EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(nbt.getString("player"));
				
			if (player != null && player.getTeam() != null) {
				
				return EnumColorUtil.getColorByPrefix(((ScorePlayerTeam)player.getTeam()).getColorPrefix());					
			}
		}
			
		return EnumColorUtil.AQUA;			
	}
	
	private void render(ItemStack is) {
		
		GL11.glPushMatrix();
		
		GL11.glRotatef(180, 1, 0, 0);
		
		EnumColorUtil color = getEnumColor(is);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, color.red, color.green, color.blue);
		
		GL11.glPopMatrix();
	}
}