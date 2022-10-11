import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class CalcTest {
    private val sut = MinimumDenominatorUseCalculator()

    @ParameterizedTest
    @ValueSource(ints = [1, 5, 10, 100, 500, 1000, 5000, 10000])
    fun `Given an amount that is exactly equal to a denomination, return only that denomination`(amount: Int) {
        val result = sut.calculate(amount)
        assertContentEquals(listOf(amount), result.toPay)
        assertContentEquals(emptyList(), result.change)
    }

    @ParameterizedTest
    @CsvSource(
        "1001 -> [1000 1]   -> []",
        "1006 -> [1000 5 1] -> []",
        "1009 -> [1000 10]  -> [1]",
    )
    fun `non trivial`(@ConvertWith(CsvStringArrayConverter::class) args: TestArgs) {
        val result = sut.calculate(args.amount)
        assertContentEquals(args.exchange.toPay, result.toPay)
        assertContentEquals(args.exchange.change, result.change)
    }

    @Test
    fun `Enumerate all unique combinations`() {
        val results = sut.combinatorialSum(349)
        assertEquals(5500, results.size)
    }

    @Test
    fun `Enumerate minimum combinations`() {
        val results = sut.minimumCombinatorialSum(349)
        assertEquals(1, results.size)
        assertEquals(0, results[0].change.size)
        assertEquals("[1, 1, 1, 1, 5, 10, 10, 10, 10, 100, 100, 100]", results[0].toPay.toString())
    }
}

data class TestArgs(
    val amount: Int,
    val exchange: Exchange
)
