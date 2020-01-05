/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    
    public void graph(int count, int news)
    {
        Node aux = this.top;
        int c = 0;
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph pila{  \n" ;
            w += "rankdir = TB; ";
            w += "node [shape = rectangle, width = 0.75, height = 0.75]; \n";
            if (aux == null) {
                w += "nodo [label =\"PILA VACIA\", shape = plaintext]";
            } else {
                while (aux != null) {
                    if (c < news) {
                        w += "node" + c + " [label=\"" + aux.getData() + "\", style=filled, fillcolor=fireBrick1];";
                    }else{
                        w += "node" + c + " [label=\"" + aux.getData() + "\", style=filled, fillcolor=lightblue];";
                    }
                    aux = aux.getNext();
                    c++;
                }

                aux = this.top;
                c = 0;

                while (aux.getNext() != null) {
                    w += "node" + c + " -> node" +( c + 1 )+ ";";
                    aux = aux.getNext();
                    c++;
                }
            }
            w += "}";
            
            pw.println(w);
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fw != null)
                {
                    fw.close();
                }
            }
            catch(Exception x)
            {
                x.printStackTrace();
            }
        }
        
        try 
        {
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
}
