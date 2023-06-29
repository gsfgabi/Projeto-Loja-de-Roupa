package modelo;

public class CompraProduto {
    private int idCompraProduto;
    private Produto produto;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    // Construtor
    public CompraProduto(int idCompraProduto, Produto produto, int quantidade, double valorUnitario, double valorTotal) {
        this.idCompraProduto = idCompraProduto;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }
    
    // Getters e setters
    public int getIdCompraProduto() {
        return idCompraProduto;
    }

    public void setIdCompraProduto(int idCompraProduto) {
        this.idCompraProduto = idCompraProduto;
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
        return "idCompraProduto: " + idCompraProduto + 
                ", produto: " + produto + 
                ", quantidade: " + quantidade + 
                ", valorUnitario: " + valorUnitario + 
                ", valorTotal: R$ " + valorTotal;
    }
    
    
}
