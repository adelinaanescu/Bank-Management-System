package com.example.finalprojectjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {
    Transaction t= new Transaction(1,"Food","Expense",100,"RON");


    @Test
    public void test2(){
        assertEquals("Food", t.getDescription());
    }
}