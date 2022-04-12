package mars.mips.SO.ProcessManager;

import java.util.List;

public class ProcessesTable {
    private static List<PCB> ProcessosProntos;
    private static PCB ProcessoExecutando;
    
    public static void criarProcesso(int enderecoInicio){
        PCB novoProcesso = new PCB(enderecoInicio);
        ProcessosProntos.add(novoProcesso);
    }

    public static void attProcExecutando(){
        ProcessoExecutando.copiarRegistradoresParaPCB();
        ProcessoExecutando.setEstadoProcesso("Pronto");
    }
}
