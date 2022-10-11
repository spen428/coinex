interface ICalc {
    fun calculate(amount: Int): Exchange
    fun combinatorialSum(amount: Int): List<Exchange>
    fun minimumCombinatorialSum(amount: Int): List<Exchange>
}