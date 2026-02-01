package gay.mountainspring.aquifer.util.expression;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

@FunctionParameter(name = "value")
public class ASecHFunction extends AbstractFunction {
	@Override
	public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
		double value = parameterValues[0].getNumberValue().doubleValue();
		if (Double.compare(value, 0.0D) < 0 || Double.compare(value, 1.0D) > 0) {
			throw new EvaluationException(functionToken, "Value must be between zero and one, inclusive");
		}
		double recip = 1.0D/value;
		return expression.convertDoubleValue(Math.log(recip + (Math.sqrt(Math.pow(recip, 2) - 1))));
	}
}