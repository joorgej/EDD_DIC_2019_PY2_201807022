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

public class AVLTree  
{  
    private class Node  
    {  
        private int data; 
        private int height;  
        private Node left;
        private Node right;  
        
        public Node(int data)  
        {  
            this.data = data;  
            this.height = 1;  
        }  

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
  
           
    }    
    
    private Node root;  
  
    private int getHeight(Node node)  
    {  
        if (node == null)
        {
            return 0; 
        }
            
  
        return node.getHeight(); 
                
    }  
  

    private int getHigher(int a, int b)  
    {  
        return (a > b) ? a : b;  
    }  
  
     
    private Node rightRotation(Node y)  
    {  
        Node x = y.getLeft(); 
        Node T2 = x.getRight(); 
  

        x.setRight(y); 
        y.setLeft(T2);  
  
 
        y.setHeight(getHigher(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);  
        x.setHeight(getHigher(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);  
  
 
        return x;  
    }  
  
 
    private Node leftRotation(Node x)  
    {  
        Node y = x.getRight();  
        Node T2 = y.getLeft();  
  

        y.setLeft(x);  
        x.setRight(T2);  
  

        x.setHeight(getHigher(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);  
        y.setHeight(getHigher(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);  
  
  
        return y;  
    }  
  
  
    private int getBalance(Node node)  
    {  
        if(node == null)
        {
            return 0; 
        }
        else
        {
            return ((getHeight(node.getLeft()) - getHeight(node.getRight())));
        }
    }  
  
    public void add(int data)
    {
        this.root = addR(this.root, data);
    }
    
    private Node addR(Node node, int data)  
    {  

        if (node == null)
        {
            return (new Node(data));
        }
        if (data <= node.getData())
        {
            node.setLeft(addR(node.getLeft(), data));
        }    
        else if (data > node.getData())
        {
            node.setRight(addR(node.getRight(), data));
        }     
        else 
        {
            return node; 
        }

        node.setHeight(1 + getHigher(getHeight(node.getLeft()), getHeight(node.getRight()))) ;  
        
        return rotation(node, data);
          
    }  
  
    private Node rotation(Node node, int data)
    {
        int balance = getBalance(node);  

        if (balance > 1)
        {
            if(0 < getBalance(node.getLeft()))
            {
                return rightRotation(node);
            }
            else
            {
                node.setLeft(leftRotation(node.getLeft()));  
                return rightRotation(node);
            }
        }

        if (balance < -1)
        {
            if(0 > getBalance(node.getRight()))
            {
                return leftRotation(node);
            }
            else
            {
                node.setRight(rightRotation(node.getRight()));  
                return leftRotation(node);
            }
        }  

        return node;
    }

    private Node getLaterNode(Node node)  
    {  
        Node actual = node;  
  

        while (actual.getLeft() != null)  
        {
            actual = actual.getLeft();  
        }
        
  
        return actual;  
    }  
  
    public void delete(int data)
    {
        this.root = deleteR(this.root, data);
    }
    
    private Node deleteR(Node node, int data)  
    {  

        if (node == null)
        {
            return node;
        }
              
  
        if (data < node.getData())
        {
            node.setLeft(deleteR(node.getLeft(), data));
        }              
        else if (data > node.getData()) 
        {
            node.setRight(deleteR(node.getRight(), data));  
        }   
        else
        {  
            if ((node.getLeft() == null) || (node.getRight() == null))  
            {  
                Node aux = null;  
                if (aux == node.getLeft()) 
                {
                    aux = node.getRight();  
                }    
                else
                {
                    aux = node.getLeft();
                }
                  
                if (aux == null)  
                {  
                    aux = node;  
                    node = null;  
                }  
                else
                {
                    node = aux; 
                }
                
                return aux;
                
                               
            }  
            else
            {  
  
                Node aux = getLaterNode(node.getRight());  
  
 
                node.setData(aux.getData());  
  
 
                node.setRight(deleteR(node.getRight(), aux.getData()));  
            }  
        }                
  
        node.setHeight(getHigher(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);  
 
        return rotation(node, data);  
    }  
  
 
    public String[] preOrder()  
    {  
        return preOrderR(this.root).split("%");
    }  
    
    private String preOrderR(Node node)
    {
        String traversal = "";
        if (node != null)  
        {  
            traversal += node.getData() + "%";  
            traversal += preOrderR(node.getLeft()) + "%";  
            traversal += preOrderR(node.getRight()) + "%";  
        }
        return traversal;
    }
    
    public String[] inOrder()  
    {  
        return inOrderR(this.root).split("%");
    }  
    
    private String inOrderR(Node node)
    {
        String traversal = "";
        if (node != null)  
        {   
            traversal += preOrderR(node.getLeft()) + "%";  
            traversal += node.getData() + "%";
            traversal += preOrderR(node.getRight()) + "%";  
        }
        return traversal;
    }
    
    public String[] postOrder()  
    {  
        return postOrderR(this.root).split("%");
    }  
    
    private String postOrderR(Node node)
    {
        String traversal = "";
        if (node != null)  
        {   
            traversal += preOrderR(node.getLeft()) + "%";  
            traversal += preOrderR(node.getRight()) + "%";  
            traversal += node.getData() + "%";
        }
        return traversal;
    }
    
    public void graph(int count)
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph Sort {  \n" ;
            w += "node [shape=circle, style= filled]; \n";
            w += graphR(this.root);
            w += "}" ;
            
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
    
    public String graphR(Node node)
    {
        if(node==null)
        {
            return "";
        }
        else if(node.getLeft()==null && node.getRight()==null)
        {
            return node.getData()+"";
        }
        else
        {
            String graph = "";
            if(node.getLeft()!=null)
            {
                graph += node.getData()+ " -> "+ node.getLeft().getData()+";\n";
            }
            
            if(node.getRight()!=null)
            {
                graph += node.getData()+ " -> "+ node.getRight().getData()+";\n";
            }
            
            if(node.getLeft()!=null)
            {
                graph += graphR(node.getLeft());
            }
            if(node.getRight()!=null)
            {
                graphR(node.getRight());
            }
                
            return graph;
        }
        
        
                
    }
    
}