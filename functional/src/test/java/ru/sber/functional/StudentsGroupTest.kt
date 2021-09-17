package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class StudentsGroupTest {

    private val studentsGroup = StudentsGroup()

    @BeforeEach
    fun setUp() {
        studentsGroup.init(
            listOf(
                Student("Ivan", "Ivanov", age = 12, averageRate = 4.5),
                Student("Dmitry", "Petrov", age = 45, averageRate = 2.4),
                Student("Igor", "Grishin", age = 21, averageRate = 4.9),
                Student("Ivan", "Ivanov", age =  56, averageRate = 3.5),
                Student("Vlad", "Vradon", age =  9, averageRate = 1.3),
            )
        )
    }

    @ParameterizedTest
    @MethodSource("predicate different average rate data set")
    fun `filter by average rate`(pred: (Student) -> Boolean, expected: Int) {
        val filteredListStudent = studentsGroup.filterByPredicate(pred)

        assertEquals(expected, filteredListStudent.size)
    }

    @ParameterizedTest
    @MethodSource("predicate different age data set")
    fun `filter by age`(pred: (Student) -> Boolean, expected: Int) {
        val filteredListStudent = studentsGroup.filterByPredicate(pred)

        assertEquals(expected, filteredListStudent.size)
    }

    companion object {
        @JvmStatic
        fun `predicate different average rate data set`() = listOf(
            Arguments.of( { student: Student -> student.averageRate > 4.0 }, 2),
            Arguments.of( { student: Student -> student.averageRate == -1.5 }, 0),
            Arguments.of( { student: Student -> student.averageRate in 3.9..4.6 }, 1),
        )

        @JvmStatic
        fun `predicate different age data set`() = listOf(
            Arguments.of( { student: Student -> student.age > 18 }, 3),
            Arguments.of( { student: Student -> student.age == 21 }, 1),
            Arguments.of( { student: Student -> student.age in 19..21 }, 1),
        )
    }
}