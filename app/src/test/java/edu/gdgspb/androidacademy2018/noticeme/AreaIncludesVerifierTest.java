package edu.gdgspb.androidacademy2018.noticeme;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AreaIncludesVerifierTest {

    @Test
    public void isInArea() {
        AreaIncludesVerifier verifier = new AreaIncludesVerifier(30.333825, 59.974009, 1000.0);
        //DataArt
        assertTrue(verifier.isInArea(30.333826, 59.974009));
    }

    @Test
    public void isNotArea() {
        AreaIncludesVerifier verifier = new AreaIncludesVerifier(30.336800, 59.94321, 1000.0);
        //Шушары
        assertFalse(verifier.isInArea(30.382030, 59.793775));
    }
}

