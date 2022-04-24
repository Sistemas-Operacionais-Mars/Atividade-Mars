package mars.mips.SO.ProcessManager;

import java.util.List;

public class Semaphore {
    private int valor;
    private int enderecoSemaforo;

    private List<PCB> processosBloqueados;

    public Semaphore(int valor, int enderecoSemaforo) {
        this.valor = valor;
        this.enderecoSemaforo = enderecoSemaforo;
    }

    public void eliminarLista() {
        processosBloqueados.clear();
    }

    public void incrementarValor() {
        if(processosBloqueados.size() > 0) {
            PCB processoPronto = processosBloqueados.remove(0);
            ProcessesTable.adicionarProcesso(processoPronto);
        } else ++valor;
    }

    public void decrementarValor() {
        if(valor > 0) --valor;
        else {
            PCB processoExecutando = ProcessesTable.getProcessoTopo();
            processoExecutando.setEstadoProcesso("Bloqueado");
            processosBloqueados.add(processoExecutando);

            Scheduler.escalonar();
        }
    }

    public int getValor() {
        return valor;
    }

    public int getEnderecoSemaforo() {
        return enderecoSemaforo;
    }
}
