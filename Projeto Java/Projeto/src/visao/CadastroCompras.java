package visao;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import repositorio.Repositorio;

import modelo.Compra;
import modelo.CompraProduto;
import modelo.Fornecedor;
import modelo.Produto;
import java.awt.Color;


public class CadastroCompras extends javax.swing.JFrame {
    private List<CompraProduto> itensC;
    
    public CadastroCompras() {
        initComponents();
        this.itensC = new ArrayList<>();
        carregarFornecedoresBox();
        carregarProdutosBox();
        idAutomatico();
        atualizarRegistros();
        
    }
    
    //PRECISA ADICIONAR ID AUTOMATICAMENTE, MAS NÃO SE REPETIR
public void idAutomatico() {
    
    int menorIdCompra = Integer.MAX_VALUE;

    if (Repositorio.compras.isEmpty()) {
        menorIdCompra = 0;
    } else {
      for (Compra c : Repositorio.compras) {
         if (c.getIdCompra() < menorIdCompra) {
             menorIdCompra = c.getIdCompra();
         }
        }
    }

    int idCompraAtual = menorIdCompra + 1;
    
  while (idRepetido(idCompraAtual)) {
        idCompraAtual++;
       }
    idCadastro.setText(String.valueOf(idCompraAtual));
}

private boolean idRepetido(int idCompra) {
    for (Compra c : Repositorio.compras) {
        if (c.getIdCompra() == idCompra) {
            return true;
        }
    }
    return false;
}


   
  
   public void unlockDataInput(){
    //IMPEDE ALTERAÇÃO DA DATA EM UMA MESMA COMPRA
        dayCadastro.setEditable(true);
        monthCadastro.setEditable(true);
        yearCadastro.setEditable(true);
        
        Color backgroundColor = new Color(255,255,255);
        dayCadastro.setBackground(backgroundColor);
        monthCadastro.setBackground(backgroundColor);
        yearCadastro.setBackground(backgroundColor);
        
   }
   
   public void lockDataInput(){
        //IMPEDE ALTERAÇÃO DA DATA EM UMA MESMA COMPRA
        dayCadastro.setEditable(false);
        monthCadastro.setEditable(false);
        yearCadastro.setEditable(false);
        
        Color backgroundColor = new Color(204, 204, 204);
        dayCadastro.setBackground(backgroundColor);
        monthCadastro.setBackground(backgroundColor);
        yearCadastro.setBackground(backgroundColor);
        
   }

   
   public void carregarFornecedoresBox() {
       
    //DEPENDE DE QUE FORNECEDORES TENHA:
    //@Override
    //public String toString() {
    //    return nome; // Return the "nome" variable as the string representation of the Fornecedor object
    //}
    
       
    DefaultComboBoxModel<Object> fornecedorB = new DefaultComboBoxModel<>();
    fornecedorB.addElement("Selecionar");
    for (Fornecedor f : Repositorio.fornecedores) {
        fornecedorB.addElement(f);
    }
    fornecedorBox.setModel(fornecedorB);
    }


  

    
    public void carregarProdutosBox() {
        
    DefaultComboBoxModel<Object> produtoB = new DefaultComboBoxModel<>();
    produtoB.addElement("Selecionar");
    for (Produto p : Repositorio.produtos) {
    produtoB.addElement(p);
    
    }
    produtoBox.setModel(produtoB);
    }

    
    //VARIAVEIS LOCAIS
 
    public void campos_igual_variaveis(){
        
        
        //VARIAVEL X = TEXTO NO RESPECTIVO CAMPO
        
        int idCompra = Integer.parseInt(this.idCadastro.getText());
        double valorP = Double.parseDouble(this.valorProdutoText.getText());
        double valorT = Double.parseDouble(this.valorTotalText.getText());
        String dia = this.dayCadastro.getText();
        String mes = this.monthCadastro.getText();
        String ano = this.yearCadastro.getText();
        String data = (dia+"/"+mes+"/"+ano);
        Produto p1 = (Produto) this.produtoBox.getSelectedItem();
        Fornecedor f1 = (Fornecedor) this.fornecedorBox.getSelectedItem();
        String quantidade = this.quantidadeProduto.getText();
 
        
    }
    
