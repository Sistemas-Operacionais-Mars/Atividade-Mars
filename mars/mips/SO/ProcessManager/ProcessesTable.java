package mars.mips.SO.ProcessManager;
import java.util.ArrayList;
import java.util.List;

public class ProcessesTable {
    private static List<PCB> listaProcessos = new ArrayList<PCB>();

    public static void adicionarProcesso(PCB processo) {
        processo.setEstadoProcesso("Pronto");
        listaProcessos.add(processo);
    }

    public static void criarProcesso(int enderecoInicio, int prioridade){
        PCB novoProcesso = new PCB(enderecoInicio, prioridade);
        adicionarProcesso(novoProcesso);
    }

    public static int getTamanhoLista() {
        return listaProcessos.size();
    }

    public static PCB getProcessoTopo() {
        return listaProcessos.get(0);
    }

    public static PCB getProcessoExecutando() {
        PCB processoTopo = getProcessoTopo();

        if(
            processoTopo == null || 
            !processoTopo.getEstadoProcesso().equals("Executando")
        ) {
            return null;
        }

        return processoTopo;
    }

    public static boolean removerProcesso(PCB processo) {
        PCB processoTopo = getProcessoTopo();

        if(processo == processoTopo) {
            removerProcessoTopo();
            return true;
        }

        return listaProcessos.remove(processo);
    }

    public static PCB removerProcessoTopo() {
        PCB processoRemovido = listaProcessos.remove(0);
        return processoRemovido;
    }

    public static List<PCB> getListaProcessos() {
        return listaProcessos;
    }
}
