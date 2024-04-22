package ro.unibuc.hello.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxCalculatorTest{

    ////// Class Partitioning //////

    @Test
    public void testNegativeSalary() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaxCalculator(-1, 1000, 500, true, true).calculateTax();
        });
    }

    @Test
    public void testNegativeDivident() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaxCalculator(2500, -1, 500, true, false).calculateTax();});
    }

    @Test
    public void testNegativeRental() {
        assertThrows(IllegalArgumentException.class, () -> { new TaxCalculator(2500, 1000, -1, false, true).calculateTax();});
    }


    @Test
    public void testClassPartition1_SalaryLow_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(155, new TaxCalculator(2500, 1000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition2_SalaryLow_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(155, new TaxCalculator(2500, 1000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition3_SalaryLow_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(355, new TaxCalculator(2500, 1000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition4_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(405, new TaxCalculator(2500, 1000, 500, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition5_SalaryLow_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2035, new TaxCalculator(2500, 12000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition6_SalaryLow_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(2035, new TaxCalculator(2500, 12000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition7_SalaryLow_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(2235, new TaxCalculator(2500, 12000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition8_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2285, new TaxCalculator(2500, 12000, 500, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition9_SalaryMedium_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(275, new TaxCalculator(4500, 1000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition10_SalaryMedium_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(455, new TaxCalculator(4500, 1000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition11_SalaryMedium_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(635, new TaxCalculator(4500, 1000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition12_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(905, new TaxCalculator(4500, 1000, 500, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition13_SalaryMedium_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2155, new TaxCalculator(4500, 12000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition14_SalaryMedium_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(2335, new TaxCalculator(4500, 12000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition15_SalaryMedium_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(2515, new TaxCalculator(4500, 12000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition16_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2785, new TaxCalculator(4500, 12000, 500, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition17_SalaryHigh_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(915, new TaxCalculator(8000, 1000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition18_SalaryHigh_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(1555, new TaxCalculator(8000, 1000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition19_SalaryHigh_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(1555, new TaxCalculator(8000, 1000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition20_SalaryHigh_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2355, new TaxCalculator(8000, 1000, 500, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition21_SalaryHigh_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2795, new TaxCalculator(8000, 12000, 500, true, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition22_SalaryHigh_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(3435, new TaxCalculator(8000, 12000, 500, true, false).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition23_SalaryHigh_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(3435, new TaxCalculator(8000, 12000, 500, false, true).calculateTax(), 1.0);
    }

    @Test
    public void testClassPartition24_SalaryHigh_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(4235, new TaxCalculator(8000, 12000, 500, false, false).calculateTax(), 1.0);
    }

////// Boundary values //////

    @Test
    public void testBoundaryValue1_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(0, new TaxCalculator(0, 0, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue2_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(800, new TaxCalculator(0, 9999, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue3_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(800, new TaxCalculator(0, 10000, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue4_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(300, new TaxCalculator(2999, 0, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue5_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100, new TaxCalculator(2999, 9999, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue6_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100, new TaxCalculator(2999, 10000, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue7_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(300, new TaxCalculator(3000, 0, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue8_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100, new TaxCalculator(3000, 9999, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue9_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100, new TaxCalculator(3000, 10000, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue10_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1200, new TaxCalculator(5999, 0, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue11_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000, new TaxCalculator(5999, 9999, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue12_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000, new TaxCalculator(5999, 10000, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue13_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1200, new TaxCalculator(6000, 0, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue14_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000, new TaxCalculator(6000, 9999, 0, false, false).calculateTax(), 1.0);
    }

    @Test
    public void testBoundaryValue15_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000, new TaxCalculator(6000, 10000, 0, false, false).calculateTax(), 1.0);
    }



}