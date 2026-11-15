
package com.main;

import com.excepciones.ComodinNoDisponibleException;
import com.excepciones.MonedasInsuficientesException;
import com.excepciones.SinVidasDisponiblesException;
import com.excepciones.TiempoAgotadoException;
import com.model.Pregunta;
import com.service.JuegoService;
import com.view.LoginView;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;
public class Main extends Application {

    private JuegoService juegoService = new JuegoService();
    private StackPane rootContainer;

    private VBox homeScreen;
    private VBox gameScreen;
    private VBox shopScreen;

    private Label lblPregunta;
    private Label lblEstado;
    private Label lblPista;
    private Label lblTiempo;

    private RadioButton[] rbOpciones;
    private ToggleGroup grupoOpciones;
    private Button btnResponder;
    private Pregunta preguntaActual;

    private Button btnBomba;
    private Button btnEscudo;
    private Button btnCambio;
    private Button btnPista;
    private Button btnDobleOp;

    private Label shopCoinsLabel;
    private Label feedbackTienda;

    // Temporizador
    private Timeline temporizador;
    private int segundosRestantes = 20;

    @Override
    public void start(Stage primaryStage) {

        juegoService = new JuegoService();

        LoginView loginView = new LoginView(primaryStage);
        Scene loginScene = loginView.crearEscena();

        primaryStage.setTitle("Trivia - Login / Registro");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void iniciarJuegoPrincipal(Stage primaryStage) {
        Main mainApp = new Main();
        mainApp.lanzarVentanaPrincipal(primaryStage);
    }

    public void lanzarVentanaPrincipal(Stage primaryStage) {

        rootContainer = new StackPane();

        homeScreen = crearPantallaHome();
        gameScreen = crearPantallaJuego();
        shopScreen = crearPantallaTienda();

        rootContainer.getChildren().addAll(
                homeScreen, gameScreen, shopScreen
        );

        mostrarPantalla(homeScreen);

        Scene scene = new Scene(rootContainer, 980, 720);
        scene.getRoot().setId("app-root");

        try {
            scene.getStylesheets().add(
                    getClass().getResource("/styles.css").toExternalForm()
            );
        } catch (Exception e) {
        }

        primaryStage.setTitle(
                "Trivia Quest - JavaFX (Jugador: "
                + LoginView.usuarioLogueado + ")"
        );

        // Detener el temporizador si se cierra la ventana.
        primaryStage.setOnCloseRequest(e -> detenerTemporizador());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox crearPantallaHome() {

        VBox home = new VBox(24);
        home.setId("home-screen");
        home.setPadding(new Insets(40));
        home.setAlignment(Pos.CENTER);

        Label eyebrow = new Label("✦ DESAFIÁ TUS CONOCIMIENTOS ✦");
        eyebrow.setId("eyebrow");

        Label titulo = new Label("TRIVIA QUEST");
        titulo.setId("home-title");

        Label desc = new Label(
                "¡Hola, " + LoginView.usuarioLogueado
                + "! Respondé preguntas, ganá monedas y conseguí comodines."
        );

        Button btnJugar = new Button("▶  JUGAR");
        btnJugar.setId("primary-button");

        btnJugar.setOnAction(e -> {
            cargarSiguientePregunta();
            mostrarPantalla(gameScreen);
        });

        Button btnTienda = new Button("🛍  TIENDA");
        btnTienda.setId("secondary-button");

        btnTienda.setOnAction(e -> {
            shopCoinsLabel.setText(
                    String.valueOf(juegoService.getMonedas())
            );

            feedbackTienda.setText("");
            mostrarPantalla(shopScreen);
        });

        HBox acciones = new HBox(15, btnJugar, btnTienda);
        acciones.setId("home-actions");
        acciones.setAlignment(Pos.CENTER);

        home.getChildren().addAll(
                eyebrow, titulo, desc, acciones
        );

        return home;
    }

    private VBox crearPantallaJuego() {

        VBox game = new VBox(16);
        game.setId("game-screen");
        game.setPadding(new Insets(20));
        game.setAlignment(Pos.CENTER);

        Button btnSalir = new Button("←  Salir al inicio");
        btnSalir.setId("back-button");

        btnSalir.setOnAction(e -> {
            detenerTemporizador();
            mostrarPantalla(homeScreen);
        });

        lblEstado = new Label();
        lblEstado.setId("lbl-estado");

        // Contador visual
        lblTiempo = new Label("⏱️ Tiempo: 20 segundos");
        lblTiempo.setStyle(
                "-fx-font-size: 20px;"
                + "-fx-font-weight: bold;"
        );

        lblPregunta = new Label();
        lblPregunta.setId("lbl-pregunta");
        lblPregunta.setWrapText(true);

        lblPista = new Label("");
        lblPista.setId("lbl-pista");

        grupoOpciones = new ToggleGroup();
        rbOpciones = new RadioButton[4];

        VBox vboxOpciones = new VBox(12);
        vboxOpciones.setId("vbox-opciones");
        vboxOpciones.setAlignment(Pos.CENTER_LEFT);

        for (int i = 0; i < 4; i++) {
            rbOpciones[i] = new RadioButton();
            rbOpciones[i].setToggleGroup(grupoOpciones);
            vboxOpciones.getChildren().add(rbOpciones[i]);
        }

        btnBomba = new Button("💣 50/50");
        btnBomba.setOnAction(e -> usarBomba());

        btnEscudo = new Button("🛡️ Escudo");
        btnEscudo.setOnAction(e -> usarEscudo());

        btnCambio = new Button("🔄 Saltar");
        btnCambio.setOnAction(e -> usarCambio());

        btnPista = new Button("💡 Pista");
        btnPista.setOnAction(e -> usarPista());

        btnDobleOp = new Button("✨ Doble Op.");
        btnDobleOp.setOnAction(e -> usarDobleOp());

        HBox panelComodines = new HBox(
                8, btnBomba, btnEscudo, btnCambio, btnPista, btnDobleOp
        );

        panelComodines.setId("powerups-panel");
        panelComodines.setAlignment(Pos.CENTER);

        btnResponder = new Button("CONFIRMAR RESPUESTA");
        btnResponder.setId("primary-button");

        btnResponder.setOnAction(e -> procesarRespuesta());

        game.getChildren().addAll(
                btnSalir,
                lblEstado,
                lblTiempo,
                lblPregunta,
                lblPista,
                vboxOpciones,
                panelComodines,
                btnResponder
        );

        return game;
    }

    private VBox crearPantallaTienda() {

        VBox shop = new VBox(18);
        shop.setId("shop-screen");
        shop.setPadding(new Insets(25));
        shop.setAlignment(Pos.CENTER);

        Label titulo = new Label("TIENDA DE COMODINES");
        titulo.setId("section-title");

        VBox listaTienda = new VBox(10);
        listaTienda.setAlignment(Pos.CENTER);

        listaTienda.getChildren().add(
                crearItemTienda("💣 Bomba 50/50 (🪙 30)", "fifty")
        );

        listaTienda.getChildren().add(
                crearItemTienda("🛡️ Escudo Protector (🪙 40)", "escudo")
        );

        listaTienda.getChildren().add(
                crearItemTienda("🔄 Cambio de Pregunta (🪙 25)", "cambio")
        );

        listaTienda.getChildren().add(
                crearItemTienda("💡 Pista del Sabio (🪙 15)", "pista")
        );

        listaTienda.getChildren().add(
                crearItemTienda("✨ Doble Oportunidad (🪙 35)", "dobleop")
        );

        feedbackTienda = new Label("");
        shopCoinsLabel = new Label("0");

        HBox saldoBox = new HBox(
                5, new Label("Tu saldo actual: 🪙"), shopCoinsLabel
        );

        saldoBox.setAlignment(Pos.CENTER);

        Button btnVolver = new Button("←  VOLVER AL INICIO");
        btnVolver.setId("back-button");

        btnVolver.setOnAction(e -> mostrarPantalla(homeScreen));

        shop.getChildren().addAll(
                titulo, listaTienda, feedbackTienda, saldoBox, btnVolver
        );

        return shop;
    }

    private Button crearItemTienda(String texto, String codigo) {

        Button btn = new Button(texto);
        btn.setId("shop-item");

        btn.setOnAction(e -> {
            try {
                juegoService.comprarComodin(codigo);

                feedbackTienda.setText("¡Compra realizada con éxito!");

                shopCoinsLabel.setText(
                        String.valueOf(juegoService.getMonedas())
                );

            } catch (MonedasInsuficientesException ex) {
                feedbackTienda.setText(ex.getMessage());
            }
        });

        return btn;
    }

    private void mostrarPantalla(VBox pantallaTarget) {

        if (pantallaTarget != gameScreen) {
            detenerTemporizador();
        }

        rootContainer.getChildren().forEach(
                node -> node.setVisible(false)
        );

        pantallaTarget.setVisible(true);
    }

    // ==========================================
    // TEMPORIZADOR
    // ==========================================

    
private void iniciarTemporizador() {

    detenerTemporizador();

    segundosRestantes = 20;

    actualizarTextoTiempo();

    temporizador = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {

                segundosRestantes--;

                actualizarTextoTiempo();

                if (segundosRestantes <= 0) {

                    System.out.println("¡EL TIEMPO LLEGÓ A CERO!");

                    detenerTemporizador();

                    Platform.runLater(() -> {
                        manejarTiempoAgotado();
                    });
                }
            })
    );

    temporizador.setCycleCount(20);
    temporizador.play();
}


