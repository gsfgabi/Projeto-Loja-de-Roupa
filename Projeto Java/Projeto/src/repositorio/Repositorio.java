package repositorio;

import modelo.Categoria;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import modelo.Compra;
import modelo.Fornecedor;
import modelo.Produto;
import modelo.Pedido;

public class Repositorio {
    public static List<Produto> produtos = new ArrayList<>();
    public static List<Pedido> pedidos = new ArrayList<>();
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Categoria> categorias = new ArrayList<>();
    public static List<Fornecedor> fornecedores = new ArrayList<>();
    public static List<Compra> compras = new ArrayList<>();

    public static void load(){

        clientes.add(new Cliente (1, "Geo", "geo@gmaol.com", "1234",44, 981818181));
        clientes.add(new Cliente (2, "Gabi", "geo@gmaol.com", "1234",  44, 981818181));
        clientes.add(new Cliente (3, "Jardy", "geo@gmaol.com", "1234",  44, 981818181));
        
        categorias.add(new Categoria(1, "Blusas", "Blusas de todas as variedades e modelos"));
        categorias.add(new Categoria(2, "Calças", "Calças de todas as variedades e modelos"));
        categorias.add(new Categoria(3, "Vestidos", "Vestidos de festa e casuais"));
        categorias.add(new Categoria(4, "Saias", "Saias curtas e longas"));
        categorias.add(new Categoria(5, "Casacos", "Casacos de inverno e jaquetas"));
        
        produtos.add(new Produto(1, "Blusa de Renda", "Blusa de Renda", "M", 10, 28.99, "Azul", categorias.get(0)));
        produtos.add(new Produto(2, "Calça Legging", "Calça confortável","G", 5, 39.99, "Preto", categorias.get(1)));
        produtos.add(new Produto(3, "Vestido Longo", "Vestido elegante", "P", 3, 119.99, "Vermelho", categorias.get(2)));
        produtos.add(new Produto(4, "Saia Plissada", "Saia feminina", "GG", 8, 35.99, "Rosa", categorias.get(3)));
        produtos.add(new Produto(5, "Casaco de Inverno", "Casaco quente", "M", 12, 125.99, "Preto", categorias.get(4)));
        
        fornecedores.add(new Fornecedor (1,"Renner","1111111","email@mail", 1));
    }
    
}
