package party.lemons.lklib.enchantment;

import com.google.common.collect.Maps;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import party.lemons.lklib.util.MathUtils;

import java.util.Map;
import java.util.UUID;

public class LKEnchantment extends Enchantment
{
    private final Map<EntityAttribute, EntityAttributeModifier> attributeModifiers = Maps.newHashMap();

    public LKEnchantment(Enchantment.Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes)
    {
        super(weight, type, slotTypes);

        initAttributes();
    }

    public void initAttributes()
    {

    }

    public void onTick(LivingEntity entity, ItemStack stack, int level)
    {

    }

    protected void addAttributeModifier(EntityAttribute attribute, String uuid, double amount, EntityAttributeModifier.Operation operation)
    {
        EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString(uuid), this::getTranslationKey, amount, operation);
        this.attributeModifiers.put(attribute, entityAttributeModifier);
    }

    public boolean addAttributes(LivingEntity entity, ItemStack stack, EquipmentSlot slot, int level)
    {
        if(attributeModifiers.size() <= 0 || stack.isEmpty()) return false;

        for(Map.Entry<EntityAttribute, EntityAttributeModifier> attributeEntry : this.attributeModifiers.entrySet())
        {
            UUID id = MathUtils.uuidFromString(slot.toString());
            EntityAttributeInstance entityAttributeInstance = entity.getAttributes().getCustomInstance(attributeEntry.getKey());
            if(entityAttributeInstance != null)
            {
                EntityAttributeModifier mod = attributeEntry.getValue();
                entityAttributeInstance.removeModifier(mod);
                entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(id, this.getTranslationKey() + " " + level, this.adjustModifierAmount(level, mod), mod.getOperation()));
            }
        }
        return true;
    }

    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier)
    {
        return modifier.getValue() * (double) (amplifier);
    }

    public void removeAttributes(LivingEntity entity, EquipmentSlot slot)
    {
        for(Map.Entry<EntityAttribute, EntityAttributeModifier> attributeEntry : this.attributeModifiers.entrySet())
        {
            UUID slotID = MathUtils.uuidFromString(slot.toString());
            EntityAttributeInstance entityAttributeInstance = entity.getAttributes().getCustomInstance(attributeEntry.getKey());
            if(entityAttributeInstance != null)
            {
                EntityAttributeModifier mod = entityAttributeInstance.getModifier(slotID);
                if(mod != null)
                    entityAttributeInstance.removeModifier(mod);
                else
                    System.out.println("ERROR REMOVING MODIFIER: DOESNT EXIST??? : " + entityAttributeInstance.getAttribute().getTranslationKey());
            }
        }
    }
}
