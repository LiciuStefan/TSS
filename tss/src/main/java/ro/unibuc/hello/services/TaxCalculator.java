package ro.unibuc.hello.services;

public class TaxCalculator {
    private double salary;
    private double dividends;
    private double rentalIncome;
    private Boolean hasDiploma;
    private Boolean optsForFoodTickets;

    public TaxCalculator(double salary, double dividends, double rentalIncome, Boolean hasDiploma, Boolean optsForFoodTickets) {
        if (salary < 0 || dividends < 0 || rentalIncome < 0) {
            throw new IllegalArgumentException("Income values cannot be negative");
        }
        this.salary = salary;
        this.dividends = dividends;
        this.rentalIncome = rentalIncome;
        this.hasDiploma = hasDiploma;
        this.optsForFoodTickets = optsForFoodTickets;
    }

    public double calculateTax() {
        double totalTax = 0;

        // Salary tax calculation
        double taxableSalary = optsForFoodTickets ? salary * 0.8 : salary;
        if (taxableSalary >= 6001) {
            totalTax += (taxableSalary - 6000) * 0.4 + 3000 * 0.2;
        } else if (taxableSalary >= 3001) {
            totalTax += (taxableSalary - 3000) * 0.2;
        }

        // Income tax calculation
        if (!hasDiploma) {
            totalTax += taxableSalary * 0.1;
        }

        // Dividends tax calculation
        totalTax += dividends * 0.08;
        if (dividends >= 10001) {
            totalTax += 1000;
        }

        // Rental income tax calculation
        totalTax += rentalIncome * 0.15;

        return totalTax;
    }

    public void Main(String[] args)
    {
        TaxCalculator a = new TaxCalculator(3500, 0, 2000, false, true);
        System.out.println(a.calculateTax());
    }


}
