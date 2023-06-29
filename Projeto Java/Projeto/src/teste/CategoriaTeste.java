package teste;

/**
 *
 * @author Gabriella
 */
import modelo.Categoria;
import repositorio.Repositorio;

public class CategoriaTeste {

    public static void main(String[] args) {

        // Carrega as categorias no repositório
        Repositorio.load();

        Categoria c1 = new Categoria(1, "Blusas", "Blusas de todas as variedades e modelos");
        Categoria c2 = new Categoria(2, "Calcas", "Calcas de todas as variedades e modelos");

        System.out.println("Categorias:");
        System.out.println(c1.toString());
        System.out.println(c2.toString());

    }
}
