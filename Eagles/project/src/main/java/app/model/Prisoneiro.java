package app.model;

public class Prisoneiro {
    private int id;
    private String lugar;
    private String nome;
    private String arma;

    // Construtor com ID (usado ao buscar do banco para edição/exclusão)
    public Prisoneiro(int id, String lugar, String nome, String arma) {
        this.id = id;
        this.lugar = lugar;
        this.nome = nome;
        this.arma = arma;
    }

    // Construtor sem ID (usado para criar novos registros antes de salvar)
    public Prisoneiro(String lugar, String nome, String arma) {
        this.lugar = lugar;
        this.nome = nome;
        this.arma = arma;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getArma() { return arma; }
    public void setArma(String arma) { this.arma = arma; }
}