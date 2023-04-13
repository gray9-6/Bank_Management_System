package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {
    JButton b1,b2;
    JPasswordField p1,p2;
    String pin;
    Pin(String pin){

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
        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 14));
        label1.setBounds(460,120,400,35);
        l3.add(label1);  // hume label ko image ke upar show karwana hai

        JLabel label2 = new JLabel("New PIN: ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 14));
        label2.setBounds(380,170,150,35);
        l3.add(label2);

        // Password
        p1 = new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(550,170,150,25);
        p1.setFont(new Font("Raleway", Font.BOLD,20));
        l3.add(p1);

        JLabel label3 = new JLabel("Re-Enter New PIN: ");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD, 14));
        label3.setBounds(380,200,400,35);
        l3.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(550,205,150,25);
        p2.setFont(new Font("Raleway", Font.BOLD,20));
        l3.add(p2);

        // Adding buttons
        b1 = new JButton("CHANGE");
        b1.setBounds(615,307,150,30);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(615,345,150,30);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        // Setting Frame
        setLayout(null);
        setBounds(0,0,1400,730);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String pin1 = p1.getText();
            String pin2 = p2.getText();

            // agar  pin1 & pin2 match nahi kar rhi
            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            if (e.getSource()==b1){   // agar user ne pin enter hi nahi kari
                if (p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter New PIN");
                    return;
                }
                if (p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Re-Enter New PIN");
                    return;
                }

                // connection ka object banaya, pin change karne ke liye ,,, pin ko humne 3 tables mein store karwaya tha
                Conne conne = new Conne();
                String q1 = "update bank set pin = '"+pin1+"' where pin = '"+pin+"'";  // updating pin
                String q2 = "update login set pin = '"+pin1+"' where pin = '"+pin+"'";
                String q3 = "update signup3 set pin = '"+pin1+"' where pin = '"+pin+"'";

                // ab unki query banayi,, ou value update karwa di
                conne.statement.executeUpdate(q1);
                conne.statement.executeUpdate(q2);
                conne.statement.executeUpdate(q3);

                // Message display karwaya
                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);   // sucessfully changes ke baad frame clos ho jaayega
                new main_Class(pin); // or ab hum main frame pe chale jaayege

            } else if (e.getSource()==b2) {   // back button
                new main_Class(pin);
                setVisible(false);
            }


        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {

        new Pin("");
    }


}
