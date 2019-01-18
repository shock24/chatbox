/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograms;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class serverGUI extends JFrame {
    JTextArea showarea;
    JButton clear;
    JTextArea typearea;
    JButton send;
    Container c;
    String str;
    BufferedReader br;
    OutputStreamWriter os;
    PrintWriter Out;
    Socket s;
    public serverGUI()throws Exception
    {
       this.setTitle("Server Corner");
        this.setBounds(300,300,500,300);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLUE);
        
        showarea=new JTextArea();
        //showarea.setBackground(Color.white);
        showarea.setBounds(10,10,460,180);
        showarea.setEditable(false);
        
        typearea=new JTextArea();
        typearea.setBounds(10,200,350,40);
        
        
        send=new JButton("Send"); 
        send.setBounds(390,200,75,20);
        
        clear=new JButton("Clear");
        clear.setBounds(390,230,75,20);
        
        c.add(showarea);
        c.add(typearea);
        c.add(send);
        c.add(clear);
        
        this.setVisible(true);
        initailizeSocket();
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new OutputStreamWriter(s.getOutputStream());
        Out=new PrintWriter(os);
        
        Thread t=new Thread(new Runnable() {
           public void run() {
               try {
                   while(true)
                   {
                   Receive_print();
                   }
                   
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
        t.start();
        
        
        
        
        send.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae)
           {
               sendperformed();
           }
        });
        
        clear.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae)
           {
               clearperformed();
           }
        });
        
       
    }
    public void clearperformed()
    {
        showarea.setText("");
    }
    public void sendperformed()
    {
        try
               {
                
                str=typearea.getText();
                typearea.setText("");
                Out.println(str);
                os.flush();
                Out.flush();
             //   Thread.sleep(1000);
             //   clientGUI ob=new clientGUI();
             //   ob.receive_show();
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
    }
    
    
    public void Receive_print()throws Exception
    {
        //System.out.println(s);
        String str=br.readLine();
        System.out.println(str);
        showarea.append(str);
        showarea.append("\n");
    }
    private void initailizeSocket() throws Exception{
        System.out.println("Server is started");
        ServerSocket ss=new ServerSocket(9998);
        
        System.out.println("Server is waiting for client");
        s=ss.accept();
        
        System.out.println("Client connected"+s);
    }
    public static void main(String args[])throws Exception
    {
        serverGUI ob=new serverGUI();
        
    }
    
}
