package com.amcoding.materialcalculator.domain

enum class Operation (val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    DIVIDE('/'),
    MULTIPLY('x'),
    PERCENT('%')
}

val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.values().find { it.symbol == symbol }
        ?: throw java.lang.IllegalArgumentException("Invalid Symbol")
}