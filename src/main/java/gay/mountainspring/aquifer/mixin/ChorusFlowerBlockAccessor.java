package gay.mountainspring.aquifer.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@Mixin(ChorusFlowerBlock.class)
public interface ChorusFlowerBlockAccessor {
	@Invoker("grow")
	void invokeGrow(World world, BlockPos pos, int age);
	
	@Invoker("die")
	void invokeDie(World world, BlockPos pos);
	
	@Invoker("isSurroundedByAir")
	static boolean invokeIsSurroundedByAir(WorldView world, BlockPos pos, @Nullable Direction direction) {
		throw new AssertionError();
	}
}