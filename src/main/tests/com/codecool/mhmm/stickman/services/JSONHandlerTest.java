package com.codecool.mhmm.stickman.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONHandlerTest {

    @Test
    void sanityCheck() {
        JSONHandler jsonHandler = JSONHandler.getInstance();
        assertNotNull(jsonHandler);
    }

}