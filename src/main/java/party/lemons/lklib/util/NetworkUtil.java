package party.lemons.lklib.util;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NetworkUtil
{
	public static void serverSendToNearby(World world, Identifier packet, PacketByteBuf buf, double x, double y, double z)
	{
		if(world.isClient) return;

		ServerWorld sw = (ServerWorld) world;

		for(ServerPlayerEntity pl : sw.getPlayers())
		{
			if(pl.getServerWorld() != sw) return;

			BlockPos pos = pl.getBlockPos();
			if(pos.isWithinDistance(new Vec3d(x, y, z), 32))
			{
				ServerSidePacketRegistry.INSTANCE.sendToPlayer(pl, packet, buf);
			}
		}
	}

	public static void serverSendTracking(World world, BlockPos blockPos, Identifier id, PacketByteBuf buf)
	{
		if(world.isClient()) return;

		for(ServerPlayerEntity pl : PlayerLookup.tracking((ServerWorld) world, blockPos))
		{
			ServerPlayNetworking.send(pl, id, buf);
		}
	}

}
