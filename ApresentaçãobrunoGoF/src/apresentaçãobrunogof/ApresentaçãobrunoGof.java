package apresentaçãobrunogof;

import java.util.ArrayList;
import java.util.List;

// ABSTRAÇÃO (CONTRATO): Não importa quem é o objeto, 
// desde que ele saiba reagir ao método 'update'.
interface Licitante {
    void update(String mensagem);
}
//No Código 1 o usuário é um tipo fixo, no Código 2 o usuário é um comportamento padronizado.

  // INTERFACE  =  CONTRATO (MOLDE) (NOME BOTAO)
  // PADRONIZAÇÃO: Define como qualquer leilão deve se comportar 
// (adicionar, remover e notificar).
interface PublicadorDeLeilao {
    void adicionarLicitante(Licitante o); 
    void removerLicitante(Licitante o);
    void notificarLicitante();
}


// IMPLEMENTS SIGNIFICA : assumir o compromisso de seguir um contrato (a  Interface) 
// TER TUDO QUE A INTERFACE PublicadorDeLeilao EXIGE

// Classe Leilao implementando o contrato do Publicador

class Leilao implements PublicadorDeLeilao {

    // ACOPLAMENTO FRACO: A lista aceita QUALQUER coisa que seja um 'Licitante'.
    // Pode ser Pessoa, Empresa, Robô, App, etc.
 //
    private List<Licitante> observers = new ArrayList<>();
    private double maiorLance;

    @Override  // SOBREESCREVER 
    public void adicionarLicitante(Licitante o) {
        observers.add(o);
    }
// O leilão não se importa quem é o licitante, ele só sabe que ele tem um método 'update' para chamar.
    @Override
    public void removerLicitante(Licitante o) {
        observers.remove(o);
    }

    @Override
    public void notificarLicitante() {
        // POLIMORFISMO: O leilão avisa todo mundo de forma genérica.
        // Ele não precisa saber os detalhes internos de cada licitante.

        for (Licitante o : observers) {  // FOR-EACH 
            o.update("Novo lance: R$ " + maiorLance);
        }
    }
// O leilão registra um novo lance e automaticamente notifica os licitantes.
    public void novoLance(double valor) {
        this.maiorLance = valor;
        System.out.println("\n Novo lance registrado: R$ " + valor);
        notificarLicitante();
    }
}

// Classe Usuario implementando Licitante (SEGUINDO TODOS OS REQUISITOS )
// IMPLEMENTAÇÃO: O Usuario agora assina o contrato de Licitante.
class Usuario implements Licitante { // O USUÁRIO É UM LICITANTE (CONTRATO)
    private String nome;

    //CONSTRUTOR PARA INICIALIZAR O NOME DO USUÁRIO
    public Usuario(String nome) {
        this.nome = nome;
    }


    // O USUÁRIO DECIDE COMO QUER REAGIR AO UPDATE (POLIMORFISMO)
    @Override
    public void update(String mensagem) {
        System.out.println(nome + " recebeu notificacao: " + mensagem);
    }
    
   
    
}

// Classe Principal
public class ApresentaçãobrunoGof {

    // Cada classe decide como quer tratar a atualização recebida.
    public static void main(String[] args) {
        Leilao leilao = new Leilao();

// Criamos usuários que agora são licitantes, mas o leilão não precisa saber disso.
        Usuario u1 = new Usuario("Lis");
        Usuario u2 = new Usuario("Joao");
        Usuario u3 = new Usuario("Maria");
// O leilão só vê "Licitantes", ele não se importa se são usuários, robôs ou apps.
        leilao.adicionarLicitante(u1);
        leilao.adicionarLicitante(u2);
        leilao.adicionarLicitante(u3);
// O leilão registra um novo lance e notifica todo mundo de forma genérica.
        leilao.novoLance(100);
        leilao.removerLicitante(u2);
        leilao.novoLance(200);
    }
}

/*Seguimos o  padrão QUANDO : separamos o Leilão dos Usuários, criamos as interfaces
Licitante e PublicadorDeLeilao, mantemos a lista de interessados e garantimos que o aviso seja
 disparado automaticamente.*/


// O Usuário agora é "disfarçado" de Licitante.
// O Leilão não vê mais um "João" ou uma "Maria", ele vê um "Objeto que sabe dar update".
// Isso permite que o sistema seja Genérico:
// Você pode ter Usuário, Robô, App de Celular ou Sistema de Log na mesma lista,
// pois para o Leilão, todos eles são apenas "Licitantes".

