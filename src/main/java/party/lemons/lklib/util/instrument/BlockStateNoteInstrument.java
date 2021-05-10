package party.lemons.lklib.util.instrument;

import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockStateNoteInstrument extends NoteInstrument{
    private final BlockState state;

    public BlockStateNoteInstrument(BlockState state, SoundEvent sound) {
        super(sound);
        this.state = state;
    }

    @Override
    public boolean isValidInstrumentBlock(World world, BlockPos pos, BlockState state) {
        return state.equals(this.state);
    }
}
