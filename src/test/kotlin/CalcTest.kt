import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContentEquals

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
        val results = sut.combinatorialSum(5)
        val expected = listOf(
            Exchange(listOf(5), emptyList()),
            Exchange(listOf(1, 1, 1, 1, 1), emptyList()),
        ).sortedBy { it.toString() }
        assertContentEquals(
            expected,
            results
        )
    }
}

data class TestArgs(
    val amount: Int,
    val exchange: Exchange
)
