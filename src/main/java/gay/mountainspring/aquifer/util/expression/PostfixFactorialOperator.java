package gay.mountainspring.aquifer.util.expression;

import java.math.BigDecimal;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.operators.AbstractOperator;
import com.ezylang.evalex.operators.PostfixOperator;
import com.ezylang.evalex.parser.Token;

@PostfixOperator
public class PostfixFactorialOperator extends AbstractOperator {
	@Override
	public EvaluationValue evaluate(Expression expression, Token operatorToken, EvaluationValue... operands) throws EvaluationException {
		int number = operands[0].getNumberValue().intValue();
		BigDecimal factorial = BigDecimal.ONE;
		for (int i = 1; i <= number; i++) {
			factorial = factorial.multiply(new BigDecimal(i, expression.getConfiguration().getMathContext()), expression.getConfiguration().getMathContext());
		}
		return expression.convertValue(factorial);
	}
}