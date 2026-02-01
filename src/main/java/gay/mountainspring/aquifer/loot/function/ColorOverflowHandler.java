package gay.mountainspring.aquifer.loot.function;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.MathHelper;

public enum ColorOverflowHandler implements StringIdentifiable {
	MOD("mod", i -> i & 255),
	CLAMP("clamp", i -> MathHelper.clamp(i, 0, 255));
	
	@SuppressWarnings("deprecation")
	public static final StringIdentifiable.EnumCodec<ColorOverflowHandler> CODEC = StringIdentifiable.createCodec(ColorOverflowHandler::values);
	final String type;
	private final Int2IntFunction function;
	
	private ColorOverflowHandler(String type, Int2IntFunction function) {
		this.type = type;
		this.function = function;
	}
	
	@Override
	public String asString() {
		return this.type;
	}
	
	public int apply(int i) {
		return this.function.applyAsInt(i);
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
	@SuppressWarnings("deprecation")
	public static ColorOverflowHandler fromString(String type) {
		var handler = (ColorOverflowHandler) CODEC.byId(type);
		if (handler != null) {
			return handler;
		} else {
			throw new IllegalArgumentException("Invalid handler: " + type);
		}
	}
}