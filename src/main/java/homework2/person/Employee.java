package homework2.person;

import homework2.Library;
import homework2.exceptions.InvalidNumberException;
import homework2.exceptions.NoPublicationsFoundException;
import homework2.publication.Genre;
import homework2.publication.Publication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class Employee extends Person {

    private static final Logger logger = LogManager.getLogger(Library.class.getName());

    static {
        logger.info("Employee arrives and opens the library");
    }


    @Override
    public void greet(Person person) {
        logger.log(Level.getLevel("DIALOG"), this.name + " - Hello " + person.getName() + ", welcome to the library");
    }

    public Employee(String name) {
        super(name);
    }

    public List<Publication> search(List<Publication> publications, Customer customer) throws NoPublicationsFoundException {
        List<Publication> publicationsFound = new ArrayList<>();
        for (Publication publication : publications) {
            if (((publication.getGenre()).compareTo(customer.getPreferences())) == 0) {
                publicationsFound.add(publication);
            }
        }
        if (publicationsFound.isEmpty()) {
            throw new NoPublicationsFoundException("Couldn't find any publication with the preferences of the customer " + customer.getName());
        }
        return publicationsFound;
    }

    public void serveCustomer(Customer customer, List<Publication> publications) {
        List<Publication> publicationsFound;
        try {
            publicationsFound = search(publications, customer);
        } catch (NoPublicationsFoundException e) {
            logger.warn(e);
            customer.setPreferences(Genre.CLASSICS);
            publicationsFound = search(publications, customer);
        }

        Publication publicationChosenCustomer = customer.choosePublication(publicationsFound);
        logger.info("Publication chosen by " + customer.getName() + " is: " + publicationChosenCustomer);

        try {
            customer.increaseBill(publicationChosenCustomer, 3);
        } catch (InvalidNumberException e) {
            logger.error(e);
        }

        logger.info("The current bill of " + customer.getName() + " is: " + String.format("$ %.2f", customer.getBill()));

        changeAvailability(publicationChosenCustomer);
        logger.warn("Publication " + publicationChosenCustomer.getTitle() + " is " + ((publicationChosenCustomer.getAvailable()) ? "Available" : "Not Available"));
    }

    public void changeAvailability(Publication publication) {
        if (publication.getAvailable()) {
            publication.setAvailable(!publication.getAvailable());
        }
    }

    public void checkSamePublication(Publication publication, Publication publication2) {
        publication.isSamePublication(publication2);
    }

}
