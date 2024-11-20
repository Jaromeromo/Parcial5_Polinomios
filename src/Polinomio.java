import java.awt.Font;
import javax.swing.JLabel;

public class Polinomio {
    private Nodo cabeza;

    
    public Polinomio() {
        cabeza = null;
    }

    public Polinomio(Nodo cabeza) {
        this.cabeza = cabeza; 
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    
    public Nodo getMayorExponente() {
        Nodo apuntador = cabeza;
        Nodo mayor = null;

        while (apuntador != null) {
            if (mayor == null || apuntador.getExponente() > mayor.getExponente()) {
                mayor = apuntador;
            }
            apuntador = apuntador.siguiente;
        }

        return mayor;
    }

    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                Nodo predecesor = null;
                int encontrado = 0;
                while (apuntador != null && encontrado == 0) {
                    if (n.getExponente() == apuntador.getExponente()) {
                        encontrado = 1;
                    } else if (n.getExponente() < apuntador.getExponente()) {
                        encontrado = 2;
                    } else {
                        predecesor = apuntador;
                        apuntador = apuntador.siguiente;
                    }
                }
                if (encontrado == 1) {
                    double coeficiente = n.getCoeficiente() + apuntador.getCoeficiente();
                    if (coeficiente == 0) {
                        if (predecesor == null) {
                            cabeza = apuntador.siguiente;
                        } else {
                            predecesor.siguiente = apuntador.siguiente;
                        }
                    } else {
                        apuntador.setCoeficiente(coeficiente);
                    }
                } else {
                    insertar(n, predecesor);
                }
            }
        }
    }

    public void insertar(Nodo n, Nodo predecesor) {
        if (n != null) {
            if (predecesor == null) {
                n.siguiente = cabeza;
                cabeza = n;
            } else {
                n.siguiente = predecesor.siguiente;
                predecesor.siguiente = n;
            }
        }
    }

    public void limpiar() {
        cabeza = null;
    }

    public String[] getTextos() {
        String[] lineas = new String[2];
        String espacio = " ";
        Nodo apuntador = cabeza;
        lineas[0] = "";
        lineas[1] = "";
        while (apuntador != null) {
            String texto = String.valueOf(apuntador.getCoeficiente()) + " X";
            if (apuntador.getCoeficiente() >= 0) {
                texto = "+" + texto;
            }
            lineas[0] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);
            lineas[1] += texto;

            texto = String.valueOf(apuntador.getExponente());
            lineas[0] += texto;
            lineas[1] += String.format("%0" + texto.length() + "d", 0).replace("0", espacio);

            apuntador = apuntador.siguiente;
        }

        return lineas;
    }

    public void mostrar(JLabel lbl) {
        String[] lineas = getTextos();
        String espacio = "&nbsp;";
        lineas[0] = lineas[0].replace(" ", espacio);
        lineas[1] = lineas[1].replace(" ", espacio);
        lbl.setFont(new Font("Courier New", Font.PLAIN, 12));
        lbl.setText("<html>" + lineas[0] + "<br>" + lineas[1] + "</html>");
    }

    public Polinomio derivar(Polinomio p1) {
        Polinomio pR = new Polinomio();
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            if (apuntador.getExponente() != 0) {
                Nodo n = new Nodo(apuntador.getCoeficiente() * apuntador.getExponente(), apuntador.getExponente() - 1);
                pR.agregar(n);
            }
            apuntador = apuntador.siguiente;
        }
    
        return pR;
    }
    
    
    public void agregarNodo(Nodo cabeza) {
        agregar(cabeza); 
    }
    

    // ********** Metodos estaticos **********

    public static Polinomio sumar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();

        while (apuntador1 != null || apuntador2 != null) {
            Nodo n = null;

            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                if (apuntador1.getCoeficiente() + apuntador2.getCoeficiente() != 0) {
                    n = new Nodo(apuntador1.getCoeficiente() + apuntador2.getCoeficiente(), apuntador1.getExponente());
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null
                    || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n = new Nodo(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n = new Nodo(apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }

            if (n != null) {
                pR.agregar(n);
            }
        }

        return pR;
    }

    public static Polinomio restar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        Nodo apuntador2 = p2.getCabeza();

        while (apuntador1 != null || apuntador2 != null) {
            Nodo n = null;

            if (apuntador1 != null && apuntador2 != null && apuntador1.getExponente() == apuntador2.getExponente()) {
                if (apuntador1.getCoeficiente() - apuntador2.getCoeficiente() != 0) {
                    n = new Nodo(apuntador1.getCoeficiente() - apuntador2.getCoeficiente(), apuntador1.getExponente());
                }
                apuntador1 = apuntador1.siguiente;
                apuntador2 = apuntador2.siguiente;
            } else if (apuntador2 == null
                    || (apuntador1 != null && apuntador1.getExponente() < apuntador2.getExponente())) {
                n = new Nodo(apuntador1.getCoeficiente(), apuntador1.getExponente());
                apuntador1 = apuntador1.siguiente;
            } else {
                n = new Nodo(-apuntador2.getCoeficiente(), apuntador2.getExponente());
                apuntador2 = apuntador2.siguiente;
            }

            if (n != null) {
                pR.agregar(n);
            }
        }

        return pR;
    }

    public static Polinomio multiplicar(Polinomio p1, Polinomio p2) {
        Polinomio pR = new Polinomio();

        Nodo apuntador1 = p1.getCabeza();
        while (apuntador1 != null) {

            Nodo apuntador2 = p2.getCabeza();
            while (apuntador2 != null) {
                Nodo n = new Nodo(apuntador1.getCoeficiente() * apuntador2.getCoeficiente(),
                        apuntador1.getExponente() + apuntador2.getExponente());
                pR.agregar(n);
                apuntador2 = apuntador2.siguiente;
            }
            apuntador1 = apuntador1.siguiente;
        }

        return pR;
    }
    public static Polinomio[] dividir(Polinomio p1, Polinomio p2) {
        Polinomio[] pR = new Polinomio[2];
        Polinomio cociente = new Polinomio();
        Polinomio residuo = new Polinomio(p1.getCabeza());

        Nodo mayorP2 = p2.getMayorExponente();

        while (residuo.getCabeza() != null && residuo.getMayorExponente().getExponente() >= mayorP2.getExponente()) {
            Nodo mayorResiduo = residuo.getMayorExponente();
            double coeficiente = mayorResiduo.getCoeficiente() / mayorP2.getCoeficiente();
            int exponente = mayorResiduo.getExponente() - mayorP2.getExponente();

            Nodo nuevoNodo = new Nodo(coeficiente, exponente);
            Polinomio terminoDivisor = new Polinomio(nuevoNodo);

            cociente.agregar(nuevoNodo);

            Polinomio restaTerminos = Polinomio.multiplicar(terminoDivisor, p2);
            residuo = Polinomio.restar(residuo, restaTerminos);
        }

        pR[0] = cociente;
        pR[1] = residuo;
        return pR;
    }

}
