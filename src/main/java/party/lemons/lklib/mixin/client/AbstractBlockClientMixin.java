package party.lemons.lklib.mixin.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import party.lemons.lklib.util.registry.LKBlockSettings;
import party.lemons.lklib.util.registry.RLayer;

@Mixin(AbstractBlock.class)
public class AbstractBlockClientMixin
{
	@Inject(at = @At("TAIL"), method = "<init>")
	public void onConstruct(AbstractBlock.Settings settings, CallbackInfo cbi)
	{
		if(settings instanceof LKBlockSettings)
		{
			RLayer layer = ((LKBlockSettings)settings).getLayer();
			if(layer != null)
				BlockRenderLayerMap.INSTANCE.putBlock(((Block)(Object)this), layer.getAsRenderLayer());
		}
	}
}