    private void detenerTemporizador() {

        if (temporizador != null) {
            temporizador.stop();
            temporizador = null;
        }
    }

    private void actualizarTextoTiempo() {

        if (lblTiempo != null) {

            lblTiempo.setText(
                    "⏱️ Tiempo restante: "
                    + segundosRestantes + " segundos"
            );

            if (segundosRestantes <= 5) {
                lblTiempo.setStyle(
                        "-fx-font-size: 20px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: red;"
                );
            } else {
                lblTiempo.setStyle(
                        "-fx-font-size: 20px;"
                        + "-fx-font-weight: bold;"
                );
            }
        }
    }

    private void manejarTiempoAgotado() {
        System.out.println("Ejecutando manejarTiempoAgotado()");
        try {

            // Lanzamos y capturamos nuestra excepción.
            throw new TiempoAgotadoException(
                    "¡Se terminó el tiempo para responder!"
            );

        } catch (TiempoAgotadoException ex) {

            try {

                boolean continuar =
                        juegoService.registrarTiempoAgotado();

                actualizarEstadoTexto();

                if (continuar) {

                    mostrarAlerta(
                            "¡Tiempo agotado!",
                            ex.getMessage()
                            + "\nTenés otra oportunidad para responder.",
                            Alert.AlertType.WARNING
                    );

                    // Nueva oportunidad: otros 20 segundos.
                    iniciarTemporizador();

                } else {

                    mostrarAlerta(
                            "¡Tiempo agotado!",
                            ex.getMessage()
                            + "\nPerdiste una vida.",
                            Alert.AlertType.WARNING
                    );

                    cargarSiguientePregunta();
                }

            } catch (SinVidasDisponiblesException exVidas) {

                detenerTemporizador();
                manejarGameOver(exVidas);
            }
        }
    }

