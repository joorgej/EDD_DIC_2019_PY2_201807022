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
public class BTree {
    public class Page{
        public class Node{
            private int data;
            
            public Node(int data)
            {
                this.data = data;
            }

            public void setData(int data) {
                this.data = data;
            }

            public int getData() {
                return data;
            }
        }
        
        private Page[] pointers;
        private Node[] nodes;

        private int usedN;
        private int usedP;
        
        public Page()
        {
            this.usedN = 0;
            this.usedP = 0;
            this.pointers= new Page[5];
            this.nodes = new Node[5];
            
        }
        
        public boolean isFull(){return this.usedN==5;}
        
        public boolean isEmpty(){return this.usedN==0;};
        
        public Node getNode(int index)
        {
             if(index<usedN)
             {
                 return nodes[index];
             }
             else
             {
                 return null;
             }
        }
        
        public Page getPage(int data)
        {
            for(int i = 0; i<usedN; i++)
            {
                if(data<nodes[i].getData())
                {
                    return pointers[i];
                }
            }
            return pointers[usedP-1];
        }
        
        public int getUsedN() {
            return usedN;
        }

        public void setUsedN(int usedN) {
            this.usedN = usedN;
        }

        public int getUsedP() {
            return usedP;
        }

        public void setUsedP(int usedP) {
            this.usedP = usedP;
        }
        
        public void add(int data){
            nodes[usedN]=new Node(data);
            usedN++;
            int i, j;
            Node aux;
            boolean flag;

            for (i = 0; i < usedN - 1; i++) {
                flag = true;
                for (j = 0; j < usedN - i - 1; j++) {
                    if (nodes[j].getData() > nodes[j + 1].getData()) {
                        aux = nodes[j];
                        nodes[j] = nodes[j + 1];
                        nodes[j + 1] = aux;
                        flag = false;
                    }
                }

                if (flag) {
                    break;
                }
            }
            
            
        }
        
        
    }
    
    private Page root;
    private int levels;
    private int size;
    
    public void add(int data)
    {
        if(root==null){
            Page p = new Page();
            p.add(data);
            this.root = p;
        }else{
            addR(data, root);
        }
    }
    
    private Page addR(int data, Page page)
    {
        Page aux = page.getPage(data);
        if(aux==null)
        {
            if(page.isFull())
            {
                
                aux = new Page();
                aux.add(data);
                
                
                
                
            }
            else
            {
                page.add(data);
            }
        }
        else
        {
            
            Page returned = addR(data, aux);
            if(returned != null){
                
            }
        }
        return null;
    }
}
