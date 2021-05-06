package party.lemons.lklib.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import party.lemons.lklib.util.item.TotemItem;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_Totem extends Entity
{

    @Shadow public abstract ItemStack getStackInHand(Hand hand);

    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cbi)
    {
        if (!source.isOutOfWorld())
        {
            for(Hand hand : Hand.values())
            {
                ItemStack stack = this.getStackInHand(hand);
                if (stack.getItem() instanceof TotemItem && ((TotemItem) stack.getItem()).canActivate((LivingEntity)(Object)this)) {
                    ItemStack activateStack = stack.copy();
                    stack.decrement(1);

                    ((TotemItem)activateStack.getItem()).activateTotem((LivingEntity)(Object)this, activateStack);

                    cbi.setReturnValue(true);
                }
            }

        }
    }

    public LivingEntityMixin_Totem(EntityType<?> type, World world) {
        super(type, world);
    }
}
