package mars.mips.SO.ProcessManager;

import java.util.List;

public class SemaphoreList {
    private static List<Semaphore> semaforos;

    public static void criarSemaforo(int valor, int enderecoSemaforo) {
        Semaphore semaforo = new Semaphore(valor, enderecoSemaforo);
        semaforos.add(semaforo);
    }

    public static Semaphore obterSemaforoPorEndereco(int endereco) throws Exception {
        for(Semaphore semaforo : semaforos) {
            if(semaforo.getEnderecoSemaforo() == endereco) {
                return semaforo;
            }
        }

        throw new Exception("Endereço não associado a um semáforo.");
    }
}
