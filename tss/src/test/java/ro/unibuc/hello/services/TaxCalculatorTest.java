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
    public void testNegativeDividend() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaxCalculator(2500, -1, 500, true, false).calculateTax();
        });
    }

    @Test
    public void testNegativeRental() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaxCalculator(2500, 1000, -1, false, true).calculateTax();
        });
    }


    @Test
    public void testClassPartition1_SalaryLow_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(155.0, new TaxCalculator(2500, 1000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition2_SalaryLow_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(155.0, new TaxCalculator(2500, 1000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition3_SalaryLow_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(355.0, new TaxCalculator(2500, 1000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition4_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(405.0, new TaxCalculator(2500, 1000, 500, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition5_SalaryLow_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2035.0, new TaxCalculator(2500, 12000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition6_SalaryLow_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(2035.0, new TaxCalculator(2500, 12000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition7_SalaryLow_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(2235.0, new TaxCalculator(2500, 12000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition8_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2285.0, new TaxCalculator(2500, 12000, 500, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition9_SalaryMedium_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(275.0, new TaxCalculator(4500, 1000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition10_SalaryMedium_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(455.0, new TaxCalculator(4500, 1000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition11_SalaryMedium_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(635.0, new TaxCalculator(4500, 1000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition12_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(905.0, new TaxCalculator(4500, 1000, 500, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition13_SalaryMedium_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2155.0, new TaxCalculator(4500, 12000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition14_SalaryMedium_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(2335.0, new TaxCalculator(4500, 12000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition15_SalaryMedium_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(2515.0, new TaxCalculator(4500, 12000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition16_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2785.0, new TaxCalculator(4500, 12000, 500, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition17_SalaryHigh_DividendLow_WithDiploma_WithFoodTicket() {
        assertEquals(915.0, new TaxCalculator(8000, 1000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition18_SalaryHigh_DividendLow_WithDiploma_WithoutFoodTicket() {
        assertEquals(1555.0, new TaxCalculator(8000, 1000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition19_SalaryHigh_DividendLow_WithoutDiploma_WithFoodTicket() {
        assertEquals(1555.0, new TaxCalculator(8000, 1000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition20_SalaryHigh_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2355.0, new TaxCalculator(8000, 1000, 500, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition21_SalaryHigh_DividendHigh_WithDiploma_WithFoodTicket() {
        assertEquals(2795.0, new TaxCalculator(8000, 12000, 500, true, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition22_SalaryHigh_DividendHigh_WithDiploma_WithoutFoodTicket() {
        assertEquals(3435.0, new TaxCalculator(8000, 12000, 500, true, false).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition23_SalaryHigh_DividendHigh_WithoutDiploma_WithFoodTicket() {
        assertEquals(3435.0, new TaxCalculator(8000, 12000, 500, false, true).calculateTax(), 0.01);
    }

    @Test
    public void testClassPartition24_SalaryHigh_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(4235.0, new TaxCalculator(8000, 12000, 500, false, false).calculateTax(), 0.01);
    }

////// Boundary values //////

    @Test
    public void testBoundaryValue1_SalaryLow_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(0.0, new TaxCalculator(0, 0, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue2_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(800.0, new TaxCalculator(0, 10000, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue3_SalaryLow_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1800.08, new TaxCalculator(0, 10001, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue4_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(300.0, new TaxCalculator(3000, 0, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue5_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100.0, new TaxCalculator(3000, 10000, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue6_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2100.08, new TaxCalculator(3000, 10001, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue7_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(300.3, new TaxCalculator(3001, 0, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue8_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1100.3, new TaxCalculator(3001, 10000, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue9_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2100.38, new TaxCalculator(3001, 10001, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue10_SalaryMedium_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1200.0, new TaxCalculator(6000, 0, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue11_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000.0, new TaxCalculator(6000, 10000, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue12_SalaryMedium_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(3000.08, new TaxCalculator(6000, 10001, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue13_SalaryHigh_DividendLow_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(1200.5, new TaxCalculator(6001, 0, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue14_SalaryHigh_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(2000.5, new TaxCalculator(6001, 10000, 0, false, false).calculateTax(), 0.01);
    }

    @Test
    public void testBoundaryValue15_SalaryHigh_DividendHigh_WithoutDiploma_WithoutFoodTicket() {
        assertEquals(3000.58, new TaxCalculator(6001, 10001, 0, false, false).calculateTax(), 0.01);
    }

}