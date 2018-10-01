package com.codecool.mhmm.stickman.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelGeneratorTest {

    @Test
    void sanityCheck() {
        LevelGenerator levelGenerator = LevelGenerator.getInstance();
        assertNotNull(levelGenerator);
    }

}