abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "(7|8)[0-9]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_PHONE)

        return listOf()
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[аА-яЯ]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > 16)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_NAME)

        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "^[aA-zZ0-9]+@[aA-zZ]+\\.[aA-zZ]+$".toRegex()

        if (value == null)
            return listOf(ErrorCode.NULL_VALUE)

        if (value.length > 32)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_EMAIL)

        return listOf()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[0-9]+".toRegex()

        if (value == null)
            return listOf(ErrorCode.INVALID_EMAIL)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LONG)

        if (!value.matches(regex))
            return listOf(ErrorCode.INVALID_SNILS)

        val snils = intStringToIntArray(value.dropLast(2))
        val controlNumber = value.takeLast(2).toInt()

        val calculateControlNumber = calculateControlNumber(snils)

        if (calculateControlNumber != controlNumber)
            return listOf(ErrorCode.INVALID_SNILS)

        return listOf()
    }

    private fun calculateControlNumber(snils: IntArray): Int {
        var controlNumber = 0
        var index = 9

        for (digit in snils) {
            controlNumber += digit * index
            index--
        }

        while (controlNumber > 101) {
            controlNumber %= 101
        }

        if (controlNumber == 100 || controlNumber == 101) {
            controlNumber = 0
        }

        return controlNumber
    }

    private fun intStringToIntArray(string: String): IntArray {
        return string.map { it.toString().toInt() }.toIntArray()
    }
}