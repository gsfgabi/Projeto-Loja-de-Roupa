package modelo;

public class Endereco {
    private Integer id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private Integer cep;

    // Construtor
    public Endereco(Integer id, String rua, String numero, String bairro, String cidade, 
            String uf, String complemento, Integer cep) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
        this.cep = cep;
    }
    
    /*O método exibirEndereco() imprime na tela todas as informações do 
    endereço. O método atualizarEndereco() permite atualizar as informações 
    do endereço com novos valores. E o método excluirEndereco() remove 
    todas as informações do endereço, deixando seus atributos como nulos*/

    // Getters e setters

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }
    
    @Override
    public String toString() {
        return "Endereco: " + "id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", complemento=" + complemento + ", cep=" + cep + '}';
    }
    
    
}