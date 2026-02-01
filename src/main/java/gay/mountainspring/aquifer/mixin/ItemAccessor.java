package gay.mountainspring.aquifer.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;

@Mixin(Item.class)
public interface ItemAccessor {
	@Mutable
	@Accessor("recipeRemainder")
	public void setRecipeRemainder(@Nullable Item recipeRemainder);
	
	@Mutable
	@Accessor("components")
	public void setComponents(ComponentMap components);
}