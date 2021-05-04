package party.lemons.lklib.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import party.lemons.lklib.LKLib;
import party.lemons.lklib.entity.LKBoatEntity;
import party.lemons.lklib.util.registry.RegistryHelper;

public final class LKEntities
{
    public static final EntityType<LKBoatEntity> LK_BOAT = FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<LKBoatEntity>) LKBoatEntity::new).trackable(128, 3).dimensions(EntityDimensions.fixed(1.375F, 0.5625F)).build();

    public static void init()
    {
        RegistryHelper.register(LKLib.MODID, Registry.ENTITY_TYPE, EntityType.class, LKEntities.class);
    }

    private LKEntities(){}
}
