package gay.mountainspring.aquifer.util.expression;

import java.math.BigDecimal;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

@FunctionParameter(name = "value")
public class ASecFunction extends AbstractFunction {
	@Override
	public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
		BigDecimal parameterValue = parameterValues[0].getNumberValue();
		
		if (parameterValue.compareTo(BigDecimal.ONE) < 0 && parameterValue.compareTo(MINUS_ONE) > 0) {
			throw new EvaluationException(functionToken, "Illegal asec(x) for -1< x < 1: x = " + parameterValue);
		}
		
		return expression.convertDoubleValue(Math.toDegrees(Math.acos(1.0D/parameterValue.doubleValue())));
	}
}