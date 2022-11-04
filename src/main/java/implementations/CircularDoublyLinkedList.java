package implementations;

public class CircularDoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;
    }

    public Node<E> createCircularDoublyLinkedList(E value) {
        head = new Node<>();
        Node newNode = new Node();
        newNode.value = value;
        head = tail = newNode;
        newNode.next = newNode;
        newNode.prev = newNode;
        size = 1;

        return head;
    }

    public void insertNode(E nodeValue, int location) {
        Node newNode = new Node();
        newNode.value = nodeValue;

        if (head == null) {
            createCircularDoublyLinkedList(nodeValue);
            return;
        } else if (location == 0) {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        } else if (location >= 0) {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            tail = newNode;
        } else {
            Node tempNode = head;
            int index = 0;

            while (index < location - 1) {
                tempNode = tempNode.next;
                index++;
            }

            newNode.prev = tempNode;
            newNode.next = tempNode.next;
            tempNode.next = newNode;
            newNode.next.prev = newNode;
        }

        size++;
    }

    public void traverseCircularDoublyLinkedList() {
        if (head != null) {
            Node tempNode = head;

            for (int i = 0; i < size; i++) {
                System.out.print(tempNode.value);

                if (i != size - 1) {
                    System.out.print(" -> ");
                }

                tempNode = tempNode.next;
            }
        } else {
            System.out.println("The Circular Doubly Linked List does not exist.");
        }

        System.out.println();
    }

    public void reverseTraversalCircularDoublyLinkedList() {
        if (head != null) {
            Node tempNode = tail;

            for (int i = 0; i < size; i++) {
                System.out.print(tempNode.value);

                if (i != size - 1) {
                    System.out.print(" <- ");
                }

                tempNode = tempNode.prev;
            }
        } else {
            System.out.println("The Circular Doubly Linked List does not exist.");
        }

        System.out.println();
    }

    public boolean searchNode(E nodeValue) {
        if (head != null) {
            Node tempNode = head;

            for (int i = 0; i < size; i++) {
                if (tempNode.value == nodeValue) {
                    System.out.println("The node is found at location: " + i);

                    return true;
                }

                tempNode = tempNode.next;
            }
        }

        System.out.println("Node not found!");

        return false;
    }

    public void deleteNode(int location) {
        if (head == null) {
            System.out.println("The Circular Doubly Linked List does not exist.");
            return;
        } else if (location == 0) {
            if (size == 1) {
                head.prev = null;
                head.next = null;
                head = null;
                tail = null;
                size--;
                return;
            } else {
                head = head.next;
                head.prev = tail;
                tail.next = head;
                size--;
            }
        } else if (location >= size) {
            if (size == 1) {
                head.prev = null;
                head.next = null;
                head = null;
                tail = null;
                size--;
                return;
            } else {
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
                size--;
            }
        } else {
            Node tempNode = head;

            for (int i = 0; i < location - 1; i++) {
                tempNode = tempNode.next;
            }

            tempNode.next = tempNode.next.next;
            tempNode.next.prev = tempNode;
            size--;
        }
    }

    public void deleteCircularDoublyLinkedList() {
        Node tempNode = head;

        for (int i = 0; i < size; i++) {
            tempNode.prev = null;
            tempNode = tempNode.next;
        }

        head = null;
        tail = null;

        System.out.println("The Circular Doubly Linked List has been deleted!");
    }
}
