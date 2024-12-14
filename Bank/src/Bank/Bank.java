package Bank;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.OwnerPIN;
import javacard.framework.PIN;
import javacard.framework.Util;

public class Bank extends Applet implements PIN {
	
	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	
	public static final byte INS_Retrive_10 = 0x01;
	public static final byte INS_Retrive_20 = 0x02;
	public static final byte INS_Retrive_50 = 0x03;
	public static final byte INS_Retrive_100 = 0x04;
	public static final byte INS_Retrive_200 = 0x05;
	public static final byte INS_Retrive_Costume = 0x06;
	
	public static final byte INS_add_10 = 0x11;
	public static final byte INS_add_20 = 0x12;
	public static final byte INS_add_50 = 0x13;
	public static final byte INS_add_100 = 0x14;
	public static final byte INS_add_200 = 0x15;
	public static final byte INS_add_Costume = 0x16;
	
	public static final byte INS_Show_Balance = 0x20;
	
	public static final byte INS_VIRIF_PIN = 0x40;
	private final static byte PIN_SIZE =4;
	private final static byte PIN_TRY_LIMIT=3;
	
	private OwnerPIN pin;
	private byte [] balance;
	
	private Bank() {
        // Initialize PIN with maximum tries and PIN size
        pin = new OwnerPIN(PIN_TRY_LIMIT, PIN_SIZE);
        // Set the initial PIN value (example: 1234)
        byte[] initialPIN = {(byte)0x01, (byte)0x02, (byte)0x03, (byte)0x04};
        pin.update(initialPIN, (short)0, PIN_SIZE);
        
        balance= new byte [] {(byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,(byte)0x00};
        
	}

	public static void install(byte bArray[], short bOffset, byte bLength) throws ISOException {
		new Bank().register();
	}

	public void process(APDU apdu) throws ISOException {
		byte[] buffer = apdu.getBuffer();
		if (this.selectingApplet()) return;
		if (buffer[ISO7816.OFFSET_CLA] != CLA_MONAPPLET) {
		ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
		}
		switch (buffer[ISO7816.OFFSET_INS]) {
		case INS_VIRIF_PIN:
			PIN_Code(apdu);
			break;
		case INS_Retrive_Costume:
			Retrive_Costume(apdu);
			break;
		case INS_Retrive_10 :
			Retrive_10(apdu);
			break;
		case INS_Retrive_20 :
			Retrive_20(apdu);
			break;
		case INS_Retrive_50 :
			Retrive_50(apdu);
			break;
		case INS_Retrive_100 :
			Retrive_100(apdu);
			break;
		case INS_Retrive_200 :
			Retrive_200(apdu);
			break;
		case INS_add_10 :
			add_10(apdu);
			break;
		case INS_add_20 :
			add_20(apdu);
			break;
		case INS_add_50 :
			add_50(apdu);
			break;
		case INS_add_100 :
			add_100(apdu);
			break;
		case INS_add_200 :
			add_200(apdu);
			break;
		case INS_add_Costume :
			add_costume(apdu);
			break;

		case INS_Show_Balance:
			 show_balance(apdu);
			 break;

		default:
		ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);

	}}



	public boolean check(byte[] pinAttempt, short offset, byte length)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		// TODO Auto-generated method stub
		return pin.check(pinAttempt, offset, length);
	}

	public byte getTriesRemaining() {
		// TODO Auto-generated method stub
		return pin.getTriesRemaining();
	}

	public boolean isValidated() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public void PIN_Code(APDU apdu){
	byte[] buffer = apdu.getBuffer();
    short numBytes = apdu.setIncomingAndReceive();
    if (numBytes != PIN_SIZE) {
    	buffer[0] = pin.getTriesRemaining();
		apdu.setOutgoingAndSend((short) 0, (short) 1);
        ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
    }
    if (!check(buffer, ISO7816.OFFSET_CDATA, PIN_SIZE)) {
    	buffer[0] = pin.getTriesRemaining();
		apdu.setOutgoingAndSend((short) 0, (short) 1);
        ISOException.throwIt(ISO7816.SW_SECURITY_STATUS_NOT_SATISFIED);
    }
    else
    {
		buffer[0] = pin.getTriesRemaining();
		apdu.setOutgoingAndSend((short) 0, (short) 1);}


}

	 public static byte[] add(byte[] array1, byte[] array2) {
		    byte[] r = new byte[array1.length]; // assuming both arrays are same length
		    int carry = 0;
		    for ( int i = array1.length - 1; i >= 0; i-- ) { // LSB to MSB
		    	
				int sum = (array1[i] )+(array2[i] )+carry;
				//System.out.println(sum);
		        r[i] = (byte) (sum %10);
		        carry = (Integer) sum / 10;
		    }
		    return r;
		}

		public static byte[] subtract(byte[] num1, byte[] num2) {

	        byte[] result = new byte[num1.length];
	        int borrow = 0;

	        for (int i = num1.length - 1; i >= 0; i--) {
	            // Subtracting current digits along with borrow
	            int temp = (num1[i] & 0xFF) - (num2[i] & 0xFF) - borrow;

	            // If the subtraction is negative, add 10 and set borrow
	            if (temp < 0) {
	                temp += 10;
	                borrow = 1;
	            } else {
	                borrow = 0;
	            }

	            result[i] = (byte) temp;
	        }

	        return result;
	    
	    }
	
	public void Retrive_10(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01,(byte)0x00};
		balance= subtract(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void Retrive_20(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x02,(byte)0x00};
		balance= subtract(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void Retrive_50(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x05,(byte)0x00};
		balance= subtract(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void Retrive_100(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,(byte)0x00};
		balance= subtract(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void Retrive_200(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00,(byte)0x00};
		balance= subtract(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	
	public void Retrive_Costume(APDU apdu) {
		byte[] buffer = apdu.getBuffer();
		apdu.setIncomingAndReceive();
		
		byte numBytes = buffer[ISO7816.OFFSET_LC];
		if (numBytes != (byte) 0x05)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
		byte[] c = new byte[5];
		Util.arrayCopyNonAtomic(buffer, ISO7816.OFFSET_CDATA, c, (short) 0, (short) 5);
		balance = subtract(balance,c);
		
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
		
	}
	public void add_10(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01,(byte)0x00};
		balance= add(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = (byte) balance[i];
		}
		
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void add_20(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x02,(byte)0x00};
		balance= add(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void add_50(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x05,(byte)0x00};
		balance= add(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void add_100(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,(byte)0x00};
		balance= add(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void add_200(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		byte [] c= new byte [] {(byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00,(byte)0x00};
		balance= add(balance,c);
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}
	public void add_costume(APDU apdu) {
		byte[] buffer = apdu.getBuffer();
		apdu.setIncomingAndReceive();
		
		byte numBytes = buffer[ISO7816.OFFSET_LC];
		if (numBytes != (byte) 0x05)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
		byte[] c = new byte[5];
		Util.arrayCopyNonAtomic(buffer, ISO7816.OFFSET_CDATA, c, (short) 0, (short) 5);
		balance = add(balance,c);
		
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
		
	}
	
	public void show_balance(APDU apdu)
	{
		byte[] buffer = apdu.getBuffer();
		for (int i=0 ; i<balance.length ; i++)
		{
			buffer[i] = balance[i];
		}
		apdu.setOutgoingAndSend((short) 0, (short) 5);
	}

	
	
}