import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CsvStringArrayConverterTest {
    @Test
    fun `Happy path`() {
        val result = CsvStringArrayConverter.convert("1 -> [1 2] -> [3]", Array::class.java) as TestArgs
        assertEquals(TestArgs(1, Exchange(listOf(1, 2), listOf(3))), result)
    }
}