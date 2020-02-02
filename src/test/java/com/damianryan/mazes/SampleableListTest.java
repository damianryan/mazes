package com.damianryan.mazes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SampleableListTest {

    @DisplayName("is empty when new")
    @Test
    void newListIsEmpty() {
        assertEquals(0, new SampleableList<>().size(), () -> "a new SampleableList should have 0 elements");
    }

    @DisplayName("returns null if sampled when empty")
    @Test
    void emptyListSampleReturnsNull() {
        assertNull(new SampleableList<>().sample(), () -> "a smaple from an empty list should be null");
    }

    @DisplayName("returns the singleton entry if sampled when containing only one entry")
    @Test
    void singletonListSampleReturnsSingleton() {
        assertEquals("Damian",
                     new SampleableList<>(Collections.singletonList("Damian")).sample(),
                     () -> "a sample from a singleton list should return the singleton entry");
    }
}