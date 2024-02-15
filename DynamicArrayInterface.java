package Java.libraries.DATA_STRUCTURES.dynamicArray;

/* a dynamic array should have methods to add an element, insert an element to a specific index, delete an element, search for an element, check if
 * the array is empty, check if the array is at capacity, create a string from the array, and print the array
 */

public interface DynamicArrayInterface<T> {

    void add(T data);

    void insert(T data, int index);

    void delete(T data);

    int search(T data);

    boolean isFull();

    boolean isEmpty();

    String toString();

    void print();

}
