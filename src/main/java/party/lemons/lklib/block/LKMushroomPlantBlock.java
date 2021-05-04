package party.lemons.lklib.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import party.lemons.lklib.util.registry.BlockWithItem;

import java.util.Random;
import java.util.function.Supplier;

public class LKMushroomPlantBlock extends MushroomPlantBlock implements BlockWithItem
{

	private final Supplier<ConfiguredFeature> giantShroomFeature;

	public LKMushroomPlantBlock(Supplier<ConfiguredFeature> giantShroomFeature, Settings settings)
	{
		super(settings);

		this.giantShroomFeature = giantShroomFeature;
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
	{
		BlockPos blockPos = pos.down();
		BlockState blockState = world.getBlockState(blockPos);
		if(blockState.isIn(BlockTags.MUSHROOM_GROW_BLOCK))
		{
			return true;
		}else
		{
			return (world.getBaseLightLevel(pos, 0) < 13 || getDefaultState().getLuminance() > 6) && this.canPlantOnTop(blockState, world, blockPos);
		}
	}

	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient)
	{
		return giantShroomFeature != null;
	}

	public boolean trySpawningBigMushroom(ServerWorld serverWorld, BlockPos pos, BlockState state, Random random)
	{
		if(giantShroomFeature == null)
			return false;

		serverWorld.removeBlock(pos, false);
		if(giantShroomFeature.get().generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos))
		{
			return true;
		}else
		{
			serverWorld.setBlockState(pos, state, 3);
			return false;
		}
	}
}
