package mars.mips.SO.ProcessManager;

public class Scheduler {
    public static void escalonar(){
        PCB processoExecutando = ProcessesTable.removerProcessoTopo();
        
        if(!processoExecutando.getEstadoProcesso().equals("Bloqueado")) {
            ProcessesTable.adicionarProcesso(processoExecutando);
        }
    }
}