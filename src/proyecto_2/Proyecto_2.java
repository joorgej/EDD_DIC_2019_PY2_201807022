/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_2;

import EDD.HashTable;
import EDD.Sorts;
import Views.Login;

/**
 *
 * @author Jorge
 */
public class Proyecto_2 {

    
    public static  HashTable ht = new HashTable();
    
    public static void main(String[] args) {   
        
       
        Sorts s = new Sorts(5);
        s.add(5);
        s.add(3);
        s.add(1);
        s.add(4);
        s.add(2);
        
        s.bubbleSort();
        
        s.graph();
        
        Login log = new Login();
        
    }
    
}