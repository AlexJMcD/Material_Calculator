package com.amcoding.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionEvaluatorTest{

    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(4.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Number(3.0)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4.0)
    }

    @Test
    fun `Evaluate expression with decimals`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(14.2),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(7.9),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(4.3)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(17.8)
    }

    @Test
    fun `Evaluate expression with parentheses`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(4.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(0.2)
    }

    @Test
    fun `Evaluate long expression`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(5.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Number(2.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(8.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(12.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(14.0),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(2.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Number(6.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(8.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(2.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(4.0),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(9.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Number(7.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(6.0),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(59.0),
                ExpressionPart.Oper(Operation.SUBTRACT),
                ExpressionPart.Number(0.0),
                ExpressionPart.Oper(Operation.ADD),
                ExpressionPart.Number(109.0),
                ExpressionPart.Oper(Operation.DIVIDE),
                ExpressionPart.Number(4.0),
                ExpressionPart.Oper(Operation.MULTIPLY),
                ExpressionPart.Number(6.0)
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(75.88095238095238)
    }
}