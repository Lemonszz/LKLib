package party.lemons.lklib.util.extension;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.mob.MobEntity;
import party.lemons.lklib.util.access.MobEntityAccess;

import java.util.Set;

public interface GoalSelectorExtension
{
	void lk_copy(GoalSelector selector);
	Set<PrioritizedGoal> lk_getGoals();
	void lk_remove(Class<? extends Goal> goalClass);

	static void copy(GoalSelector to, GoalSelector from)
	{
		((GoalSelectorExtension)to).lk_copy(from);
	}

	static void removeGoal(MobEntity mob, Class<? extends Goal> goalClass)
	{
		removeFrom(((MobEntityAccess)mob).lk_getGoalSelector(), goalClass);
	}

	static void removeTarget(MobEntity mob, Class<? extends Goal> goalClass)
	{
		removeFrom(((MobEntityAccess)mob).lk_getTargetSelector(), goalClass);
	}

	static void removeFrom(GoalSelector goalSelector, Class<? extends Goal> goalClass)
	{
		((GoalSelectorExtension)goalSelector).lk_remove(goalClass);
	}
}
