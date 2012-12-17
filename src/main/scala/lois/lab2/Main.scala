package lois.lab2

import fuzzy.EquationsSystem
import parser.Parser
import java.io.FileWriter

/**
 * @author Q-YAA
 */
object Main {

    def main(args: Array[String]) {
        val parser = new Parser("knowledgeBase/knowledgeBase_0.txt")
        val knowledgeBase = parser.parse
        val inferenceResult = knowledgeBase.reverseFuzzyInference()

        printResults(inferenceResult._1, inferenceResult._2, knowledgeBase.reason.toArray)
    }

    private def printResults(equationSystem: EquationsSystem, solution: Array[Float], reason: Array[String]) {
        val fileWriter = new FileWriter("output.txt")

        fileWriter.write("--------------------------- Система уравнений ---------------------\n\n")
        fileWriter.write(equationSystem.toString)
        fileWriter.write("\n\n--------------------------- Решение ---------------------\n\n")
        fileWriter.write(solutionToString(solution, reason))

        fileWriter.close()
    }

    private def solutionToString(solution: Array[Float], reason: Array[String]): String = {
        val stringBuilder = new StringBuilder

        for (i <- 0 until solution.size) {
            stringBuilder.append(reason(i)).append(" = ").append(solution(i)).append("; ")
        }

        stringBuilder.toString()
    }
}
