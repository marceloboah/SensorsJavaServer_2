package serial;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener;
import model.Config;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import controler.ConfigDao;
 
/** Programa de Testes Marcelo Boá */
/** Programa de Testes Marcelo Boá */
/** Programa de Testes Marcelo Boá */
/** Programa de Testes Marcelo Boá */
/** Programa de Testes Marcelo Boá */
/** Programa de Testes Marcelo Boá */
public class SerialTest implements SerialPortEventListener {
	SerialPort serialPort;
        /** The port we're normally going to use. */
	/*private String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM4", // Windows
			};*/
	/** Buffered input stream from the port */
	private InputStream input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	
	public String mbrreader = null;
	public int sizes = 0;
	public int contaFrase = 0;
	List<String> vetorGeral = new ArrayList();
	public String recebe = "";
	public int counter=0;
	//public Config config = new Config();
	public ConfigDao configDao = new ConfigDao();

	
	public void initialize(String myport, String linuxPort) {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		String PORT_NAMES[] = { 
				"/dev/tty.usbserial-A9007UX1", // Mac OS X
				linuxPort, // Linux "/dev/ttyUSB0"
				myport, // Windows
				};
		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		/*if (oEvent.getEventType() == SerialPortEvent.OUTPUT_BUFFER_EMPTY) {
			try {
				output.write(113);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		

		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			int data;
			byte[] buffer = new byte[1024];
			String parametro="";
			try {
				int len = 0;
                while ( ( data = input.read()) > -1 )
                {
                    if ( data == 'K' ) {
                    	System.out.println("-");
                    	
                        break;
                    }
                    buffer[len++] = (byte) data;
                }
                parametro += new String(buffer,0,len);
                configDao.updateTable(parametro);
                System.out.print(new String(buffer,0,len));
                
                
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
			
			/*
			try {
				int available = input.available();
				byte chunk[] = new byte[available];
				input.read(chunk, 0, available);
				
				
				
				
				new Thread() { 
					
					@Override public void run() {
						// Displayed results are codepage dependent
						
						String mystr = 	new String(chunk);
						
						//System.out.print(mystr);
						//if(chunk.length>=7){
							for(int i = 0; i < chunk.length; i++)
						    {
								//System.out.print(i+"-");
								
								char myval2 = (char) chunk[i];
								
								System.out.println("myval2="+myval2);
						    }
						//}
						
					}
					
				}.start();	
				
				
				
				
				recebe = "";
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
			*/
		}
		//System.out.println("recebe="+recebe);
		/*try {
			Thread.sleep(100);
			System.out.println("Tamanho Vetor="+vetorGeral.size());
			vetorGeral = new ArrayList();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//System.out.println("Tamanho Vetor="+vetorGeral.size());
		//vetorGeral = new ArrayList();
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	/*public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize("COM3");
		System.out.println("Started");
		
	}*/
}