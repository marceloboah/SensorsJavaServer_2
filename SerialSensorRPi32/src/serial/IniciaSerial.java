package serial;




import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logger.StrackTraceAppender;


public class IniciaSerial extends JFrame  implements ActionListener  {




		//public JFrame myFrame;
		public JPanel topPanel;
		public JPanel mainPanel;
		public JButton btnCadastrar;
		public DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		public Date date = new Date(); 
		public JLabel input01;
		public JLabel input02;
		public JTextField inputPortLinux;
		public int idNf;
		public boolean validaOperacao;
		public GridBagLayout gridBag = new GridBagLayout();
		public GridBagConstraints c = new GridBagConstraints();
		public JButton btnIniciar = new JButton("Iniciar");
		public JComboBox<String> inputPort = new JComboBox<String>();
		public List<String> listPort;
		public URL caminhoImagem;
		public Image iconeTitulo;
		public Font fonte = new Font("Dialog", Font.BOLD, 30); 
		public Font fonts = new Font("Dialog", Font.BOLD, 18); 
		
		public void Inicia() {
			
			this.setTitle("Seleciona Porta do Sensor");
			this.setSize(800, 600);
			
			this.setResizable(false);
			caminhoImagem = this.getClass().getClassLoader().getResource("img/joana3.png");
	        iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
	        this.setIconImage(iconeTitulo);
			

	        input01 = new JLabel("Seleciona Porta do Sensor (Windows):");
			input01.setFont(fonte);
			input02 = new JLabel("Digite a Porta do Sensor (Linux):");
			input02.setFont(fonte);
			
			inputPortLinux = new JTextField(5); 
			inputPortLinux.setFont(fonts);
			
			
			topPanel = new JPanel();
			topPanel.setSize(800, 600);
			
			for (int i = 1; i < 21; i++) {
				inputPort.addItem("COM"+i);
			}
			
			inputPort.setFont(fonts);
			btnIniciar.addActionListener(this);
			

			topPanel.setLayout(gridBag);

			c.gridx = 0;
			c.gridy = 0;
			topPanel.add(input01, c);
			
			c.gridx = 0;
			c.gridy = 1;
			topPanel.add(input02, c);

			c.gridx = 1;
			c.gridy = 0;
			topPanel.add(inputPort, c);
			
			
			
			c.gridx = 1;
			c.gridy = 1;
			topPanel.add(inputPortLinux, c);
			
			c.gridx = 1;
			c.gridy = 2;
			topPanel.add(btnIniciar, c);

			this.getContentPane().add(topPanel);

			this.setVisible(true);
		}


		@Override
		public void actionPerformed(ActionEvent e) {

			
			
			
			if (e.getSource().equals(btnIniciar)) {

				
				String portaSelecionada = inputPort.getSelectedItem().toString();
				btnIniciar.setEnabled(false);btnIniciar.setEnabled(false);
				inputPort.setEnabled(false);
				System.out.println(inputPort.getSelectedItem());
				System.out.println(inputPortLinux.getText());
				String portaAtiva = String.valueOf(inputPort.getSelectedItem());
				String portaLinux = String.valueOf(inputPortLinux.getText());
				SerialTest main = new SerialTest();
				main.initialize(portaAtiva,portaLinux);
				System.out.println("Started");
				
				
				
			}
			
		}

	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IniciaSerial classeTeste = new IniciaSerial();
		classeTeste.Inicia();
	}
	

}
