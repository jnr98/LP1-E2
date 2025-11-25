module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql; // Necessário para o Banco de Dados

    // Abre o pacote principal e o controller para o JavaFX
    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;

    // CRUCIAL: Abre o pacote model para que a Tabela consiga ler os dados
    opens app.model to javafx.base;

    exports app;
}