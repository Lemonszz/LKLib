package party.lemons.lklib.util.registry.boat;

import com.mojang.serialization.Lifecycle;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Items;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import party.lemons.lklib.LKLib;
import party.lemons.lklib.util.registry.RegistryHelper;

public class BoatTypes
{
	public static final RegistryKey<Registry<BoatType>> REG_KEY = RegistryKey.ofRegistry(LKLib.ID("boat_type"));
	public static final MutableRegistry<BoatType> REGISTRY = new SimpleRegistry<>(REG_KEY, Lifecycle.stable());

	static
	{
		((MutableRegistry) Registry.REGISTRIES).add(REG_KEY, REGISTRY, Lifecycle.stable());
	}

	//Vanilla Types
	public static final BoatType ACACIA = new VanillaBoatType(LKLib.ID("acacia"), BoatEntity.Type.ACACIA, Items.ACACIA_BOAT);
	public static final BoatType BIRCH = new VanillaBoatType(LKLib.ID("birch"), BoatEntity.Type.BIRCH, Items.BIRCH_BOAT);
	public static final BoatType DARK_OAK = new VanillaBoatType(LKLib.ID("dark_oak"), BoatEntity.Type.DARK_OAK, Items.DARK_OAK_BOAT);
	public static final BoatType JUNGLE = new VanillaBoatType(LKLib.ID("jungle"), BoatEntity.Type.JUNGLE, Items.JUNGLE_BOAT);
	public static final BoatType OAK = new VanillaBoatType(LKLib.ID("oak"), BoatEntity.Type.OAK, Items.OAK_BOAT);
	public static final BoatType SPRUCE = new VanillaBoatType(LKLib.ID("spruce"), BoatEntity.Type.SPRUCE, Items.SPRUCE_BOAT);

	public static void init()
	{
		RegistryHelper.register(LKLib.MODID, REGISTRY, BoatType.class, BoatTypes.class);
	}

	public static BoatType getVanillaType(BoatEntity.Type boatType)
	{
		for(BoatType t : REGISTRY)
		{
			if(t instanceof VanillaBoatType)
			{
				if(((VanillaBoatType) t).getVanillaType() == boatType) return t;
			}
		}

		return null;
	}
}