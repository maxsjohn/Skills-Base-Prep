import java.util.Arrays;

public class Heap {
    private int[] heap;
    private int size;
    private int capacity;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int getParentIndex(int index) { return (index - 1) / 2; }
    private int getLeftChildIndex(int index) { return 2 * index + 1; }
    private int getRightChildIndex(int index) { return 2 * index + 2; }

    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }

    private int parent(int index) { return heap[getParentIndex(index)]; }
    private int leftChild(int index) { return heap[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return heap[getRightChildIndex(index)]; }

    private void swap(int indexOne, int indexTwo) {
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity *= 2;
        }
    }

    public void add(int value) {
        ensureExtraCapacity();
        heap[size] = value;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) < heap[index]) { // For max heap
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public int extract() {
        if (size == 0) throw new IllegalStateException();
        int item = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int largerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                largerChildIndex = getRightChildIndex(index);
            }

            if (heap[index] > heap[largerChildIndex]) {
                break;
            } else {
                swap(index, largerChildIndex);
            }
            index = largerChildIndex;
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException();
        return heap[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Heap maxHeap = new Heap(10);
        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(20);
        maxHeap.add(17);

        System.out.println("Max element: " + maxHeap.peek()); // Output: 20
        System.out.println("Extract max element: " + maxHeap.extract()); // Output: 20
        System.out.println("Max element: " + maxHeap.peek()); // Output: 17
    }
}