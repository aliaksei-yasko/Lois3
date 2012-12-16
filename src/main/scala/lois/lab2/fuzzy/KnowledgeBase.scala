package lois.lab2.fuzzy

import collection.mutable

/**
 * Object that represent knowledge base.
 *
 * @author Q-YAA
 */
object KnowledgeBase {

    /**
     * List of facts.
     */
    val facts = new mutable.MutableList[FuzzySet]()

    /**
     * List of rules.
     */
    val rules = new mutable.MutableList[Rule]()

    /**
     * Used t-norm.
     */
    val tNorm = (x: Float, y: Float) => math.min(x, y)

    /**
     * Get fact by name.
     *
     * @param name name of the fact
     * @return fact with the given name
     */
    def getFact(name: String): FuzzySet = facts.find(fact => fact.name == name).get

    /**
     * Adds fact to the knowledge base.
     *
     * @param fact added fact
     */
    def addFact(fact: FuzzySet) {
        facts += fact
    }

    /**
     * Adds rule to the knowledge base.
     *
     * @param rule added rule
     */
    def addRule(rule: Rule) {
        rules += rule
    }

    /**
     * Method that execute direct fuzzy inference.
     *
     * @return array of the inference results
     */
    def reverseFuzzyInference() {

    }

    override def toString = "KnowledgeBase: {[Facts:" + facts + "]" + "[Rules" + rules + "]}"
}
