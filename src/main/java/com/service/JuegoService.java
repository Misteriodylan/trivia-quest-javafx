
package com.service;

import com.dao.PreguntaDAO;
import com.excepciones.MonedasInsuficientesException;
import com.excepciones.RespuestaInvalidaException;
import com.excepciones.ComodinNoDisponibleException;
import com.excepciones.SinVidasDisponiblesException;
import com.model.Pregunta;
import com.interfaz.Evaluable;

import java.util.Collections;
import java.util.List;

public class JuegoService {

    private PreguntaDAO preguntaDAO;
    private List<Pregunta> listaPreguntas;
    private int indiceActual;
    private int vidas;
    private int puntaje;
    private int monedas;

    // Inventario de comodines
    private int comodinesFifty;
    private int comodinesEscudo;
    private int comodinesCambio;
    private int comodinesPista;
    private int comodinesDobleOp;

    // Estado de los comodines activos
    private boolean escudoActivo;
    private boolean dobleOpActiva;
    private int intentosDobleOp;

    public JuegoService() {

        this.preguntaDAO = new PreguntaDAO();
        this.listaPreguntas = preguntaDAO.obtenerTodas();

        System.out.println("Preguntas cargadas desde la BD: "
                + (this.listaPreguntas != null
                ? this.listaPreguntas.size() : "NULL"));

        if (this.listaPreguntas == null || this.listaPreguntas.isEmpty()) {
            System.err.println("ATENCIÓN: La lista de preguntas está vacía.");
        } else {
            System.out.println("Primera pregunta: "
                    + this.listaPreguntas.get(0).getEnunciado());
        }

        Collections.shuffle(this.listaPreguntas);

        this.indiceActual = 0;
        this.vidas = 3;
        this.puntaje = 0;
        this.monedas = 0;

        this.comodinesFifty = 0;
        this.comodinesEscudo = 0;
        this.comodinesCambio = 0;
        this.comodinesPista = 0;
        this.comodinesDobleOp = 0;

        resetearComodinesTurno();
    }

    public boolean hayMasPreguntas() {
        return indiceActual < listaPreguntas.size();
    }

    public Pregunta obtenerSiguientePregunta() {

        resetearComodinesTurno();

        if (hayMasPreguntas()) {
            return listaPreguntas.get(indiceActual++);
        }

        return null;
    }

    public void resetearComodinesTurno() {
        this.escudoActivo = false;
        this.dobleOpActiva = false;
        this.intentosDobleOp = 0;
    }

    // Ahora recibe la interfaz Evaluable
    
public boolean responder(Evaluable p, int opcionSeleccionada)
        throws SinVidasDisponiblesException,
               RespuestaInvalidaException {

    // Verificar que exista una pregunta.
    if (p == null || !(p instanceof Pregunta)) {
    throw new RespuestaInvalidaException(
            "La pregunta seleccionada no es válida."
    );
}

    Pregunta pregunta = (Pregunta) p;

    // Obtener la cantidad de opciones disponibles.
    int cantidadOpciones = pregunta.getOpciones().length;

    // Verificar que la opción seleccionada sea válida.
    if (opcionSeleccionada < 1 ||
            opcionSeleccionada > cantidadOpciones) {

        throw new RespuestaInvalidaException(
                "La opción seleccionada no es válida. "
                + "Elegí un número entre 1 y "
                + cantidadOpciones + "."
        );
    }

    boolean correcta = p.evaluarRespuesta(opcionSeleccionada);

    if (correcta) {

        puntaje += 10;
        monedas += 10;

        return true;

    } else {

        if (escudoActivo) {
            escudoActivo = false;
            return false;
        }

        if (dobleOpActiva) {

            if (intentosDobleOp == 0) {
                intentosDobleOp++;
                return false;

            } else {
                dobleOpActiva = false;
                intentosDobleOp = 0;
            }
        }

        vidas--;

        if (vidas <= 0) {
            throw new SinVidasDisponiblesException(
                    "¡Te has quedado sin vidas!"
            );
        }

        return false;
    }
}


