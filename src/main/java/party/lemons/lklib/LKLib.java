package party.lemons.lklib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import party.lemons.lklib.init.LKCriterion;
import party.lemons.lklib.init.LKEntities;
import party.lemons.lklib.init.LKNetwork;
import party.lemons.lklib.util.registry.boat.BoatTypes;

public class LKLib implements ModInitializer
{
    public static final String MODID = "lklib";

    @Override
    public void onInitialize()
    {
        BoatTypes.init();
        LKEntities.init();
        LKCriterion.init();
        LKNetwork.initCommon();
    }

    public static Identifier ID(String path)
    {
        return new Identifier(MODID, path);
    }
}
