package gay.mountainspring.aquifer.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.HoneycombItem;
import net.minecraft.util.math.Direction;

public class WaxedPaneBlock extends PaneBlock {
	public static final MapCodec<WaxedPaneBlock> CODEC = createCodec(WaxedPaneBlock::new);
	
	@Override
	public MapCodec<? extends WaxedPaneBlock> getCodec() {
		return CODEC;
	}
	
	public WaxedPaneBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		Block unwaxed = HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(this);
		
		if (unwaxed != null && stateFrom.isOf(unwaxed)) {
			if (!direction.getAxis().isHorizontal()) {
				return true;
			}
			
			if (state.get(FACING_PROPERTIES.get(direction)) && stateFrom.getProperties().contains(FACING_PROPERTIES.get(direction.getOpposite())) && stateFrom.get(FACING_PROPERTIES.get(direction.getOpposite()))) {
				return true;
			}
		}
		
		return super.isSideInvisible(state, stateFrom, direction);
	}
}