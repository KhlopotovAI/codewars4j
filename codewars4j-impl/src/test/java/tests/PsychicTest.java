package tests;

import com.khlopotov.ai.Psychic;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PsychicTest {
    @Test
    public void testRandom() {
        IntStream.range(0, 5).forEach(i -> assertEquals(Psychic.guess(), java.lang.Math.random(), 0));
    }
}
