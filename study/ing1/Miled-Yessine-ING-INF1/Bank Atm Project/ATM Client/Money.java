import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadTransportException;

public class Money extends JFrame implements ActionListener{

    JLabel titre,lSolde;
    JTextField tAutre;
    JButton b10,b20,b50,b100,b150,b200;
    JRadioButton rCredit,rDebit;
    ButtonGroup grp;
    JPanel pT, pSolde,pChoix,pBtn;

    public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_TEST_CODE_PIN = 0x00;
	public static final byte INS_INTERROGER_COMPTE = 0x01;
	public static final byte INS_INCREMENTER_COMPTE = 0x02;
	public static final byte INS_DECREMENTER_COMPTE = 0x03;

    private MyClient client;
    private Apdu apdu;

    public Money(MyClient client,Apdu apdu) {
        this.client=client;
        this.apdu=apdu;

        this.setLayout(new GridLayout(4, 1));
        pT = new JPanel();
        pSolde = new JPanel();
        pChoix = new JPanel();
        pBtn = new JPanel();

        titre = new JLabel("ATM");
        titre.setFont(new Font("Serif", Font.BOLD, 40));

        try{
            apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
        }
        catch(Exception ex){System.out.println(ex);}
        long solde=0;
        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur : status word different de 0x9000");
        } else {
            int nombre_outputs=apdu.dataOut[1];
            
            for(int i=0;i<nombre_outputs;i++)
            {
                solde+=apdu.dataOut[i+2];
            }
            System.out.println("Valeur du compteur : " + solde);
        }
        lSolde = new JLabel("Votre solde est : "+solde+" DT");
        lSolde.setFont(new Font("Serif", Font.CENTER_BASELINE, 20));


        b10 = new JButton("10 DT");
        b10.addActionListener(this);
        b10.setPreferredSize(new Dimension(150, 25));
        b20 = new JButton("20 DT");
        b20.addActionListener(this);
        b20.setPreferredSize(new Dimension(150, 25));
        b50 = new JButton("50 DT");
        b50.addActionListener(this);
        b50.setPreferredSize(new Dimension(150, 25));
        b100 = new JButton("100 DT");
        b100.addActionListener(this);
        b100.setPreferredSize(new Dimension(150, 25));
        b150 = new JButton("150 DT");
        b150.addActionListener(this);
        b150.setPreferredSize(new Dimension(150, 25));
        b200 = new JButton("200 DT");
        b200.addActionListener(this);
        b200.setPreferredSize(new Dimension(150, 25));
        
        grp=new ButtonGroup();

        rCredit = new JRadioButton("Crediter (+)");
        rCredit.addActionListener(this);
        rCredit.setPreferredSize(new Dimension(150, 25));

        rDebit = new JRadioButton("Debiter (-)");
        rDebit.addActionListener(this);
        rDebit.setPreferredSize(new Dimension(150, 25));
        

        grp.add(rCredit);
        grp.add(rDebit);


        pT.add(titre);
        pSolde.add(lSolde);

        pChoix.add(b10);pChoix.add(b100);
        pChoix.add(b20);pChoix.add(b150);
        pChoix.add(b50);pChoix.add(b200);
        pChoix.setLayout(new GridLayout(3,2));

        pBtn.add(rCredit);
        pBtn.add(rDebit);


        this.setVisible(true);
        this.setSize(500, 600);

