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
public class Sorts 
{
    private int[] arr;
    private int used;
    
    public Sorts(int n)
    {
        this.arr = new int[n];
        this.used =0;
    }
    
    public void add(int data)
    {
        this.arr[this.used]=data;
        used++;
    }
    
    public void bubbleSort()
    {
        int i, j, aux;
        boolean flag;
        
        
        for(i = 0; i < arr.length-1;i++)
        {
            flag = true;
            for(j = 0; j< arr.length-i-1; j++)
            {
                if(arr[j]>arr[j+1])
                {
                    aux = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = aux;
                    flag = false;
                }
            }
            
            if(flag)
            {
                break;
            }
        }
    }
    
    public void insertionSort()
    {
        int i, j, aux;
        for(i = 1; i<arr.length; i++)
        {
            aux = arr[i];
            j = i-1;
            
            while(j>=0 && arr[j] > aux)
            {
                arr[j+1] = arr[j];
                j= j-1;
            }
            arr[j+1] = aux;
        }
    }
    
    public void quickSort()
    {
        quicky(0,arr.length);
    }
    
    private int partition(int low, int high)
    {
        int pivot = arr[high];
        int i = low-1;
        for(int j=low; j<high; j++)
        {
            if(arr[j]<pivot)
            {
                i++;
                
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
            }
        }
        
        int aux = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = aux;
        
        return i+1;
    }
    
    private void quicky(int low, int high)
    {
        if(low < high)
        {
            int part = partition(low, high);
            
            quicky(low, part-1);
            quicky(part+1, high);
        }
    }
    
    public void graph()
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph Sort {  \n" ;
            w += "node [shape=record]; \n";
            w += "nodo [label = \" ";
            for(int i = 0; i<arr.length ; i++)
            {
                
                 w += "<f"+i+"> "+arr[i];
                 
                if(i!=arr.length-1)
                {
                    w += "|";
                }
                
            }
            w += "\"]; \n";
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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\graph.png"}; 
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    
}
