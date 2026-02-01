package gay.mountainspring.aquifer.util.expression;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.functions.AbstractFunction;
import com.ezylang.evalex.functions.FunctionParameter;
import com.ezylang.evalex.parser.Token;

@FunctionParameter(name = "value")
public class InvSqrtFunction extends AbstractFunction {
	@Override
	public EvaluationValue evaluate(Expression expression, Token functionToken, EvaluationValue... parameterValues) throws EvaluationException {
		double d = parameterValues[0].getNumberValue().doubleValue();
		double halfd = 0.5D * d;
		long l = Double.doubleToLongBits(d);
		l = 0x5fe6ec85e7de30daL - (l >> 1);
		d = Double.longBitsToDouble(l);
		d *= (1.5D - (halfd * d * d));
		d *= (1.5D - (halfd * d * d));
		return expression.convertDoubleValue(d);
	}
}