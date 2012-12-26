import org.junit.Assert;
import org.junit.Test;

import lois.lab2.fuzzy.KnowledgeBase;
import lois.lab2.parser.Parser;

import static org.hamcrest.core.Is.is;

/**
 * @author Q-YAA
 */
public class EquationsSystemTest {


    @Test
    public void testCheckSolution1() {
        float[] solution = new float[] {0.0f, 0.74f, 0.0f, 0.0f, 0.65f, 0.87f, 0.13f, 0.44f};

        String fileName = "knowledgeBase/knowledgeBase_2.txt";
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();

        boolean result = knowledgeBase.equationSystem().checkSolution(solution);

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testCheckSolution2() {
        float[] solution = new float[] {0.13f, 0.74f, 0.0f, 0.0f, 0.65f, 0.87f, 0.0f, 0.44f};

        String fileName = "knowledgeBase/knowledgeBase_2.txt";
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();

        boolean result = knowledgeBase.equationSystem().checkSolution(solution);

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testCheckSolution3() {
        float[] solution = new float[] {0.0f, 0.74f, 0.0f, 0.13f, 0.65f, 0.87f, 0.0f, 0.44f};

        String fileName = "knowledgeBase/knowledgeBase_2.txt";
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();

        boolean result = knowledgeBase.equationSystem().checkSolution(solution);

        Assert.assertThat(result, is(true));
    }

    @Test
    public void testCheckSolution4() {
        float[] solution = new float[] {0.0f, 0.74f, 0.13f, 0.0f, 0.65f, 0.87f, 0.0f, 0.44f};

        String fileName = "knowledgeBase/knowledgeBase_2.txt";
        Parser parser = new Parser(fileName);
        KnowledgeBase knowledgeBase = parser.parse();

        boolean result = knowledgeBase.equationSystem().checkSolution(solution);

        Assert.assertThat(result, is(true));
    }
}
