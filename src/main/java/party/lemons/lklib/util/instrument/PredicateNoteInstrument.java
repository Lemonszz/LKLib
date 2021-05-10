package party.lemons.lklib.util.instrument;

import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class PredicateNoteInstrument extends NoteInstrument{

    private final Predicate<BlockState> predicate;

    public PredicateNoteInstrument(Predicate<BlockState> predicate, SoundEvent sound) {
        super(sound);

        this.predicate = predicate;
    }

    @Override
    public boolean isValidInstrumentBlock(World world, BlockPos pos, BlockState state) {
        return predicate.test(state);
    }
}
