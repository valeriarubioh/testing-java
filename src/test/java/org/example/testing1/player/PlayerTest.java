package org.example.testing1.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void losses_when_number_too_low(){
        Dice dice = Mockito.mock(Dice.class); //crear un dado simulado en vez de Dice dice = new Dice(6);
        Mockito.when(dice.roll()).thenReturn(2);
        Player player = new Player(dice,5);
        assertFalse(player.play());
    }
    @Test
    public void win_when_number_is_big(){
        Dice dice = Mockito.mock(Dice.class); //crear un dado simulado en vez de Dice dice = new Dice(6);
        Mockito.when(dice.roll()).thenReturn(4);
        Player player = new Player(dice,3);
        assertTrue(player.play());
    }
}