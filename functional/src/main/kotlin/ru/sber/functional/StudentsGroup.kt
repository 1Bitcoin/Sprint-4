package ru.sber.functional

class StudentsGroup {

    private lateinit var students: List<Student>

    fun filterByPredicate(pred: (Student) -> Boolean): List<Student> {
        if (this::students.isInitialized) {
            return students.filter { student -> pred(student) }
        }

        return emptyList()
    }

    fun init (sourceStudentList: List<Student>) {
        students = sourceStudentList
    }
}
