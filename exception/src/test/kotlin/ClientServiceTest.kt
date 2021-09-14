import com.google.gson.Gson
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest
    @MethodSource("success save data set")
    fun `success save client`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest
    @MethodSource("fail save client - bad email data set")
    fun `fail save client - bad email`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val exception = assertThrows<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_EMAIL)
    }

    @ParameterizedTest
    @MethodSource("fail save client - bad snils data set")
    fun `fail save client - bad snils`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val exception = assertThrows<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)
    }

    @ParameterizedTest
    @MethodSource("fail save client - all data corrupted data set")
    fun `fail save client - all data corrupted`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LONG)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
    }

    @ParameterizedTest
    @MethodSource("fail save client - bad phone data set")
    fun `fail save client - bad phone`(sourcePath: String) {
        val client = getClientFromJson(sourcePath)
        val exception = assertThrows<ValidationException> {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

    companion object {
        @JvmStatic
        fun `success save data set`() = listOf(
            Arguments.of("/success/user.json"),
            Arguments.of("/success/user_with_another_snils.json"),
            Arguments.of("/success/user_with_upper_email.json")
        )

        @JvmStatic
        fun `fail save client - bad email data set`() = listOf(
            Arguments.of("/fail/user_email_corrupted.json"),
        )

        @JvmStatic
        fun `fail save client - bad snils data set`() = listOf(
            Arguments.of("/fail/user_with_bad_snils.json"),
            Arguments.of("/fail/user_snils_corrupted.json")
        )

        @JvmStatic
        fun `fail save client - all data corrupted data set`() = listOf(
            Arguments.of("/fail/user_all_data_corrupted.json"),
            Arguments.of("/fail/user_data_corrupted.json")
        )

        @JvmStatic
        fun `fail save client - bad phone data set`() = listOf(
            Arguments.of("/fail/user_with_bad_phone.json"),
        )
    }
}