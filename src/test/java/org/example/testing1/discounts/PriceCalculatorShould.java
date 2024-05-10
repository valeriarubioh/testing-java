package org.example.testing1.discounts;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceCalculatorShould {
    @Test
    public void total_zero_when_are_prices(){
        PriceCalculator priceCalculator = new PriceCalculator();
        assertEquals(0.0, priceCalculator.getTotal(),0);
    }
    @Test
    public void total_is_sum_of_prices(){
        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.addPrice(10.2);
        priceCalculator.addPrice(15.0);
        assertEquals(25.2, priceCalculator.getTotal(),0);
    }
    @Test
    public void apply_discount_to_prices(){
        PriceCalculator priceCalculator = new PriceCalculator();
        priceCalculator.addPrice(12.5);
        priceCalculator.addPrice(17.5);
        priceCalculator.setDiscount(25);
        assertEquals(22.5, priceCalculator.getTotal(),0);
    }
}