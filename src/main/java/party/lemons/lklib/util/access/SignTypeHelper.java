package party.lemons.lklib.util.access;

import net.minecraft.util.SignType;

import java.util.Set;

public interface SignTypeHelper
{
	Set<SignType> lk_getTypes();

	static SignType register(SignType type)
	{
		((SignTypeHelper) type).lk_getTypes().add(type);

		return type;
	}
}
