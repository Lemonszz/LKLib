package party.lemons.lklib.mixin;

import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import party.lemons.lklib.util.access.MobEntityAccess;

@Mixin(MobEntity.class)
public class MobEntityMixin implements MobEntityAccess
{
    @Shadow
    @Final
    protected GoalSelector goalSelector;

    @Shadow @Final protected GoalSelector targetSelector;

    @Override
    public GoalSelector lk_getGoalSelector()
    {
        return goalSelector;
    }

    @Override
    public GoalSelector lk_getTargetSelector()
    {
        return targetSelector;
    }
}
