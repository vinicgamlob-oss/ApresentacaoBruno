package apresentaçãobrunogof;

import java.util.ArrayList;
import java.util.List;


class UsuarioSimples {
    private String nome;

    public UsuarioSimples(String nome) {
        this.nome = nome;
    }

    public void avisar(String mensagem) {
        System.out.println(nome + " recebeu notificacao: " + mensagem);
    }
}


class LeilaoSemPadrao {
    
    private List<UsuarioSimples> usuarios = new ArrayList<>();
    private double maiorLance;

    public void cadastrarUsuario(UsuarioSimples u) {
        usuarios.add(u);
    }

    public void excluirUsuario(UsuarioSimples u) {
        usuarios.remove(u);
    }

    public void novoLance(double valor) {
        this.maiorLance = valor;
        System.out.println("\n Novo lance registrado: R$ " + valor);
        
        
        for (UsuarioSimples u : usuarios) {
            u.avisar("Novo lance: R$ " + maiorLance);
        }
    }
}

public class ApresentacaobrunoSemGof {
    public static void main(String[] args) {
        LeilaoSemPadrao leilao = new LeilaoSemPadrao();

        UsuarioSimples u1 = new UsuarioSimples("Lis");
        UsuarioSimples u2 = new UsuarioSimples("Joao");

        leilao.cadastrarUsuario(u1);
        leilao.cadastrarUsuario(u2);

        leilao.novoLance(100.0);
    }
}