    // ==========================================
    // CARGA DE PREGUNTAS
    // ==========================================

    private void cargarSiguientePregunta() {

        detenerTemporizador();

        lblPista.setText("");

        if (juegoService.hayMasPreguntas()) {

            preguntaActual = juegoService.obtenerSiguientePregunta();

            lblPregunta.setText(preguntaActual.getEnunciado());

            String[] ops = preguntaActual.getOpciones();

            for (int i = 0; i < 4; i++) {

                if (i < ops.length) {

                    rbOpciones[i].setText(ops[i]);
                    rbOpciones[i].setVisible(true);
                    rbOpciones[i].setManaged(true);
                    rbOpciones[i].setSelected(false);
                    rbOpciones[i].setDisable(false);

                } else {

                    rbOpciones[i].setVisible(false);
                    rbOpciones[i].setManaged(false);
                }
            }

            actualizarEstadoTexto();

            // Cada pregunta comienza con 20 segundos.
            iniciarTemporizador();

        } else {

            detenerTemporizador();

            mostrarAlerta(
                    "¡Juego Terminado!",
                    "Has completado todas las preguntas.\nPuntaje final: "
                    + juegoService.getPuntaje(),
                    Alert.AlertType.INFORMATION
            );

            juegoService.reiniciar();
            mostrarPantalla(homeScreen);
        }
    }

    private void actualizarEstadoTexto() {

        lblEstado.setText(
                "Pts: " + juegoService.getPuntaje()
                + " | ❤️: " + juegoService.getVidas()
                + " | 🪙: " + juegoService.getMonedas()
                + " | 💣:" + juegoService.getComodinesFifty()
                + " 🛡️:" + juegoService.getComodinesEscudo()
                + " 🔄:" + juegoService.getComodinesCambio()
                + " 💡:" + juegoService.getComodinesPista()
                + " ✨:" + juegoService.getComodinesDobleOp()
        );
    }

    // ==========================================
    // COMODINES
    // ==========================================

    
private void usarBomba() {

    try {
        if (juegoService.usarComodinFifty()) {

            actualizarEstadoTexto();

            String[] ops = preguntaActual.getOpciones();

            int deshabilitadas = 0;
            int maxDeshabilitar = ops.length > 2 ? 2 : 1;

            for (int i = 0; i < ops.length; i++) {

                if (!preguntaActual.evaluarRespuesta(i)
                        && deshabilitadas < maxDeshabilitar) {

                    rbOpciones[i].setDisable(true);
                    deshabilitadas++;
                }
            }
        }

    } catch (ComodinNoDisponibleException ex) {

        mostrarAlerta(
                "Comodín no disponible",
                ex.getMessage(),
                Alert.AlertType.WARNING
        );
    }
}


