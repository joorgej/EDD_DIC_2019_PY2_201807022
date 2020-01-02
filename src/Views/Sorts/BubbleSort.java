/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Sorts;

import Views.LearningMenu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Jorge
 */
public class BubbleSort extends javax.swing.JFrame {

    /**
     * Creates new form Sorts
     */
    
    int[] arr;
    int count = 0;
    int t;
    int max = 0;
    String[] salida;
    
    
    public BubbleSort(int [] arr, boolean auto, int t) throws InterruptedException {
        initComponents();
        proyecto_2.Proyecto_2.grobalImageCount++;
        this.arr = arr;
        this.t = t;
        this.setLocationRelativeTo(null);
        graph(-2);
        sleep(500);
        ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-2)+".png");
        this.jLabel1.setIcon(img);
        this.jTextArea1.setText("Estado inicial del arreglo.");
        this.jButton1.setVisible(!auto);
        
        this.setVisible(true);

        if (auto) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bubbleSort();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t1.start();
        }
        else
        {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bubbleSort2();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t1.start();
        }


        
        
    }
    
    
    public void graph(int c, int one, int two)
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
            w += "nodo [label = <<table border=\"0\" cellspacing=\"0\"> <tr>";
            for(int i = 0; i<arr.length ; i++)
            {
                if(i==one)
                {
                     w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"red\" fixedsize=\"true\" width=\"50\" height=\"50\">"+arr[i]+"</td>";
                }
                else if(i == two)
                {
                     w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"lightblue\" fixedsize=\"true\" width=\"50\" height=\"50\">"+arr[i]+"</td>";
                }
                else
                {
                    w += "<td port=\"port"+i+"\" border=\"1\" fixedsize=\"true\" width=\"50\" height=\"50\">"+arr[i]+"</td>";
                }
                
            }
            w += "</tr></table>>]; \n";
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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+c+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    public void graph(int ultimo)
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
            w += "nodo [label = <<table border=\"0\" cellspacing=\"0\"> <tr>";
            for(int i = 0; i<arr.length ; i++)
            {
                
                 w += "<td port=\"port"+i+"\" border=\"1\" fixedsize=\"true\" width=\"50\" height=\"50\">"+arr[i]+"</td>";
                
            }
            w += "</tr></table>>]; \n";
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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+ultimo+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    public void bubbleSort() throws InterruptedException
    {
        int i, j, aux, n;
        n = arr.length;
        
        for(i = 0; i < n-1;i++)
        {
            for(j = 0; j< n-i-1; j++)
            {
                graph(count,j,j+1);
                sleep(t*1000);
                ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png");
                this.jLabel1.setIcon(img); 
                this.jLabel1.repaint();
                this.jTextArea1.setText("Se comparan "+arr[j]+" y "+arr[j+1]+".");
                count++;
                
                if(arr[j]>arr[j+1])
                {
                    aux = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = aux;
                    graph(count,j+1,j);
                    sleep(t*1000);
                    img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png");
                    this.jLabel1.setIcon(img);  
                    this.jLabel1.repaint();
                    this.jTextArea1.setText("Ya que "+arr[j+1]+" > "+arr[j]+" se intercambian.");
                    count++;
                }
            }
            
        }
        graph(-1);
        sleep(1000);
        ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-1)+".png");
        this.jLabel1.setIcon(img); 
        this.jTextArea1.setText("Estado final del arreglo.\nArreglo ordenado.");
    }
    
    public void bubbleSort2() throws InterruptedException
    {
        int i, j, aux, n;
        n = arr.length;
        String salidas = "";
        
        for(i = 0; i < n-1;i++)
        {
            for(j = 0; j< n-i-1; j++)
            {
                graph(count,j,j+1);
                sleep(100);
                salidas+="Se comparan "+arr[j]+" y "+arr[j+1]+". %";
                count++;
                
                if(arr[j]>arr[j+1])
                {
                    aux = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = aux;
                    graph(count,j+1,j);
                    sleep(100);
                    salidas+= "Ya que "+arr[j+1]+" > "+arr[j]+" se intercambian. %";
                    count++;
                }
            }
            
        }
        graph(-1);
        max = count;
        count=0;
        salida = salidas.split("%");
    }
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Siguiente");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return.png"))); // NOI18N
        jButton2.setAlignmentX(0.5F);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel3.setText("Bubble Sort");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/claveBubbleSort.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel4.setText("(Ordenamiento de Burbuja)");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setAutoscrolls(true);
        jScrollPane3.setViewportView(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.setVisible(false);
        LearningMenu m = new LearningMenu();
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        if(count<max)
        {
            ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png");
            this.jLabel1.setIcon(img);  
            this.jLabel1.repaint();
            this.jTextArea1.setText(salida[count]);
            count++;
        }
        else if(count == max)
        {
            ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-1)+".png");
            this.jLabel1.setIcon(img); 
            this.jLabel1.repaint();
            this.jTextArea1.setText("Estado final del arreglo.\nArreglo ordenado.");
        }
        
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
