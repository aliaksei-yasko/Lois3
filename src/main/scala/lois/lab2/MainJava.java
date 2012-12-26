package lois.lab2;

import lois.lab2.fuzzy.EquationsSystem;
import lois.lab2.fuzzy.KnowledgeBase;
import lois.lab2.parser.Parser;
import scala.Tuple3;
import scala.collection.immutable.List;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Q-YAA
 */
public class MainJava {

    public static void main(String[] args) throws IOException {
        String fileName = "knowledgeBase/knowledgeBase_3.txt";
        //String fileName = args[0];
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();
        Tuple3<EquationsSystem, float[], ArrayList<float[]>> inferenceResult = knowledgeBase.reverseFuzzyInference();

        printResults(inferenceResult._1(), inferenceResult._2(), inferenceResult._3(), knowledgeBase.reason());
    }

    private static void printResults(EquationsSystem equationSystem, float[] solution,
                                     ArrayList<float[]> minSolutions, List<String> reason) throws IOException {

        FileWriter fileWriter = new FileWriter("output.txt");

        fileWriter.write("--------------------------- Система уравнений ---------------------\n\n");
        fileWriter.write(equationSystem.toString());
        fileWriter.write("\n\n--------------------------- Решения ---------------------\n\n");
        fileWriter.write(solutionsToString(solution, reason, minSolutions));
        fileWriter.close();
    }

    private static String solutionsToString(float[] solution, List<String> reason, ArrayList<float[]> minSolutions) {
        StringBuilder stringBuilder = new StringBuilder();

        if (minSolutions.size() != 0) {
            for (float[] sol : minSolutions) {
                for (int i = 0; i < sol.length; i++) {
                    if (sol[i] != solution[i]) {
                        stringBuilder.append(reason.apply(i))
                                .append(" = ")
                                .append("[")
                                .append(sol[i])
                                .append("; ")
                                .append(solution[i])
                                .append("]; ");
                    } else {
                        stringBuilder.append(reason.apply(i)).append(" = ").append(solution[i]).append("; ");
                    }
                }
                stringBuilder.append("\n");
            }
        } else {
            for (int i = 0; i < solution.length; i++) {
                stringBuilder.append(reason.apply(i)).append(" = ").append(solution[i]).append("; ");
            }
        }
        return stringBuilder.toString();
    }
}
