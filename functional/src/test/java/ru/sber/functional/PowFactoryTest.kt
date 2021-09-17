package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PowFactoryTest {

    @ParameterizedTest
    @MethodSource("calculate with different params data set")
    fun `buildPowFunction should return lambda It should calculate to second power`(power: Int, value: Int, expected: Int) {
        // expect
        assertEquals(expected, PowFactory.buildPowFunction(power)(value))
    }

    @ParameterizedTest
    @MethodSource("calculate with zero power data set")
    fun `calculate pow with zero pow and value`(power: Int, value: Int, expected: Int) {
        val actual = PowFactory.buildPowFunction(power)(value)

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun `calculate with zero power data set`() = listOf(
            Arguments.of(0, 11, 1),
            Arguments.of(0, 0, 1),
            Arguments.of(0, -228, 1)
        )

        @JvmStatic
        fun `calculate with different params data set`() = listOf(
            Arguments.of(2, 2, 4),
            Arguments.of(2, -2, 4),
            Arguments.of(1, -228, -228)
        )
    }
}
