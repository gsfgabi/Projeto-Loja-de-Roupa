package modelo;

public class PedidoProduto {
    private int idPedidoProduto;
    private Produto produto;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    // Construtor
    public PedidoProduto(int idPedidoProduto, Produto produto, int quantidade, double valorUnitario, double valorTotal) {
        this.idPedidoProduto = idPedidoProduto;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    // Getters e setters

    public int getIdPedidoProduto() {
        return idPedidoProduto;
    }

    public void setIdPedidoProduto(int idPedidoProduto) {
        this.idPedidoProduto = idPedidoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Item: " + idPedidoProduto + 
               ", produto: " + produto + 
                ", quantidade: " + quantidade + 
                ", valorUnitario: " + valorUnitario + 
                ", valorTotal: " + valorTotal;
    }
    
    
}
