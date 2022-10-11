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
        return emptyList()
    }
}