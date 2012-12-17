package lois.lab2;

import java.io.FileWriter;
import java.io.IOException;

import lois.lab2.fuzzy.EquationsSystem;
import lois.lab2.fuzzy.KnowledgeBase;
import lois.lab2.parser.Parser;
import scala.Tuple2;
import scala.collection.immutable.List;

/**
 * @author Q-YAA
 */
public class MainJava {

    public static void main(String[] args) throws IOException {
        String fileName = "knowledgeBase/knowledgeBase_0.txt";
        //String fileName = args[0];
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();
        Tuple2<EquationsSystem, float[]> inferenceResult = knowledgeBase.reverseFuzzyInference();

        printResults(inferenceResult._1(), inferenceResult._2(), knowledgeBase.reason());
    }

    private static void printResults(EquationsSystem equationSystem, float[] solution, List<String> reason)
        throws IOException {

        FileWriter fileWriter = new FileWriter("output.txt");

        fileWriter.write("--------------------------- Система уравнений ---------------------\n\n");
        fileWriter.write(equationSystem.toString());
        fileWriter.write("\n\n--------------------------- Решение ---------------------\n\n");
        fileWriter.write(solutionToString(solution, reason));

        fileWriter.close();
    }

    private static String solutionToString(float[] solution, List<String> reason) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < solution.length; i++) {
            stringBuilder.append(reason.apply(i)).append(" = ").append(solution[i]).append("; ");
        }

        return stringBuilder.toString();
    }
}
