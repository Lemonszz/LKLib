package party.lemons.lklib.init;

import net.fabricmc.fabric.api.object.builder.v1.advancement.CriterionRegistry;
import party.lemons.lklib.util.advancement.WearArmourCriterion;

public class LKCriterion
{
    public static final WearArmourCriterion WEAR_ARMOUR = new WearArmourCriterion();

    public static void init() {
        CriterionRegistry.register(WEAR_ARMOUR);
    }
}
