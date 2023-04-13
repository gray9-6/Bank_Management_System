package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    TextField textField;
    JButton b1, b2;
    String pin;
    Withdrawl(String pin){

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
        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(400,130,700,35);
        l3.add(label1);  // hume label ko image ke upar show karwana hai

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(400,160,400,35);
        l3.add(label2);

        // textfile
        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(400,200,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField); // hume text ko image ke upar show karwana hai

        // Adding buttons
        b1 = new JButton("WITHDRAW");
        b1.setBounds(615,307,150,30);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(615,347,150,30);
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
        /*getting all the data,,, which enters by user and storing in string format,, this data is now going to store
         * in our database*/
        if(e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();

                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    Conne conne = new Conne();
                    // maine query chala kar database se pin liya or usko resultset mein store kar liya
                    ResultSet resultSet = conne.statement.executeQuery("select * from bank where pin = '" + pin + "'");
                    int balance = 0;
                    // data jo query se aaya hai wo resluset mein store hua bhi hai ya nhi
                    while (resultSet.next()) {
                        // agar result set mein store ho gaya hai
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    // agar user ,,,account mein pade amount se jyada,, amount withdraw kar rha hai to insufficient balance
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

                    // hume apne table mein entry bhi karwani hogi ki user ne withdraw bhi kiya hai
                    conne.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "' )");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                    setVisible(false);
                    new main_Class(pin);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if(e.getSource() == b2){
            setVisible(false);
            new main_Class(pin);
        }

    }


    public static void main(String[] args) {

        new Withdrawl("");
    }
}
