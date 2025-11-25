package app.model;

public class Rodovia {
    private int id;
    private String nome;
    private String cidade;
    private int extensao;

    // Construtor com ID (usado ao ler do Banco)
    public Rodovia(int id, String nome, String cidade, int extensao) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.extensao = extensao;
    }

    // Construtor sem ID (usado para criar novos registros)
    public Rodovia(String nome, String cidade, int extensao) {
        this.nome = nome;
        this.cidade = cidade;
        this.extensao = extensao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public int getExtensao() { return extensao; }
    public void setExtensao(int extensao) { this.extensao = extensao; }
}