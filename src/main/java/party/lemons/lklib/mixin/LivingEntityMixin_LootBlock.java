package party.lemons.lklib.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import party.lemons.lklib.util.extension.LootBlocker;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_LootBlock extends Entity implements LootBlocker
{
    @Unique
    private boolean isLootBlocked = false;

    @Override
    @Unique
    public boolean isLootBlocked()
    {
        return isLootBlocked;
    }

    @Override
    @Unique
    public void setLootBlocked(boolean block)
    {
        this.isLootBlocked = block;
    }

    @Inject(at = @At("HEAD"), method = "shouldDropLoot", cancellable = true)
    private void shouldDropLoot(CallbackInfoReturnable<Boolean> cbi)
    {
        if(isLootBlocked())
            cbi.setReturnValue(false);
    }

    @Inject(at = @At("TAIL"), method = "writeCustomDataToTag")
    private void writeData(CompoundTag tag, CallbackInfo cbi)
    {
        tag.putBoolean("LKLootBlock", isLootBlocked);
    }

    @Inject(at = @At("TAIL"), method = "readCustomDataFromTag")
    private void readData(CompoundTag tag, CallbackInfo cbi)
    {
        isLootBlocked = tag.getBoolean("LKLootBlock");
    }

    public LivingEntityMixin_LootBlock(EntityType<?> type, World world) {
        super(type, world);
    }
}
