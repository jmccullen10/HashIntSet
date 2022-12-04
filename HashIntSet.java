import java.util.Arrays;
public class HashIntSet {

    private class Node{
        public int data;
        public Node next;

        public Node(int value) {
            data = value;
            next = null;
        }
        public Node(int value, Node next) {
            data = value;
            this.next = next;
        }
    }
    private Node[] elements;
    private int size;
    public HashIntSet() {
        elements = new Node[10];
        size = 0;
    }

    public int hash(int i) {
        return (Math.abs(i) % elements.length);
    }

    public void add(int value) {
        if(!contains(value)) {
            int h = hash(value);
            Node newNode = new Node(value);
            newNode.next = elements[h];
            elements[h] = newNode;
            size++;

        }
    }

    public boolean contains(int value) {
        Node current = elements[hash(value)];
        while(current != null) {
            if(current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public String toString() {
        String s = "";
        for(Node n:elements) {
            Node current = n;
            while(current != null) {
                s += current.data + " ";
                current = current.next;
            }
        }
        return s;
    }

    public void remove(int value) {
        int h = hash(value);
        if(elements[h] != null && elements[h].data == value) {
            elements[h] = elements[h].next;
            size--;
        }else {
            Node current = elements[h];
            while(current != null && current.next != null) {
                if(current.next.data == value) {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }
    }

    public void addAll(HashIntSet set2){

        for(Node node:set2.elements){
            Node cur = node;

            while (cur != null){
                if (!this.contains(cur.data)){
                    this.add(cur.data);
                }
                cur = cur.next;
            }
        }
    }


    public boolean equals(Object object) {
        if (! (object instanceof HashIntSet)){
            return false;
        }

        HashIntSet set = (HashIntSet) object;
        if(this.size != set.size){
            return false;
        }
        for(int i = 0; i < elements.length; i++) {
            Node cur = elements[i];
            while(cur != null) {
                if(!set.contains(cur.data)) {
                    return false;
                }
                cur = cur.next;
            }
        }
        return true;
    }
    public void removeAll(HashIntSet set) {

        for(Node node:set.elements){
            Node cur = node;

            while (cur != null){
                this.remove(cur.data);
                cur = cur.next;
            }
        }
    }
    public void retainAll(HashIntSet set) {

        for (Node node:elements){
            Node cur = node;

            while (cur != null){
                if (!set.contains(cur.data)){
                    this.remove(cur.data);
                }
                cur = cur.next;
            }
        }
    }
    public int[] toArray() {
        int[] arr = new int[size];
        int pos = 0;

        for (Node node:elements){
            Node cur = node;

            while (cur != null){
                arr[pos] = cur.data;
                pos ++;
                cur = cur.next;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        HashIntSet set = new HashIntSet();
        set.add(37);
        set.add(-2);
        set.add(49);
        set.add(47);
        set.add(57);

        HashIntSet set2 = new HashIntSet();
        set2.add(37);
        set2.add(-2);
        set2.add(9);
        set2.add(47);
        set2.add(57);

        HashIntSet t = new HashIntSet();
        HashIntSet t2 = new HashIntSet();

        int[] a = {-2, 3, 5, 6, 8};
        int[] b = {2, 3, 6, 8, 11};
        for (int i = 0; i < a.length; i++){
            t.add(a[i]);
            t2.add(b[i]);
        }
        t.retainAll(t2);
        System.out.println(t);
        System.out.println(Arrays.toString(set.toArray()));
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println(set.equals(set2));
        set.retainAll(set2);
        System.out.println(set);
        set.addAll(set2);
        System.out.println(set);

    }

}
