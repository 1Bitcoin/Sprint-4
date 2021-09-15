package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
        assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }

    @ParameterizedTest
    @MethodSource("calculate pow with different double value data set")
    fun `calculate pow with different double value`(power: Int, value: Double, expected: Double, delta: Double) {
        val actual = PowFactory.buildPowFunction(power)(value) as Double

        assertEquals(expected, actual, delta)
    }

    @ParameterizedTest
    @MethodSource("calculate with zero power data set")
    fun `calculate pow with zero pow and value`(power: Int, value: Double, expected: Double, delta: Double) {
        val actual = PowFactory.buildPowFunction(power)(value) as Double

        assertEquals(expected, actual, delta)
    }

    companion object {
        @JvmStatic
        fun `calculate with zero power data set`() = listOf(
            Arguments.of(0, 11.0, 1.0, 1e-6),
            Arguments.of(0, 0.0, 1.0, 1e-6),
            Arguments.of(0, -228, 1.0, 1e-6)
        )

        @JvmStatic
        fun `calculate pow with different double value data set`() = listOf(
            Arguments.of(2, 3.0, 9.0, 1e-6),
            Arguments.of(3, 12.0, 1728.0, 1e-6),
            Arguments.of(2, -12.0, 144.0, 1e-6),
        )
    }
}
