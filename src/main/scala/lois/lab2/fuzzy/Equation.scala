package lois.lab2.fuzzy

/**
 * @author Q-YAA
 */
class Equation(val result: Float, val elements: List[(String, Float)]) {

    def checkSolution(solution: Array[Float]) = {
        val mins = for (i <- 0 until elements.size) yield math.min(elements(i)._2, solution(i))
        mins.max == result
    }

    override def toString: String = {
        val stringBuilder = new StringBuilder
        for (element <- elements) {
            stringBuilder.append("(").append(element._1).append(" min ").append(element._2).append(") ").append("max ")
        }
        stringBuilder.delete(stringBuilder.lastIndexOf("max") - 1, stringBuilder.lastIndexOf("max") + 3)
        stringBuilder.append("= ").append(result).toString()
    }
}
