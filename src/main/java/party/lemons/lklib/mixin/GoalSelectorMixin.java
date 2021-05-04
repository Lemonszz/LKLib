package party.lemons.lklib.mixin;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import party.lemons.lklib.util.extension.GoalSelectorExtension;

import java.util.Set;

@Mixin(GoalSelector.class)
public abstract class GoalSelectorMixin implements GoalSelectorExtension
{
    @Shadow
    @Final
    private Set<PrioritizedGoal> goals;

    @Shadow public abstract void remove(Goal goal);

    @Shadow public abstract void add(int priority, Goal goal);

    @Override
    public void lk_remove(Class<? extends Goal> goalClass)
    {
        goals.removeIf(g->g.getGoal().getClass().equals(goalClass));
    }

    @Override
    public void lk_copy(GoalSelector from)
    {
        goals.removeIf((g)->true);

        for(PrioritizedGoal goal : ((GoalSelectorExtension)from).lk_getGoals())
            add(goal.getPriority(), goal.getGoal());
    }

    @Override
    public Set<PrioritizedGoal> lk_getGoals()
    {
        return goals;
    }
}
