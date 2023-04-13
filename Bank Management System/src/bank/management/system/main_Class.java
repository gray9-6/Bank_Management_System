package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Class extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    main_Class(String pin){

        this.pin = pin;

        // ATM machine ka layout
        //classLoader ke function(getSystemResource) ki help se image ko apne system se liya or usko atm(image icon ke object) mein store kar diya
        ImageIcon atm = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        // ab image jo li hai ,, usko apne requirement ke hisab se scale kar liya
        Image i2 = atm.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT);
        // ab scale hone ke baad ,, waapis se usko image icon mein convert karna padega
        ImageIcon i3 = new ImageIcon(i2);
        // ab iss image ko hum apne frame mein direct add nahi kar sakte ,, toh image ko JLabel ke through lenge
        JLabel l3 = new JLabel(i3);
        // ab  uski boundary set kar denge ki wo image kaha chahiye
        l3.setBounds(0,0,1400,700);
        // ab isko add kar lenge frame mein,, humne frame class extend ki hai ,, isliye frame.add na likh kar add bhi likh sakte hai
        add(l3);

        // Label
        JLabel label = new JLabel("Please Select Your Transaction");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 24));
        label.setBounds(395,140,400,35);
        l3.add(label);  // hume label ko image ke upar show karwana hai

        // BUtton
        b1 = new JButton("DEPOSIT");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(370,227,150,30);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("CASH WITHDRAWL");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(617,227,150,30);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("FAST CASH");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(370,267,150,30);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("MINI STATEMENT");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(617,267,150,30);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("PIN CHANGE");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(370,307,150,30);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("BALANCE ENQUIRY");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125,128));
        b6.setBounds(617,307,150,30);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("EXIT");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65,125,128));
        b7.setBounds(617,345,150,30);
        b7.addActionListener(this);
        l3.add(b7);


        // Setting main class Frame
        setLayout(null);
        setBounds(0,0,1400,730);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1){
            new Deposit(pin);
            setVisible(false);
        }else if (e.getSource()==b7){
            System.exit(0);
        } else if (e.getSource()==b2) {
            new Withdrawl(pin);
            setVisible(false);
        } else if (e.getSource()==b6) {
            new BalanceEnquriy(pin);
            setVisible(false);
        } else if (e.getSource()==b3) {
            new FastCash(pin);
            setVisible(false);
        } else if (e.getSource()==b5) {
            new Pin(pin);
            setVisible(false);
        } else if (e.getSource()==b4) {
            new mini(pin);
        }
    }


    public static void main(String[] args) {

        new main_Class("");
    }
}
