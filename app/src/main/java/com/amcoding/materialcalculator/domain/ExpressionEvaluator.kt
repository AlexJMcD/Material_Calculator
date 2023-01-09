package com.amcoding.materialcalculator.domain

// -3+(5*4)
/**
 * Uses the following grammar:
 * expression : term | term + term | term - term
 * term: factor | factor * factor | factor / factor
 * factor: number | (expression) | + factor | - factor
 */
class ExpressionEvaluator(
    private val expression: List<ExpressionPart>
) {
    fun evaluate(): Double{
        return evaluateExpression(expression).value
    }

    private fun evaluateExpression(expression: List<ExpressionPart>): ExpressionResult{
        val result = evaluateTerm(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while(true){
            when(remaining.firstOrNull()){
                ExpressionPart.Oper(Operation.ADD) -> {
                    val term = evaluateTerm(remaining.drop(1))
                    sum += term.value
                    remaining = term.remainingExpression
                }
                ExpressionPart.Oper(Operation.SUBTRACT) -> {
                    val term = evaluateTerm(remaining.drop(1))
                    sum -= term.value
                    remaining = term.remainingExpression
                }
                else -> return ExpressionResult(remaining, sum)

            }
        }
    }

    private fun evaluateTerm(expression: List<ExpressionPart>): ExpressionResult{
        val result = evaluateFactor(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while(true){
            when(remaining.firstOrNull()){
                ExpressionPart.Oper(Operation.MULTIPLY) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum *= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.Oper(Operation.DIVIDE) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum /= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.Oper(Operation.PERCENT) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum %= (factor.value/100f)
                    remaining = factor.remainingExpression
                }
                else -> return ExpressionResult(remaining, sum)

            }
        }
    }

    // A factor is either a number or an expression in parentheses.
    // e.g. 5.0, -7.5, -(3+4*5)
    // But NOT something 3 * 4, 5 + 7
    private fun evaluateFactor(expression: List<ExpressionPart>): ExpressionResult{
        return when (val part = expression.firstOrNull()){
            ExpressionPart.Oper(Operation.ADD) -> {
                evaluateFactor(expression.drop(1))
            }
            ExpressionPart.Oper(Operation.SUBTRACT) -> {
                evaluateFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression, -value)
                }
            }
            ExpressionPart.Parentheses(ParenthesesType.Opening) -> {
                evaluateExpression(expression.drop(1)).run{
                    ExpressionResult(remainingExpression.drop(1), value)
                }
            }
            ExpressionPart.Oper(Operation.PERCENT) -> evaluateTerm(expression.drop(1))
            is ExpressionPart.Number -> ExpressionResult(
                remainingExpression = expression.drop(1),
                value = part.number
            )
            else -> throw java.lang.RuntimeException("Invalid part.")
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}