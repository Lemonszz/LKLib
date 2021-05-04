package party.lemons.lklib.util.world;

import net.minecraft.util.math.BlockPos;

import java.util.Random;

public enum HorizontalDirection
{
	NORTH(0, -1, 4, true), NORTH_EAST(1, -1, 5, false), EAST(1, 0, 6, true), SOUTH_EAST(1, 1, 7, false), SOUTH(0, 1, 0, true), SOUTH_WEST(-1, 1, 1, false), WEST(-1, 0, 2, true), NORTH_WEST(-1, -1, 3, false);

	public int x;
	public int z;
	private final int opposite;
	public boolean isStraight;

	HorizontalDirection(int x, int z, int opposite, boolean straight)
	{
		this.x = x;
		this.z = z;
		this.opposite = opposite;
		this.isStraight = straight;
	}

	public HorizontalDirection opposite()
	{
		return values()[opposite];
	}

	public BlockPos offset(BlockPos pos)
	{
		return pos.add(x, 0, z);
	}

	public BlockPos.Mutable setOffset(BlockPos.Mutable pos)
	{
		return pos.move(x, 0, z);
	}

	public static HorizontalDirection random(Random random)
	{
		return values()[random.nextInt(values().length)];
	}
}
