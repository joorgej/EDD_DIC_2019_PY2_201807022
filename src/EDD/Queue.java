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
    
    public void graph(int count, int news)
    {
        
        Node aux = this.left;
        int c = 0;
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);

            w += "digraph cola{  \n";
            w += "rankdir = LR; ";
            w += "node [shape = rectangle, width = 0.50, height = 0.50]; \n";
            if (aux == null) {
                w += "nodo [label =\"COLA VACIA\", shape = plaintext]";
            } else {
                while (aux != null) {
                    if (c >= size - news) {
                        w += "node" + c + " [label=\"" + aux.getData() + "\", style=filled, fillcolor=fireBrick1];";
                    } else {
                        w += "node" + c + " [label=\"" + aux.getData() + "\", style=filled, fillcolor=lightblue];";
                    }

                    aux = aux.getNext();
                    c++;
                }

                aux = this.left;
                c = 0;

                while (aux.getNext() != null) {
                    w += "node" + c + " -> node" + (c + 1) + ";";
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
    
    public void graph(int count,boolean entra)
    {
        Node aux = this.left;
        int c = 0;
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);

            w += "digraph cola{  \n";
            w += "node [shape=record]; \n";
            if (aux == null) {
                w += "nodo [label =\"VACIO\", shape = plaintext]";
            } else {
                w += "nodo [label = <<table border=\"0\" cellspacing=\"0\">";

                while (aux.getNext() != null) {

                    w += "<tr><td port=\"port" + c + "\" border=\"1\" bgcolor=\"#7BE62F\" fixedsize=\"true\" width=\"35\" height=\"35\">" + aux.getData() + "</td></tr>";
                    aux = aux.getNext();
                    c++;
                }
                if(entra){
                    w += "<tr><td port=\"port" + c + "\" border=\"1\" bgcolor=\"#D573FF\" fixedsize=\"true\" width=\"35\" height=\"35\">" + aux.getData() + "</td></tr>";
                }else
                {
                    w += "<tr><td port=\"port" + c + "\" border=\"1\" bgcolor=\"#7BE62F\" fixedsize=\"true\" width=\"35\" height=\"35\">" + aux.getData() + "</td></tr>";
                }
                

                w += "</table>>]; \n";
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
