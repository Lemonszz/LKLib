package party.lemons.lklib.util.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface BlockWithItem
{
    default boolean hasItem()
    {
        return true;
    }

    default Item.Settings makeItemSettings(ItemGroup group)
    {
        return new Item.Settings().group(group);
    }

    default Item makeItem(ItemGroup group)
    {
        return new BlockItem((Block) this, makeItemSettings(group));
    }

    default void registerItem(Identifier id, ItemGroup group)
    {
        Registry.register(Registry.ITEM, id, makeItem(group));
    }
}
