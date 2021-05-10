package party.lemons.lklib.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import party.lemons.lklib.util.instrument.NoteInstrument;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {

    @Shadow
    @Final
    public static IntProperty NOTE;

    @Inject(at = @At("HEAD"), method = "onSyncedBlockEvent", cancellable = true)
    public void onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data, CallbackInfoReturnable<Boolean> cbi) {
        BlockState downState = world.getBlockState(pos.down());
        NoteInstrument noteInstrument = NoteInstrument.findInstrumentFor(world, pos, downState);
        if(noteInstrument != null)
        {
            noteInstrument.play(world, pos, state);
            noteInstrument.doParticle(world, pos, state);
            cbi.setReturnValue(true);
        }
    }
}