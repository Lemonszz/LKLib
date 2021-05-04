package party.lemons.lklib.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import party.lemons.lklib.LKLib;
import party.lemons.lklib.network.S2C_SpawnEntityCustom;

public final class LKNetwork
{
    public static final Identifier SPAWN_ENTITY = LKLib.ID("spawn_entity");

    @Environment(EnvType.CLIENT)
    public static void initClient()
    {
        ClientPlayNetworking.registerGlobalReceiver(SPAWN_ENTITY, new S2C_SpawnEntityCustom());
    }

    public static void initCommon()
    {

    }

    private LKNetwork(){}
}
