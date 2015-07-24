package calemi.fusionwarfare.packet;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFusionGatlingGun;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.GunUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerPacketHandler implements IMessage {

	private String text;

	public ServerPacketHandler() {
	}

	public ServerPacketHandler(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}

	public static class Handler implements IMessageHandler<ServerPacketHandler, IMessage> {

		@Override
		public IMessage onMessage(ServerPacketHandler message, MessageContext ctx) {

			//System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));

			String[] data = message.text.split("%");
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;

			if (data[0].equalsIgnoreCase("addEXP")) {

				int expAmount = Integer.parseInt(data[1]);	
				
				int x = Integer.parseInt(data[2]);
				int y = Integer.parseInt(data[3]);
				int z = Integer.parseInt(data[4]);
				
				TileEntityEXPFabricator tileEntity = (TileEntityEXPFabricator)player.worldObj.getTileEntity(x, y, z);
				
				int energyCost = expAmount * 50;
				
				if (tileEntity != null) {
					
					if (EnergyUtil.canSubtractEnergy(tileEntity, energyCost)) {		
						EnergyUtil.subtractEnergy(tileEntity, energyCost);
						player.addExperience(expAmount);
					}
					
					else {
						FusionWarfare.network.sendTo(new ClientPacketHandler("error%" + (energyCost - tileEntity.energy)), (EntityPlayerMP) player);
					}
				}				
			}
			
			if (data[0].equalsIgnoreCase("currentDelay")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				TileEntityMissileSiloCore tileEntity = (TileEntityMissileSiloCore)player.worldObj.getTileEntity(x, y, z);
				
				tileEntity.update();
				
				if (tileEntity.currentDelay >= 5) tileEntity.currentDelay = 0;
				else tileEntity.currentDelay++;				
			}
			
			if (data[0].equalsIgnoreCase("sprayMode")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				TileEntityMissileSiloCore tileEntity = (TileEntityMissileSiloCore)player.worldObj.getTileEntity(x, y, z);
				
				tileEntity.update();
				
				if (tileEntity.sprayMode) tileEntity.sprayMode = false;
				else tileEntity.sprayMode = true;
			}
			
			if (data[0].equalsIgnoreCase("shoot")) {
								
				ItemFusionGun item = (ItemFusionGun)player.getCurrentEquippedItem().getItem();
				
				GunUtil.shootBullet(player.worldObj, player.getCurrentEquippedItem(), player);
			}

			return null;
		}
	}
}