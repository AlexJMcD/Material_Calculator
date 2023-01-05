package com.amcoding.materialcalculator.domain


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`(){
        //1. GIVEN
        parser = ExpressionParser("3+5-3x4/3")

        //2. DO SOMETHING WITH WHAT IS GIVEN
        val actual = parser.parse()

        //3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
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
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parentheses is properly parsed`(){
        //1. GIVEN
        parser = ExpressionParser("4-(4x5)")

        //2. DO SOMETHING WITH WHAT IS GIVEN
        val actual = parser.parse()

        //3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Oper(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Oper(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with decimal numbers is properly parsed`(){
        //1. GIVEN
        parser = ExpressionParser("14.2+7.9-4.3")

        //2. DO SOMETHING WITH WHAT IS GIVEN
        val actual = parser.parse()

        //3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(14.2),
            ExpressionPart.Oper(Operation.ADD),
            ExpressionPart.Number(7.9),
            ExpressionPart.Oper(Operation.SUBTRACT),
            ExpressionPart.Number(4.3)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Complex expression is properly parsed`(){
        //1. GIVEN
        parser = ExpressionParser("5.32/2.1x8.9-12x14+2/6x8-3-2-4+9/7.9x6.2+59-0+109/4.2x6.7")

        //2. DO SOMETHING WITH WHAT IS GIVEN
        val actual = parser.parse()

        //3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(5.32),
            ExpressionPart.Oper(Operation.DIVIDE),
            ExpressionPart.Number(2.1),
            ExpressionPart.Oper(Operation.MULTIPLY),
            ExpressionPart.Number(8.9),
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
            ExpressionPart.Number(7.9),
            ExpressionPart.Oper(Operation.MULTIPLY),
            ExpressionPart.Number(6.2),
            ExpressionPart.Oper(Operation.ADD),
            ExpressionPart.Number(59.0),
            ExpressionPart.Oper(Operation.SUBTRACT),
            ExpressionPart.Number(0.0),
            ExpressionPart.Oper(Operation.ADD),
            ExpressionPart.Number(109.0),
            ExpressionPart.Oper(Operation.DIVIDE),
            ExpressionPart.Number(4.2),
            ExpressionPart.Oper(Operation.MULTIPLY),
            ExpressionPart.Number(6.7)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Percent sign is properly parsed`(){
        //1. GIVEN
        parser = ExpressionParser("10%3.4")

        //2. DO SOMETHING WITH WHAT IS GIVEN
        val actual = parser.parse()

        //3. ASSERT EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(10.0),
            ExpressionPart.Oper(Operation.PERCENT),
            ExpressionPart.Number(3.4)
        )
        assertThat(expected).isEqualTo(actual)
    }
}