package homework2;

import homework2.exceptions.DuplicatedPublicationException;
import homework2.interfaces.PublicationProcessor;
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

    private final Logger logger = LogManager.getLogger(Library.class.getName());

    private final List<Publication> publications = new Vector<>();
    private final List<Publication> largestPublications = new ArrayList<>();
    private final HashMap<String, Double> salesEmployee = new HashMap<>();
    public void addPublication(Publication publication){
        publications.add(publication);
    }

    public void createLargestPublications() {
        largestPublications.addAll(publications);
        largestPublications.sort((a, b) -> Integer.compare(b.getPages(), a.getPages()));
        for (int i = largestPublications.size(); i > 5; i--) {
            largestPublications.remove(largestPublications.size() - 1);
        }
    }

//    public void employeeAttends(Employee employee, CustomLinkedList<Customer> customers){
//
//        salesEmployee.put(employee.getName(), 0.0);
//        if (customers.getHead() != null) {
//            var node = new Object() {
//                Node<Customer> currentNode = customers.getHead();
//            };
//            while (node.currentNode != null) {
//                employee.serveCustomer(node.currentNode.getData(), publications);
//                salesEmployee.computeIfPresent(employee.getName(), (k, v) -> v + node.currentNode.getData().getBill());
//                node.currentNode = node.currentNode.getNextNode();
//            }
//        }
//        logger.info("sales of " + employee.getName() + String.format(" : $ %.2f", salesEmployee.get(employee.getName())));
//    }

    public void employeeAttend(Employee employee, Customer customer){
        salesEmployee.putIfAbsent(employee.getName(), 0.0);
        employee.serveCustomer(customer, publications);
        salesEmployee.computeIfPresent(employee.getName(), (k,v)-> v + customer.getBill());
        logger.info("sales of " + employee.getName() + String.format(" : $ %.2f", salesEmployee.get(employee.getName())));
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




}
