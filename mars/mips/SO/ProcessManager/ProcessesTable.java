package mars.mips.SO.ProcessManager;
import java.util.LinkedList;
import java.util.Queue;

public class ProcessesTable {
    private static Queue<PCB> filaProcessos = new LinkedList<PCB>();

    public static void adicionarProcesso(PCB processo) {
        if(filaProcessos.size() == 0) {
            processo.setEstadoProcesso("Executando");
        } else processo.setEstadoProcesso("Pronto");

        filaProcessos.add(processo);
    }

    public static void criarProcesso(int enderecoInicio){
        PCB novoProcesso = new PCB(enderecoInicio);
        adicionarProcesso(novoProcesso);
    }

    public static PCB getProcessoTopo() {
        return filaProcessos.peek();
    }

    public static PCB removerProcessoTopo() {
        PCB processoRemovido = filaProcessos.remove();

        if(filaProcessos.size() != 0) {
            PCB processoTopo = getProcessoTopo();
            processoTopo.setEstadoProcesso("Executando");
        }

        return processoRemovido;
    }
}
