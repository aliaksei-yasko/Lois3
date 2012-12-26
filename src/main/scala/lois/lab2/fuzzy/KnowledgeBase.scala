package lois.lab2.fuzzy

import lois.lab2.Program
import scala.collection.JavaConversions._
import java.util

/**
 * Object that represent knowledge base.
 *
 * @author Q-YAA
 */
class KnowledgeBase(val reason: List[String], val matrix: Matrix, val consequent: List[Float]) {

    def equationSystem = createEquationSystem(reason, matrix, consequent)

    /**
     * Method that execute reverse fuzzy inference.
     *
     * @return array of the inference results
     */
    def reverseFuzzyInference(): (EquationsSystem, Array[Float], util.ArrayList[Array[Float]]) = {
        val preSolutionMatrix = createPreSolutionMatrix(equationSystem, (x: Float, y: Float) => x > y)
        val solution = preSolutionMatrix.inf

        var minSolution = preSolutionMatrix.inf
        var minSolutions = new java.util.ArrayList[Array[Float]]()

        //max solution
        if (!equationSystem.checkSolution(solution)) {
            throw new IllegalStateException("Solution dosen't exist.")
        }

        var sss = List[Float]()
        //check maximal solution
        for (x <- 0 until minSolution.length) {
            if (minSolution.apply(x) == 1f) {
                //can be 0? If no -> try to find minimum value from consequent
                minSolution.update(x, 0f)
                if (!equationSystem.checkSolution(minSolution)) {
                    sss = consequent.sortWith((x, y) => x > y)
                    var tru = true
                    var lnght = sss.size - 1
                    while (tru && lnght > -1) {
                        var number = sss.apply(lnght)
                        lnght = lnght - 1
                        minSolution.update(x, number)
                        if (equationSystem.checkSolution(minSolution)) {
                            tru = false
                        }
                    }
                    if (tru == true) {
                        minSolution.update(x, 1f)
                    }
                }
            }
        }

        //combine all possible solutions with 0
        var size = solution.length
        val array: Array[Int] = Array.fill[Int](size)(0)
        var set: java.util.Set[String] = null

        //all zeros
        for (x <- 0 until solution.length - 1) {
            array.update(x, 1)
            set = Program.showPermutations(array)
            for (s <- set) {
                var tmpMinSolution = minSolution.clone()
                for (x <- 0 until s.length) {
                    if (s.charAt(x).equals('0')) {
                        tmpMinSolution.update(x, 0)
                    }
                }
                if (equationSystem.checkSolution(tmpMinSolution)) {
                    minSolutions.add(tmpMinSolution)
                }
            }
            set.clear()
        }

        //contains positions of 0 in solutions
        var listOfZeros: Array[String] = Array.fill[String](minSolutions.size())("")

        //filter minimal solutions
        for (x <- 0 until minSolutions.size()) {
            var oneSolution = minSolutions.apply(x)
            for (y <- 0 until oneSolution.length) {
                if (oneSolution.apply(y) == 0) {
                    listOfZeros.update(x, listOfZeros.apply(x) + y)
                }
            }
        }

        var contains = true
        for (x <- 0 until listOfZeros.length) {
            for (y <- 0 until listOfZeros.length) {
                var contains = true
                if (listOfZeros.apply(x) != -1 && listOfZeros.apply(y) != -1 && x != y) {
                    for (z <- 0 until listOfZeros.apply(y).size) {
                        if (!(listOfZeros.apply(x).contains(listOfZeros.apply(y).charAt(z)))) {
                            contains = false
                        }
                    }
                    if (contains) listOfZeros.update(y, "")
                }
            }
        }

        var finalMinSolutions = new java.util.ArrayList[Array[Float]]()
        for (x <- 0 until listOfZeros.length) {
            if (listOfZeros.apply(x) != "") {
                finalMinSolutions.add(minSolutions.apply(x))
            }
        }

        (equationSystem, solution, finalMinSolutions)
    }

    private def createEquationSystem(reason: List[String], matrix: Matrix, consequent: List[Float]): EquationsSystem = {

        val equations = Array.ofDim[Equation](matrix.height)
        for (i <- 0 until matrix.height) {
            val equationElements = for (j <- 0 until matrix.width) yield (reason(j), matrix.getValue(i, j))
            equations(i) = new Equation(consequent(i), equationElements.toList)
        }

        new EquationsSystem(equations.toList)
    }

    private def createPreSolutionMatrix(equationSystem: EquationsSystem, f: (Float, Float) => Boolean): Matrix = {
        val solutionMatrix = new Matrix(equationSystem.height, equationSystem.width)
        solutionMatrix.fill(1)

        for (i <- 0 until equationSystem.height) {

            val equation = equationSystem.getEquation(i)
            for (j <- 0 until equation.elements.size) {
                if (f(equation.elements(j)._2, equation.result)) {
                    solutionMatrix.setValue(i, j, equation.result)
                }
            }
        }

        solutionMatrix
    }

    override def toString =
        "KnowledgeBase: {[Reason: " + reason + "]" + "[Matrix: " + matrix + "]" + "[Consequent: " + consequent + "]}"
}
