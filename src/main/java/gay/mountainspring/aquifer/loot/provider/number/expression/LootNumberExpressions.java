package gay.mountainspring.aquifer.loot.provider.number.expression;

import gay.mountainspring.aquifer.Aquifer;
import gay.mountainspring.aquifer.registry.AquiferRegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class LootNumberExpressions {
	private LootNumberExpressions() {}
	
	public static void init() {
		
	}
	
	public static final RegistryKey<LootNumberExpression> IDENTITY = create("identity");
	public static final RegistryKey<LootNumberExpression> INCREMENT = create("increment");
	public static final RegistryKey<LootNumberExpression> DECREMENT = create("decrement");
	public static final RegistryKey<LootNumberExpression> ADDITION = create("addition");
	public static final RegistryKey<LootNumberExpression> SUBTRACTION = create("subtraction");
	public static final RegistryKey<LootNumberExpression> MULTIPLICATION = create("multiplication");
	public static final RegistryKey<LootNumberExpression> DIVISION = create("division");
	public static final RegistryKey<LootNumberExpression> MIN = create("min");
	public static final RegistryKey<LootNumberExpression> MAX = create("max");
	public static final RegistryKey<LootNumberExpression> ABSOLUTE_VALUE = create("absolute_value");
	public static final RegistryKey<LootNumberExpression> SQUARE = create("square");
	public static final RegistryKey<LootNumberExpression> SQUARE_ROOT = create("square_root");
	public static final RegistryKey<LootNumberExpression> INVERSE_SQUARE_ROOT = create("inverse_square_root");
	public static final RegistryKey<LootNumberExpression> CUBE = create("cube");
	public static final RegistryKey<LootNumberExpression> CUBE_ROOT = create("cube_root");
	public static final RegistryKey<LootNumberExpression> EXPONENTIAL = create("exponential");
	public static final RegistryKey<LootNumberExpression> POWER = create("power");
	public static final RegistryKey<LootNumberExpression> SELF_POWER = create("self_power");
	public static final RegistryKey<LootNumberExpression> LOGARITHM = create("logarithm");
	public static final RegistryKey<LootNumberExpression> NATURAL_LOGARITHM = create("natural_logarithm");
	public static final RegistryKey<LootNumberExpression> FACTORIAL = create("factorial");
	public static final RegistryKey<LootNumberExpression> DOUBLE = create("double");
	public static final RegistryKey<LootNumberExpression> HALF = create("half");
	public static final RegistryKey<LootNumberExpression> NEGATE = create("negate");
	public static final RegistryKey<LootNumberExpression> RECIPROCAL = create("reciprocal");
	public static final RegistryKey<LootNumberExpression> SINE_DEG = create("sine_deg");
	public static final RegistryKey<LootNumberExpression> SINE_RAD = create("sine_rad");
	public static final RegistryKey<LootNumberExpression> COSINE_DEG = create("cosine_deg");
	public static final RegistryKey<LootNumberExpression> COSINE_RAD = create("cosine_rad");
	public static final RegistryKey<LootNumberExpression> SECANT_DEG = create("secant_deg");
	public static final RegistryKey<LootNumberExpression> SECANT_RAD = create("secant_rad");
	public static final RegistryKey<LootNumberExpression> COSECANT_DEG = create("cosecant_deg");
	public static final RegistryKey<LootNumberExpression> COSECANT_RAD = create("cosecant_rad");
	public static final RegistryKey<LootNumberExpression> TANGENT_DEG = create("tangent_deg");
	public static final RegistryKey<LootNumberExpression> TANGENT_RAD = create("tangent_rad");
	public static final RegistryKey<LootNumberExpression> COTANGENT_DEG = create("cotangent_deg");
	public static final RegistryKey<LootNumberExpression> COTANGENT_RAD = create("cotangent_rad");
	public static final RegistryKey<LootNumberExpression> ARC_SINE_DEG = create("arc_sine_deg");
	public static final RegistryKey<LootNumberExpression> ARC_SINE_RAD = create("arc_sine_rad");
	public static final RegistryKey<LootNumberExpression> ARC_COSINE_DEG = create("arc_cosine_deg");
	public static final RegistryKey<LootNumberExpression> ARC_COSINE_RAD = create("arc_cosine_rad");
	public static final RegistryKey<LootNumberExpression> ARC_SECANT_DEG = create("arc_secant_deg");
	public static final RegistryKey<LootNumberExpression> ARC_SECANT_RAD = create("arc_secant_rad");
	public static final RegistryKey<LootNumberExpression> ARC_COSECANT_DEG = create("arc_cosecant_deg");
	public static final RegistryKey<LootNumberExpression> ARC_COSECANT_RAD = create("arc_cosecant_rad");
	public static final RegistryKey<LootNumberExpression> ARC_TANGENT_DEG = create("arc_tangent_deg");
	public static final RegistryKey<LootNumberExpression> ARC_TANGENT_RAD = create("arc_tangent_rad");
	public static final RegistryKey<LootNumberExpression> ARC_COTANGENT_DEG = create("arc_cotangent_deg");
	public static final RegistryKey<LootNumberExpression> ARC_COTANGENT_RAD = create("arc_cotangent_rad");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_SINE = create("hyperbolic_sine");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_COSINE = create("hyperbolic_cosine");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_SECANT = create("hyperbolic_secant");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_COSECANT = create("hyperbolic_cosecant");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_TANGENT = create("hyperbolic_tangent");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_COTANGENT = create("hyperbolic_cotangent");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_SINE = create("hyperbolic_arc_sine");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_COSINE = create("hyperbolic_arc_cosine");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_SECANT = create("hyperbolic_arc_secant");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_COSECANT = create("hyperbolic_arc_cosecant");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_TANGENT = create("hyperbolic_arc_tangent");
	public static final RegistryKey<LootNumberExpression> HYPERBOLIC_ARC_COTANGENT = create("hyperbolic_arc_cotangent");
	
	private static RegistryKey<LootNumberExpression> create(String name) {
		return RegistryKey.of(AquiferRegistryKeys.LOOT_NUMBER_EXPRESSION, Identifier.of(Aquifer.MOD_ID, name));
	}
}