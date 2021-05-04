package party.lemons.lklib.block;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import party.lemons.lklib.util.registry.BlockWithItem;

public class LKSaplingBlock extends SaplingBlock implements BlockWithItem
{
	public LKSaplingBlock(SaplingGenerator generator, Settings settings)
	{
		super(generator, settings);
	}
}
