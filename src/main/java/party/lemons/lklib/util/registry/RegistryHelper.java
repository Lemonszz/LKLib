package party.lemons.lklib.util.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;

public final class RegistryHelper
{

    /***
     * Registers all static final fields of the given type in the given class to the given registry
     * @param modid
     * @param registry
     * @param typeClass
     * @param from
     * @param callbacks
     */
    @SafeVarargs
    public static <T> void register(String modid, Registry<T> registry, Class typeClass, Class from, RegistryCallback<T>... callbacks)
    {
        try
        {
            Field[] fields = from.getDeclaredFields();

            for(Field field : fields)
            {
                if(typeClass.isAssignableFrom(field.getType()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
                {

                    T value = (T) field.get(from);
                    String regName = field.getName().toLowerCase(Locale.ENGLISH);
                    Identifier id = new Identifier(modid, regName);

                    Registry.register(registry, id, value);

                    for(RegistryCallback<T> cb : callbacks)
                    {
                        cb.callback(registry, value, id);
                    }
                }
            }

        }catch(Exception e)
        {
            //if crash == true; dont();
            e.printStackTrace();
        }
    }

    public interface RegistryCallback<T>
    {
        void callback(Registry<T> registry, T registryObject, Identifier identifier);
    }

    public static class BlockWithItemCallback implements RegistryCallback<Block>
    {
        private final ItemGroup group;

        public BlockWithItemCallback(ItemGroup group)
        {
            this.group = group;
        }

        @Override
        public void callback(Registry<Block> registry, Block bl, Identifier id)
        {
            if(!(bl instanceof BlockWithItem)) return;

            BlockWithItem info = (BlockWithItem) bl;
            if(!info.hasItem()) return;

            info.registerItem(id, group);
        }
    }

    private RegistryHelper()
    {
    }
}