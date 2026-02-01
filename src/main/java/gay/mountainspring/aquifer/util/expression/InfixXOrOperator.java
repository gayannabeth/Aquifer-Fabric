package gay.mountainspring.aquifer.util.expression;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.operators.AbstractOperator;
import com.ezylang.evalex.operators.InfixOperator;
import com.ezylang.evalex.parser.Token;

@InfixOperator(precedence = 6)
public class InfixXOrOperator extends AbstractOperator {
	@Override
	public EvaluationValue evaluate(Expression expression, Token operatorToken, EvaluationValue... operands) throws EvaluationException {
		return expression.convertValue(expression.evaluateSubtree(operands[0].getExpressionNode()).getBooleanValue() != expression.evaluateSubtree(operands[1].getExpressionNode()).getBooleanValue());
	}
}