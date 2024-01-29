package org.agty.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgtyUtilsTest {

    @org.junit.jupiter.api.Test
    void echo() {

    }

    @org.junit.jupiter.api.Test
    void testEcho() {
    }

    @org.junit.jupiter.api.Test
    void testEcho1() {
    }

    @org.junit.jupiter.api.Test
    void testEcho2() {
    }

    @org.junit.jupiter.api.Test
    void testEcho3() {
    }

    @org.junit.jupiter.api.Test
    void testEcho4() {
    }

    @org.junit.jupiter.api.Test
    void testEcho5() {
    }

    @org.junit.jupiter.api.Test
    void filesize_title() {
    }

    @org.junit.jupiter.api.Test
    void round() {
    }

    @org.junit.jupiter.api.Test
    void testRound() {
    }

    @org.junit.jupiter.api.Test
    void testRound1() {
    }

    @org.junit.jupiter.api.Test
    void md5() {
        assertEquals("202cb962ac59075b964b07152d234b70", AgtyUtils.md5("123"));
    }

    @org.junit.jupiter.api.Test
    void echoMSG() {
    }

    @org.junit.jupiter.api.Test
    void testEchoMSG() {
    }

    @org.junit.jupiter.api.Test
    void testEchoMSG1() {
    }

    @org.junit.jupiter.api.Test
    void date() {
    }

    @org.junit.jupiter.api.Test
    void dateConvert() {
    }

    @org.junit.jupiter.api.Test
    void sub_str() {
    }

    @org.junit.jupiter.api.Test
    void hencode() {
    }

    @org.junit.jupiter.api.Test
    void hdecode() {
    }

    @org.junit.jupiter.api.Test
    void where() {
    }

    @org.junit.jupiter.api.Test
    void testWhere() {
    }

    @org.junit.jupiter.api.Test
    void testWhere1() {
    }

    @org.junit.jupiter.api.Test
    void numeric() {
    }

    @org.junit.jupiter.api.Test
    void distance() {
    }

    @org.junit.jupiter.api.Test
    void arguments() {
    }

    @org.junit.jupiter.api.Test
    void isFile() {
    }

    @org.junit.jupiter.api.Test
    void isDir() {
    }

    @org.junit.jupiter.api.Test
    void implode() {
    }

    @org.junit.jupiter.api.Test
    void rand() {
    }

    @Test
    void stringIsSet() {
        String string = "1";
        String stringNull = null;
        String stringEmpty = "";
        String stringEmptySpaces = "    ";

        assertTrue(AgtyUtils.stringIsSet(string));
        assertFalse(AgtyUtils.stringIsSet(stringNull));
        assertFalse(AgtyUtils.stringIsSet(stringEmpty));
        assertFalse(AgtyUtils.stringIsSet(stringEmptySpaces));
    }
}