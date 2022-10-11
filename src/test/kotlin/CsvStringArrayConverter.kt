import org.junit.jupiter.params.converter.SimpleArgumentConverter

object CsvStringArrayConverter : SimpleArgumentConverter() {
    public override fun convert(source: Any?, targetType: Class<*>?): Any {
        if (source !is String)
            TODO("Not yet implemented")

        val spl = source.split("->")
            .map { it.trim() }

        return TestArgs(
            spl[0].toInt(),
            Exchange(
                listFrom(spl[1]),
                listFrom(spl[2]),
            )
        )
    }

    private fun listFrom(s: String): List<Int> {
        return s.replace("[", "")
            .replace("]", "")
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .map { it.toInt() }
    }
}
