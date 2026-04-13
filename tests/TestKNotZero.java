import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestKNotZero {
    private static final String PREFIX = "./data/";

    public static final String WORD_HISTORY_SIZE14377_FILE = PREFIX + "word_history_size14377.csv";
    public static final String YEAR_HISTORY_FILE = PREFIX + "year_history.csv";
    public static final String SYNSETS_SIZE82191_FILE = PREFIX + "synsets_size82191.txt";
    public static final String HYPONYMS_SIZE82191_FILE = PREFIX + "hyponyms_size82191.txt";
    public static final String SYNSETS_EECS_FILE = PREFIX + "synsets_eecs.txt";
    public static final String HYPONYMS_EECS_FILE = PREFIX + "hyponyms_eecs.txt";
    public static final String WORD_HISTORY_EECS_FILE = PREFIX + "word_history_eecs.csv";

    // Single word, k != 0
    @Test
    public void testFoodCakeK5() {
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE14377_FILE, YEAR_HISTORY_FILE,
                SYNSETS_SIZE82191_FILE, HYPONYMS_SIZE82191_FILE);

        NgordnetQuery nq = new NgordnetQuery(List.of("food", "cake"), 1950, 1990, 5);
        String actual = handler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
    }

    // EECS dataset, k != 0 — from spec: words="CS61A", startYear=2010, endYear=2020, k=4
    @Test
    public void testEECSK4() {
        NgordnetQueryHandler handler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_EECS_FILE, YEAR_HISTORY_FILE,
                SYNSETS_EECS_FILE, HYPONYMS_EECS_FILE);

        NgordnetQuery nq = new NgordnetQuery(List.of("CS61A"), 2010, 2020, 4);
        String actual = handler.handle(nq);
        String expected = "[CS170, CS61A, CS61B, CS61C]";
        assertThat(actual).isEqualTo(expected);
    }
}
