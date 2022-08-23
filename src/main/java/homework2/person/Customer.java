package homework2.person;

import homework2.Library;
import homework2.exceptions.NoNegativeNumberException;
import homework2.exceptions.NoZeroNumberException;
import homework2.exceptions.NumberTooBigException;
import homework2.publication.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class Customer extends Person {

    private static final Logger logger = LogManager.getLogger(Library.class.getName());

    private Genre preferences;
    private double bill = 0;

    public Customer(String name, Genre preferences) {
        super(name);
        this.preferences = preferences;
    }

    public double getBill() {
        return bill;
    }

    public void increaseBill(Publication publication, int copies) throws NumberTooBigException, NoNegativeNumberException, NoZeroNumberException {
        double discount = 1.00;
        if (copies > 10) {
            throw new NumberTooBigException("Can not make more than 10 copies");
        } else if (copies < 0) {
            throw new NoNegativeNumberException("Can not make negative amount of copies");
        } else if (copies == 0) {
            throw new NoZeroNumberException("Can not make 0 amount of copies");
        }
        if (publication instanceof Book) {
            discount = ((Book) publication).addDiscount();
            bill += ((Book) publication).calculateBuyPrice(copies) * discount;
        } else if (publication instanceof NewsPaper) {
            bill += ((NewsPaper) publication).calculateBuyPrice(copies) * discount;
        } else if (publication instanceof Pamphlet) {
            bill += ((Pamphlet) publication).calculateCopyPrice(copies) * discount;
        } else if (publication instanceof ComicBook) {
            bill += ((ComicBook) publication).calculateBuyPrice(copies);
        }
    }

    public Genre getPreferences() {
        return preferences;
    }

    public void setPreferences(Genre preferences) {
        this.preferences = preferences;
    }


    @Override
    public void greet(Person person) {
        logger.log(Level.getLevel("DIALOG"), this.name + " - Hello " + person.getName());
    }

    public Publication choosePublication(List<Publication> publications) {
        return publications.get((int) Math.floor(((Math.random() * (publications.size())))));
    }

}
