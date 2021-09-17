package ru.sber.functional

import kotlin.math.*

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(power: Int): (Int) -> Int {
        return { value -> value.toDouble().pow(power).toInt() }
    }
}