    //CALCULA VALOR TOTAL E ATUALIZA
    public void calcularTotal() {
    double valorTotalCadastro = 0.0;
    int idCompra = Integer.parseInt(idCadastro.getText());

    for (CompraProduto compra : this.itensC) {
        if (compra.getIdCompraProduto() == idCompra) {
            valorTotalCadastro += compra.getValorTotal();
        }
    }

    String valorTotalFormatado = String.format("%.2f", valorTotalCadastro);
    valorTotalFormatado = valorTotalFormatado.replace(".", ",");
    valorTotalText.setText(valorTotalFormatado);
}


     
     public void limparProduto(){
         
        idAutomatico();
        valorProdutoText.setText("0,00");
        quantidadeProduto.setText("");
        produtoBox.setSelectedIndex(0);
        
        
        
     }
    
    public void adicionar(){
        
        if (this.itensC == null) {
        this.itensC = new ArrayList<>();
        }

        
        //PRECISO APRESENTAR OS ITENS ADICIONADOS NA JList ANTES DE CADASTRALOS NO RESERVATÓRIO!!!! Me ajudem
        
        int idCompra = Integer.parseInt(this.idCadastro.getText());
        Produto produto = (Produto) this.produtoBox.getSelectedItem();
        int quantidade = Integer.parseInt(this.quantidadeProduto.getText());
        
        String valorPText = this.valorProdutoText.getText().replace(",", ".");
        double valorP = Double.parseDouble(valorPText);
        double valorT = valorP * quantidade;
      
        CompraProduto c1 = new CompraProduto(idCompra, produto, quantidade, valorP, valorT);
        this.itensC.add(c1);
        
        lockDataInput();

        calcularTotal();
        
    }
    

public void atualizar() {
    
    DefaultListModel<String> lista = new DefaultListModel<>();
    int idCadastroLista = Integer.parseInt(idCadastro.getText());

    for (CompraProduto comprasNaLista : this.itensC) {
        if (comprasNaLista.getIdCompraProduto() == idCadastroLista) {
            lista.addElement(comprasNaLista.toString());
        }
    }

    Carrinho.setModel(lista);

    idAutomatico();
}


   
   
   
      public void atualizarRegistros() {
       
    DefaultListModel<String> registros = new DefaultListModel<>();
    
    for (Compra registradas : Repositorio.compras) {
        registros.addElement(registradas.toString()); // Use a suitable method to get the string representation of CompraProduto
    }
    this.RegistroCompras.setModel(registros);
    }

    
    
public void cadastrar() {
    
    String dia = this.dayCadastro.getText();
    String mes = this.monthCadastro.getText();
    String ano = this.yearCadastro.getText();
    String data = dia + "/" + mes + "/" + ano;
    Fornecedor f1 = (Fornecedor) this.fornecedorBox.getSelectedItem();
    int idCompra = Integer.parseInt(this.idCadastro.getText());
    
    String valorTotalCorreto = this.valorTotalText.getText().replace(",", ".");
    double valorTotal = Double.parseDouble(valorTotalCorreto);

    Compra compra = new Compra(idCompra, data, valorTotal, f1);
    compra.setItemCompra(this.itensC);
    Repositorio.compras.add(compra);
    
    valorTotalText.setText("0,00");
    
    
    
    idAutomatico();
    atualizar();
    
    unlockDataInput();
   
    JOptionPane.showMessageDialog(this,"Compra cadastrada com sucesso.");
    
}

        
        
        
    

        
        

        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        RegistroCompras = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        yearCadastro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        produtoBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        idCadastro = new javax.swing.JTextField();
        quantidadeProduto = new javax.swing.JTextField();
        fornecedorBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        adicionarButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        valorTotalText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dayCadastro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        monthCadastro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        valorProdutoText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        limparButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Carrinho = new javax.swing.JList<>();
        cadastrarButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(RegistroCompras);

