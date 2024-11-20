
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmPolinomios extends JFrame {

    private final JButton btnAgregar;
    private final JButton btnCalcular;
    private final JButton btnLimpiar;
    private final JComboBox cmbOperacion;
    private final JComboBox cmbPolinomio;
    private final JLabel lblCoeficiente;
    private final JLabel lblX;
    private final JLabel lblExponente;
    private final JLabel lblPolinomio1;
    private final JLabel lblPolinomio2;
    private final JLabel lblPolinomioR;
    private final JLabel lblPolinomioRE;
    private final JLabel lblPolinomioRD;
    private final JTextField txtCoeficiente;
    private final JTextField txtExponente;

    public FrmPolinomios() {
        lblCoeficiente = new JLabel();
        txtCoeficiente = new JTextField();
        lblX = new JLabel();
        lblExponente = new JLabel();
        txtExponente = new JTextField();
        cmbPolinomio = new JComboBox();
        btnAgregar = new JButton();
        btnLimpiar = new JButton();
        lblPolinomio1 = new JLabel();
        lblPolinomio2 = new JLabel();
        cmbOperacion = new JComboBox();
        lblPolinomioR = new JLabel();
        lblPolinomioRE = new JLabel();
        lblPolinomioRD = new JLabel();
        btnCalcular = new JButton();

        setSize(600, 450);
        setTitle("Polinomios");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblCoeficiente.setText("Coeficiente:");
        lblCoeficiente.setBounds(10, 60, 80, 25);
        getContentPane().add(lblCoeficiente);

        txtCoeficiente.setBounds(80, 60, 100, 25);
        getContentPane().add(txtCoeficiente);

        lblX.setFont(new java.awt.Font("Times New Roman", 2, 48)); // NOI18N
        lblX.setText("x");
        lblX.setBounds(180, 40, 50, 40);
        getContentPane().add(lblX);

        lblExponente.setText("Exponente");
        lblExponente.setBounds(130, 20, 80, 25);
        getContentPane().add(lblExponente);

        txtExponente.setBounds(210, 20, 100, 25);
        getContentPane().add(txtExponente);

        cmbPolinomio.setModel(
                new DefaultComboBoxModel(new String[]{"Polinomio 1", "Polinomio 2"}));
        cmbPolinomio.setBounds(300, 50, 100, 25);
        getContentPane().add(cmbPolinomio);

        btnAgregar.setText("Agregar");
        btnAgregar.setBounds(410, 50, 80, 25);
        getContentPane().add(btnAgregar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarClick(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBounds(500, 50, 80, 25);
        getContentPane().add(btnLimpiar);

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarClick(evt);
            }
        });

        lblPolinomio1.setBackground(new java.awt.Color(0, 153, 204));
        lblPolinomio1.setOpaque(true);
        lblPolinomio1.setBounds(0, 90, 600, 50);
        getContentPane().add(lblPolinomio1);

        lblPolinomio2.setBackground(new java.awt.Color(0, 153, 204));
        lblPolinomio2.setOpaque(true);
        lblPolinomio2.setBounds(0, 150, 600, 50);
        getContentPane().add(lblPolinomio2);

        btnCalcular.setText("Calcular");
        btnCalcular.setBounds(10, 210, 100, 25);
        getContentPane().add(btnCalcular);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCalcularClick(evt);
            }
        });

        cmbOperacion.setModel(
                new DefaultComboBoxModel(new String[]{"Suma", "Resta", "Multiplicacion", "Division", "Derivada"}));
        cmbOperacion.setBounds(120, 210, 100, 25);
        getContentPane().add(cmbOperacion);

        lblPolinomioR.setBackground(new java.awt.Color(255, 204, 153));
        lblPolinomioR.setOpaque(true);
        lblPolinomioR.setBounds(0, 250, 600, 50);
        getContentPane().add(lblPolinomioR);

        lblPolinomioRE.setBackground(new java.awt.Color(255, 255, 255));
        lblPolinomioRE.setOpaque(true);
        lblPolinomioRE.setBounds(0, 370, 600, 50);
        getContentPane().add(lblPolinomioRE);

        lblPolinomioRD.setBackground(new java.awt.Color(255, 153, 153));
        lblPolinomioRD.setOpaque(true);
        lblPolinomioRD.setBounds(0, 310, 600, 50);
        getContentPane().add(lblPolinomioRD);
    }

    Polinomio p1 = new Polinomio();
    Polinomio p2 = new Polinomio();

    private void btnAgregarClick(ActionEvent evt) {
        double coef = Double.parseDouble(txtCoeficiente.getText());
        int expo = Integer.parseInt(txtExponente.getText());

        switch (cmbPolinomio.getSelectedIndex()) {
            case 0:
                p1.agregar(new Nodo(coef, expo));
                p1.mostrar(lblPolinomio1);
                break;
            case 1:
                p2.agregar(new Nodo(coef, expo));
                p2.mostrar(lblPolinomio2);
                break;
        }
    }

    private void btnCalcularClick(ActionEvent evt) {
        Polinomio pR;
        switch (cmbOperacion.getSelectedIndex()) {
            case 0:
                // sumar
                pR = Polinomio.sumar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 1:
                // restar
                pR = Polinomio.restar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 2:
                // multiplicar
                pR = Polinomio.multiplicar(p1, p2);
                pR.mostrar(lblPolinomioR);
                break;
            case 3:
                // dividir
                Polinomio[] result = Polinomio.dividir(p1, p2);
                result[0].mostrar(lblPolinomioR); // Cociente
                result[1].mostrar(lblPolinomioRE); // Residuo
                break;
            case 4:
                // derivada
                pR = p1.derivar(p1); // Deriva el polinomio p1
                pR.mostrar(lblPolinomioR);

        }
    }

    private void btnLimpiarClick(ActionEvent evt) {
        p1 = new Polinomio();
        p2 = new Polinomio();
        lblPolinomio1.setText("");
        lblPolinomio2.setText("");
        lblPolinomioR.setText("");
        lblPolinomioRE.setText("");
        lblPolinomioRD.setText("");
    }
}
