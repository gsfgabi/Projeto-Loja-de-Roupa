package teste;
import modelo.Pedido;
import modelo.Cliente;

class PedidoTeste {

     public static void main(String[] args) {
         Cliente c1 = new Cliente (1, "Geo", "geo@gmaol.com", "1234", 44, 981818181);
         Pedido p1 = new Pedido(1, "13/05/2025", "Correio", 1345.98, c1);
        
        //System.out.println(p1.getId() == 1);
       // System.out.println(p1.getCodigoBarras().equals("13131"));
        //System.out.println(p1.getDescrição().equals("Detergente Ipe"));
    }
     
}
