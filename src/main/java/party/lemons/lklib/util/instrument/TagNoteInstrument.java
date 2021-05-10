package party.lemons.lklib.util.instrument;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TagNoteInstrument extends NoteInstrument{
    private final Tag<Block> tag;

    public TagNoteInstrument(Tag<Block> tag, SoundEvent sound) {
        super(sound);

        this.tag = tag;
    }

    @Override
    public boolean isValidInstrumentBlock(World world, BlockPos pos, BlockState state) {
        return state.isIn(tag);
    }
}
