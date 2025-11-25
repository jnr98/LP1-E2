package app.model;

public class Passaporte {
    private int id;
    private String nome;
    private String numero;
    private String pais;

    // Construtor COMPLETO (usado pelo DAO ao ler do banco)
    public Passaporte(int id, String nome, String numero, String pais) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.pais = pais;
    }

    // Construtor SEM ID (usado pelo Controller ao criar um novo)
    public Passaporte(String nome, String numero, String pais) {
        this.nome = nome;
        this.numero = numero;
        this.pais = pais;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // O método toString é útil para debugar, se quiser pode adicionar:
    @Override
    public String toString() {
        return nome + " (" + pais + ")";
    }
}