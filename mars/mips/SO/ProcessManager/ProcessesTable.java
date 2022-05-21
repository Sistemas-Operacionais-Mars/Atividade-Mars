package mars.mips.SO.ProcessManager;
import java.util.ArrayList;
import java.util.List;

public class ProcessesTable {
    private static int ultimoEnderecoPrograma = 0;
    private static List<PCB> listaProcessos = new ArrayList<PCB>();

    public static void adicionarProcesso(PCB novoProcesso) {
        novoProcesso.setEstadoProcesso("Pronto");        
        listaProcessos.add(novoProcesso);
        resetarLimitesInferiores();
    }

    public static void criarProcesso(int enderecoInicio, int prioridade){
        PCB novoProcesso = new PCB(enderecoInicio, prioridade);
        adicionarProcesso(novoProcesso);
    }

    public static void resetarLimitesInferiores() {
        int tamanhoLista = getTamanhoLista();

        for(int ind=0 ; ind<tamanhoLista ; ind++) {
            PCB processoIterado = listaProcessos.get(ind);

            if(ind+1 != tamanhoLista) {
                PCB proximoProcesso = listaProcessos.get(ind+1);
                processoIterado.setEnderecoFim(proximoProcesso.getEnderecoInicio());
            } else processoIterado.setEnderecoFim(ultimoEnderecoPrograma);
        }
    }

    public static int getTamanhoLista() {
        return listaProcessos.size();
    }

    public static PCB getProcessoTopo() {
        return listaProcessos.size() == 0 ? null : listaProcessos.get(0);
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

    public static int getUltimoEnderecoPrograma() {
        return ultimoEnderecoPrograma;
    }

    public static void setUltimoEnderecoPrograma(
        int ultimoEnderecoProgramaRecebido
    ) {
        ultimoEnderecoPrograma = ultimoEnderecoProgramaRecebido;
    }
}
