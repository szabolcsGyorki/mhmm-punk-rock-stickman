package com.codecool.mhmm.stickman.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveHandlerTest {

    @Test
    void sanityCheck() {
        MoveHandler moveHandler = MoveHandler.getInstance();
        assertNotNull(moveHandler);
    }

}