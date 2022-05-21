package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

public class MemoryManager {
    public static int tamPagVirtual = 32;//tamanho da página virtual
    public static int qntMaxBlocos = 4;//quantidade máxima de blocos de alocação por processo
    public static String algSubstituicao;//NRU, FIFO, SECOND_CHANCE, LRU
    
    public static int pc;//variavel que receberá o valor do registrador do program counter
    public static PCB procExec;//variavel que receberá o pcb em estado "executando" na tabela de processos 

    public static void verificarMemoria(){
        pc = RegisterFile.getProgramCounter();
        procExec = ProcessesTable.getProcessoExecutando();

        if(procExec.getRegSuperior() > pc) {
            SystemIO.printString("O endereço do limite superior " + procExec.getRegSuperior() + " está fora da área de acesso!");
            System.exit(0);//finaliza simulação
        }else if(procExec.getRegInferior() < pc) {
            SystemIO.printString("O endereço do limite inferior " + procExec.getRegInferior() + " está fora da área de acesso!");
            System.exit(0);//finaliza simulação
        }
    }
}
