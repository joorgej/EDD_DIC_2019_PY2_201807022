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
public class Graph {
    boolean[][] matrix;
    String [] nodes;
    
    public Graph(int n)
    {
        this.matrix = new boolean[n][n];
        for(int i = 0; i<n;i++)
        {
            for(int j = 0; j<n;j++)
            {
                matrix[i][j] = false;
            }
        }
        this.nodes = new String[n];
    }
    
    public void addNodes(String[] nodes)
    {
        int i, j;
        String aux;
        boolean flag;
        
        
        for(i = 0; i < nodes.length-1;i++)
        {
            flag = true;
            for(j = 0; j< nodes.length-i-1; j++)
            {
                if(nodes[j].compareTo(nodes[j+1])>0)
                {
                    aux = nodes[j];
                    nodes[j] = nodes[j+1];
                    nodes[j+1] = aux;
                    flag = false;
                }
            }
            
            if(flag)
            {
                break;
            }
        }
        
        this.nodes = nodes;
    }
    
    public void addUniones(String node, String[] union)
    {
        int i = getNodeIndex(node);
        if(i==-1)
        {
            System.out.println("Error: No existe el nodo descrito");
        }
        else
        {
            
            for(int j = 0; j<union.length; j++)
            {
                this.matrix[i][getNodeIndex(union[j])]=true;
            }
        }
        
               
    }
    
    public int getNodeIndex(String node)
    {
        if(nodes == null)
        {
            return -1;
        }
        
        int num = 0;
        
        for(String n: this.nodes)
        {
            if(n.equals(node))
            {
                return num;
            }
            num++;
        }
        return -1;
    }
    
    public String getNodeName(int index)
    {
        return this.nodes[index];
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public String[] getNodes() {
        return nodes;
    }
    
    public String[] getAdjacency(String node)
    {
        String salidas = "";
        for(int i = 0; i<nodes.length; i++)
        {
            
            if(matrix[getNodeIndex(node)][i])
            {
                salidas += nodes[i]+"%";
            }
        }
        return salidas.split("%");
    }
  
}


