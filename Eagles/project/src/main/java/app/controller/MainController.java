package app.controller;

import app.dao.PassaporteDAO;
import app.dao.PrisoneiroDAO;
import app.dao.RodoviaDAO;
import app.model.Passaporte;
import app.model.Prisoneiro;
import app.model.Rodovia;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Desktop;
import java.net.URI;

public class MainController {

    // === RODOVIA ===
    @FXML private TextField rodoviaNomeField, rodoviaCidadeField, rodoviaExtensaoField;
    @FXML private TableView<Rodovia> tabelaRodovia;
    @FXML private TableColumn<Rodovia, String> colRodoviaNome, colRodoviaCidade;
    @FXML private TableColumn<Rodovia, Integer> colRodoviaExtensao;

    // === PRISIONEIRO ===
    @FXML private TextField prisoneiroLugarField, prisoneiroNomeField, prisoneiroArmaField;
    @FXML private TableView<Prisoneiro> tabelaPrisoneiro;
    @FXML private TableColumn<Prisoneiro, String> colPrisLugar, colPrisNome, colPrisArma;

    // === PASSAPORTE ===
    @FXML private TextField passaporteNomeField, passaporteNumeroField, passaportePaisField;
    @FXML private TableView<Passaporte> tabelaPassaporte;
    @FXML private TableColumn<Passaporte, String> colPassNome, colPassNumero, colPassPais;

    // DAOs
    private final RodoviaDAO rodoviaDAO = new RodoviaDAO();
    private final PrisoneiroDAO prisoneiroDAO = new PrisoneiroDAO();
    private final PassaporteDAO passaporteDAO = new PassaporteDAO();

    // Seleções
    private Rodovia rodoviaSelecionada;
    private Prisoneiro prisoneiroSelecionado;
    private Passaporte passaporteSelecionado;

    @FXML
    public void initialize() {
        System.out.println(">>> INICIANDO O CONTROLLER...");
        configurarRodovia();
        configurarPrisioneiro();
        configurarPassaporte();
        System.out.println(">>> CONTROLLER INICIADO COM SUCESSO!");
    }

