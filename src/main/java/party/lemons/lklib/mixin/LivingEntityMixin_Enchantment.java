package party.lemons.lklib.mixin;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import party.lemons.lklib.enchantment.LKEnchantment;
import party.lemons.lklib.util.extension.LootBlocker;
import party.lemons.lklib.util.item.ItemUtil;

import java.util.Collection;
import java.util.Iterator;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_Enchantment extends Entity implements LootBlocker
{
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Unique
    private final Collection<Pair<EquipmentSlot, ItemStack>> attributeStacks = Lists.newArrayList();

    @Unique
    public boolean hasAttributeStack(ItemStack stack)
    {
        for(Pair<EquipmentSlot, ItemStack> pair : attributeStacks)
        {
            if(pair.getRight().equals(stack)) return true;
        }
        return false;
    }

    @Unique
    private boolean hasStackEquipInSlot(ItemStack stack, EquipmentSlot slot)
    {
        return getEquippedStack(slot).equals(stack);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo cbi) {
        if (!getEntityWorld().isClient()) {
            Iterator<Pair<EquipmentSlot, ItemStack>> it = attributeStacks.iterator();
            while (it.hasNext()) {
                Pair<EquipmentSlot, ItemStack> pair = it.next();
                ItemStack st = pair.getRight();
                if (!hasStackEquipInSlot(st, pair.getLeft())) {
                    ItemUtil.forEachEnchantment((en, stack, lvl) ->
                    {
                        if (en instanceof LKEnchantment) {
                            ((LKEnchantment) en).removeAttributes((LivingEntity) (Object) this, pair.getLeft());
                        }
                    }, st, true);
                    it.remove();
                }
            }

            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = getEquippedStack(slot);
                if (!stack.isEmpty()) {
                    ItemUtil.forEachEnchantment((en, st, lvl) ->
                    {
                        if (en instanceof LKEnchantment) {
                            ((LKEnchantment) en).onTick((LivingEntity) (Object) this, st, lvl);
                            if (!hasAttributeStack(st) && ((LKEnchantment) en).addAttributes((LivingEntity) (Object) this, st, slot, lvl)) {
                                attributeStacks.add(new Pair<>(slot, st));
                            }
                        }
                    }, stack);
                }
            }
        }
    }

    public LivingEntityMixin_Enchantment(EntityType<?> type, World world) {
        super(type, world);
    }
}
