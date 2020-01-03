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
public class Stack {

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

    private Node top;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(String data) {
        Node entry = new Node(data);
        if (isEmpty()) {
            this.top = entry;
            this.size++;
            return;
        } else {
            entry.setNext(this.top);
            this.top = entry;
            this.size++;
        }

    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }

        Node aux = this.top;
        this.top = this.top.getNext();
        this.size--;
        return aux.getData();

    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return this.top.getData();
    }
    
    public boolean exist(String node)
    {
        Node aux = this.top;
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
