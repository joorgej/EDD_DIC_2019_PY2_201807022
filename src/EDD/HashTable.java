/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Objects.User;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author Jorge
 */
public class HashTable 
{
    
    private User[] table;
    private int size;
    private int used;
    
    public HashTable()
    {
        this.size = 37;
        this.used = 0;
        this.table = new User[size];
    }
    
    public void add(User data)
    {  
        if(0.55<(double)this.used/(double)this.size)
        {
            
            reHashing();
        }
        this.table[getAddIndex(data.getCarnet())]=data;
        this.used++;
        
    }
    
    public boolean exist(int carnet){
        if(table[hashFunc(carnet)]!=null && table[hashFunc(carnet)].getCarnet()==carnet)
        {
            return true;
        }
        else
        {
            int iterator = 0;
            while(table[secundaryHashFunc(carnet, iterator)]!=null && iterator<3*this.size)
            {
                if(table[secundaryHashFunc(carnet, iterator)].getCarnet()==carnet) 
                {
                    return true;
                }
                iterator++;
            }
            return false;
        }
    }
    
    public int size()
    {
        return this.used;
    }
    
    public User[] users()
    {
        User[] aux = new User[used];
        int i = 0;
        for(int k = 0; k<size; k++)
        {
            if(table[k]!=null)
            {
                aux[i] = table[k];
                i++;
            }
        }
        
        return aux;
    }
    
    public void delete(int carnet)
    {
        this.table[getIndex(carnet)]=null;
        this.used--;
    }
    
    public void updateName(int carnet, String name)
    {
        this.table[getIndex(carnet)].setNombre(name);
    }
    
    public void updateLastName(int carnet, String lastName)
    {
        this.table[getIndex(carnet)].setApellido(lastName);
    }
    
    public User getUser(int carnet)
    {
        return this.table[getIndex(carnet)];
    }
    
    
    private int getAddIndex(int carnet)
    {
        if(table[hashFunc(carnet)]==null)
        {
            return hashFunc(carnet);
        }
        else
        {
            int iterator = 0;
            while(table[secundaryHashFunc(carnet, iterator)]!=null)
            {
                iterator++;
            }
            return secundaryHashFunc(carnet, iterator);
        }
    }
    
    
    private int getIndex(int carnet)
    {
        try{
        if(table[hashFunc(carnet)]==null && table[hashFunc(carnet)].getCarnet()==carnet)
        {
            return hashFunc(carnet);
        }
        else
        {
            int iterator = 0;
            while(table[secundaryHashFunc(carnet, iterator)]==null || table[secundaryHashFunc(carnet, iterator)].getCarnet()!=carnet)
            {
                iterator++;
            }
            return secundaryHashFunc(carnet, iterator);
        }
        }catch(Exception e){
            return getIndexSinProblemas(carnet);
        }
    }
    private int getIndexSinProblemas(int carnet)
    {
        for(int i = 0; i<table.length; i++){
            if(table[i]!=null){
                if(table[i].getCarnet()==carnet){
                    return i;
                }
            }
        }
        return 0;
    }
    
    private void reHashing()
    {
        User[] table = this.table;
        int prime = nextPrime();
        int index;
        this.table = new User[prime];
        this.size = prime;
        for(int i = 0; i<table.length ;i++)
        {
            if(table[i]!=null)
            {
                index = getAddIndex(table[i].getCarnet());
                this.table[index]=table[i];
            }
        }
        
        
    }
    
    private int nextPrime()
    {
        int prime = this.size;
        boolean flag = false;
        
        do
        {
            prime++;
            flag = false;
            for(int i = 2; i<size; i++)
            {
                if(prime%i==0)
                {
                    flag = true;
                }
            }
            
        }while(flag);
        
        
        return prime;
    }
    
    private int hashFunc(int carnet)
    {
        return carnet%this.size;
    }
    
    private int secundaryHashFunc(int carnet, int iterator)
    {
        return ((carnet%this.size)+1*iterator)%size;
    }
    
    public void graph(int count)
    {
        
        int c = 0;
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);

            w += "digraph hash{  \n";
            w += "rankdir = LR; ";
            w += "node [shape = none]; \n";
            w += "nodo1 [label = <<table border=\"0\" cellspacing=\"0\">";
            for(int i = 0; i<table.length; i++)
            {
                if(table[i]!=null)
                {
                    w += "<tr><td port=\"port" + c + "\" border=\"1\" fixedsize=\"true\" width=\"100\" height=\"72\">"; 
                    w += "No:    "+(c+1)+"<br/>"; 
                    w += "Llave: "+i;
                    w += "</td></tr>";
                    c++;
                }
            }
            c=0;
            w += "</table>>]; \n";
            
            w += "nodo2 [label = <<table border=\"0\" cellspacing=\"0\">";
            for(int i = 0; i<table.length; i++)
            {
                if(table[i]!=null)
                {
                    w += "<tr><td port=\"porti" + c + "\" border=\"1\" fixedsize=\"true\" width=\"600\" height=\"75\">"; 
                    w += "Nombre:     "+table[i].getNombre() +"<br/>";
                    w += "Apellido:   "+table[i].getApellido()+"<br/>";
                    w += "Carnet:     "+table[i].getCarnetS()+"<br/>";
                    w += "Contraseña: "+table[i].getContraseña()+"<br/>";
                    w += "</td></tr>";
                    c++;
                }
            }
            w += "</table>>]; \n";
            c=0;
            for(int i = 0; i<table.length; i++)
            {
                if(table[i]!=null)
                {
                   w += "nodo1:port"+c+" -> "+"nodo2:porti"+c+"; \n";
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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graphT"+count+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
}
