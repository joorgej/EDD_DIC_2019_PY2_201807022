/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Graph;

import EDD.Graph;
import EDD.Queue;
import EDD.Stack;
import Views.Sorts.*;
import Views.LearningMenu;
import java.awt.Color;
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
public class DepthSearch extends javax.swing.JFrame {

    /**
     * Creates new form Sorts
     */
    
    Stack pila;
    Queue procesadors;
    Graph grafo;
    String[] nodes;
    boolean[][] adjacency;
    boolean[] nodesActivos;
    int count = 0;
    int count2 = 0;
    int t;
    int max = 0;
    String[] salida;
    
    
    public DepthSearch(Graph grafo, boolean auto, int t) throws InterruptedException {
        initComponents();
        proyecto_2.Proyecto_2.grobalImageCount++;
        this.nodes = grafo.getNodes();
        this.adjacency = grafo.getMatrix();
        
        pila = new Stack();
        this.grafo = grafo;
        this.procesadors = new Queue();
        this.nodesActivos = new boolean[nodes.length];
        
        this.t = t;
        this.setLocationRelativeTo(null);
        graph(-1);
        sleep(250);
        procesadors.graph(-2, false);
        sleep(200);
        pila.graph(-3, 0);
        sleep(200);
        ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graphi"+(-1)+".png");
        this.jLabel1.setIcon(img);
        img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-2)+".png");
        this.jLabel5.setIcon(img);
        img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-3)+".png");
        this.jLabel6.setIcon(img);
        this.jTextArea1.setText("Estado inicial de la matriz. \nMatriz vacia.");
        this.jButton1.setVisible(!auto);
        
        this.setVisible(true);

        if (auto) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        depthSearch();
                        depthSearch2();
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DepthSearch.class.getName()).log(Level.SEVERE, null, ex);
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
                        depthSearch();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DepthSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t1.start();
        }


        
        
    }
    
    
    public void graph(int c)
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
                        
                            w +="nodo"+i+" -> "+"nodo"+j+"  [arrowhead=none];";
                       
                        
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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graphi"+c+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    public void graph(int c, int recor[][], int[] nod)
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
                boolean flag = true;
                for(int j = 0; j<nod.length; j++)
                {
                    if(nod[j]==i){
                        w += "nodo"+i+" [label = \""+nodes[i]+"\", style=filled, fillcolor = \"#FD5048\" ];";
                        flag = false;
                    }
                       
                }
                if(flag)
                {
                    w += "nodo"+i+" [label = \""+nodes[i]+"\", style=filled, fillcolor = lightblue ];";
                }
                         
            }
            
            for(int i = 0; i<nodes.length; i++)
            {
                for (int j = i; j < nodes.length; j++) {
                    boolean flag = true;
                    if (adjacency[i][j]) {
                        for (int k = 0; k < recor.length; k++) {
                            if ((i == recor[k][0] && j == recor[k][1])||(j == recor[k][0] && i == recor[k][1])) {
                                w += "nodo" + i + " -> " + "nodo" + j + " [arrowhead=none, color=fireBrick1, penwidth=\"2.5\"];";
                                flag = false;
                            }

                        }
                        if (flag) {
                            w += "nodo" + i + " -> " + "nodo" + j + "  [arrowhead=none];";
                        }
                    }

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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graphi"+c+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    
    public void graph2(int c, int recor[][], int[] nod)
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        String w = "";
        try
        {
            fw = new FileWriter("C:\\EDDProyect\\graph.dot");
            pw = new PrintWriter(fw);
            
            w += "digraph grafo {  \n" ;
            w += "rankdir = TB; \n";
            w += "node [shape=circle]; \n";
            
            w += "nodo"+0+" [label = \""+nodes[0]+"\", style=filled, fillcolor = \"#D573FF\" ];";
            
            for(int i = 1; i<nodes.length ; i++)
            {
                w += "nodo"+i+" [label = \""+nodes[i]+"\", style=filled, fillcolor = \"#FD5048\" ];";         
            }
            
            for(int i = 0; i<nodes.length; i++)
            {
                for (int j = i; j < nodes.length; j++) {
                    if (adjacency[i][j]) {
                        for (int k = 0; k < recor.length; k++) {
                            if ((i == recor[k][0] && j == recor[k][1])||(j == recor[k][0] && i == recor[k][1])) {
                                w += "nodo" + i + " -> " + "nodo" + j + " [arrowhead=none, color=fireBrick1, penwidth=\"2.5\"];";
                                
                            }

                        }
                    }

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
            String [] cmd = {"dot","-Tpng","C:\\EDDProyect\\graph.dot", "-o", "C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+c+".png"};
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) 
        {
            System.out.println (ioe);
        }
    }
    
    
    public void depthSearch() throws InterruptedException
    {
        try {

            Queue aux = new Queue();
            int[][] recorrido = new int[nodes.length - 1][2];
            int[] nod = new int[nodes.length];

            graph(count2);
            sleep(200);
            count2++;

            int recorre = 0;
            pila.push(nodes[0]);
            aux.enqueue(nodes[0]);
            nod[0] = 0;
            String[] adjacencys;

            pila.graph(count, 1);
            sleep(200);
            count++;
            procesadors.graph(count, false);
            sleep(200);
            count++;
            graph(count2, recorrido, nod);
            sleep(200);
            count2++;

            do {

                adjacencys = this.grafo.getAdjacency(pila.peek());
                String node = pila.peek();
                System.out.println("-----------"+node+"----------");
                int encolados = 0;
                procesadors.enqueue(pila.pop());
                String n = "";

                for (int j = 0; j < adjacencys.length; j++) {
                    if (!aux.exist(adjacencys[j])) {
                        pila.push(adjacencys[j]);
                        aux.enqueue(adjacencys[j]);
                        recorrido[recorre][0] = grafo.getNodeIndex(node);
                        recorrido[recorre][1] = grafo.getNodeIndex(adjacencys[j]);
                        recorre++;
                        System.out.println(adjacencys[j]);
                        nod[recorre] = grafo.getNodeIndex(adjacencys[j]);
                        graph(count2, recorrido, nod);
                        sleep(200);
                        count2++;

                        encolados++;
                    }

                }

                pila.graph(count, encolados);
                sleep(200);
                count++;
                procesadors.graph(count, true);
                sleep(200);
                count++;

            } while (!pila.isEmpty());

            graph2(-4, recorrido, nod);
            sleep(200);
            pila.graph(-5, 0);
            sleep(200);
            procesadors.graph(-6, false);
            sleep(200);

            max = count;
            count = 0;
            count2 = 0;

        } catch (Exception e) {
            System.out.println("Algo salio mal :(");
        }
        
        
    }
    
    public void depthSearch2() throws InterruptedException {
       
        try {
            for (int i = 0; i <= max; i++) {
                sleep(t * 1000);
                if (count < max) {
                    ImageIcon img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graph" + count + ".png");
                    this.jLabel6.setIcon(img);
                    this.jLabel6.repaint();

                    img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graph" + (count + 1) + ".png");
                    this.jLabel5.setIcon(img);
                    this.jLabel5.repaint();

                    img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graphi" + (count2) + ".png");
                    this.jLabel1.setIcon(img);
                    this.jLabel1.repaint();

                    //this.jTextArea1.setText(salida[count/2]);
                    count++;
                    count++;
                    count2++;
                } else if (count == max) {
                    ImageIcon img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graph" + (-5) + ".png");
                    this.jLabel6.setIcon(img);
                    this.jLabel6.repaint();
                    count++;
                    img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graph" + (-6) + ".png");
                    this.jLabel5.setIcon(img);
                    this.jLabel5.repaint();

                    img = new ImageIcon("C:\\EDDProyect\\" + proyecto_2.Proyecto_2.grobalImageCount + "graph" + (-4) + ".png");
                    this.jLabel1.setIcon(img);
                    this.jLabel1.repaint();
                    this.jTextArea1.setText("Estado final de la matriz de adyacecia.\nMatriz terminada.");
                }

            }

        } catch (Exception e) {
            System.out.println("");
        }

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
        jScrollPane5 = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();

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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
        jLabel3.setText("Depth-First Search");

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel4.setText("(Recorrido por Profundidad)");

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setAutoscrolls(true);
        jScrollPane4.setViewportView(jLabel5);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setAutoscrolls(true);
        jScrollPane2.setViewportView(jLabel1);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setAutoscrolls(true);
        jScrollPane5.setViewportView(jLabel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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
            this.jLabel6.setIcon(img);  
            this.jLabel6.repaint();
            
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(count+1)+".png");
            this.jLabel5.setIcon(img);  
            this.jLabel5.repaint();
            
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graphi"+(count2)+".png");
            this.jLabel1.setIcon(img);  
            this.jLabel1.repaint();
            
            //this.jTextArea1.setText(salida[count/2]);
            
            count++;
            count++;
            count2++;
        }
        else if(count == max)
        {
            ImageIcon img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-5)+".png");
            this.jLabel6.setIcon(img); 
            this.jLabel6.repaint();
            count++;
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-6)+".png");
            this.jLabel5.setIcon(img);  
            this.jLabel5.repaint();
            
            img = new ImageIcon("C:\\EDDProyect\\"+proyecto_2.Proyecto_2.grobalImageCount+"graph"+(-4)+".png");
            this.jLabel1.setIcon(img);  
            this.jLabel1.repaint();
            this.jTextArea1.setText("Estado final de la matriz de adyacecia.\nMatriz terminada.");
        }
        
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
