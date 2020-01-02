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
    
}
