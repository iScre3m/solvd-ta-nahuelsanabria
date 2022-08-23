package homework2;

import homework2.collections.CustomLinkedList;
import homework2.exceptions.DuplicatedPublicationException;
import homework2.person.Customer;
import homework2.person.Employee;
import homework2.publication.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Library {

    private static final Logger logger = LogManager.getLogger(Library.class.getName());

    public static void main(String[] args) {

        Book book1 = new Book("The Hacienda", "Isabel Cañas", 352, true, Genre.MYSTERY, "The small mind is fully of relativity");
        Book book2 = new Book("Moby-Dick", "Herman Melville", 360, true, Genre.CLASSICS, "The visitor is like the cow");
        NewsPaper newsPaper1 = new NewsPaper("20 of May 2022", "The New York Times", 45, true, Genre.CLASSICS, "Always confidently receive the ancient lord. For today's weather it will be sunny");
        Magazine magazine1 = new Magazine("ALL ABOUT LOVE", "Vogue", 40, true, Genre.ROMANCE, "The pit is full of control. Silence acquires when you praise with parador.");
        Book book3 = new Book("The Hacienda", "Isabel Cañas", 357, true, Genre.MYSTERY, "Thought is the only life, the only guarantee totally");
        Pamphlet pamphlet1 = new Pamphlet("Rehab", 3, true, Genre.RELIGION, "We take your problems seriously, come to our classes");
        ComicBook comicBook1 = new ComicBook("Flash", "DC Comics", 30, true, Genre.FANTASY, "Sorry I am late, th-there is hope, Time to save the world");
        Employee employee1 = new Employee("Olga");
        Customer customer1 = new Customer("Charles", Genre.WAR);
        Customer customer2 = new Customer("Robin", Genre.MYSTERY);
        Customer customer3 = new Customer("Matthew", Genre.FANTASY);

        CustomLinkedList<Customer> customers = new CustomLinkedList<>();
        customers.insert(customer1);
        customers.insert(customer2);
        customers.insertHead(customer3);


        logger.info("Amount of customers on the store = " + customers.size());
        customers.display();

        employee1.greet(customer1);
        customer1.greet(employee1);
        employee1.greet(customer2);
        customer2.greet(employee1);

        List<Publication> publications = new Vector<>();
        publications.add(book1);
        publications.add(book2);
        publications.add(newsPaper1);
        publications.add(magazine1);
        publications.add(book3);
        publications.add(pamphlet1);
        publications.add(comicBook1);

        List<Publication> largestPublications = new ArrayList<>(publications);

        largestPublications.sort((a, b) -> Integer.compare(b.getPages(), a.getPages()));
        for (int i = largestPublications.size(); i > 5; i--) {
            largestPublications.remove(largestPublications.size() - 1);
        }

        HashMap<String, Double> salesEmployee = new HashMap<>();

        employee1.serveCustomer(customer1, publications);
        salesEmployee.put(employee1.getName(), customer1.getBill());
        employee1.serveCustomer(customer2, largestPublications);
        salesEmployee.computeIfPresent(employee1.getName(), (k, v) -> v + customer2.getBill());
        employee1.serveCustomer(customer3, publications);
        salesEmployee.computeIfPresent(employee1.getName(), (k, v) -> v + customer3.getBill());
        logger.info("sales of " + employee1.getName() + String.format(" : $ %.2f", salesEmployee.get(employee1.getName())));

        try {
            employee1.checkSamePublication(book1, book1);
        } catch (DuplicatedPublicationException e) {
            logger.error(e);
        }

    }

}