    // ==================== LÓGICA RODOVIA ====================
    private void configurarRodovia() {
        colRodoviaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colRodoviaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colRodoviaExtensao.setCellValueFactory(new PropertyValueFactory<>("extensao"));

        tabelaRodovia.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            if (n != null) {
                rodoviaSelecionada = n;
                rodoviaNomeField.setText(n.getNome());
                rodoviaCidadeField.setText(n.getCidade());
                rodoviaExtensaoField.setText(String.valueOf(n.getExtensao()));
            }
        });
        carregarRodovia();
    }

    private void carregarRodovia() {
        try {
            System.out.println("Tentando carregar lista de rodovias...");
            tabelaRodovia.setItems(FXCollections.observableArrayList(rodoviaDAO.listar()));
            System.out.println("Lista de rodovias carregada!");
        } catch (Exception e) {
            e.printStackTrace(); // IMPRIME O ERRO REAL NO CONSOLE
            showError("Erro ao carregar Rodovias: " + e.getMessage());
        }
    }

    @FXML
    private void onSalvarRodovia() {
        System.out.println(">>> Botão Salvar Rodovia Clicado!");
        try {
            String nome = rodoviaNomeField.getText().trim();
            String cidade = rodoviaCidadeField.getText().trim();
            String extStr = rodoviaExtensaoField.getText().trim();

            System.out.println("Dados: " + nome + ", " + cidade + ", " + extStr);

            if (nome.isEmpty() || cidade.isEmpty() || extStr.isEmpty()) {
                showError("Preencha todos os campos da Rodovia!"); return;
            }
            int ext = Integer.parseInt(extStr);

            if (rodoviaSelecionada == null) {
                System.out.println("Tentando fazer INSERT no banco...");
                rodoviaDAO.salvar(new Rodovia(nome, cidade, ext));
                System.out.println("INSERT OK!");
                showInfo("Rodovia salva com sucesso!");
            } else {
                System.out.println("Tentando fazer UPDATE no banco...");
                rodoviaSelecionada.setNome(nome);
                rodoviaSelecionada.setCidade(cidade);
                rodoviaSelecionada.setExtensao(ext);
                rodoviaDAO.atualizar(rodoviaSelecionada);
                System.out.println("UPDATE OK!");
                showInfo("Rodovia atualizada com sucesso!");
            }
            limparRodovia();
            carregarRodovia();
        } catch (NumberFormatException e) {
            showError("A extensão deve ser um número válido!");
        } catch (Exception e) {
            System.out.println("!!! ERRO AO SALVAR RODOVIA !!!");
            e.printStackTrace(); // ISSO VAI MOSTRAR O MOTIVO REAL NO CONSOLE
            showError("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onExcluirRodovia() {
        if (rodoviaSelecionada != null) {
            try {
                rodoviaDAO.deletar(rodoviaSelecionada.getId());
                limparRodovia();
                carregarRodovia();
                showInfo("Rodovia excluída!");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Erro: " + e.getMessage());
            }
        } else {
            showError("Selecione uma rodovia para excluir.");
        }
    }

    @FXML private void onLimparRodovia() { limparRodovia(); }

    private void limparRodovia() {
        rodoviaNomeField.clear(); rodoviaCidadeField.clear(); rodoviaExtensaoField.clear();
        rodoviaSelecionada = null; tabelaRodovia.getSelectionModel().clearSelection();
    }

    // ==================== LÓGICA PRISIONEIRO ====================
    private void configurarPrisioneiro() {
        colPrisLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        colPrisNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPrisArma.setCellValueFactory(new PropertyValueFactory<>("arma"));

        tabelaPrisoneiro.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            if (n != null) {
                prisoneiroSelecionado = n;
                prisoneiroLugarField.setText(n.getLugar());
                prisoneiroNomeField.setText(n.getNome());
                prisoneiroArmaField.setText(n.getArma());
            }
        });
        carregarPrisioneiro();
    }

    private void carregarPrisioneiro() {
        try {
            tabelaPrisoneiro.setItems(FXCollections.observableArrayList(prisoneiroDAO.listar()));
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro ao carregar Prisioneiros: " + e.getMessage());
        }
    }

    @FXML
    private void onSalvarPrisoneiro() {
        System.out.println(">>> Botão Salvar Prisioneiro Clicado!");
        try {
            String lugar = prisoneiroLugarField.getText().trim();
            String nome = prisoneiroNomeField.getText().trim();
            String arma = prisoneiroArmaField.getText().trim();

            if (nome.isEmpty() || lugar.isEmpty()) {
                showError("Preencha os campos do Prisioneiro!"); return;
            }

            if (prisoneiroSelecionado == null) {
                prisoneiroDAO.salvar(new Prisoneiro(lugar, nome, arma));
                showInfo("Prisioneiro salvo com sucesso!");
            } else {
                prisoneiroSelecionado.setLugar(lugar);
                prisoneiroSelecionado.setNome(nome);
                prisoneiroSelecionado.setArma(arma);
                prisoneiroDAO.atualizar(prisoneiroSelecionado);
                showInfo("Prisioneiro atualizado com sucesso!");
            }
            limparPrisioneiro();
            carregarPrisioneiro();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onExcluirPrisoneiro() {
        if (prisoneiroSelecionado != null) {
            try {
                prisoneiroDAO.deletar(prisoneiroSelecionado.getId());
                limparPrisioneiro();
                carregarPrisioneiro();
                showInfo("Prisioneiro excluído!");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Erro: " + e.getMessage());
            }
        } else {
            showError("Selecione um prisioneiro para excluir.");
        }
    }

    @FXML private void onLimparPrisoneiro() { limparPrisioneiro(); }

    private void limparPrisioneiro() {
        prisoneiroLugarField.clear(); prisoneiroNomeField.clear(); prisoneiroArmaField.clear();
        prisoneiroSelecionado = null; tabelaPrisoneiro.getSelectionModel().clearSelection();
    }

    // ==================== LÓGICA PASSAPORTE ====================
    private void configurarPassaporte() {
        colPassNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPassNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPassPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        tabelaPassaporte.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            if (n != null) {
                passaporteSelecionado = n;
                passaporteNomeField.setText(n.getNome());
                passaporteNumeroField.setText(n.getNumero());
                passaportePaisField.setText(n.getPais());
            }
        });
        carregarPassaporte();
    }

    private void carregarPassaporte() {
        try {
            tabelaPassaporte.setItems(FXCollections.observableArrayList(passaporteDAO.listar()));
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro ao carregar Passaportes: " + e.getMessage());
        }
    }

    @FXML
    private void onSalvarPassaporte() {
        System.out.println(">>> Botão Salvar Passaporte Clicado!");
        try {
            String nome = passaporteNomeField.getText().trim();
            String numero = passaporteNumeroField.getText().trim();
            String pais = passaportePaisField.getText().trim();

            if (nome.isEmpty() || numero.isEmpty()) {
                showError("Preencha os campos do Passaporte!"); return;
            }

            if (passaporteSelecionado == null) {
                passaporteDAO.salvar(new Passaporte(nome, numero, pais));
                showInfo("Passaporte salvo com sucesso!");
            } else {
                passaporteSelecionado.setNome(nome);
                passaporteSelecionado.setNumero(numero);
                passaporteSelecionado.setPais(pais);
                passaporteDAO.atualizar(passaporteSelecionado);
                showInfo("Passaporte atualizado com sucesso!");
            }
            limparPassaporte();
            carregarPassaporte();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onExcluirPassaporte() {
        if (passaporteSelecionado != null) {
            try {
                passaporteDAO.deletar(passaporteSelecionado.getId());
                limparPassaporte();
                carregarPassaporte();
                showInfo("Passaporte excluído!");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Erro: " + e.getMessage());
            }
        } else {
            showError("Selecione um passaporte para excluir.");
        }
    }

    @FXML private void onLimparPassaporte() { limparPassaporte(); }

    private void limparPassaporte() {
        passaporteNomeField.clear(); passaporteNumeroField.clear(); passaportePaisField.clear();
        passaporteSelecionado = null; tabelaPassaporte.getSelectionModel().clearSelection();
    }

    // ==================== UTILITÁRIOS ====================
    @FXML private void onOpenLink() {
        try { Desktop.getDesktop().browse(new URI("https://youtu.be/09839DpTctU?t=52")); }
        catch (Exception e) {}
    }

    private void showInfo(String msg) { new Alert(Alert.AlertType.INFORMATION, msg).showAndWait(); }
    private void showError(String msg) { new Alert(Alert.AlertType.ERROR, msg).showAndWait(); }
}