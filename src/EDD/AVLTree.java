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
public class AVLTree {
    
//___________________________________Nodo______________________________________    
    
    public class Node{

        private Node left;
        private Node right;
        private int data;
        
        Node(int data){
            this.left = null;
            this.right = null;
            this.data = data;
        }
        
        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getData() {
            return data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setData(int data) {
            this.data = data;
        }
   
    }
//_____________________________________________________________________________    

    
    
    private Node root;
    
    
    AVLTree(){ 
        this.root = null;
    }
    
    private int nodeHeight(Node aux){  
        
        if(aux==null){
            return 0;
        } 
        
        int left = 1+nodeHeight(aux.getLeft());
        int right = 1+nodeHeight(aux.getRight());
        
        if(left>=right){
            return left;
        }
        else{
            return right;
        }  
    }
    
    
    private int getValance(Node aux){
        
        return nodeHeight(aux.getLeft())-nodeHeight(aux.getRight());
        
    }
    
    
    public void add(int n){
        
        
        if(this.root == null){
            this.root = new Node(n);
            return;
        }
        
        addR(this.root, n);
        
    }
    
    private void addR(Node aux, int n){
        if(aux.getData()>n){
            if(aux.getLeft()!=null){
                addR(aux.getLeft(),n);
            }
            else{
                aux.setLeft(new Node(n));
            }
        }
        else{
            if(aux.getRight()!=null){
                addR(aux.getRight(), n);
            }
            else{
                aux.setRight(new Node(n));
            }
        }
        
        
    }
    
    private void rotate(Node aux){
        int valance = getValance(aux);
        
        if(valance >2){
            if(true){
                
            }else{
                
            }
        }
        else if(valance < -2){
            if(true){
                
            }else{
                
            }
        }
    }
    
    
}
