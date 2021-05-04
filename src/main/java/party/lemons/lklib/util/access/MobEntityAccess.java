package party.lemons.lklib.util.access;

import net.minecraft.entity.ai.goal.GoalSelector;

public interface MobEntityAccess
{
	GoalSelector lk_getGoalSelector();
	GoalSelector lk_getTargetSelector();
}
