package homework2;

import homework2.exceptions.DuplicatedPublicationException;
import homework2.interfaces.PublicationProcessor;
import homework2.person.Customer;
import homework2.person.Employee;
import homework2.publication.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private final Logger logger = LogManager.getLogger(Library.class.getName());

    private final List<Publication> publications = new Vector<>();
    private List<Publication> largestPublications = new ArrayList<>();
    private final HashMap<String, Double> salesEmployee = new HashMap<>();
    public void addPublication(Publication publication){
        publications.add(publication);
    }

    public void createLargestPublications(){
        largestPublications = publications.stream().sorted(Comparator.comparingInt(Publication::getPages).reversed()).limit(5).collect(Collectors.toList());
    }
    public void displayLargestPublications(){
        largestPublications.forEach(logger::info);
    }

    public void employeeAttend(Employee employee, Customer customer){
        salesEmployee.putIfAbsent(employee.getName(), 0.0);
        employee.serveCustomer(customer, publications);
        salesEmployee.computeIfPresent(employee.getName(), (k,v) -> v + customer.getBill());
        logger.info("Current sales of " + employee.getName() + String.format(" : $ %.2f", salesEmployee.get(employee.getName())));
    }


    public void displayCounterOfPublications(PublicationProcessor publicationProcessor){
        for(Publication p: publications){
            if (publicationProcessor.processPublication(p))
                logger.info("Publication: " + p.getTitle() + " " + p.getPages());
        }
    }

    public void verifySamePublication(Employee employee ,Publication publication, Publication publication2){
        try {
            employee.checkSamePublication(publication, publication2);
        } catch (DuplicatedPublicationException e) {
            logger.error(e);
        }
    }

    public void displaySalesOfEmployees(){
        salesEmployee.forEach((key, value) -> logger.info("Sales of " + key + String.format(" : $ %.2f", value)));
    }

    public void displayBestEmployee(){
        salesEmployee.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(1).forEach(x -> logger.info("Best Employee is: " + x.getKey()));
    }

    public double sumOfSales(){
        return salesEmployee.values().stream().mapToDouble(d->d).sum();
    }

    public void displaySumOfSales(){
        logger.info("The sum of the sales is " +  String.format(" : $ %.2f", sumOfSales()));
    }

    public void restock(Employee employee){
        employee.restockPublications(publications);
    }

    public void displayPublications(){
        for (Publication p: publications) {
            logger.info(p);
        }
    }


}
