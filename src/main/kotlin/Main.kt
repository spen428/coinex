fun main(args: Array<String>) {
    Main(MinimumDenominatorUseCalculator()).run(args)
}

class Main(private val calc: ICalc) {
    fun run(args: Array<String>) {
        calc.calculate(args[0].toInt())
    }
}