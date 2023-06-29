package modelo;

import java.util.List;

public class Compra {
    private int idCompra;
    private String data;
    private double valorTotal;
    private Fornecedor fornecedor;
    public List<CompraProduto> itemCompra;

    // Construtor
    public Compra(int idCompra, String data, double valorTotal, Fornecedor fornecedor) {
        this.idCompra = idCompra;
        this.data = data;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
    }
    
    // Getters e setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<CompraProduto> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(List<CompraProduto> itemCompra) {
        this.itemCompra = itemCompra;
    }

    @Override
    public String toString() {
        return "idCompra: " + idCompra +
        "data=" + data +
        "valorTotal=" + valorTotal +
        "fornecedor=" + fornecedor +
        "itemCompra=" + itemCompra ;
    }
    
    
}

