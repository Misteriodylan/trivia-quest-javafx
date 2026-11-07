package com.service;

import com.dao.PreguntaDAO;
import com.excepciones.MonedasInsuficientesException;
import com.excepciones.SinVidasDisponiblesException;
import com.model.Pregunta;

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

    // Estado del comodín activo en el turno actual
    private boolean escudoActivo;
    private boolean dobleOpActiva;
    private int intentosDobleOp;

    
    public JuegoService() {
        this.preguntaDAO = new PreguntaDAO();
        this.listaPreguntas = preguntaDAO.obtenerTodas();
        // --- LÍNEA DE DIAGNÓSTICO ---
    System.out.println("🔍 [JuegoService] Preguntas cargadas desde la BD: " + (this.listaPreguntas != null ? this.listaPreguntas.size() : "NULL"));
    
    if (this.listaPreguntas == null || this.listaPreguntas.isEmpty()) {
        System.err.println("⚠️ ¡ATENCIÓN! La lista de preguntas llegó vacía. Revisa tu PreguntaDAO o la conexión a la BD.");
    } else {
        System.out.println("✅ Primera pregunta de muestra: " + this.listaPreguntas.get(0).getEnunciado());
    }
    // ----------------------------
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

    public boolean responder(Pregunta p, int opcionSeleccionada) throws SinVidasDisponiblesException {
        boolean correcta = p.evaluarRespuesta(opcionSeleccionada);
        
        if (correcta) {
            puntaje += 10;
            monedas += 10;
            return true;
        } else {
            // Si tiene escudo o doble oportunidad activa en el primer intento, no pierde vida
            if (escudoActivo) {
                escudoActivo = false; // Se consume la protección
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
            // Si no tiene comodín de protección, pierde una vida
            vidas--;
            if (vidas <= 0) {
                throw new SinVidasDisponiblesException("¡Te has quedado sin vidas!");
            }
            return false;
        }
    }

    // Método general de compras en la tienda
    public void comprarComodin(String tipo) throws MonedasInsuficientesException {
        int costo = 0;
        switch (tipo.toLowerCase()) {
            case "fifty": costo = 30; break;
            case "escudo": costo = 40; break;
            case "cambio": costo = 25; break;
            case "pista": costo = 15; break;
            case "dobleop": costo = 35; break;
        }

        if (this.monedas < costo) {
            throw new MonedasInsuficientesException("No tenés suficientes monedas para esta compra.");
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

    // Métodos para consumir comodines durante la partida
    public boolean usarComodinFifty() {
        if (comodinesFifty > 0) {
            comodinesFifty--;
            return true;
        }
        return false;
    }

    public boolean usarEscudo() {
        if (comodinesEscudo > 0 && !escudoActivo) {
            comodinesEscudo--;
            escudoActivo = true;
            return true;
        }
        return false;
    }

    public boolean usarCambio() {
        if (comodinesCambio > 0) {
            comodinesCambio--;
            return true;
        }
        return false;
    }

    public boolean usarPista() {
        if (comodinesPista > 0) {
            comodinesPista--;
            return true;
        }
        return false;
    }

    public boolean usarDobleOp() {
        if (comodinesDobleOp > 0 && !dobleOpActiva) {
            comodinesDobleOp--;
            dobleOpActiva = true;
            intentosDobleOp = 0;
            return true;
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
    // Método que se ejecuta únicamente cuando el jugador pierde (Game Over)
    
public void reiniciarPorDerrota() {
    Collections.shuffle(this.listaPreguntas);
    this.indiceActual = 0;
    this.vidas = 3;
    this.puntaje = 0;

    // El jugador pierde solamente el 20% de sus monedas
    this.monedas -= (int) (this.monedas * 0.20);

    resetearComodinesTurno();
}
}