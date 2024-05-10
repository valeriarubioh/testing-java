package org.example.testing1.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilTest {
    @Test
    public void weak_less_than_8letters(){
        assertEquals(PasswordUtil.SecurityLevel.WEAK,PasswordUtil.accessPasword("1234AA!"));
    }
    @Test
    public void weak_only_letters(){
        assertEquals(PasswordUtil.SecurityLevel.WEAK,PasswordUtil.accessPasword("abcdefgh"));
    }
    @Test
    public void medium_letters_and_numbers(){
        assertEquals(PasswordUtil.SecurityLevel.MEDIUM,PasswordUtil.accessPasword("abcd1234"));
    }
    @Test
    public void strong_letter_numbers_symbols(){
        assertEquals(PasswordUtil.SecurityLevel.STRONG,PasswordUtil.accessPasword("abcd1234!"));
    }
}