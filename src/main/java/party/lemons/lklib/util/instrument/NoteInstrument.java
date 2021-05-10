package party.lemons.lklib.util.instrument;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public abstract class NoteInstrument
{
    public static List<NoteInstrument> INSTRUMENTS = Lists.newArrayList();

    public static NoteInstrument findInstrumentFor(World world, BlockPos pos, BlockState state)
    {
        for(NoteInstrument instrument : INSTRUMENTS)
        {
            if(instrument.isValidState(world, pos, state))
                return instrument;
        }

        return null;
    }

    private SoundEvent sound;
    public NoteInstrument(SoundEvent sound)
    {
        this.sound = sound;

        INSTRUMENTS.add(this);
    }


    public abstract boolean isValidState(World world, BlockPos pos, BlockState state);

    public float getPitch(World world, BlockPos pos, BlockState state)
    {
        int i = state.get(NoteBlock.NOTE);
        return (float)Math.pow(2.0D, (double)(i - 12) / 12.0D);
    }

    public SoundEvent getSound()
    {
        return sound;
    }

    public void play(World world, BlockPos pos, BlockState state)
    {
        world.playSound(null, pos, getSound(), SoundCategory.RECORDS, 3.0F, getPitch(world, pos, state));
    }

    public void doParticle(World world, BlockPos pos, BlockState state)
    {
        int i = state.get(NoteBlock.NOTE);
        world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)i / 24.0D, 0.0D, 0.0D);
    }
}
