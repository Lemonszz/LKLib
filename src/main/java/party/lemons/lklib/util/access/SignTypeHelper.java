package party.lemons.lklib.util.access;

import net.minecraft.util.SignType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public interface SignTypeHelper
{
	Set<SignType> lk_getTypes();

	static SignType register(String name)
	{
		SignType type = createSignType(name);

		((SignTypeHelper) type).lk_getTypes().add(type);

		return type;
	}

	static SignType createSignType(String name)
	{
		try {
			Constructor<SignType> constructor = SignType.class.getDeclaredConstructor(String.class);
			constructor.setAccessible(true);

			SignType type = constructor.newInstance(name);
			return type;
		}
		catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
