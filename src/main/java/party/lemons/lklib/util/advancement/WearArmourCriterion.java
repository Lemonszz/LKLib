package party.lemons.lklib.util.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import party.lemons.lklib.LKLib;

public class WearArmourCriterion extends AbstractCriterion<WearArmourCriterion.Conditions>
{
	private static final Identifier ID = LKLib.ID("wear_armor");

	public Identifier getId()
	{
		return ID;
	}

	public Conditions conditionsFromJson(JsonObject jsonObject, EntityPredicate.Extended extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer)
	{
		ItemPredicate itemPredicates = ItemPredicate.fromJson(jsonObject.get("item"));
		return new Conditions(extended, itemPredicates);
	}

	public void trigger(ServerPlayerEntity player)
	{
		this.test(player, (conditions)->
		{
			return conditions.matches(player.getArmorItems());
		});
	}

	public static class Conditions extends AbstractCriterionConditions
	{
		private final ItemPredicate item;

		public Conditions(EntityPredicate.Extended player, ItemPredicate item)
		{
			super(WearArmourCriterion.ID, player);
			this.item = item;
		}

		public boolean matches(Iterable<ItemStack> armorItems)
		{
			for(ItemStack st : armorItems)
			{
				if(item.test(st)) return true;
			}
			return false;
		}

		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer)
		{
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.add("item", item.toJson());
			return jsonObject;
		}
	}
}
