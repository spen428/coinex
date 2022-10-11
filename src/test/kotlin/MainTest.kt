import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MainTest {
    @Test
    fun `Given user input is valid, calls calculator class with parsed input`() {
        val userInput = arrayOf("1000")
        val calculatorMock = object : ICalc {
            var lastInput: Int = -1
            override fun calculate(amount: Int): Exchange {
                lastInput = amount
                return Exchange(emptyList(), emptyList())
            }

            override fun combinatorialSum(amount: Int): List<Exchange> {
                TODO("Not yet implemented")
            }
        }

        Main(calculatorMock).run(userInput)

        assertEquals(1000, calculatorMock.lastInput)
    }
}