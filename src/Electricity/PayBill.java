package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    JTextField t1;
    Choice c1,c2;
    JButton b1,b2;
    String meter;
    PayBill(String meter){
        this.meter = meter;
        setLayout(null);
        
        setBounds(550, 220, 900, 600);
        
        JLabel title = new JLabel("Electricity Bill");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(120, 5, 400, 30);
        add(title);
        
        JLabel lblmeternumber = new JLabel("Meter No");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);
        
        c1 = new Choice();
        c1.setBounds(300, 200, 200, 20);
        c1.add("January");
        c1.add("February");
        c1.add("March");
        c1.add("April");
        c1.add("May");
        c1.add("June");
        c1.add("July");
        c1.add("August");
        c1.add("September");
        c1.add("October");
        c1.add("November");
        c1.add("December");
        add(c1);
        
        
        JLabel lblunit = new JLabel("Units");
        lblunit.setBounds(35, 260, 200, 20);
        add(lblunit);
        
        JLabel unit = new JLabel("");
        unit.setBounds(300, 260, 200, 20);
        add(unit);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);
        
        JLabel totalbill = new JLabel("");
        totalbill.setBounds(300, 320, 200, 20);
        add(totalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);
        
        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);
        
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                meternumber.setText(rs.getString("meter_no"));
                name.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = 'January' ");
            while(rs.next()){
                unit.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        }catch(Exception e){}
        
        c1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");
                    while(rs.next()){
                        unit.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
                }catch(Exception e){}
            }
        });
        
        b1 = new JButton("Pay");
        b1.setBounds(100, 460, 100, 25);
        add(b1);
        b2 = new JButton("Back");
        b2.setBounds(230, 460, 100, 25);
        add(b2);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                Conn c = new Conn();
                c.s.executeQuery("update bill status = 'Paid' where meter_no = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");
                
            }catch(Exception e){}
            this.setVisible(false);
            new Paytm(meter).setVisible(true);

        }else if(ae.getSource()== b2){
            this.setVisible(false);
        }        
    }
    
       
    public static void main(String[] args){
        new PayBill("").setVisible(true);
    }
}
