class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_PHONE(101, "Номер телефона должен состоять из цифр и начинаться на 7 или 8"),
    INVALID_NAME(102, "Имя и фамилия должны быть написаны на кириллице"),
    INVALID_EMAIL(103, "Почта должна быть написана на латинице"),
    INVALID_SNILS(104, "Снилс должен состоять только из цифр"),
    NULL_VALUE(105, "Значение равно null"),
    INVALID_LONG(106, "Недопустимая длина значения")
}