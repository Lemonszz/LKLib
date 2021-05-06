package party.lemons.lklib.mixin;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin
{
    @Inject(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;generateEnchantments(Ljava/util/Random;Lnet/minecraft/item/ItemStack;IZ)Ljava/util/List;"), method = "enchant", locals = LocalCapture.CAPTURE_FAILHARD)
    private static void enchant(Random random, ItemStack target, int level, boolean treasureAllowed, CallbackInfoReturnable<ItemStack> cbi, List<EnchantmentLevelEntry> list)
    {
		/*
			Remove all curses from possible enchantment list except a max of 2.
			This almost mimics vanilla's behaviour, as there's only 2 curses.
			Since we have many more curses, without this, curses are over represented.
		 */
        long curseCount = list.stream().filter(e->e.enchantment.isCursed()).count();
        if(curseCount > 2)
        {
            Collections.shuffle(list);
            List<EnchantmentLevelEntry> toRemove = Lists.newArrayList();
            int found = 0;
            for(EnchantmentLevelEntry e : list)
            {
                if(e.enchantment.isCursed())
                {
                    if(found < 2)
                    {
                        found++;
                    }
                    else
                    {
                        toRemove.add(e);
                    }
                }
            }

            list.removeAll(toRemove);
        }
    }
}
