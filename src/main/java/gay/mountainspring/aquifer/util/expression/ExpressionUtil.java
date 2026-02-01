package gay.mountainspring.aquifer.util.expression;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import com.ezylang.evalex.functions.FunctionIfc;
import com.ezylang.evalex.operators.OperatorIfc;
import com.google.common.collect.Lists;

public class ExpressionUtil {
	private ExpressionUtil() {}
	
	private static final List<String> FUNCTION_NAMES = Lists.newArrayList();
	private static final List<String> PREFIX_OPERATOR_NAMES = Lists.newArrayList();
	private static final List<String> INFIX_OPERATOR_NAMES = Lists.newArrayList();
	private static final List<String> POSTFIX_OPERATOR_NAMES = Lists.newArrayList();
	private static final List<Map.Entry<String, Supplier<FunctionIfc>>> FUNCTION_BUILDERS = Lists.newArrayList();
	private static final List<Map.Entry<String, Supplier<OperatorIfc>>> OPERATOR_BUILDERS = Lists.newArrayList();
	
	static {
		FUNCTION_NAMES.addAll(List.of(
				"ABS",
				"AVERAGE",
				"CEILING",
				"COALESCE",
				"FACT",
				"FLOOR",
				"IF",
				"LOG",
				"LOG10",
				"MAX",
				"MIN",
				"NOT",
				"RANDOM",
				"ROUND",
				"SQRT",
				"SUM",
				"SWITCH",
				"STR_CONTAINS",
				"STR_ENDS_WITH",
				"STR_FORMAT",
				"STR_LEFT",
				"STR_LENGTH",
				"STR_LOWER",
				"STR_MATCHES",
				"STR_RIGHT",
				"STR_SPLIT",
				"STR_STARTS_WITH",
				"STR_SUBSTRING",
				"STR_TRIM",
				"STR_UPPER",
				"ACOS",
				"ASOCH",
				"ACOSR",
				"ACOT",
				"ACOTH",
				"ACOTR",
				"ASIN",
				"ASINH",
				"ASINR",
				"ATAN2",
				"ATAN2R",
				"ATAN",
				"ATANH",
				"ATANR",
				"COS",
				"COSH",
				"COSR",
				"COT",
				"COTH",
				"COTR",
				"CSC",
				"CSCH",
				"CSCR",
				"DEG",
				"RAD",
				"SEC",
				"SECH",
				"SECR",
				"SIN",
				"SINH",
				"SINR",
				"TAN",
				"TANH",
				"TANR",
				"DT_DATE_NEW",
				"DT_DATE_PARSE",
				"DT_DATE_FORMAT",
				"DT_DATE_TO_EPOCH",
				"DT_DURATION_NEW",
				"DT_DURATION_PARSE",
				"DT_DURATION_FROM_MILLIS",
				"DT_DURATION_TO_MILLIS",
				"DT_NOW",
				"DT_TODAY"
		));
		PREFIX_OPERATOR_NAMES.addAll(List.of(
				"-",
				"+",
				"!"
		));
		INFIX_OPERATOR_NAMES.addAll(List.of(
				"-",
				"+",
				"*",
				"/",
				"^",
				"%",
				"=",
				"==",
				"!=",
				"<>",
				">",
				">=",
				"<",
				"<=",
				"&&",
				"||"
		));
		
		registerOperator("!", PostfixFactorialOperator::new);
		registerOperator("^^", InfixXOrOperator::new);
		
		registerFunction("ASEC", ASecFunction::new);
		registerFunction("ASECH", ASecRFunction::new);
		registerFunction("ASECR", ASecHFunction::new);
		registerFunction("ACSC", ACscFunction::new);
		registerFunction("ACSCH", ACscRFunction::new);
		registerFunction("ACSCR", ACscHFunction::new);
		registerFunction("CBRT", CbrtFunction::new);
		registerFunction("INV_SQRT", InvSqrtFunction::new);
	}
	
	public static void registerFunction(String name, Supplier<FunctionIfc> functionBuilder) {
		if (!FUNCTION_NAMES.contains(name)) {
			FUNCTION_NAMES.add(name);
			FUNCTION_BUILDERS.add(Map.entry(name, functionBuilder));
		}
	}
	
	public static void registerOperator(String name, Supplier<OperatorIfc> operatorBuilder) {
		OperatorIfc op = operatorBuilder.get();
		if (op.isPrefix() && !PREFIX_OPERATOR_NAMES.contains(name)) {
			PREFIX_OPERATOR_NAMES.add(name);
			OPERATOR_BUILDERS.add(Map.entry(name, operatorBuilder));
		} else if (op.isInfix() && !INFIX_OPERATOR_NAMES.contains(name)) {
			INFIX_OPERATOR_NAMES.add(name);
			OPERATOR_BUILDERS.add(Map.entry(name, operatorBuilder));
		} else if (op.isPostfix() && !POSTFIX_OPERATOR_NAMES.contains(name)) {
			POSTFIX_OPERATOR_NAMES.add(name);
			OPERATOR_BUILDERS.add(Map.entry(name, operatorBuilder));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map.Entry<String, FunctionIfc>[] getFunctions() {
		return (Entry<String, FunctionIfc>[]) FUNCTION_BUILDERS.stream().map(entry -> Map.entry(entry.getKey(), entry.getValue().get())).toList().toArray();
	}
	
	@SuppressWarnings("unchecked")
	public static Map.Entry<String, OperatorIfc>[] getOperators() {
		return (Entry<String, OperatorIfc>[]) OPERATOR_BUILDERS.stream().map(entry -> Map.entry(entry.getKey(), entry.getValue().get())).toList().toArray();
	}
}