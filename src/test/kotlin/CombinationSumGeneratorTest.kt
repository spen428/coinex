import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class CombinationSumGeneratorTest {
    @Test
    fun `Happy path`() {
        val expected = listOf(
            listOf(2, 2, 2, 2),
            listOf(2, 3, 3),
            listOf(3, 5),
        )
        val actual = CombinationSumGenerator.combinationSum(intArrayOf(2, 3, 5), 8);
        assertContentEquals(expected, actual)
    }
}