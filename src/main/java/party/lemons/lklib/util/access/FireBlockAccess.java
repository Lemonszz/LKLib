package party.lemons.lklib.util.access;

import net.minecraft.block.Block;

public interface FireBlockAccess
{
    void lk_registerFlammable(Block block, int burnChance, int spreadChance);
}