        this.add(pT, BorderLayout.NORTH);
        this.add(pSolde, BorderLayout.NORTH);
        this.add(pChoix, BorderLayout.CENTER);
        this.add(pBtn, BorderLayout.SOUTH);
    }
    private void messageBox(String msg,int type,String tit)
    {
            JOptionPane optionPane = new JOptionPane(msg,type);
            JDialog dialog = optionPane.createDialog(tit);
            dialog.setAlwaysOnTop(true); // to show top of all other application
            dialog.setVisible(true); // to visible the dialog
    }

    private void debiter(int montant)
    {
        int montantInt=montant;
        int number_bytes = 1;
        int montantSave=montantInt;
        while(montantSave > 127)
        {
            number_bytes++;
            montantSave-=127;
        }
        
        byte[] montantTab = new byte[number_bytes];
        int counter=0;
        montantSave=montantInt;
        while(montantSave > 127)
        {
            montantTab[counter]=(byte)127;
            montantSave-=127;
            counter++;
        }
        montantTab[counter]=(byte)montantSave;
        try{
            apdu = client.Msg(INS_DECREMENTER_COMPTE, (byte) number_bytes, montantTab, (byte) 0x7f);
        }catch(Exception ex){System.out.println(ex);}
        
        if (apdu.getStatus() == 0x6A85) {
            System.out.println("Erreur : status word different de 0x9000");
            messageBox("Impossible d'effectuer cette opération !", JOptionPane.WARNING_MESSAGE, "Warning");
        } else {
            System.out.println("OK");
            messageBox("Un montant de "+montant+" DT a été retiré avec succès de votre compte !", JOptionPane.INFORMATION_MESSAGE, "Success");
        }
    }

    private void crediter(int montant)
    {
        int montantInt=montant;
        int number_bytes = 1;
        int montantSave=montantInt;
        while(montantSave > 127)
        {
            number_bytes++;
            montantSave-=127;
        }
        
        byte[] montantTab = new byte[number_bytes];
        int counter=0;
        montantSave=montantInt;
        while(montantSave > 127)
        {
            montantTab[counter]=(byte)127;
            montantSave-=127;
            counter++;
        }
        montantTab[counter]=(byte)montantSave;
        try{
            apdu = client.Msg(INS_INCREMENTER_COMPTE, (byte) number_bytes, montantTab, (byte) 0x7f);
        }catch(Exception ex){System.out.println(ex);}
        
        if (apdu.getStatus() == 0x6A85) {
            System.out.println("Erreur : status word different de 0x9000");
            messageBox("Impossible d'effectuer cette opération !", JOptionPane.WARNING_MESSAGE, "Warning");
        } else {
            System.out.println("OK");
            messageBox("Un montant de "+montant+" DT a été déposé avec succès sur votre compte !", JOptionPane.INFORMATION_MESSAGE, "Success");
        }
    }
    

    public void actionPerformed(ActionEvent e ){
        if((e.getSource()==b10||e.getSource()==b20||e.getSource()==b50||e.getSource()==b100||e.getSource()==b150||e.getSource()==b200)&&(!rCredit.isSelected()&&!rDebit.isSelected())){
            messageBox("Veuillez sélectionner une option ci-dessous !", JOptionPane.WARNING_MESSAGE, "Warning");
        }

        if(rCredit.isSelected())
        {
            System.out.println("Crediter (+) is selected");
            if(e.getSource()==b10)
            {
                crediter(10);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b20)
            {
                crediter(20);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b50)
            {
                crediter(50);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b100)
            {
                crediter(100);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b150)
            {
                crediter(150);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b200)
            {
                crediter(200);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
        }



        if(rDebit.isSelected())
        {    
            System.out.println("Debiter (-) is selected");
            if(e.getSource()==b10)
            {
                debiter(10);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b20)
            {
                debiter(20);
                
                try{
                    apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                }
                catch(Exception ex){System.out.println(ex);}
                long balance=0;
                if (apdu.getStatus() != 0x9000) {
                    System.out.println("Erreur : status word different de 0x9000");
                } else {
                    int nombre_outputs=apdu.dataOut[1];
                    
                    for(int i=0;i<nombre_outputs;i++)
                    {
                        balance+=apdu.dataOut[i+2];
                    }
                    System.out.println("Valeur du compteur : " + balance);
                    
                    lSolde.setText("Votre solde est : "+balance+" DT");
                }
            }
            if(e.getSource()==b50)
            {
                debiter(50);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b100)
            {
                debiter(100);
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b150)
            {
                debiter(150);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
            if(e.getSource()==b200)
            {
                debiter(200);
                
                try{
                        apdu = client.Msg(INS_INTERROGER_COMPTE, (byte) 0x00, null, (byte) 0x7f);
                    }
                    catch(Exception ex){System.out.println(ex);}
                    long balance=0;
                    if (apdu.getStatus() != 0x9000) {
                        System.out.println("Erreur : status word different de 0x9000");
                    } else {
                        int nombre_outputs=apdu.dataOut[1];
                        
                        for(int i=0;i<nombre_outputs;i++)
                        {
                            balance+=apdu.dataOut[i+2];
                        }
                        System.out.println("Valeur du compteur : " + balance);
                        
                        lSolde.setText("Votre solde est : "+balance+" DT");
                    }
            }
        }
    }
    
}
