package party.lemons.lklib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import party.lemons.lklib.entity.render.LKBoatRenderer;
import party.lemons.lklib.init.LKEntities;
import party.lemons.lklib.init.LKNetwork;

public class LKLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LKNetwork.initClient();

        EntityRendererRegistry.INSTANCE.register(LKEntities.LK_BOAT, (r, c)->new LKBoatRenderer(r));
    }
}
