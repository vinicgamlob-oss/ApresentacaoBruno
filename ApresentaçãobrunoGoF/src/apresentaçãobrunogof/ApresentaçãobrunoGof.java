package apresentaçãobrunogof;

import java.util.ArrayList;
import java.util.List;


interface Licitante {
    void update(String mensagem);
}

  // INTERFACE  =  CONTRATO (MOLDE) (NOME BOTAO)
interface PublicadorDeLeilao {
    void adicionarLicitante(Licitante o); 
    void removerLicitante(Licitante o);
    void notificarLicitante();
}


// IMPLEMENTS SIGNIFICA : assumir o compromisso de seguir um contrato (a  Interface) TER TUDO QUE A INTERFACE Subject EXIGE

// Classe Leilao implementando o contrato do Publicador

class Leilao implements PublicadorDeLeilao {
 
    private List<Licitante> observers = new ArrayList<>();
    private double maiorLance;

    @Override  // SOBREESCREVER 
    public void adicionarLicitante(Licitante o) {
        observers.add(o);
    }

    @Override
    public void removerLicitante(Licitante o) {
        observers.remove(o);
    }

    @Override
    public void notificarLicitante() {
        for (Licitante o : observers) {  // FOR-EACH 
            o.update("Novo lance: R$ " + maiorLance);
        }
    }

    public void novoLance(double valor) {
        this.maiorLance = valor;
        System.out.println("\n Novo lance registrado: R$ " + valor);
        notificarLicitante();
    }
}

// Classe Usuario implementando Licitante (SEGUINDO TODOS OS REQUISITOS )
class Usuario implements Licitante {
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(String mensagem) {
        System.out.println(nome + " recebeu notificacao: " + mensagem);
    }
    
    // O diagrama mostra esse método na classe Usuario
    public void receberNotificacao() {
        // Lógica extra se necessário
    }
}

// Classe Principal
public class ApresentaçãobrunoGof {
    public static void main(String[] args) {
        Leilao leilao = new Leilao();

        Usuario u1 = new Usuario("Lis");
        Usuario u2 = new Usuario("Joao");
        Usuario u3 = new Usuario("Maria");

        leilao.adicionarLicitante(u1);
        leilao.adicionarLicitante(u2);
        leilao.adicionarLicitante(u3);

        leilao.novoLance(100);
        leilao.removerLicitante(u2);
        leilao.novoLance(200);
    }
}
