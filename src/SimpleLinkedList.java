import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SimpleLinkedList(List<String> list) {
        for (String s : list) {
            addLast(s);
        }

        this.size = list.size();
    }

    public void add(int index, String s) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            addFirst(s);
        } else if (index == size) {
            addLast(s);
        } else {
            Node current = getNode(index);
            Node newNode = new Node(current.prev, s, current);

            size++;
            current.prev.next = newNode;
            current.prev = newNode;
        }
    }

    public void addFirst(String s) {
        Node current = head;
        Node newNode = new Node(null, s, current);

        head = newNode;
        if (current == null) {
            tail = newNode;
        } else {
            current.prev = newNode;
        }

        size++;
    }

    public void addLast(String s) {
        Node current = tail;
        Node newNode = new Node(current, s, null);

        tail = newNode;
        if (current == null) {
            head = newNode;
        } else {
            current.next = newNode;
        }

        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(String s) {
        Node current = head;

        while (current != null) {
            if (current.data.equals(s)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public String get(int index) {
        return getNode(index).data;
    }

    public String getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        return head.data;
    }

    public String getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        return tail.data;
    }

    public int indexOf(String s) {
        int i = 0;
        Node current = head;

        while (current != null) {
            if (current.data.equals(s)) {
                return i;
            }

            i++;
            current = current.next;
        }

        return -1;
    }

    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            return removeFirst();
        } else if (index == (size - 1)) {
            return removeLast();
        }

        int i = 0;
        Node current = head;

        while (i++ < index) {
            current = current.next;
        }

        size--;
        current.prev.next = current.next;
        current.next.prev = current.prev;

        return current.data;
    }

    public boolean remove(String s) {
        int index = indexOf(s);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    public String removeFirst() {
        String data = null;

        if (head == null) {
            throw new NoSuchElementException();
        } else if (head == tail) {
            data = head.data;

            head = null;
            tail = null;
            size = 0;
        } else {
            data = head.data;
            head = head.next;
            head.prev = null;

            size--;
        }

        return data;
    }

    public String removeLast() {
        String data = null;

        if (tail == null) {
            throw new NoSuchElementException();
        } else if (head == tail) {
            data = tail.data;

            head = null;
            tail = null;
            size = 0;
        } else {
            data = tail.data;
            tail = tail.prev;
            tail.next = null;

            size--;
        }

        return data;
    }

    public String set(int index, String s) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node oldNode = getNode(index);
        Node newNode = new Node(oldNode.prev, s, oldNode.next);

        oldNode.prev.next = newNode;
        oldNode.next.prev = newNode;
        oldNode.prev = oldNode.next = null;

        return oldNode.data;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String s = "[";
        Node current = head;

        while (current != null) {
            s = s + current.data;
            current = current.next;

            if (current != null) {
                s = s + ", ";
            }
        }

        return s + "]";
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        int count = 0;
        Node current = head;

        while (current != null) {
            if (count++ == index) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private static class Node {

        Node prev;
        String data;
        Node next;

        public Node(Node prev, String data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}
