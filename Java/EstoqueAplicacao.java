import java.io.*;
import java.text.*;
import java.util.Vector;

class Preco {
    private String dataInicial;
    private String dataFinal;
    private Produto item;
    private float preco;

    public Preco(String dataInicial, String dataFinal, Produto item, float preco) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.item = item;
        this.preco = preco;
    }

    public void imprimePreco() {
        System.out.println("Data Inicial: " + dataInicial);
        System.out.println("Data Final: " + dataFinal);
        System.out.println("Preco: " + preco);
    }

    public float getPreco() {
        return preco;
    }
}

class Produto {
    private int codigo;
    private String nome;
    private String unidade;
    private float quantidade;
    private Vector precos;

    public Produto(int codigo, String nome, String unidade, float quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        precos = new Vector();
    }

    public void adicionaPreco(Preco p) {
        precos.add(p);
    }

    public void imprimeProduto() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Unidade: " + unidade);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Precos:");
        for (int i = 0; i < precos.size(); i++) {
            ((Preco) precos.get(i)).imprimePreco();
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void devolucao(float qtd) {
        quantidade += qtd;
    }

    public void executaBaixa(float qtd) {
        quantidade -= qtd;
    }

    public float getPrecoAtual() {
        if (precos.size() == 0)
            return 0;
        Preco p = (Preco) precos.lastElement();
        return p.getPreco();
    }
}

class ItemVenda {
    private String data;
    private Produto item;
    private float quantidade;
    private Venda venda;

    public ItemVenda(String data, Produto item, float quantidade, Venda venda) {
        this.data = data;
        this.item = item;
        this.quantidade = quantidade;
        this.venda = venda;
        item.executaBaixa(quantidade);
    }

    public void imprimeItemVenda() {
        System.out.println("Data: " + data);
        System.out.println("Produto: " + item.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preco unitaro: " + item.getPrecoAtual());
    }

    public float getTotal() {
        return quantidade * item.getPrecoAtual();
    }

    public Produto getProduto() {
        return item;
    }

    public float getQuantidade() {
        return quantidade;
    }
}

class Venda {
    private int codigo;
    private String data;
    private String hora;
    private Vector itensVenda;

    public Venda(int codigo, String data, String hora) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.itensVenda = new Vector();
    }

    public void adicionaItem(ItemVenda item) {
        itensVenda.add(item);
    }

    public void imprimeVenda() {
        System.out.println("Venda codigo: " + codigo);
        System.out.println("Data: " + data + " Hora: " + hora);
        float total = 0;
        for (int i = 0; i < itensVenda.size(); i++) {
            ItemVenda iv = (ItemVenda) itensVenda.get(i);
            iv.imprimeItemVenda();
            total += iv.getTotal();
        }
        System.out.println("Valor total da venda: " + total);
    }

    public int getCodigo() {
        return codigo;
    }
}

class Estoque {
    private String empresa;
    private Vector listaDeProdutos;
    private Vector listaDeVendas;

    public Estoque(String empresa) {
        this.empresa = empresa;
        listaDeProdutos = new Vector();
        listaDeVendas = new Vector();
    }

    public void adicionaProduto(Produto p) {
        listaDeProdutos.add(p);
    }

    public void adicionaVenda(Venda v) {
        listaDeVendas.add(v);
    }

    public Produto consultaProduto(int codigo) {
        for (int i = 0; i < listaDeProdutos.size(); i++) {
            Produto p = (Produto) listaDeProdutos.get(i);
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    public Venda consultaVenda(int codigo) {
        for (int i = 0; i < listaDeVendas.size(); i++) {
            Venda v = (Venda) listaDeVendas.get(i);
            if (v.getCodigo() == codigo) return v;
        }
        return null;
    }

    public void imprimeProdutos() {
        System.out.println("\n" + empresa + " - Lista de Produtos");
        for (int i = 0; i < listaDeProdutos.size(); i++) {
            Produto p = (Produto) listaDeProdutos.get(i);
            p.imprimeProduto();
            System.out.println();
        }
    }

    public void imprimeVendas() {
        System.out.println("\n" + empresa + " - Lista de Vendas");
        for (int i = 0; i < listaDeVendas.size(); i++) {
            Venda v = (Venda) listaDeVendas.get(i);
            v.imprimeVenda();
            System.out.println();
        }
    }

    public void removeProduto(int codigo) {
        Produto p = consultaProduto(codigo);
        if (p != null) listaDeProdutos.remove(p);
    }

    public void removeVenda(int codigo) {
        Venda v = consultaVenda(codigo);
        if (v != null) listaDeVendas.remove(v);
    }
}

public class EstoqueAplicacao {
    public static String le_string() {
        String s = "";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            s = br.readLine();
        } catch (IOException e) {
            s = "";
        }
        return s;
    }

    public static float le_float() {
        String s = "";
        float f = 0;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            s = br.readLine();
            DecimalFormat df = new DecimalFormat();
            Number n = df.parse(s);
            f = n.floatValue();
        } catch (IOException | ParseException e) {
            f = 0;
        }
        return f;
    }

