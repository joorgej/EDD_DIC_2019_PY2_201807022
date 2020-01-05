/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Graph;

import Views.Sorts.*;
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
public class AdjacencyMatrix extends javax.swing.JFrame {

    /**
     * Creates new form Sorts
     */
    
    String[] nodes;
    boolean[][] adjacency;
    boolean[][] adjacency2;
    int count = 0;
    int t;
    int max = 0;
    String[] salida;
    
    
    public AdjacencyMatrix(String [] nodes, boolean[][] adjacency, boolean auto, int t) throws InterruptedException {
        initComponents();
        proyecto_2.Proyecto_2.grobalImageCount++;
        this.nodes = nodes;
        this.adjacency = adjacency;
        int n = this.nodes.length;
        this.adjacency2 = new boolean[n][n];
        for(int i = 0; i<n;i++)
        {
            for(int j = 0; j<n;j++)
            {
                adjacency2[i][j] = false;
            }
        }
        
        this.t = t;
        this.setLocationRelativeTo(null);
        graph(-2,-1,-1,false);
        sleep(250);
        graph(-1,-1,-1);
        sleep(250);
        ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-1)+".png");
        this.jLabel1.setIcon(img);
        img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-2)+".png");
        this.jLabel5.setIcon(img);
        this.jTextArea1.setText("Estado inicial de la matriz. \nMatriz vacia.");
        this.jButton1.setVisible(!auto);
        
        this.setVisible(true);

        if (auto) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        adjacentMatrix();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdjacencyMatrix.class.getName()).log(Level.SEVERE, null, ex);
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
                        adjacentMatrix2();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdjacencyMatrix.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t1.start();
        }


        
        
    }
    
    
    public void graph(int c, int one, int two )
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph grafo {  \n" ;
            w += "node [shape=circle]; \n";
            
            for(int i = 0; i<nodes.length ; i++)
            {
                w += "nodo"+i+" [label = \""+nodes[i]+"\", style=filled, fillcolor = lightblue ];";         
            }
            
            for(int i = 0; i<nodes.length; i++)
            {
                for(int j = i; j<nodes.length;  j++)
                {
                    if(adjacency[i][j])
                    {
                        if((i==one && j == two)||(j==one && i== two))
                        {
                            w +="nodo"+i+" -> "+"nodo"+j+" [dir = both, color=fireBrick1];";
                        }else
                        {
                            w +="nodo"+i+" -> "+"nodo"+j+" [dir = both];";
                        }
                        
                    }
                    
                }
            }
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
    
    public void graph(int ultimo, int one, int two, boolean entra)
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph matriz{  \n" ;
            w += "node [shape=record]; \n";
            w += "nodo [label = <<table border=\"0\" cellspacing=\"0\"> <tr> <td border=\"0\"></td>";
            for(int i = 0; i<nodes.length ; i++)
            {
                
                 w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"#7BE62F\" fixedsize=\"true\" width=\"35\" height=\"35\">"+nodes[i]+"</td>";
                
            }
            
             w += "</tr>";
            for(int i = 0; i<nodes.length; i++)
            {
                w += "<tr>";
                w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"#FD5048\" fixedsize=\"true\" width=\"35\" height=\"35\">"+nodes[i]+"</td>";
                for(int j = 0; j<nodes.length; j++)
                {
                    if(i == one && j == two && entra)
                    {
                        w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"#D573FF\" fixedsize=\"true\" width=\"35\" height=\"35\">"+"true"+"</td>";
                    }
                    else if(adjacency2[i][j])
                    {
                        w += "<td port=\"port"+i+"\" border=\"1\" bgcolor=\"#60D2FB\" fixedsize=\"true\" width=\"35\" height=\"35\">"+"true"+"</td>";
                    }
                    else
                    {
                         w += "<td port=\"port"+i+"\" border=\"1\" fixedsize=\"true\" width=\"35\" height=\"35\">"+"false"+"</td>";
                    }
                    
                }
                w += "</tr>";
            }
            w += "</table>>]; \n";
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
    
    public void adjacentMatrix() throws InterruptedException
    {
        for(int i = 0; i<nodes.length; i++)
        {
            for(int j = 0; j<nodes.length;j++)
            {
                if(adjacency[i][j])
                {
                    adjacency2[i][j]=adjacency[i][j];
                    graph(count, i, j);
                    sleep((t/2)*1000);
                    graph(count+1, i, j, true);
                    sleep((t/2)*1000);
                    ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png");
                    this.jLabel1.setIcon(img); 
                    this.jLabel1.repaint();
                    count++;
                    img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+count+".png");
                    this.jLabel5.setIcon(img); 
                    this.jLabel5.repaint();
                    count++;
                    this.jTextArea1.setText("El nodo "+ nodes[i]+ " tiene una arista hacia el nodo " +nodes[j]+".");
                }
                
            }
        }
        graph(-3,-1,-1,false);
        sleep(t*1000);
        ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-1)+".png");
        this.jLabel1.setIcon(img); 
        img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-3)+".png");
        this.jLabel5.setIcon(img); 
        this.jTextArea1.setText("Estado final de la matriz de adyacecia.\nMatriz terminada.");
        
    }
    
    public void adjacentMatrix2() throws InterruptedException
    {
        String salida = "";
        for(int i = 0; i<nodes.length; i++)
        {
            for(int j = 0; j<nodes.length;j++)
            {
                if(adjacency[i][j])
                {
                    adjacency2[i][j]=adjacency[i][j];
                    graph(count, i, j);
                    sleep(200);
                    graph(count+1,i,j,true);
                    sleep(200);
                    count++;
                    count++;
                    salida+= "El nodo "+ nodes[i]+ " tiene una arista hacia el nodo " +nodes[j]+". %";
                }
                
            }
        }
        graph(-3,-1,-1,false);
        sleep(200);
        max = count;
        count=0;
        this.salida = salida.split("%");
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Calibri Light", 1, 21)); // NOI18N
        jButton1.setText("Siguiente");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
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
        jLabel3.setText("Adjacency Matrix");

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel4.setText("(Matriz de Adyacencia)");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setAutoscrolls(true);
        jScrollPane4.setViewportView(jLabel5);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setAutoscrolls(true);
        jScrollPane2.setViewportView(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
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
            
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(count+1)+".png");
            this.jLabel5.setIcon(img);  
            this.jLabel5.repaint();
            
            this.jTextArea1.setText(salida[count/2]);
            
            count++;
            count++;
        }
        else if(count == max)
        {
            ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-1)+".png");
            this.jLabel1.setIcon(img); 
            this.jLabel1.repaint();
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-3)+".png");
            this.jLabel5.setIcon(img);  
            this.jLabel5.repaint();
            this.jTextArea1.setText("Estado final de la matriz de adyacecia.\nMatriz terminada.");
        }
        
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
