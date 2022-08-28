package homework2;

import homework2.collections.CustomLinkedList;
import homework2.person.Customer;
import homework2.person.Employee;
import homework2.publication.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(App.class.getName());

        Library library = new Library();

        Book book1 = new Book("The Hacienda", "Isabel Cañas", 352, true, Genre.MYSTERY, "The small mind is fully of relativity");
        Book book2 = new Book("Moby-Dick", "Herman Melville", 360, true, Genre.CLASSICS, "The visitor is like the cow");
        NewsPaper newsPaper1 = new NewsPaper("20 of May 2022", "The New York Times", 45, true, Genre.CLASSICS, "Always confidently receive the ancient lord. For today's weather it will be sunny");
        Magazine magazine1 = new Magazine("ALL ABOUT LOVE", "Vogue", 40, true, Genre.ROMANCE, "The pit is full of control. Silence acquires when you praise with parador.");
        Book book3 = new Book("The Hacienda", "Isabel Cañas", 357, true, Genre.MYSTERY, "Thought is the only life, the only guarantee totally");
        Pamphlet pamphlet1 = new Pamphlet("Rehab", 3, true, Genre.RELIGION, "We take your problems seriously, come to our classes");
        ComicBook comicBook1 = new ComicBook("Flash", "DC Comics", 30, true, Genre.FANTASY, "Sorry I am late, th-there is hope, Time to save the world");
        Employee employee1 = new Employee("Olga");
        Employee employee2 = new Employee("Martin");
        Customer customer1 = new Customer("Charles", Genre.WAR);
        Customer customer2 = new Customer("Robin", Genre.MYSTERY);
        Customer customer3 = new Customer("Matthew", Genre.FANTASY);

        CustomLinkedList<Customer> customers = new CustomLinkedList<>();
        customers.insert(customer1);
        customers.insert(customer2);
        customers.insertHead(customer3);

        customers.display();
        logger.info("Amount of customers on the store = " + customers.size());
        customers.display();

        employee1.greet(customer1);
        customer1.greet(employee1);
        employee1.greet(customer2);
        customer2.greet(employee1);
        employee1.greet(customer3);
        customer3.greet(employee1);

        library.addPublication(book1);
        library.addPublication(book2);
        library.addPublication(newsPaper1);
        library.addPublication(magazine1);
        library.addPublication(book3);
        library.addPublication(pamphlet1);
        library.addPublication(comicBook1);

        library.createLargestPublications();

        library.displayCounterOfPublications(publication -> publication.getPages() > 100 );

        library.verifySamePublication(employee1, book1, book2);

        library.employeeAttend(employee1, customer1);
        library.employeeAttend(employee1, customer2);
        library.employeeAttend(employee2, customer3);
        library.displaySalesOfEmployees();
        library.displayBestEmployee();
        library.displaySumOfSales();
    }
}
