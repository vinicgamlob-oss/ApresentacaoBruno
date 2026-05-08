package apresentaçãobrunogof;

import java.util.ArrayList;
import java.util.List;

// Classe Usuario (Sem interface, o Leilao depende diretamente dela)
// CLASSE CONCRETA: O sistema só aceita este tipo específico de usuário
class UsuarioSimples {
    private String nome;

    public UsuarioSimples(String nome) {
        this.nome = nome;
    }
// O usuário tem um método específico para receber notificações, 
// mas o leilão precisa conhecer esse método para funcionar.
    public void avisar(String mensagem) {
        System.out.println(nome + " recebeu notificacao: " + mensagem);
    }
}

// Classe Leilao (Gerencia os usuários manualmente)
// ACOPLAMENTO FORTE: A lista está presa a 'UsuarioSimples'. 
// Se quiser adicionar um 'RoboLicitante', este código quebra.
class LeilaoSemPadrao {
    // Note que aqui estamos presos à classe UsuarioSimples
    private List<UsuarioSimples> usuarios = new ArrayList<>();
    private double maiorLance;
// O Leilão precisa conhecer a estrutura exata do usuário para notificá-lo.
    public void cadastrarUsuario(UsuarioSimples u) {
        usuarios.add(u);
    }
// O Leilão precisa conhecer a estrutura exata do usuário para removê-lo.
    public void excluirUsuario(UsuarioSimples u) {
        usuarios.remove(u);
    }
// O Leilão precisa conhecer a estrutura exata do usuário para notificá-lo.
    public void novoLance(double valor) {
        this.maiorLance = valor;
        System.out.println("\n Novo lance registrado: R$ " + valor);
//        
        // O Leilao "sabe" exatamente como notificar o usuario
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

        // VIOLAÇÃO DE RESPONSABILIDADE: O Leilão precisa saber 
        // exatamente qual método o usuário tem (u.avisar).

        leilao.cadastrarUsuario(u1);
        leilao.cadastrarUsuario(u2);

        leilao.novoLance(100.0);
    }
}

// O Usuário é uma peça específica e insubstituível.
// O Leilão só aceita este modelo exato (UsuarioSimples).
// É como uma tomada antiga que só aceita um tipo de plugue:
// Se você quiser conectar um "Robo" ou uma "Empresa", o Leilão não deixa.