    public static int le_int() {
        String s = "";
        int i = 0;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            s = br.readLine();
            DecimalFormat df = new DecimalFormat();
            Number n = df.parse(s);
            i = n.intValue();
        } catch (IOException | ParseException e) {
            i = 0;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.print("Entre com o nome da empresa: ");
        String nomeEmpresa = le_string();
        Estoque estoque = new Estoque(nomeEmpresa);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("1) Adiciona produto");
            System.out.println("2) Adiciona preco ao produto");
            System.out.println("3) Imprime produtos");
            System.out.println("4) Remove produto");
            System.out.println("5) Adiciona venda");
            System.out.println("6) Adiciona item a uma venda");
            System.out.println("7) Imprime vendas");
            System.out.println("8) Remove venda");
            System.out.println("0) Sair");
            System.out.print("Opcao: ");
            opcao = le_int();

            switch (opcao) {
                case 1:
                    System.out.print("Codigo: ");
                    int codigo = le_int();
                    System.out.print("Nome: ");
                    String nome = le_string();
                    System.out.print("Unidade: ");
                    String unidade = le_string();
                    System.out.print("Quantidade inicial: ");
                    float qtd = le_float();
                    estoque.adicionaProduto(new Produto(codigo, nome, unidade, qtd));
                    break;

                case 2:
                    System.out.print("Codigo do produto: ");
                    int cod = le_int();
                    Produto p = estoque.consultaProduto(cod);
                    if (p != null) {
                        System.out.print("Data inicial: ");
                        String di = le_string();
                        System.out.print("Data final: ");
                        String df = le_string();
                        System.out.print("Preco: ");
                        float pr = le_float();
                        p.adicionaPreco(new Preco(di, df, p, pr));
                    } else {
                        System.out.println("Produto nao encontrado!");
                    }
                    break;

                case 3:
                    estoque.imprimeProdutos();
                    break;

                case 4:
                    System.out.print("Codigo do produto a remover: ");
                    int codR = le_int();
                    estoque.removeProduto(codR);
                    break;

                case 5:
                    System.out.print("Codigo da venda: ");
                    int cv = le_int();
                    System.out.print("Data: ");
                    String data = le_string();
                    System.out.print("Hora: ");
                    String hora = le_string();
                    estoque.adicionaVenda(new Venda(cv, data, hora));
                    break;

                case 6:
                    System.out.print("Codigo da venda: ");
                    int codVenda = le_int();
                    Venda v = estoque.consultaVenda(codVenda);
                    if (v == null) {
                        System.out.println("Venda nao encontrada!");
                        break;
                    }
                    System.out.print("Codigo do produto: ");
                    int codP = le_int();
                    Produto prod = estoque.consultaProduto(codP);
                    if (prod == null) {
                        System.out.println("Produto nao encontrado!");
                        break;
                    }
                    System.out.print("Quantidade: ");
                    float quantidade = le_float();
                    System.out.print("Data do item: ");
                    String dataItem = le_string();
                    ItemVenda iv = new ItemVenda(dataItem, prod, quantidade, v);
                    v.adicionaItem(iv);
                    break;

                case 7:
                    estoque.imprimeVendas();
                    break;

                case 8:
                    System.out.print("Codigo da venda a remover: ");
                    int codRem = le_int();
                    estoque.removeVenda(codRem);
                    break;
            }
        }
    }
}
