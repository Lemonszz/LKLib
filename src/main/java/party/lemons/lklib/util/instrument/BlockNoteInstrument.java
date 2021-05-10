package party.lemons.lklib.util.instrument;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNoteInstrument extends NoteInstrument{
    private final Block block;

    public BlockNoteInstrument(Block block, SoundEvent sound) {
        super(sound);

        this.block = block;
    }

    @Override
    public boolean isValidInstrumentBlock(World world, BlockPos pos, BlockState state) {
        return state.getBlock() == block;
    }
}
