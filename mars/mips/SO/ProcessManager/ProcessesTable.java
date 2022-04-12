package mars.mips.SO.ProcessManager;
import java.util.ArrayList;
import java.util.List;

public class ProcessesTable {
    private static List<PCB> ProcessosProntos = new ArrayList<PCB>();
    private static PCB processoExecutando;
    
    public static void criarProcesso(int enderecoInicio){
        PCB novoProcesso = new PCB(enderecoInicio);
        ProcessosProntos.add(novoProcesso);
        Scheduler.escalonar();
    }

    public static PCB getProcessoExecutando() {
        return processoExecutando;
    }

    public static void setProcessoExecutando(PCB pcb) {
        processoExecutando = pcb;
        processoExecutando.setEstadoProcesso("Executando");
    }

}
