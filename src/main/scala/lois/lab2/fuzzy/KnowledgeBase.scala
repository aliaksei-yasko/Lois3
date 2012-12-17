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
    def reverseFuzzyInference() {
        val equationSystem = createEquationSystem(reason, matrix, consequent)
        System.out.println(equationSystem)
    }

    private def createEquationSystem(reason: List[String], matrix: Matrix, consequent: List[Float]): EquationsSystem = {

        val equations = Array.ofDim[Equation](matrix.height)
        for (i <- 0 until matrix.height) {

            val equationElements = Array.ofDim[(String, Float)](matrix.width)
            for (j <- 0 until matrix.width) {
                equationElements(j) = (reason(j), matrix.getValue(i, j))
            }

            equations(i) = new Equation(consequent(i), equationElements.toList)
        }

        new EquationsSystem(equations.toList)
    }

    override def toString =
        "KnowledgeBase: {[Reason: " + reason + "]" + "[Matrix: " + matrix + "]" + "[Consequent: " + consequent + "]}"
}
