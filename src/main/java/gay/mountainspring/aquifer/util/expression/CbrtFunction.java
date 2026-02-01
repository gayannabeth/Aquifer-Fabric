package gay.mountainspring.aquifer.util.expression;

import java.math.BigDecimal;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

@FunctionParameter(name = "value")
public class CbrtFunction extends AbstractFunction {
	@Override
	public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
		BigDecimal x = parameterValues[0].getNumberValue();
		
		if (x.compareTo(BigDecimal.ZERO) == 0) {
			return expression.convertValue(BigDecimal.ZERO);
		}
		
		return expression.convertDoubleValue(Math.cbrt(x.doubleValue()));
	}
}