package homework2.collections;


import homework2.Library;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLinkedList<T> {

    private static final Logger logger = LogManager.getLogger(Library.class.getName());

    private Node<T> head;

    public void insert(T data) {

        Node<T> newNode = new Node<T>(data);

        if (this.head == null) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    public void insertHead(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.setNextNode(head);
        head = newNode;
    }

    public void insertAt(int index, T data) {
        Node<T> nodeToBeInserted = new Node<T>(data);
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNextNode();
        }
        nodeToBeInserted.setNextNode(node.getNextNode());
        node.setNextNode(nodeToBeInserted);
    }

    public void deleteNodeAt(int index) {
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNextNode();
        }
        node.setNextNode(node.getNextNode().getNextNode());
    }

    public void display() {
        if (head != null) {
            Node<T> currentNode = head;
            while (currentNode.getNextNode() != null) {
                logger.info(currentNode.getData());
                currentNode = currentNode.getNextNode();
            }
            logger.info(currentNode.getData());
        }
    }


    public int size() {
        int counterNodes = 0;
        if (head != null) {
            Node<T> currentNode = head;
            counterNodes++;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
                counterNodes++;
            }
        }
        return counterNodes;
    }
}
