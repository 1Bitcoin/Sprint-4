package ru.sber.functional

import kotlin.math.*

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(power: Int): (Any) -> Any {
        return { value ->
            when (value) {
                is Int -> value.toDouble().pow(power).toInt()
                is Double -> value.pow(power)
                else -> println("Допустимый тип аргумента: Int, Double")
            }
        }
    }
}


