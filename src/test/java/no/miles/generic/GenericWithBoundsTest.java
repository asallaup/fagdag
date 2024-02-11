package no.miles.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericWithBoundsTest {

    @Test
    void testUpperBond() {
        var myClass = new GenericWithBounds();
        myClass.testUpperBond();
    }
}