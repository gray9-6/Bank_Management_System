package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;
    mini(String pin){

        this.pin = pin;
        // setting Frame
        getContentPane().setBackground(new Color(204, 255, 239));
        setSize(350,600);
        setLocation(20,20);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setLayout(null);

        // Label
        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("National Bank");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        //ab hume data ko tables mein se nikalna hai or unko label3 mein set karna hai
        try{
            Conne conne = new Conne();
            ResultSet resultSet = conne.statement.executeQuery("select * from login where pin = '"+pin+"'");
            while (resultSet.next()){
                label3.setText("Card Number:  "+ resultSet.getString("card_number").substring(0,4) + "XXXXXXXX"+ resultSet.getString("card_number").substring(12));
            }
        } catch (Exception e1){
            e1.printStackTrace();
        }

        //
        try{
            Conne conne = new Conne();
            // ab bank table mein se data nikalna hai
            ResultSet resultSet = conne.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            int balance =0;

            while (resultSet.next()){
                label1.setText(label1.getText() + "<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+ "<br><br><html>");
                // agar toh table mein type column mein deposit hai toh deposit karo
                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));  // nahi toh withdraw karo
                }
            }

            label4.setText("Your Total Balance is Rs "+balance);

        } catch (Exception exception){
            exception.printStackTrace();
        }

        // Buttons
        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }


    public static void main(String[] args) {
        new mini("");
    }

}
