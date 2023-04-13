package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JLabel label1,label2,label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1,button2,button3;
    Login(){
        super("Bank Management System");


        // setting  bank image
        ImageIcon bank = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = bank.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);
        // setting  card image
        ImageIcon card = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image ii2 = card.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(630,350,100,100);
        add(iimage);

        // Label - welcome to atm
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 38));
        label1.setBounds(230,125,450,40);
        add(label1);
        // Label - card
        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150,190,375,30);
        add(label2);
        // text field for card no.
        textField2 = new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial",Font.BOLD,14));
        add(textField2);
        // Label - pin
        label3 = new JLabel("PIN: ");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150,250,375,30);
        add(label3);
        // password field for pin
        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325,250,230,30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        // Sign IN button
        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300,300,100, 30);
        button1.addActionListener(this);
        add(button1);
        // Clear Button
        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430,300,100, 30);
        button2.addActionListener(this);
        add(button2);
        // Sign Up Button
        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300,350,230, 30);
        button3.addActionListener(this);
        add(button3);

        // setting  background image
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2 = background.getImage().getScaledInstance(850,480,Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0,0,850,480);
        add(iiimage);


        // setting frame
        setLayout(null);
        setBounds(250,120,850,480);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
           if(e.getSource() == button1){
               // connection class ka object bana liya
               Conne conne = new Conne();

               /*getting all the data,,, which enters by user and storing in string format,, this data is now going to store
                * in our database*/
               String cardno = textField2.getText();
               String pin = passwordField3.getText();
               /*humne sign up ka jo last page tha waha 2 table banaya tha ,, toh  hum uss login table mein
               * jo data enter hua tha(car and pin no.) ,, usse yaha match karwayenge,, agar match hua toh user aage jaayega*/
               // toh humne login se data liya or card_number ko cardno se match karwana hai same for pin
               String q = "select * from login where card_number = '"+cardno+"' and  pin = '"+pin+"'";
               // ab table se jo bhi data nikal kar aayega usko resulset mein store kar liya
               ResultSet resultSet = conne.statement.executeQuery(q);  //executeQuery se data nikal rhe hai query se
               // result set ke andar data aaya hai ya nahi
               if (resultSet.next()){
                   // agar aa gaya hai to login frame ki visibility false set kar do
                   setVisible(false);
                   // or main class pe chale jaao
                   new main_Class(pin);
               }else {
                   JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
               }
           } else if (e.getSource() == button2) {
               textField2.setText("");
               passwordField3.setText("");

           } else if (e.getSource() == button3) {
               // sign up pe click karne se new sign up button open ho jaayega
               new Signup();
               //or login waala frame close ho jaayega
               setVisible(false);
           }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }






    public static void main(String[] args) {

        Login login = new Login();
    }

}