        jButton2.setText("Atualizar Lista");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(494, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(441, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(14, 14, 14))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGap(55, 55, 55)))
        );

        jTabbedPane1.addTab("Registro de Compras", jPanel2);

        yearCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yearCadastroKeyTyped(evt);
            }
        });

        jLabel3.setText("Produto:");

        jLabel4.setText("Quantidade:");

        produtoBox.setEditable(true);
        produtoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Código da Compra:");

        idCadastro.setEditable(false);
        idCadastro.setBackground(new java.awt.Color(204, 204, 204));
        idCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCadastroActionPerformed(evt);
            }
        });

        quantidadeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeProdutoActionPerformed(evt);
            }
        });
        quantidadeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantidadeProdutoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                quantidadeProdutoKeyTyped(evt);
            }
        });

        fornecedorBox.setEditable(true);
        fornecedorBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fornecedorBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Data:");

        jLabel5.setText("Valor Total:");

        jLabel6.setText("Fornecedor:");

        adicionarButton.setText("Adicionar Item");
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar e Fechar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        valorTotalText.setEditable(false);
        valorTotalText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valorTotalText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorTotalText.setText("0,00");
        valorTotalText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorTotalTextActionPerformed(evt);
            }
        });
        valorTotalText.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                valorTotalTextPropertyChange(evt);
            }
        });
        valorTotalText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorTotalTextKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("R$");

        dayCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dayCadastroKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel8.setText("/");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel9.setText("/");

        monthCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                monthCadastroKeyTyped(evt);
            }
        });

        jLabel14.setText("Valor Unitário:");

        valorProdutoText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valorProdutoText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorProdutoText.setText("0,00");
        valorProdutoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorProdutoTextActionPerformed(evt);
            }
        });
        valorProdutoText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorProdutoTextKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("R$");

        limparButton.setText("Limpar Campos");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonActionPerformed(evt);
            }
        });

        Carrinho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CarrinhoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Carrinho);

        cadastrarButton.setText("Registrar Compra");
        cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dayCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(adicionarButton)
                                .addGap(221, 221, 221))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(produtoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addGap(12, 12, 12)
                                .addComponent(valorProdutoText, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(limparButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fornecedorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cadastrarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valorTotalText)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(dayCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(monthCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(yearCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(produtoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(quantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorProdutoText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarButton)
                    .addComponent(limparButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fornecedorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(valorTotalText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel5)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cadastrarButton)
                    .addComponent(jButton3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registar Compra", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void yearCadastroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearCadastroKeyTyped
       
        //QUANDO UMA TECLA É DIGITADA, SERÁ CONSUMIDA SE FOR UM DIGITO (apenas números)
        char typed = evt.getKeyChar();
        if (!Character.isDigit(typed)){
            evt.consume();
        }

String medida = this.yearCadastro.getText();
 
        if((medida.length()) >=2){  
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_yearCadastroKeyTyped

    private void produtoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoBoxActionPerformed
        
        //PRECISO PUXAR OS PRODUTOS DA LISTA DE PRODUTOS CADASTRADOS, PELO MENOS SEUS NOMES!
      
       
    }//GEN-LAST:event_produtoBoxActionPerformed

    private void quantidadeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeProdutoActionPerformed

    }//GEN-LAST:event_quantidadeProdutoActionPerformed

    private void quantidadeProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeProdutoKeyTyped
    
//QUANDO UMA TECLA É DIGITADA, SERÁ CONSUMIDA SE FOR UM DIGITO (apenas números)
        char typed = evt.getKeyChar();
        if (!Character.isDigit(typed)){
            evt.consume();
           
        }
        
        String medida = this.quantidadeProduto.getText();
 
        if((medida.length()) >=3){  
            evt.consume();
        }  
        
        
    }//GEN-LAST:event_quantidadeProdutoKeyTyped

    private void fornecedorBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fornecedorBoxActionPerformed
    
     //PRECISO PUXAR OS FORNECEDORES DA LISTA DE FORNECEDORES CADASTRADOS! Mas como?   
        
    }//GEN-LAST:event_fornecedorBoxActionPerformed

    private void adicionarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarButtonActionPerformed

        //PRECISO APRESENTAR OS ITENS ADICIONADOS NO CARRINHO NA JList ANTES DE CADASTRALOS NO RESERVATÓRIO, MAS NÃO CONSIGO!
        
        
        //verifica se algum campo está em branco antes de permitir salvar o registro. Nada pode ser nulo//
        if (valorProdutoText.getText().equals("00,00")||valorProdutoText.getText().equals("")||valorProdutoText.getText().equals("0")||valorProdutoText.getText().equals("00,00")||valorProdutoText.getText().equals("000,00")||idCadastro.getText().equals("")||dayCadastro.getText().equals("")||monthCadastro.getText().equals("")||yearCadastro.getText().equals("")||quantidadeProduto.getText().equals("")||quantidadeProduto.getText().equals("0")||produtoBox.getSelectedItem().toString().equals("Selecionar")){
        
            JOptionPane.showMessageDialog(this,"Todos os campos devem ser preenchidos.");
        } else {
 
        adicionar();
        atualizar();
        calcularTotal();

        };
        
    limparProduto();
    }//GEN-LAST:event_adicionarButtonActionPerformed

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void valorTotalTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorTotalTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorTotalTextActionPerformed

    private void valorTotalTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorTotalTextKeyTyped
        evt.consume();
        // TODO add your handling code here:
    }//GEN-LAST:event_valorTotalTextKeyTyped

    private void dayCadastroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayCadastroKeyTyped
        char typed = evt.getKeyChar();
        if (!Character.isDigit(typed)){
            evt.consume();
        }
        
        
        String dia_medida = this.dayCadastro.getText();
 
        if((dia_medida.length()) >=2){  
            evt.consume();
        }
    }//GEN-LAST:event_dayCadastroKeyTyped

    private void monthCadastroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthCadastroKeyTyped
        char typed = evt.getKeyChar();
        if (!Character.isDigit(typed)){
            evt.consume();
        }

String medida = this.monthCadastro.getText();
 
        if((medida.length()) >=2){  
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_monthCadastroKeyTyped

    private void valorProdutoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorProdutoTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorProdutoTextActionPerformed

    private void valorProdutoTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorProdutoTextKeyTyped
        String medida = this.valorProdutoText.getText();
        
        char typed = evt.getKeyChar();
        if (Character.isLetter(typed)){
            evt.consume();
        }
 
        if((medida.length()) >=8){  
            evt.consume();
        } 
    }//GEN-LAST:event_valorProdutoTextKeyTyped
  
    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
    
    limparProduto();
    atualizar();

        
    }//GEN-LAST:event_limparButtonActionPerformed

    private void idCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCadastroActionPerformed

    private void quantidadeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeProdutoKeyPressed

        
    }//GEN-LAST:event_quantidadeProdutoKeyPressed

    private void valorTotalTextPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_valorTotalTextPropertyChange
            // TODO add your handling code here:
    }//GEN-LAST:event_valorTotalTextPropertyChange

    private void cadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarButtonActionPerformed

        
        
        if (idCadastro.getText().equals("")||dayCadastro.getText().equals("")||monthCadastro.getText().equals("")||yearCadastro.getText().equals("")||fornecedorBox.getSelectedItem().toString().equals("Selecionar")){
             JOptionPane.showMessageDialog(this,"Selecione um fornecedor.");
        } else {
            
            cadastrar();
            this.itensC = new ArrayList<>();

        }   
        
        
        
        
        
    }//GEN-LAST:event_cadastrarButtonActionPerformed

    private void CarrinhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CarrinhoMouseClicked
     
        //PUXAR DADOS SELECIONADOS PARA CAMPOS
   
    }//GEN-LAST:event_CarrinhoMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        atualizarRegistros();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Carrinho;
    private javax.swing.JList<String> RegistroCompras;
    private javax.swing.JButton adicionarButton;
    private javax.swing.JButton cadastrarButton;
    private javax.swing.JTextField dayCadastro;
    private javax.swing.JComboBox<Object> fornecedorBox;
    private javax.swing.JTextField idCadastro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limparButton;
    private javax.swing.JTextField monthCadastro;
    private javax.swing.JComboBox<Object> produtoBox;
    private javax.swing.JTextField quantidadeProduto;
    private javax.swing.JTextField valorProdutoText;
    private javax.swing.JTextField valorTotalText;
    private javax.swing.JTextField yearCadastro;
    // End of variables declaration//GEN-END:variables


    
    }

    
   
    

    
    
    
    
    
    









