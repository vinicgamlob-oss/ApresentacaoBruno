/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentaçãobrunogof;


import java.util.ArrayList;
import java.util.List;

// Interface Observer
interface Observer {
    void update(String mensagem);
}

// Interface Subject
interface Subject {
    void adicionarObserver(Observer o);
    void removerObserver(Observer o);
    void notificarObservers();
}

// ConcreteSubject
class Leilao implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private double maiorLance;

    @Override
    public void adicionarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removerObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer o : observers) {
            o.update("Novo lance: R$ " + maiorLance);
        }
    }

    public void novoLance(double valor) {
        this.maiorLance = valor;
        System.out.println("\n Novo lance registrado: R$ " + valor);
        notificarObservers();
    }
}

// ConcreteObserver
class Usuario implements Observer {
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    @Override
    public void update(String mensagem) {
        System.out.println(nome + " recebeu notificacao " + mensagem);
    }
}

// Classe principal
public class ApresentaçãobrunoGof {
    public static void main(String[] args) {

        Leilao leilao = new Leilao();

        Usuario u1 = new Usuario("Lis");
        Usuario u2 = new Usuario("Joao");
        Usuario u3 = new Usuario("Maria");

        // Inscrevendo observadores
        leilao.adicionarObserver(u1);
        leilao.adicionarObserver(u2);
        leilao.adicionarObserver(u3);

        // Simulando lances
        leilao.novoLance(100);
        leilao.novoLance(150);

        // Removendo um usuário
        leilao.removerObserver(u2);

        leilao.novoLance(200);
    }
}

