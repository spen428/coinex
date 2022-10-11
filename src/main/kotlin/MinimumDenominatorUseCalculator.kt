import java.util.*


class MinimumDenominatorUseCalculator : ICalc {
    private val denominations = arrayOf(1, 5, 10, 50, 100, 500, 1000, 5000, 10000)

    override fun calculate(amount: Int): Exchange {
        val wallet = ArrayList<Int>()
        var remaining = amount
        while (remaining > 0) {
            val nextDenomination = getNextDenomination(remaining)
            remaining -= nextDenomination
            wallet.add(nextDenomination)
        }
        return Exchange(
            wallet,
            emptyList()
        )
    }

    private fun getNextDenomination(amount: Int): Int {
        return denominations.filter { it <= amount }.max()
    }

    override fun combinatorialSum(amount: Int): List<Exchange> {
        val results = CombinationSumGenerator.combinationSum(denominations.toIntArray(), amount)
        return results.map { Exchange(it, emptyList()) }
    }

    override fun minimumCombinatorialSum(amount: Int): List<Exchange> {
        val results = combinatorialSum(amount)
        val smallestExchangeSize = results.minOf { it.toPay.size + it.change.size }
        return results.filter { it.toPay.size + it.change.size == smallestExchangeSize }
    }
}
