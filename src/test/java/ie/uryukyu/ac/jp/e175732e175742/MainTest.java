package ie.uryukyu.ac.jp.e175732e175742;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void transform() {
        int[] result = Main.transform("12");
        assertEquals(1, result[0]);
    }
}