package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit  extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton b1, b2;

    Deposit(String pin){

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
        l3.setBounds(0,0,1300,700);
        // ab isko add kar lenge frame mein,, humne frame class extend ki hai ,, isliye frame.add na likh kar add bhi likh sakte hai
        add(l3);


        // Label
        JLabel label1 = new JLabel("ENETR AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(360,140,400,35);
        l3.add(label1);  // hume label ko image ke upar show karwana hai

        // textfile
        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(360,190,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField); // hume text ko image ke upar show karwana hai

        // Adding buttons
        b1 = new JButton("DEPOSIT");
        b1.setBounds(565,300,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(565,340,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);


       // set Deposit Frame
        setLayout(null);
        setBounds(0,0,1400,730);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            /*getting all the data,,, which enters by user and storing in string format,, this data is now going to store
             * in our database*/
            String amount = textField.getText();
            Date date = new Date();

            // agar user ne amount enter nahi kiya
            if (e.getSource()==b1) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Conne conne = new Conne();
                    conne.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "','Deposit', '" + amount + "')");
                    // table mein data store hone ke baad ab hum user ko msg display karenge
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    // ab jab deposit ho gaya,, toh hum chahte hai hamar deposit wala frame close ho jaaye
                    setVisible(false);
                    // or hum main frame mein chale jaaye
                new main_Class(pin);
                }
            }else if (e.getSource()==b2){
                // ab agar user back button pe click kare toh ,, deposit waala frame close ho jaayega
                setVisible(false);
                // or wo main frame pe chala jaayega
                new main_Class(pin);
            }

        } catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Deposit("");
    }


}
