package party.lemons.lklib.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DebugUtil
{
	public static void printMissingLangKeys()
	{
		final String[] s = {""};

		Registry.BLOCK.forEach(b->
		{
			if(!I18n.hasTranslation(b.getTranslationKey()) && b.asItem() == Items.AIR)
			{
				s[0] += "\"" + b.getTranslationKey() + "\":\n";
			}
		});

		Registry.ITEM.forEach(b->
		{
			if(!I18n.hasTranslation(b.getTranslationKey()))
			{
				s[0] += "\"" + b.getTranslationKey() + "\":\n";
			}
		});

		Registry.ENTITY_TYPE.forEach(b->
		{
			if(!I18n.hasTranslation(b.getTranslationKey()))
			{
				s[0] += "\"" + b.getTranslationKey() + "\":\n";
			}
		});

		Registry.ENCHANTMENT.forEach((b)->{
			if(!I18n.hasTranslation(b.getTranslationKey()))
			{
				s[0] += "\"" + b.getTranslationKey() + "\":\n";
			}
		});

		System.out.println(s[0]);
	}

	public static void printMissingBlockLoot(String modid)
	{
		final String[] s = {""};
		Registry.BLOCK.forEach((i)-> {
			Identifier id = Registry.BLOCK.getId(i);
			if (id.getNamespace().equals(modid)) {
				if(i.getLootTableId() == LootTables.EMPTY || MinecraftClient.getInstance().getServer().getLootManager().getTable(i.getLootTableId()) == LootTable.EMPTY)
					s[0] += id + "\n";
			}
		});

		System.out.println(s[0]);
	}
}
