package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(pred: (Student) -> Boolean): List<Student> {
        if (this::students.isInitialized) {
            return students.filter { student -> pred(student) }
        }

        return emptyList()
    }
}
