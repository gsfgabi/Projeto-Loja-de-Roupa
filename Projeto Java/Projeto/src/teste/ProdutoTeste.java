package teste;

/**
 *
 * @author Gabriella
 */
import modelo.Produto;
import repositorio.Repositorio;

public class ProdutoTeste {

    public static void main(String[] args) {

        // Carrega as categorias no repositório
        Repositorio.load();

        Produto p3 = new Produto(3, "Vestido", "Vestido Florido", "GG", 10, 100.99, "Floral", Repositorio.categorias.get(0));

        System.out.println(p3.toString());
    }
}



