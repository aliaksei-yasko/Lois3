package lois.lab2

import parser.Parser

/**
 * @author Q-YAA
 */
object Main {

    def main(args: Array[String]) {
        val parser = new Parser("knowledgeBase/knowledgeBase_0.txt")
        val knowledgeBase = parser.parse
    }
}
