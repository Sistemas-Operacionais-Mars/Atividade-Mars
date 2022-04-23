package mars.mips.SO.ProcessManager;

import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {

    private static Queue<PCB> fila = new PriorityQueue<PCB>();
    
    public static void escalonar(){
        PCB processoExecutando = ProcessesTable.getProcessoExecutando();

        if(!processoExecutando.getEstadoProcesso().equals("Bloqueado")) {
            ProcessesTable.adicionarProcessoPronto(processoExecutando);
        }

        ProcessesTable.setProcessoExecutando(fila.remove());    
    }

    public static void adicionarNaFila(PCB novoPCB){
        fila.add(novoPCB);
    }
}