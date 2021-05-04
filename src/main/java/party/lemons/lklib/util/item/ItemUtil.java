package party.lemons.lklib.util.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemUtil
{
    public static Item.Settings settings(ItemGroup group)
    {
        return new Item.Settings().group(group);
    }

    public static void forEachEnchantment(Consumer consumer, ItemStack stack, boolean allowEmpty)
    {
        if(!stack.isEmpty() || allowEmpty)
        {
            ListTag listTag = stack.getEnchantments();

            for(int i = 0; i < listTag.size(); ++i)
            {
                String string = listTag.getCompound(i).getString("id");
                int j = listTag.getCompound(i).getInt("lvl");
                Registry.ENCHANTMENT.getOrEmpty(Identifier.tryParse(string)).ifPresent((enchantment)->
                {
                    consumer.accept(enchantment, stack, j);
                });
            }

        }
    }

    public static void forEachEnchantment(Consumer consumer, ItemStack stack)
    {
        forEachEnchantment(consumer, stack, false);
    }

    @FunctionalInterface
    public interface Consumer
    {
        void accept(Enchantment enchantment, ItemStack stack, int level);
    }
}
