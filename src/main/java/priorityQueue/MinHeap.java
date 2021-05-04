package priorityQueue;

/** A priority queue: represented by the min heap.
 *  Used in Prim's algorithm. */
public class MinHeap {
    private Elem[] heap; // the array to store the heap where each element has a node id and a cost.
    private int maxsize; // the maximum size of the array
    private int size; // the current size of the heap
    private int[] positions; //where an index is a node id, and the value is its index in the heap array

    /**
     * Inner class to store element in the heap
     */
    private class Elem {
        private int nodeId;
        private int cost;

        public Elem(int nodeIdInput, int costInput) {
            nodeId = nodeIdInput;
            cost = costInput;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getNodeId() {
            return nodeId;
        }
    }


    public int getHeapCost(int nodeId) {
        return heap[nodeId].getCost();
    }

    /**
     * Constructor
     * @param max the maximum size of the heap
     */
    public MinHeap(int max) {
        maxsize = max;
        heap = new Elem[maxsize];
        size = 0;
        heap[0] = new Elem(Integer.MIN_VALUE, Integer.MIN_VALUE);
        positions = new int[max];
    }

    /** Return the index of the left child of the element at index pos
     *
     * @param pos the index of the element in the heap array
     * @return the index of the left child
     */
    private int leftChild(int pos) {
        return 2 * pos;
    }

    /** Return the index of the right child
     *
     * @param pos the index of the element in the heap array
     * @return the index of the right child
     */
    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    /** Return the index of the parent
     *
     * @param pos the index of the element in the heap array
     * @return the index of the parent
     */
    private int parent(int pos) {
        return pos / 2;
    }

    /** Returns true if the node in a given position is a leaf
     *
     * @param pos the index of the element in the heap array
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    /** Swap given elements: one at index pos1, another at index pos2
     *
     * @param pos1 the index of the first element in the heap
     * @param pos2 the index of the second element in the heap
     */
    private void swap(int pos1, int pos2) {
        Elem tmp;
        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        positions[tmp.getNodeId()] = pos2;
        positions[heap[pos2].getNodeId()] = pos1;
        heap[pos2] = tmp;
    }

    /** Inserts node id with the given priority into the priority queue.
     *
     * @param nodeId the id of the node
     * @param priority is the cost of the edge.
     */
    public void insert(int nodeId, int priority) {
        size++;
        heap[size] = new Elem(nodeId, priority);
        positions[nodeId] = size;

        int current = size;
        while (heap[current].getCost() < heap[parent(current)].getCost()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Reduces the priority of the given vertex in the priority queue to newPriority,
     * rearranging the queue (minHeap) as necessary.
     * @param nodeId the id of the node
     * @param newPriority new Priority
     */
    public void reduceKey(int nodeId, int newPriority) {
        int heap_idx = positions[nodeId];

        if (newPriority < heap[heap_idx].getCost())
        {
            heap[heap_idx].setCost(newPriority);
            pushUp(heap_idx);
        } else {
            throw new IllegalArgumentException("New priority has to be lower than current priority");
        }
    }

    /**
     * private helper method to push the node up the minheap
     * @param position current position
     */
    public void pushUp(int position) {
        while (heap[parent(position)].getCost() > heap[position].getCost()) {
            swap(parent(position), position);
            position = parent(position);
        }
    }

    /** Removes the vertex with the smallest priority from the queue, and returns it.
     *
     * @return the smallest element in the priority queue
     */
    public int removeMin() {
        swap(1, size); // swap the end of the heap into the root
        size--;  	   // removed the end of the heap
        // fix the heap property - push down as needed
        if (size != 0)
            pushDown(1);
        return heap[size + 1].getNodeId();
    }

    /** Push the value down the heap if it does not satisfy the heap property
     *
     * @param position the index of the element in the heap
     */
    private void pushDown(int position) {
        int smallestchild;
        while (!isLeaf(position)) {
            smallestchild = leftChild(position); // set the smallest child to left child
            if ((smallestchild < size) && (heap[smallestchild].getCost() > heap[smallestchild + 1].getCost()))
                smallestchild = smallestchild + 1; // right child was smaller, so smallest child = right child

            // the value of the smallest child is less than value of current,
            // the heap is already valid
            if (heap[position].getCost() <= heap[smallestchild].getCost())
                return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
}
