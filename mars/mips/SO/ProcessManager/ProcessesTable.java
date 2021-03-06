package mars.mips.SO.ProcessManager;
import java.util.ArrayList;
import java.util.List;

import mars.mips.SO.memory.MemoryManager;
import mars.mips.SO.memory.VirtualTable;

public class ProcessesTable {
    private static int ultimoEnderecoPrograma = 0;
    private static List<PCB> listaProcessos = new ArrayList<PCB>();

    public static void adicionarProcesso(PCB novoProcesso) {
        novoProcesso.setEstadoProcesso("Pronto");
        
        if(novoProcesso.getEnderecoFim() == 0) {
            novoProcesso.setEnderecoFim(ultimoEnderecoPrograma);
        }

        int tamanhoLista = listaProcessos.size();
        if(tamanhoLista > 0) {
            PCB penultimoProcesso = listaProcessos.get(tamanhoLista - 1);

            if(penultimoProcesso.getEnderecoFim() == 0) {
                penultimoProcesso.setEnderecoFim(
                    novoProcesso.getEnderecoInicio() - 4
                );
            }
        }
        
        listaProcessos.add(novoProcesso);
    }

    public static void criarProcesso(int enderecoInicio, int prioridade){
        PCB novoProcesso = new PCB(
            enderecoInicio, prioridade, 
            new VirtualTable(MemoryManager.getQntMaxBlocos())
        );
        adicionarProcesso(novoProcesso);
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
