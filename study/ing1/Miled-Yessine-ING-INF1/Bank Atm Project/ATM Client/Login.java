import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

public class Login extends JFrame implements ActionListener{

    JLabel titre, lPin;
    JPasswordField tPin;
    JButton bLog;
    JPanel pT, pForm, pPin, pBtn;

    public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_TEST_CODE_PIN = 0x00;
	public static final byte INS_INTERROGER_COMPTE = 0x01;
	public static final byte INS_INCREMENTER_COMPTE = 0x02;
	public static final byte INS_DECREMENTER_COMPTE = 0x03;

    private MyClient client;
    private Apdu apdu;

    public static void main (String [] args) throws IOException, CadTransportException
    {
        MyClient client = new MyClient();
        Apdu apdu = null ;
        client.Connect();
        client.Select();
        //jcwde Applet.app
        //Code PIN est 2002
        Login l = new Login(client,apdu);
    }

    public Login(MyClient client,Apdu apdu) {
        this.client=client;
        this.apdu=apdu;

        this.setLayout(new GridLayout(3, 1));
        pT = new JPanel();
        pForm = new JPanel();
        pBtn = new JPanel();

        titre = new JLabel("ATM");
        titre.setFont(new Font("Serif", Font.BOLD, 40));


        lPin = new JLabel("PIN Code :");
        tPin = new JPasswordField(20);
        tPin.setPreferredSize(new Dimension(30, 30));
        lPin.setFont(new Font("Serif", Font.BOLD, 15));
        lPin.setPreferredSize(new Dimension(100, 45));
        pPin = new JPanel();
        pPin.add(lPin);
        pPin.add(tPin);

        bLog = new JButton("Connect");
        bLog.addActionListener(this);
        bLog.setPreferredSize(new Dimension(150, 25));

        pT.add(titre);
        pForm.setLayout(new GridLayout(1, 1));
        pForm.add(pPin);
        pBtn.add(bLog);

        this.setVisible(true);
        this.setSize(500, 600);

        this.add(pT, BorderLayout.NORTH);
        this.add(pForm, BorderLayout.CENTER);
        this.add(pBtn, BorderLayout.SOUTH);
    }
    private void messageBox(String msg,int type,String tit)
    {
            JOptionPane optionPane = new JOptionPane(msg,type);
            JDialog dialog = optionPane.createDialog(tit);
            dialog.setAlwaysOnTop(true); // to show top of all other application
            dialog.setVisible(true); // to visible the dialog

    }
    
    public void actionPerformed(ActionEvent b ) {
        if(b.getSource()==bLog)
        {
            String pass=String.valueOf(tPin.getPassword());
            int byte1=(int)(Integer.parseInt(pass)/1000)%10;
            int byte2=(int)(Integer.parseInt(pass)/100)%10;
            int byte3=(int)(Integer.parseInt(pass)/10)%10;
            int byte4=(int)(Integer.parseInt(pass))%10;

            byte[] pin_ok ;
            pin_ok= new byte[] { (byte)byte1,(byte)byte2,(byte)byte3,(byte)byte4 };
            
            try{
                this.apdu = client.Msg(INS_TEST_CODE_PIN, (byte) 0x04, pin_ok, (byte) 0x7f);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            

            if (apdu.getStatus() == 0x6300) {
            {
                messageBox("Code PIN invalide !", JOptionPane.WARNING_MESSAGE, "Warning");
            }
            } else
            {
                System.out.println("Code PIN valide");
                Money m=new Money(client,apdu);
            }
        }
        tPin.setText("");
    }
    
    
}

