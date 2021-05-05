package party.lemons.lklib.util.registry.boat;

import net.minecraft.client.model.ModelPart;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import party.lemons.lklib.util.access.SignTypeHelper;

import java.util.function.Supplier;

public class BoatType
{
	public final Identifier id;
	public final Supplier<ItemConvertible> item;

	public BoatType(Identifier id, Supplier<ItemConvertible> item)
	{
		this.id = id;
		this.item = item;
	}

	public Identifier getTexture()
	{
		return new Identifier(id.getNamespace(), "textures/entity/boat/" + id.getPath() + ".png");
	}
}