package com.amcoding.materialcalculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Double): ExpressionPart
    data class Oper(val operation: Operation): ExpressionPart
    data class Parentheses(val type: ParenthesesType): ExpressionPart
}

sealed interface ParenthesesType {
    object Opening: ParenthesesType
    object Closing: ParenthesesType
}