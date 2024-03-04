package example;

import java.util.List;

import static example.Movie.MovieType.NEW_RELEASE;

@SuppressWarnings("StringConcatenationInLoop")
class Customer {

    public static String getStatement(String name, List<Rental> rentals) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        for (Rental rental : rentals) {
            double amount = 0;
            //determine amounts for rental line
            switch (rental.getMovie().getPriceCode()) {
                case REGULAR -> {
                    amount += 2;
                    if (rental.getDaysRented() > 2)
                        amount += (rental.getDaysRented() - 2) * 1.5;
                }
                case NEW_RELEASE -> amount += rental.getDaysRented() * 3;
                case CHILDRENS -> {
                    amount += 1.5;
                    if (rental.getDaysRented() > 3)
                        amount += (rental.getDaysRented() - 3) * 1.5;
                }
            }
            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints ++;
            //show figures for this rental
            result += "\t" + rental.getMovie().getTitle()+ "\t" + amount + "\n";
            totalAmount += amount;
        }
        //add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }


}
