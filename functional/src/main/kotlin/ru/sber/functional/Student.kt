package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отсутствует",
    val age: Int,
    val averageRate: Double,
    val city: String = "Moscow",
    val specialization: String = "Отсутствует",
    val prevEducation: String? = null,
)
