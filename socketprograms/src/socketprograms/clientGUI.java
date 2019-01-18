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

public class clientGUI extends JFrame {
    JTextArea showarea;
    JButton clear;
    JTextArea typearea;
    JButton send;
    Container c;
    String ip;
    int port;
    String str;
    OutputStreamWriter os;
    PrintWriter Out;
    BufferedReader br;
    Socket s;
    clientGUI()throws Exception
    {
        this.setTitle("Client Corner");
        this.setBounds(300,300,500,300);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.yellow);
        
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
        initialize();
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        os=new OutputStreamWriter(s.getOutputStream());
        Out=new PrintWriter(os);
        Thread t=new Thread(new Runnable() {
            public void run() {
                try {
                    while(true)
                    {
                    receive_show();
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
    public void receive_show()throws Exception
    {
         
         str=br.readLine();
         System.out.print(str);
         showarea.append(str);
         showarea.append("\n");
         System.out.println("Data from server is "+str);
    }
    public void sendperformed()
    {
            try 
               {
               str=typearea.getText();
            //   showarea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            //   showarea.append(str);
               //typearea.setText("");
            //   showarea.append("\n");
                //ip=sc.nextLine();
                

                //str=sc.nextLine();
                System.out.println(str);
                typearea.setText("");
                Out.println(str);
                os.flush();
                Out.flush();
               // Thread.sleep(1000);
                //serverGUI ob=new serverGUI();
                //ob.Receive_print();

               
               }
               catch(Exception e)
               {
                //   System.out.println(e);
                   e.printStackTrace();
               }
    }
    public void clearperformed()
    {
         showarea.setText("");
    }
    public void initialize()throws Exception
    {
        port=9998;
        ip="localhost";
        s=new Socket(ip, port);
    }
    public static void main(String args[])throws Exception
    {
        clientGUI ob=new clientGUI();
        
    }
}