    // Compras de comodines
    public void comprarComodin(String tipo)
            throws MonedasInsuficientesException {

        int costo = 0;

        switch (tipo.toLowerCase()) {

            case "fifty": costo = 30; break;
            case "escudo": costo = 40; break;
            case "cambio": costo = 25; break;
            case "pista": costo = 15; break;
            case "dobleop": costo = 35; break;
        }

        if (this.monedas < costo) {
            throw new MonedasInsuficientesException(
                    "No tenés suficientes monedas para esta compra.");
        }

        this.monedas -= costo;

        switch (tipo.toLowerCase()) {

            case "fifty": comodinesFifty++; break;
            case "escudo": comodinesEscudo++; break;
            case "cambio": comodinesCambio++; break;
            case "pista": comodinesPista++; break;
            case "dobleop": comodinesDobleOp++; break;
        }
    }

    // Utilización de comodines
   
public boolean usarComodinFifty()
        throws ComodinNoDisponibleException {

    if (comodinesFifty > 0) {
        comodinesFifty--;
        return true;
    }

    throw new ComodinNoDisponibleException(
            "No tenés bombas 50/50 disponibles. ¡Compralas en la tienda!"
    );
}

public boolean usarEscudo()
        throws ComodinNoDisponibleException {

    if (comodinesEscudo <= 0) {
        throw new ComodinNoDisponibleException(
                "No tenés escudos disponibles. ¡Compralos en la tienda!"
        );
    }

    if (escudoActivo) {
        throw new ComodinNoDisponibleException(
                "Ya tenés un escudo activado en esta pregunta."
        );
    }

    comodinesEscudo--;
    escudoActivo = true;
    return true;
}

public boolean usarCambio()
        throws ComodinNoDisponibleException {

    if (comodinesCambio > 0) {
        comodinesCambio--;
        return true;
    }

    throw new ComodinNoDisponibleException(
            "No tenés cambios de pregunta disponibles. ¡Compralos en la tienda!"
    );
}

public boolean usarPista()
        throws ComodinNoDisponibleException {

    if (comodinesPista > 0) {
        comodinesPista--;
        return true;
    }

    throw new ComodinNoDisponibleException(
            "No tenés pistas disponibles. ¡Compralas en la tienda!"
    );
}

public boolean usarDobleOp()
        throws ComodinNoDisponibleException {

    if (comodinesDobleOp <= 0) {
        throw new ComodinNoDisponibleException(
                "No tenés comodines de Doble Oportunidad disponibles."
        );
    }

    if (dobleOpActiva) {
        throw new ComodinNoDisponibleException(
                "Ya tenés activada la Doble Oportunidad."
        );
    }

    comodinesDobleOp--;
    dobleOpActiva = true;
    intentosDobleOp = 0;
    return true;
}


public boolean registrarTiempoAgotado()
        throws SinVidasDisponiblesException {
System.out.println("Registrando tiempo agotado...");
    // Si tiene escudo, lo consume y permite continuar.
    if (escudoActivo) {
        escudoActivo = false;
        return true;
    }

    // Si tiene doble oportunidad, permite un intento más.
    if (dobleOpActiva) {

        if (intentosDobleOp == 0) {
            intentosDobleOp++;
            return true;
        } else {
            dobleOpActiva = false;
            intentosDobleOp = 0;
        }
    }

    // Si no tiene protección, pierde una vida.
    vidas--;

    if (vidas <= 0) {
        throw new SinVidasDisponiblesException(
                "¡Te has quedado sin vidas!"
        );
    }

    return false;
}

    // Getters
    public int getVidas() { return vidas; }
    public int getPuntaje() { return puntaje; }
    public int getMonedas() { return monedas; }

    public int getComodinesFifty() { return comodinesFifty; }
    public int getComodinesEscudo() { return comodinesEscudo; }
    public int getComodinesCambio() { return comodinesCambio; }
    public int getComodinesPista() { return comodinesPista; }
    public int getComodinesDobleOp() { return comodinesDobleOp; }

    public boolean isEscudoActivo() { return escudoActivo; }
    public boolean isDobleOpActiva() { return dobleOpActiva; }

    public void reiniciar() {

        Collections.shuffle(this.listaPreguntas);

        this.indiceActual = 0;
        this.vidas = 3;
        this.puntaje = 0;

        resetearComodinesTurno();
    }

    public void reiniciarPorDerrota() {

        Collections.shuffle(this.listaPreguntas);

        this.indiceActual = 0;
        this.vidas = 3;
        this.puntaje = 0;

        // El jugador pierde el 20% de sus monedas
        this.monedas -= (int) (this.monedas * 0.20);

        resetearComodinesTurno();
    }
}



