package lois.lab2.parser

import lois.lab2.fuzzy.{KnowledgeBase, Matrix}
import sun.misc.IOUtils
import java.io.FileInputStream
import java.util.regex.Pattern

/**
 * @author Q-YAA
 */
class Parser(val fileName: String) {

    val text = new String(IOUtils.readFully(new FileInputStream(fileName), -1, true))

    private val reasonPattern = "reason = \\{(.+)\\}"
    private val consequentPattern = "consequent = \\{(.+)\\}"
    private val matrixRowPattern = "\\[(.+)\\]"

    def parse = new KnowledgeBase(parseReasons, parseMatrix, parseConsequent)

    private def parseReasons: List[String] = {
        val pattern = Pattern.compile(reasonPattern)
        val matcher = pattern.matcher(text)

        matcher.find()
        (matcher.group(1) + " ").split("; ").toList
    }

    private def parseMatrix: Matrix = {
        val pattern = Pattern.compile(matrixRowPattern)
        val matcher = pattern.matcher(text)

        var matrixStringData = List[List[String]]()

        while (matcher.find()) {
            matrixStringData = (matcher.group(1) + " ").split("; ").toList :: matrixStringData
        }

        matrixStringData = matrixStringData.reverse

        val matrixFloatData = Array.ofDim[Float](matrixStringData.size, matrixStringData(0).size)
        for (i <- 0 until matrixFloatData.size) {
            for (j <- 0 until matrixFloatData(0).size) {
                matrixFloatData(i)(j) = matrixStringData(i)(j).toFloat
            }
        }

        new Matrix(matrixFloatData)
    }

    private def parseConsequent: List[Float] = {
        val pattern = Pattern.compile(consequentPattern)
        val matcher = pattern.matcher(text)

        matcher.find()
        (matcher.group(1) + " ").split("; ").toList.map(string => string.toFloat)
    }
}
