/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author Jorge
 */
public class Queue {

    private class Node {

        private Node next;
        private String data;

        private Node(String data) {
            this.next = null;
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public String getData() {
            return data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    private Node left;
    private Node right;
    private int size;

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(String data) {
        Node entry = new Node(data);
        if (isEmpty()) {
            this.left = entry;
            this.right = entry;
            this.size++;
            return;
        } else {
            this.right.setNext(entry);
            this.right = entry;
            this.size++;

        }
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node aux = this.left;
        this.left = this.left.getNext();
        if (aux == this.right) {
            this.right = null;
        }
        this.size--;
        return aux.getData();

    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }

        return this.left.getData();
    }
    
    public boolean exist(String node)
    {
        Node aux = this.left;
        while(aux!= null)
        {
            if(aux.getData().equals(node))
            {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }
}
