/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import Objects.User;


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
            System.out.println("rehash");
            reHashing();
        }
        this.table[getAddIndex(data.getCarnet())]=data;
        this.used++;
        System.out.println("entro");
        
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
                System.out.println("entro");
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
        if(table[hashFunc(carnet)].getCarnet()==carnet)
        {
            return hashFunc(carnet);
        }
        else
        {
            int iterator = 0;
            while(table[secundaryHashFunc(carnet, iterator)].getCarnet()!=carnet)
            {
                iterator++;
            }
            return secundaryHashFunc(carnet, iterator);
        }
    }
    
    private void reHashing()
    {
        int prime = nextPrime();
        User[] aux = new User[prime];
        int j = this.size;
        this.size = prime;
        
        for(int i = 0; i<this.size;i++)
        {
            if(table[i]!=null)
            {
                aux[getIndex(table[i].getCarnet())]=table[i];
            }
        }
        
        this.table = aux;
        
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
        return ((carnet%(this.size+1))*iterator)%size;
    }
    
    
}
