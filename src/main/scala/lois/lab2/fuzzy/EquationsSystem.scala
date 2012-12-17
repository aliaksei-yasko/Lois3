package lois.lab2.fuzzy

/**
 * @author Q-YAA
 */
class EquationsSystem(val equations: List[Equation]) {

    def getEquation(index: Int) = equations(index)

    def height = equations.size

    def width = equations(0).elements.size

    def checkSolution(solution: Array[Float]) = !equations.map(equation => equation.checkSolution(solution)).contains(false)

    override def toString: String = {
        val stringBuilder = new StringBuilder
        equations.foreach(equation => stringBuilder.append(equation.toString).append("\n"))
        stringBuilder.toString()
    }
}
