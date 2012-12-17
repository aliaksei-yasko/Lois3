package lois.lab2.fuzzy

/**
 * Object that represent knowledge base.
 *
 * @author Q-YAA
 */
class KnowledgeBase(val reason: List[String], val matrix: Matrix, val consequent: List[Float]) {

    /**
     * Method that execute reverse fuzzy inference.
     *
     * @return array of the inference results
     */
    def reverseFuzzyInference(): (EquationsSystem, Array[Float]) = {
        val equationSystem = createEquationSystem(reason, matrix, consequent)
        val preSolutionMatrix = createPreSolutionMatrix(equationSystem)
        val solution = preSolutionMatrix.inf

        if (!equationSystem.checkSolution(solution)) {
            throw new IllegalStateException("Solution dosen't exist.")
        }

        (equationSystem, solution)
    }

    private def createEquationSystem(reason: List[String], matrix: Matrix, consequent: List[Float]): EquationsSystem = {

        val equations = Array.ofDim[Equation](matrix.height)
        for (i <- 0 until matrix.height) {
            val equationElements = for (j <- 0 until matrix.width) yield (reason(j), matrix.getValue(i, j))
            equations(i) = new Equation(consequent(i), equationElements.toList)
        }

        new EquationsSystem(equations.toList)
    }

    private def createPreSolutionMatrix(equationSystem: EquationsSystem): Matrix = {
        val solutionMatrix = new Matrix(equationSystem.height, equationSystem.width)
        solutionMatrix.fill(1)

        for (i <- 0 until equationSystem.height) {

            val equation = equationSystem.getEquation(i)
            for (j <- 0 until equation.elements.size) {
                if (equation.elements(j)._2 > equation.result) {
                    solutionMatrix.setValue(i, j, equation.result)
                }
            }
        }

        solutionMatrix
    }

    override def toString =
        "KnowledgeBase: {[Reason: " + reason + "]" + "[Matrix: " + matrix + "]" + "[Consequent: " + consequent + "]}"
}
