package party.lemons.lklib.util.world;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.registry.BuiltinRegistries;

public final class WorldUtil
{
    public static void scatterItemStack(Entity e, ItemStack stack)
    {
        ItemScatterer.spawn(e.world, e.getX(), e.getY(), e.getZ(), stack);
    }

    public static StructureProcessorList registerJigsawStructure(StructureProcessorList li, Identifier id)
    {
        BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_PROCESSOR_LIST, id, li);

        return li;
    }

    private WorldUtil(){}
}
