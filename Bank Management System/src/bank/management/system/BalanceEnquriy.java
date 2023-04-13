package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquriy extends  JFrame implements ActionListener {

    String pin;
    JLabel label2;
    JButton b1;
    BalanceEnquriy(String pin){

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
        JLabel label1 = new JLabel("You Current Balance is Rs ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(370,160,700,35);
        l3.add(label1);  // hume label ko image ke upar show karwana hai

        label2 = new JLabel();
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(370,180,400,35);
        l3.add(label2);

        // Adding buttons
        b1 = new JButton("Back");
        b1.setBounds(610,345,150,30);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        int balance = 0;
        try{
            Conne conne = new Conne();
            // hume amount ka data nikalna hai or usko label mein store karwana hai ,, kyonki balance show bhi karna hai
            ResultSet resultSet = conne.statement.executeQuery("select * from bank where pin = '"+pin+"' ");
            // ab pata lagana padega ki data result set mein nikal kar aaya bhi hai ya nhi
            while (resultSet.next()){
                // agar result set mein store ho gaya hai
                if(resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else{
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // ab jo bhi balance hai usko label 2 mein store karwa diya
        label2.setText("" + balance);

        // setting balanceEnquiry  Frame
        setLayout(null);
        setBounds(0,0,1400,730);
        //to remove the close,minimize and expand opiton
        setUndecorated(true);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // yaha hume bs back button ko kaam karwana hai
        setVisible(false);
        new main_Class(pin);
    }


    public static void main(String[] args) {

        new BalanceEnquriy("");
    }

}
