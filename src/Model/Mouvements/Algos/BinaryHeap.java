package Model.Mouvements.Algos;

import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * class implémentant un tas binaire minimal
 */
public class BinaryHeap {
    // Attributs de la classe BinaryTree
    private Node[] heap;
    private int heapSize;

    /**
     * constructeur de la classe BinaryHeap
     * @param capacity
     */
    public BinaryHeap(int capacity){
        heapSize = 0;
        heap = new Node[ capacity+1];
    }

    /**
     * méthode vérifiant si le tas est vide
     * @return
     */
    public boolean isEmpty(){
        return heapSize==0;
    }

    /**
     * méthode vérifiant si le tas est plein
     * @return
     */
    public boolean isFull(){
        if(heapSize == heap.length){
            System.out.println("heapsize : " + heapSize);
            System.out.println("heap length : " + heap.length);
        }
        return heapSize == heap.length;
    }

    /**
     * méthode renvoyant la position du parent de i
     * @param i
     * @return
     */
    private int parent(int i){
        return (i-1)/2;
    }

    /**
     * méthode renvoyant le k-ième enfant de i (k entre 1 et 2)
     * @param i
     * @param k
     * @return
     */
    private int kthChild(int i,int k){
        return 2*i + k;
    }

    /**
     * méthode d'insertion d'un élément dans le tas
     * @param x
     */
    public void insert(Node x){
        if(isFull())
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        // On met le noeud dans le tas à la fin
        heap[heapSize++] = x;
        // On le fait remonter
        heapifyUp(heapSize-1);
    }

    /**
     * méthode supprimant un noeud du tas
     * @param x
     * @return
     */
    public Node delete(int x){
        if(isEmpty())
            throw new NoSuchElementException("Heap is empty, No element to delete");
        // On sauvegarde la valeur du noeud supprimé
        Node key = heap[x];
        // On place le suucesseur du noeud supprimé à la place du noeud supprimé
        heap[x] = heap[heapSize -1];
        // On décrémente la taille du tas
        heapSize--;
        // On descend l'élément à la position x
        heapifyDown(x);
        return key;
    }

    /**
     * méthode de conservation des propriétés du tas, montant un élément
     * @param i
     */
    private void heapifyUp(int i) {
        Node tmp = heap[i];
        // On compare en fonction des F-value des noeuds, le tas est un tas min.
        while(i>0 && tmp.getF() < heap[parent(i)].getF()){
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = tmp;
    }

    /**
     *  This method used to maintain the heap property while deleting an element.
     *
     */
    private void heapifyDown(int i){
        int child;
        Node tmp = heap[i];
        // On compare en fonction des F-value des noeuds, le tas est un tas min.
        while(kthChild(i, 1) < heapSize){
            child = minChild(i);
            if(tmp.getF() > heap[child].getF()){
                heap[i] = heap[child];
            }
            else{
                break;
            }
            i = child;
        }
        heap[i] = tmp;
    }

    /**
     * méthode renvoyant l'enfant de plus petite F-value du noeud i
     * @param i
     * @return
     */
    private int minChild(int i){
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return heap[leftChild].getF() < heap[rightChild].getF() ? leftChild:rightChild;
    }

    /**
     * getter de heap
     * @return
     */
    public Node[] getHeap() {
        return heap;
    }

    /**
     * getter de la taille de heap
     * @return
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * méthode surécrivant toString de Object
     * @return
     */
    public String toString()
    {
        String s = "nHeap = |";
        for (int i = 0; i < heapSize; i++)
            s += i + " : " + heap[i].getF() +" | ";
        return s;
    }
    /**
     *  This method returns the max element of the heap.
     *  complexity: O(1)
     */
    public Node findMin(){
        if(!isEmpty()) {
            return heap[0];
        }
        assert false;
        return null;
    }

    public static void main(String[] args){


        BinaryHeap bh2 = new BinaryHeap(50);

        Node no0 = new Node(10,10);
        no0.setF(0);
        bh2.insert(no0);

        Node no1 = new Node(10,10);
        no1.setF(1);
        bh2.insert(no1);

        Node no2 = new Node(10,10);
        no2.setF(2);
        bh2.insert(no2);

        Node no3 = new Node(10,10);
        no3.setF(3);
        bh2.insert(no3);

        Node no4 = new Node(10,10);
        no4.setF(4);
        bh2.insert(no4);

        Node no5 = new Node(10,10);
        no5.setF(5);
        bh2.insert(no5);

        Node no6 = new Node(10,10);
        no6.setF(6);
        bh2.insert(no6);

        Node no7 = new Node(10,10);
        no7.setF(7);
        bh2.insert(no7);

        Node no8 = new Node(10,10);
        no8.setF(8);
        bh2.insert(no8);

        Node no9 = new Node(10,10);
        no9.setF(9);
        bh2.insert(no9);

        System.out.println(bh2);

        bh2.delete(7);

        System.out.println(bh2);


        Node no75 = new Node(10,10);
        no75.setF(7.5);
        bh2.insert(no75);
        System.out.println(bh2);

        bh2.insert(no7);
        System.out.println(bh2);

        Node no05 = new Node(10,10);
        no05.setF(0.5);
        bh2.insert(no05);
        System.out.println(bh2);

        Node no10 = new Node(10,10);
        no10.setF(10);
        bh2.insert(no10);

        Node no11 = new Node(10,10);
        no11.setF(11);
        bh2.insert(no11);

        Node no12 = new Node(10,10);
        no12.setF(12);
        bh2.insert(no12);

        System.out.println(bh2);

        Node no15 = new Node(10,10);
        no15.setF(1.5);
        bh2.insert(no15);

        System.out.println(bh2);
        System.out.println(bh2.getHeapSize());

        for(int i = 0; i < 14; i++){
            bh2.delete(0);
        }
        System.out.println(bh2);

        System.out.println(bh2.getHeapSize());


    }
}