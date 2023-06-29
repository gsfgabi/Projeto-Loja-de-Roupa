package modelo;

import java.util.List;
import modelo.Endereco;

public class Cliente {
    private Integer idCliente;
    private String nome;
    private String email;
    private String cpf;
    private Integer ddd;
    private Integer numero;
    private List<Endereco> enderecos;

    // Construtor
    public Cliente(Integer idCliente, String nome, String email, String cpf,
            Integer ddd, Integer numero) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.ddd = ddd;
        this.numero = numero;
    }

    // Getters e setters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}