    private void usarEscudo() {

    try {
        if (juegoService.usarEscudo()) {

            actualizarEstadoTexto();

            lblPista.setText(
                    "🛡️ ¡Escudo activado! Si te equivocás en esta pregunta no perderás vidas."
            );
        }

    } catch (ComodinNoDisponibleException ex) {

        mostrarAlerta(
                "Comodín no disponible",
                ex.getMessage(),
                Alert.AlertType.WARNING
        );
    }
}
    private void usarCambio() {

    try {
        if (juegoService.usarCambio()) {

            mostrarAlerta(
                    "Pregunta Cambiada",
                    "Saltaste la pregunta actual.",
                    Alert.AlertType.INFORMATION
            );

            cargarSiguientePregunta();
        }

    } catch (ComodinNoDisponibleException ex) {

        mostrarAlerta(
                "Comodín no disponible",
                ex.getMessage(),
                Alert.AlertType.WARNING
        );
    }
}

    private void usarPista() {

    try {
        if (juegoService.usarPista()) {

            actualizarEstadoTexto();

            lblPista.setText(
                    "💡 Pista: La pregunta pertenece a la categoría de ["
                    + preguntaActual.getCategoria() + "]."
            );
        }

    } catch (ComodinNoDisponibleException ex) {

        mostrarAlerta(
                "Comodín no disponible",
                ex.getMessage(),
                Alert.AlertType.WARNING
        );
    }
}

    private void usarDobleOp() {

    try {
        if (juegoService.usarDobleOp()) {

            actualizarEstadoTexto();

            lblPista.setText(
                    "✨ ¡Doble Oportunidad activada! Podés fallar un intento sin perder vidas."
            );
        }

    } catch (ComodinNoDisponibleException ex) {

        mostrarAlerta(
                "Comodín no disponible",
                ex.getMessage(),
                Alert.AlertType.WARNING
        );
    }
}

    // ==========================================
    // PROCESAR RESPUESTAS
    // ==========================================

    private void procesarRespuesta() {

        int seleccion = -1;

        for (int i = 0; i < 4; i++) {

            if (rbOpciones[i].isVisible()
                    && rbOpciones[i].isSelected()
                    && !rbOpciones[i].isDisable()) {

                seleccion = i;
                break;
            }
        }

        if (seleccion == -1) {

            mostrarAlerta(
                    "Advertencia",
                    "Por favor seleccione una opción disponible.",
                    Alert.AlertType.WARNING
            );

            return;
        }

        // Guardamos el estado de los comodines antes de responder.
        boolean escudoAntes = juegoService.isEscudoActivo();
        boolean dobleAntes = juegoService.isDobleOpActiva();

        detenerTemporizador();

        try {

            boolean correcta = juegoService.responder(
                    preguntaActual, seleccion
            );

            if (correcta) {

                mostrarAlerta(
                        "¡Correcto!",
                        "¡Muy bien! Ganaste 10 monedas.",
                        Alert.AlertType.INFORMATION
                );

                cargarSiguientePregunta();

            } else {

                if (escudoAntes) {

                    mostrarAlerta(
                            "Respuesta Errónea",
                            "¡El escudo te protegió de perder una vida! "
                            + "Intentá responder nuevamente.",
                            Alert.AlertType.WARNING
                    );

                    rbOpciones[seleccion].setDisable(true);

                    iniciarTemporizador();

                } else if (dobleAntes
                        && juegoService.isDobleOpActiva()) {

                    mostrarAlerta(
                            "Respuesta Errónea",
                            "¡Tenés Doble Oportunidad! "
                            + "Probá con otra opción.",
                            Alert.AlertType.WARNING
                    );

                    rbOpciones[seleccion].setDisable(true);

                    iniciarTemporizador();

                } else {

                    mostrarAlerta(
                            "Incorrecto",
                            "Respuesta errónea.",
                            Alert.AlertType.ERROR
                    );

                    cargarSiguientePregunta();
                }
            }

            actualizarEstadoTexto();

        } catch (SinVidasDisponiblesException ex) {

            detenerTemporizador();
            manejarGameOver(ex);
        }
    }

    // ==========================================
    // GAME OVER
    // ==========================================

    private void manejarGameOver(SinVidasDisponiblesException ex) {

        detenerTemporizador();

        int monedasAntes = juegoService.getMonedas();

        juegoService.reiniciarPorDerrota();

        int monedasPerdidas =
                monedasAntes - juegoService.getMonedas();

        mostrarAlerta(
                "Game Over",
                ex.getMessage()
                + "\n\nMonedas anteriores: " + monedasAntes
                + "\nMonedas perdidas: " + monedasPerdidas
                + "\nMonedas conservadas: " + juegoService.getMonedas(),
                Alert.AlertType.ERROR
        );

        mostrarPantalla(homeScreen);
    }

    // ==========================================
    // ALERTAS
    // ==========================================

    private void mostrarAlerta(
            String titulo,
            String mensaje,
            Alert.AlertType tipo) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
