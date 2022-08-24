package homework2;

import homework2.collections.CustomLinkedList;
import homework2.collections.Node;
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

    private List<Publication> publications = new Vector<>();
    private List<Publication> largestPublications = new ArrayList<>();
    private HashMap<String, Double> salesEmployee = new HashMap<>();
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

    public void employeeAttends(Employee employee, CustomLinkedList<Customer> customers){

        salesEmployee.put(employee.getName(), 0.0);
        if (customers.getHead() != null) {
            var node = new Object() {
                Node<Customer> currentNode = customers.getHead();
            };
            while (node.currentNode != null) {
                employee.serveCustomer(node.currentNode.getData(), publications);
                salesEmployee.computeIfPresent(employee.getName(), (k, v) -> v + node.currentNode.getData().getBill());
                node.currentNode = node.currentNode.getNextNode();
            }
        }
        logger.info("sales of " + employee.getName() + String.format(" : $ %.2f", salesEmployee.get(employee.getName())));
    }
//
//        try {
//            employee1.checkSamePublication(book1, book1);
//        } catch (DuplicatedPublicationException e) {
//            logger.error(e);
//        }


}
