package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    FastCash(String pin){

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
        JLabel label = new JLabel("SELECT WITHDRAWL AMOUNT");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 20));
        label.setBounds(410,140,400,35);
        l3.add(label);  // hume label ko image ke upar show karwana hai

        // BUtton
        b1 = new JButton("Rs. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(370,227,150,30);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(617,227,150,30);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(370,267,150,30);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(617,267,150,30);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(370,307,150,30);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125,128));
        b6.setBounds(617,307,150,30);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
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
        if (e.getSource()==b7) {   // agar back button press kiya hai toh
            setVisible(false);   // fast cash waale ka frame visiblitiy false set kar do
            new main_Class(pin); // or main frame pe chale jaao
        }else {
            /*getting all the data,,, which enters by user and storing in string format,, this data is now going to store
             * in our database*/
            // ye e.getsource ek object ko return karta hai,, isliye JButton ka use kiya hai ,, taaki error na mile
            // substring isliye liya hai taaki,, Rs. 500 mein se hume bas 500 chahiye jo 4 index ke baad se hai
            String amount = ((JButton)e.getSource()).getText().substring(4);
            // ab database ke liye connection banana padega
            Conne conne = new Conne();
            // or time bhi chahiye ki kis time pe amount nikala hai
            Date date = new Date();
            try{
                // ab bank table mein se data nikalna hai
                ResultSet resultSet = conne.statement.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance =0;
                while (resultSet.next()){
                    // agar toh table mein type column mein deposit hai toh deposit karo
                    if (resultSet.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    }else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));  // nahi toh withdraw karo
                    }
                }
                // agar toh insufficient balance hai
                if (e.getSource() != b7 && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }
                // ab jo amount hai withdraw ke baad table mein insert bhi toh karna hai
                conne.statement.executeUpdate("insert into bank values('"+pin+"','"+date+"', 'withdrawl', '"+amount+"')");
                // or user ko msg display kiya
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
            }catch (Exception exception){
                exception.printStackTrace();
            }
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {

        new FastCash("");
    }


}
