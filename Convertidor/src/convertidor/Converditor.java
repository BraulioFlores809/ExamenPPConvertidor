package convertidor;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Converditor extends JFrame{ 

	private static final long serialVersionUID = 1583724102189855698L;


	JTextField pantalla;


	double resultado;

	String operacion;


	JPanel panelNumeros, panelOperacion;


	boolean nuevaOperacion = true;


	public Converditor() {
		super();
		setSize(700, 700);
		setTitle("Convertidor de Pesos a Dollars");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);


		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}

		nuevoBotonNumerico(".");

		panel.add("Center", panelNumeros);

		panelOperacion = new JPanel();
		panelOperacion.setLayout(new GridLayout(6, 6));
		panelOperacion.setBorder(new EmptyBorder(4, 4, 4, 4));

		nuevoBotonOperacion("Convertir");
                nuevoBotonOperacion("=");
                nuevoBotonOperacion("CE");


		panel.add("East", panelOperacion);

		validate();
	}


	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.RED);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperacion.add(btn);
	}


	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}

	private void calcularResultado() {
		if (operacion.equals("Convertir")) {
			resultado = resultado/19;
		} 

		pantalla.setText("" + resultado);
		operacion = "";
	}
}
