package lois.lab2.fuzzy

/**
 * Class that represent two dimensional matrix.
 *
 * @author Q-YAA
 */
class Matrix(array: Array[Array[Float]]) {

    def this(h: Int, w: Int) = this(Array.ofDim[Float](h, w))

    val matrixData = array

    def fill(value: Float) {
        for (i <- 0 until matrixData.size) {
            for (j <- 0 until matrixData(0).size) {
                matrixData(i)(j) = value
            }
        }
    }

    /**
     * Returns the value that placed in i row and j column.
     *
     * @param i row number
     * @param j column number
     * @return Float value from the matrix
     */
    def getValue(i: Int, j: Int): Float = matrixData(i)(j)

    /**
     * Returns matrix row with i number.
     *
     * @param i row number
     * @return Array[Float] row from matrix
     */
    def getRow(i: Int): Array[Float] = matrixData(i)

    def getColumn(i: Int): Array[Float] = (for (j <- 0 until matrixData.size) yield matrixData(j)(i)).toArray

    /**
     * Set the value in i row and j column.
     *
     * @param i row number
     * @param j column number
     * @param value value to set
     */
    def setValue(i: Int, j: Int, value: Float) {
        matrixData(i)(j) = value
    }

    def inf: Array[Float] = (for (i <- 0 until matrixData(0).size) yield getColumn(i).min).toArray

    /**
     * Returns matrix width.
     *
     * @return matrix width
     */
    def width = if (matrixData.size > 0) matrixData(0).size else 0

    /**
     * Returns matrix height.
     *
     * @return matrix height
     */
    def height = matrixData.size

    override def toString: String = {
        val stringBuilder = new StringBuilder

        for (row <- matrixData) {
            for (element <- row) {
                stringBuilder.append(element).append("  ")
            }
            stringBuilder.append("\n")
        }

        stringBuilder.toString()
    }
}
