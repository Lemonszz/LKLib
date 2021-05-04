package party.lemons.lklib.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import party.lemons.lklib.util.access.BlockEntityTypeAccess;

import java.util.HashSet;
import java.util.Set;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin implements BlockEntityTypeAccess
{
    @Shadow
    @Final
    @Mutable
    private Set<Block> blocks;

    @Override
    public void lk_addBlockTypes(Block... toAdd)
    {
        Set<Block> b = new HashSet<>(blocks);

        for(Block block : toAdd)
            b.add(block);

        blocks = b;
    }
}
