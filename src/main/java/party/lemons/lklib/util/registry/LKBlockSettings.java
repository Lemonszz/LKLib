package party.lemons.lklib.util.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class LKBlockSettings extends FabricBlockSettings
{
    private RLayer layer = null;

    public LKBlockSettings(Material material, MaterialColor color)
    {
        super(material, color);
    }

    public LKBlockSettings(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    public RLayer getLayer()
    {
        return layer;
    }

    public FabricBlockSettings layer(RLayer layer)
    {
        this.layer = layer;
        return this;
    }
}
