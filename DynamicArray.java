package Java.libraries.DATA_STRUCTURES.dynamicArray;

/*
 * Dynamic arrays are a special type of array that does not have a max capacity
 * a normal array has a set size that is decided when the array is originally defined
 * a dynamic array uses a static array, but when the number of elements reaches its maximum capacity, a new and larger static array is created and
 * the elements from the old array are copied over, this increases the size of the array
 */


public class DynamicArray<T> implements DynamicArrayInterface<T> {

    /* the DynamicArray class keeps track of the number of elements in its internal array, the total capacity of the array, and the actual internal array
    itself */

    int numElements = 0;
    int capacity = 100;
    T[] array;

    /* This dynamic array class contains a constructor where the user can specifiy a max capacity, and another default capacity where a default value
    is used instead 
    
    the warning is caused because the compiler cannot guaruntee that the array of type Object will also be of type T because every class inherits from 
    Object. We know that it will however because the dynammic array is a generic class of type T */

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
    }

    public DynamicArray() {
        array = (T[]) new Object[capacity];
    }

    /*
     * if the dynamic array is full, it will be expanded
     * otherwise the element is added to the end and the number of elements is increased
     */

    public void add(T data) {
        if (numElements >= capacity)
            expand();
        
        array[numElements] = data;
        numElements ++;
    }

    /*
     * if the dynamic array is full, it is expanded
     * otherwise all the elements are shifted to the right, the element is inserted at the specified index, and the number of elements is increased
     */

    public void insert(T data, int index) {
        if (numElements >= capacity)
            expand();

        for (int i = numElements; i > index; i --) {
            array[i] = array[i - 1];
        }

        array[index] = data;
        numElements ++;
    }

    /*
     * iterates through the dynamic array and if the element at index i is equal to the data we want to delete, everything is shifted over to the left
     * by one index which will also overrite the data stored that we want to delete
     * then the last element is set to null and the number of elements is decremented
     * 
     * if the number of elements is less than 1/3 of the capacity, the dynamic array shrinks
     */

    public void delete(T data) {

        for (int i = 0; i < numElements; i ++) {
            if (array[i] == data) {
                for (int j = i + 1; j < numElements; j ++) {
                    array[j - 1] = array[j];
                }
                array[numElements - 1] = null;
                numElements --;
                if (numElements <= (int) capacity / 3) {
                    shrink();
                }
                break;
            }
        }
    }

    /*
     * iterates through the array and if the element at index i matches the data we want deleted, everything is shifted to the left by one to overrite
     * the element we want to delete
     * the difference here is that i is only increased if the element does not match what we want to delete
     * this way the loop will keep running until every element that matches what we want to delete is removed
     */

    public void deleteAll(T data) {
        int i = 0;
        while (i < numElements) {
            i ++;
            if (array[i] == data) {
                for (int j = i + 1; j < numElements; j ++) {
                    array[j - 1] = array[j];
                }
                array[numElements - 1] = null;
                numElements --;
                if (numElements <= (int) capacity / 3) {
                    shrink();
                }
                i --;
            }
        }
    }

    /*
     * iterates through the array and returns the index of the first element that matches the data passed
     * returns -1 if the element is not found
     */

    public int search(T data) {
        for (int i = 0; i < numElements; i ++) {
            if (array[i] == data) 
                return i;
        }
        return -1;

    }   

    /*
     * doubles the capacity and creates a new array of increased capacity
     * then copies over the elements from the old array into this new array and sets this.array equal to the new array
     */

    private void expand() {
        capacity = (int) capacity * 2;
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < numElements; i ++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /*
     * halves the capacity and creates a new array of decreased capacity
     * then copies over the elements from the old array into the new array and sets this.array equal to the new array
     */

    private void shrink() {
        capacity = (int) capacity / 2;
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < numElements; i ++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    /*
     * if the number of elements is 0, returns true, otherwise returns false
     */

    public boolean isEmpty() {
        return numElements == 0;
    }

    /*
     * if the number of elements is equal to the capacity, returns true, otherwise returns false
     */

    public boolean isFull() {
        return numElements == capacity;
    }

    /*
     * creates a blank string to start
     * iterates through the array and adds each element to the string
     * if the string is not empty sets the string equal to a substring of itself that excludes the final comma and space
     * if the string is empty, just adds a pair of empty brackets
     * 
     * adds on formatting and extra info and then returns the string
     */

    public String toString() {

        String string = "";

        for (int i = 0; i < numElements; i ++) {
            string += array[i] + ", ";
        }

        if (string != "") {
            string = "[" + string.substring(0, string.length() - 2) + "]\nSize: " + numElements + "\nCapacity: " + capacity + "\nEmpty: " + isEmpty() + "\n";
            return string;
        }
        string = "[]\nSize: " + numElements + "\nCapacity: " + capacity + "\nEmpty: " + isEmpty() + "\n";
        return string;
    }

    /*
     * gets a string of the array using the to string method and prints it out
     */

    public void print() {
        String string = toString();
        System.out.println(string);
    }
}