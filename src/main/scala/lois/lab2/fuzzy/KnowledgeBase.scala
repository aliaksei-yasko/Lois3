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

    }

    override def toString =
        "KnowledgeBase: {[Reason: " + reason + "]" + "[Matrix: " + matrix + "]" + "[Consequent: " + consequent + "]}"
}
