package calemi.fusionwarfare.packet;

import calemi.fusionwarfare.inventory.ContainerBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ClientPacketHandler implements IMessage {

	private String text;

	public ClientPacketHandler() {
	}

	public ClientPacketHandler(String text) {
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

	public static class Handler implements IMessageHandler<ClientPacketHandler, IMessage> {

		@Override
		public IMessage onMessage(ClientPacketHandler message, MessageContext ctx) {

			//System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));

			String[] data = message.text.split("%");
			
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		
			if (data[0].equalsIgnoreCase("error")) {
				
				int energyLeft = Integer.parseInt(data[1]);
				
				player.playSound("random.anvil_land", 1, 1);
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You need " + energyLeft + " more energy!"));	
			}
			
			if (data[0].equalsIgnoreCase("sync.fusion")) {
				
				int energy = Integer.parseInt(data[1]);
				
				if (player.openContainer instanceof ContainerBase) {				
					
					ContainerBase container = (ContainerBase)player.openContainer;	
					
					container.fusion.energy = energy;
				}
			}
						
			if (data[0].equalsIgnoreCase("force.launch")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				TileEntityMissileLauncher tileEntity = (TileEntityMissileLauncher)player.worldObj.getTileEntity(x, y, z);
				
				tileEntity.forceLaunch = true;
			}
			
			return null;
		}
	}
}