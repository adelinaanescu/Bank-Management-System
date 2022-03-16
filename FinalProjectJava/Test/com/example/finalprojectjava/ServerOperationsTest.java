package com.example.finalprojectjava;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class ServerOperationsTest {
    ServerOperations serverOperations;

    public ServerOperationsTest() {
        serverOperations = new ServerOperations();
    }

    @Test
    public void validateLogin() {

        assertEquals(true, serverOperations.validateLogin("adelina.anescu", "12345"));
    }
}