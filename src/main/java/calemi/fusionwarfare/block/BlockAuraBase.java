package calemi.fusionwarfare.block;

import calemi.fusionwarfare.tileentity.machine.TileEntityAuraPlayerTurret;

public class BlockAuraBase extends BlockBasicMachineBase {

	public BlockAuraBase(String name, Class tileEntity) {
		super("aura_" + name, tileEntity);
		setHasCustomModel();
		setBounds(3, 0, 3, 13, 12, 13);
	}
}
