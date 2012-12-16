package lois.lab2.fuzzy

/**
 * @author Q-YAA
 */
class EquationsSystem(val equations: List[Equation]) {

    def getEquation(index: Int) = equations(index)

    override def toString: String = {
        val stringBuilder = new StringBuilder
        equations.foreach(equation => stringBuilder.append(equation.toString).append("\n"))
        stringBuilder.toString()
    }
}
