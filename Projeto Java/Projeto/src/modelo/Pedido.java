package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private String data;
    private String transporte;
    private double valorTotal;
    private Cliente cliente;
    public List<PedidoProduto> itensPedido;
    // Construtor
    public Pedido(int idPedido, String data, String transporte, double valorTotal, Cliente cliente) {
        this.idPedido = idPedido;
        this.data = data;
        this.transporte = transporte;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    // Getters e setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoProduto> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoProduto> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "Pedido: " + idPedido + 
                ", data: " + data + 
                ", valorTotal: " + valorTotal+
                ", transporte: " + transporte +
                ", Itens: " + itensPedido;
    }
    
    
    
}

