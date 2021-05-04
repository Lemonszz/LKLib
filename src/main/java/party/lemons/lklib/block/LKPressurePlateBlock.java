package party.lemons.lklib.block;

import net.minecraft.block.PressurePlateBlock;
import party.lemons.lklib.util.registry.BlockWithItem;

public class LKPressurePlateBlock extends PressurePlateBlock implements BlockWithItem
{
	public LKPressurePlateBlock(ActivationRule type, Settings settings)
	{
		super(type, settings);
	}
}
