package party.lemons.lklib.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public final class NBTUtil
{
	public static String BLOCKPOS_TAG = "LKBlockPos";
	public static String BOX_TAG = "LKBox";

	//Old tag values from Biome Makeover, these will not be used for future saves.
	public static String BLOCKPOS_TAG_LEGACY = "BMBlockPos";
	public static String BOX_TAG_LEGACY = "BMBox";

	public static void writeBlockPos(BlockPos pos, CompoundTag tag)
	{
		tag.put(BLOCKPOS_TAG, NbtHelper.fromBlockPos(pos));
	}

	public static BlockPos readBlockPos(CompoundTag tag)
	{
		if(tag.contains(BLOCKPOS_TAG_LEGACY))	//Legacy conversion, this will be removed in a future update
			return NbtHelper.toBlockPos(tag.getCompound(BLOCKPOS_TAG_LEGACY));

		return NbtHelper.toBlockPos(tag.getCompound(BLOCKPOS_TAG));
	}

	public static void writeBox(Box box, CompoundTag tag)
	{
		CompoundTag boxTag = new CompoundTag();
		boxTag.putDouble("MinX", box.minX);
		boxTag.putDouble("MinY", box.minY);
		boxTag.putDouble("MinZ", box.minZ);
		boxTag.putDouble("MaxX", box.maxX);
		boxTag.putDouble("MaxY", box.maxY);
		boxTag.putDouble("MaxZ", box.maxZ);

		tag.put(BOX_TAG, boxTag);
	}

	public static Box readBox(CompoundTag tag)
	{
		if(tag.contains(BOX_TAG_LEGACY)) 	//Legacy conversion, this will be removed in a future update
		{
			CompoundTag boxTag = tag.getCompound(BOX_TAG_LEGACY);
			return new Box(
					boxTag.getDouble("MinX"),
					boxTag.getDouble("MinY"),
					boxTag.getDouble("MinZ"),
					boxTag.getDouble("MaxX"),
					boxTag.getDouble("MaxY"),
					boxTag.getDouble("MaxZ")
			);
		}

		CompoundTag boxTag = tag.getCompound(BOX_TAG);
		return new Box(
			boxTag.getDouble("MinX"),
			boxTag.getDouble("MinY"),
			boxTag.getDouble("MinZ"),
			boxTag.getDouble("MaxX"),
			boxTag.getDouble("MaxY"),
			boxTag.getDouble("MaxZ")
		);
	}


	private NBTUtil(){}
}
