package monpackage;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.OwnerPIN;

public class MyServer extends Applet {
	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_TEST_CODE_PIN = 0x00;
	public static final byte INS_INTERROGER_COMPTE = 0x01;
	public static final byte INS_INCREMENTER_COMPTE = 0x02;
	public static final byte INS_DECREMENTER_COMPTE = 0x03;

	public final static short MAX_BALANCE = 0x7FFF;

	public final static byte MAX_MONTANT_TRANSACTION = (byte)127;

	public final static byte MAX_ERROR_PIN = (byte) 0x03;

	public final static byte MAX_PIN_LENGTH = (byte) 0x04;

	
	private byte[] INIT_PIN = { (byte) 2, (byte) 0,(byte) 0,(byte) 2 };
	

	final static short SW_VERIFICATION_FAILED = 0x6300;


	final static short SW_PIN_VERIFICATION_REQUIRED = 0x6301;

	final static short SW_INVALID_TRANSACTION_AMOUNT = 0x6A83;


	final static short SW_EXCEED_MAXIMUM_BALANCE = 0x6A84;
	
	final static short SW_NEGATIVE_BALANCE = 0x6A85;


	
	OwnerPIN pin;
	static long balance ;

	private MyServer(byte bArray[], short bOffset, byte bLength) {
		pin = new OwnerPIN(MAX_ERROR_PIN, MAX_PIN_LENGTH);

		
		pin.update(INIT_PIN,(short) 0, (byte) 0x04);
	}

	public static void install(byte bArray[], short bOffset, byte bLength) throws ISOException {
		new MyServer(bArray, bOffset, bLength).register();
	}

	public boolean select() {

		
		if (pin.getTriesRemaining() == 0)
			return false;

		return true;

	}

	public void deselect() {
		pin.reset();
	}

	public void process(APDU apdu) throws ISOException {

		

		byte[] buffer = apdu.getBuffer();

		
		if (apdu.isISOInterindustryCLA()) {
			if (buffer[ISO7816.OFFSET_INS] == (byte) (0xA4)) {
				return;
			} else {
				ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
			}
		}

		
		if (this.selectingApplet())
			return;
		if (buffer[ISO7816.OFFSET_CLA] != CLA_MONAPPLET) {
			ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
		}

		switch (buffer[ISO7816.OFFSET_INS]) {
		case INS_TEST_CODE_PIN:
			VerificationPIN(apdu);
			break;
		case INS_INCREMENTER_COMPTE:
			AjouterArgent(apdu);
			break;
		case INS_DECREMENTER_COMPTE:
			ExtraireArgent(apdu);
			break;
		case INS_INTERROGER_COMPTE:
			getBalance(apdu);
			break;

		default:
			ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
		}

	}

	private void AjouterArgent(APDU apdu) {

		
		if (!pin.isValidated())
			ISOException.throwIt(SW_PIN_VERIFICATION_REQUIRED);

		byte[] buffer = apdu.getBuffer();

		
		byte numBytes = buffer[ISO7816.OFFSET_LC];

		
		byte byteRead = (byte) (apdu.setIncomingAndReceive());

		
		if (numBytes != byteRead)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
		
		long creditAmount = 0;
		for(int i=0;i<numBytes;i++)
		{
			creditAmount+=(long)buffer[(ISO7816.OFFSET_CDATA)+i];
		}
		

		
		if (creditAmount < 0)
			ISOException.throwIt(SW_INVALID_TRANSACTION_AMOUNT);

		
		if ((long) (balance + creditAmount) > MAX_BALANCE)
			ISOException.throwIt(SW_EXCEED_MAXIMUM_BALANCE);

		
		balance = (long) (balance + creditAmount);

	}

	private void ExtraireArgent(APDU apdu) {

		
		if (!pin.isValidated())
			ISOException.throwIt(SW_PIN_VERIFICATION_REQUIRED);

		byte[] buffer = apdu.getBuffer();

		byte numBytes = (byte) (buffer[ISO7816.OFFSET_LC]);

		byte byteRead = (byte) (apdu.setIncomingAndReceive());

		if (numBytes != byteRead )
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);

		
		long debitAmount = 0;
		for(int i=0;i<numBytes;i++)
		{
			debitAmount+=(long)buffer[(ISO7816.OFFSET_CDATA)+i];
		}

		
		if ((debitAmount < 0))
			ISOException.throwIt(SW_INVALID_TRANSACTION_AMOUNT);

		
		if ((long) (balance - debitAmount) < (long) 0)
			ISOException.throwIt(SW_NEGATIVE_BALANCE);

		balance = (long) (balance - debitAmount);

	}

	private void getBalance(APDU apdu) {

		byte[] buffer = apdu.getBuffer();


		short le = apdu.setOutgoing();

		if (le < 2)
			ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);

		

		
		int number_outputs=1;
		long balanceSave=balance;
		while(balanceSave>127)
		{
			number_outputs++;
			balanceSave-=127;
		}
		apdu.setOutgoingLength((byte) (number_outputs+2));
		
		buffer[0] = (byte) (balance >> 8);
		buffer[1] = (byte) (number_outputs);
		
		int counter=2;
		balanceSave=balance;
		while(balanceSave>127)
		{
			buffer[counter]=(byte) (127);
			counter++;
			balanceSave-=127;
		}
		buffer[counter] = (byte) (balanceSave & 0xFF);

		apdu.sendBytes((short) 0, (short)(number_outputs+2));

	}

	private void VerificationPIN(APDU apdu) {

		byte[] buffer = apdu.getBuffer();
		
		byte byteRead = (byte) (apdu.setIncomingAndReceive());

		
		if (pin.check(buffer, ISO7816.OFFSET_CDATA, byteRead) == false)
			ISOException.throwIt(SW_VERIFICATION_FAILED);
	
	}
}
