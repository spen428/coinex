import java.util.*

object CombinationSumGenerator {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        Arrays.sort(candidates)
        backtrack(candidates, 0, target, ArrayList(), result)
        return result
    }

    private fun backtrack(
        candidates: IntArray,
        start: Int,
        target: Int,
        list: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        if (target < 0) return
        if (target == 0) result.add(ArrayList(list))
        for (i in start until candidates.size) {
            list.add(candidates[i])
            backtrack(candidates, i, target - candidates[i], list, result)
            list.removeAt(list.size - 1)
        }
    }
}