package com.view;

import com.dao.UsuarioDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private final UsuarioDAO usuarioDAO;
    private final Stage stage;
    public static String usuarioLogueado = "";

    public LoginView(Stage stage) {
        this.stage = stage;
        this.usuarioDAO = new UsuarioDAO();
    }

    public Scene crearEscena() {
        VBox root = new VBox(16);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(42));
        root.setId("login-root");

        Label icono = new Label("🧠");
        icono.setId("login-icon");

        Label titulo = new Label("TRIVIA QUEST");
        titulo.setId("login-title");

        Label subtitulo = new Label("Poné a prueba lo que sabés");
        subtitulo.setId("login-subtitle");

        VBox tarjeta = new VBox(14);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(28));
        tarjeta.setMaxWidth(390);
        tarjeta.setId("login-card");

        Label encabezado = new Label("¡Qué bueno verte!");
        encabezado.setId("card-title");

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Nombre de usuario");
        txtUsuario.setMaxWidth(300);
        txtUsuario.getStyleClass().add("input-field");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setMaxWidth(300);
        txtPassword.getStyleClass().add("input-field");

        Button btnLogin = new Button("INICIAR SESIÓN");
        btnLogin.setId("primary-button");
        btnLogin.setMaxWidth(300);

        Button btnRegistrar = new Button("CREAR CUENTA");
        btnRegistrar.setId("secondary-button");
        btnRegistrar.setMaxWidth(300);

        Label lblMensaje = new Label();
        lblMensaje.setId("login-message");
        lblMensaje.setWrapText(true);

        btnLogin.setOnAction(e -> {
            String user = txtUsuario.getText().trim();
            String pass = txtPassword.getText().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                lblMensaje.setText("Completá tu usuario y contraseña.");
                lblMensaje.setStyle("-fx-text-fill: #ff8b9a;");
                return;
            }

            if (usuarioDAO.validarLogin(user, pass)) {
                usuarioLogueado = user;
                com.main.Main.iniciarJuegoPrincipal(stage);
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos.");
                lblMensaje.setStyle("-fx-text-fill: #ff8b9a;");
            }
        });

        btnRegistrar.setOnAction(e -> {
            String user = txtUsuario.getText().trim();
            String pass = txtPassword.getText().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                lblMensaje.setText("Completá los dos campos para registrarte.");
                lblMensaje.setStyle("-fx-text-fill: #ff8b9a;");
                return;
            }

            boolean registrado = usuarioDAO.registrarUsuario(user, pass);
            if (registrado) {
                lblMensaje.setText("¡Cuenta creada! Ahora podés iniciar sesión.");
                lblMensaje.setStyle("-fx-text-fill: #7df5b2;");
            } else {
                lblMensaje.setText("El usuario ya existe o ocurrió un error.");
                lblMensaje.setStyle("-fx-text-fill: #ff8b9a;");
            }
        });

        tarjeta.getChildren().addAll(encabezado, txtUsuario, txtPassword, btnLogin, btnRegistrar, lblMensaje);
        root.getChildren().addAll(icono, titulo, subtitulo, tarjeta);

        Scene scene = new Scene(root, 980, 720);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }
}
