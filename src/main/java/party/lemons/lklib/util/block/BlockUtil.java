package party.lemons.lklib.util.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import party.lemons.lklib.util.access.AxeItemAccess;
import party.lemons.lklib.util.access.FireBlockAccess;
import party.lemons.lklib.util.item.ItemUtil;
import party.lemons.lklib.util.registry.BlockItemPair;
import party.lemons.lklib.util.registry.LKBlockSettings;
import party.lemons.lklib.util.registry.RLayer;

public class BlockUtil
{

    public static void addStrippedLog(Block log, Block stripped)
    {
        ((AxeItemAccess) Items.WOODEN_AXE).lk_addStrippable(log, stripped);
    }

    public static FabricBlockSettings settings(Material material, float hardness)
    {
        return FabricBlockSettings.of(material).hardness(hardness).resistance(hardness);
    }

    public static FabricBlockSettings settings(Material material, RLayer layer, float hardness)
    {
        return new LKBlockSettings(material, material.getColor()).layer(layer).hardness(hardness).resistance(hardness);
    }

    public static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type)
    {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    public static void registerFlammable(Block block, int burnChance, int spreadChance)
    {
        ((FireBlockAccess) Blocks.FIRE).lk_registerFlammable(block, burnChance, spreadChance);
    }

    public static boolean always(BlockState state, BlockView world, BlockPos pos)
    {
        return true;
    }

    public static BlockItemPair registerBlockAndItem(Block block, Identifier id, ItemGroup group)
    {
        BlockItem bi = new BlockItem(block, ItemUtil.settings(group));
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, bi);
        return BlockItemPair.of(block, bi);
    }
}
