import java.util.NoSuchElementException;
/**
 * Your implementation of a linked deque.
 *
 * @author Rosemary Blair
 * @userid rblair8
 * @GTID 903359318
 * @version 1.0
 */
public class LinkedDeque<T> {
    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * Adds the data to the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) { //check data input
            throw new IllegalArgumentException("Cannot insert null data.");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (head != null) { //deque is not empty
            newNode.setNext(head);
            head.setPrevious(newNode);
        }
        head = newNode;
        if (tail == null) { //deque has one element
            tail = head;
        }
        size++;
    }

    /**
     * Adds the data to the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) { //check data input
            throw new IllegalArgumentException("Cannot insert null data.");
        }
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (tail != null) { //deque is not empty
            newNode.setPrevious(tail);
            tail.setNext(newNode);
        }
        tail = newNode;
        if (head == null) { //deque has one element
            head = tail;
        }
        size++;
    }

    /**
     * Removes the data at the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size() == 0) { //deque is empty
            throw new NoSuchElementException("Cannot remove from empty deque");
        }
        LinkedNode<T> temp = head;
        head = head.getNext();
        if (head == null) { //deque is now empty
            tail = null;
        } else {
            head.setPrevious(null);
        }
        size--;
        return temp.getData();
    }

    /**
     * Removes the data at the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size() == 0) { //deque is empty
            throw new NoSuchElementException("Cannot remove from empty deque");
        }
        LinkedNode<T> temp = tail;
        tail = tail.getPrevious();
        if (tail == null) { //deque is now empty
            head = null;
        } else {
            tail.setNext(null);
        }
        size--;
        return temp.getData();
    }

    /**
     * Returns the number of elements in the list.
     *
     * Runs in O(1) for all cases.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the head of the linked deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the tail of the linked deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}