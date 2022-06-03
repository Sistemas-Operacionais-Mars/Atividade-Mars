package mars.mips.SO.memory;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;
import mars.simulator.Simulator;
import mars.util.SystemIO;

public class MemoryManager {
    /*Foram utilizados valores padrões encontrados 
    no livro de Tanenbaum para os possíveis tamanhos 
    de página. A de 4kb é uma das mais usadas no mercado*/
    private static int tamPagVirtual = 4; //4kb, 8kb, 16kb, 32kb, 64kb

    //-----------------------------------------    
    /*Quantidade máxima de molduras na memória física.
    A quantidade de molduras também pode ser entendida 
    como a quantidade máxima de páginas virtuais mapeadas 
    permitida.*/
    private static int qntMaxBlocos = 16; // 4, 8, 16, 32
    
    //-----------------------------------------
    private static String algoritmoSubstituicao = "FIFO";

    public static void verificarMemoria() {
        PCB procExec = ProcessesTable.getProcessoExecutando();
        if(procExec == null) return;

        int pc = RegisterFile.getProgramCounter();
        if (procExec.getEnderecoInicio() > pc || procExec.getEnderecoFim() < pc){
            SystemIO.printString(
                "Os limites de endereço do processo em execução, que possui limite superior: " + 
                procExec.getEnderecoInicio() + " e limite inferior: " +
                procExec.getEnderecoFim() + " estão fora da área de acesso.\n"
            );
            SystemIO.printString("Endereço da tentativa de acesso: " + pc);

            System.out.println(
                "Os limites de endereço do processo em execução, que possui limite superior: " + 
                procExec.getEnderecoInicio() + " e limite inferior: " +
                procExec.getEnderecoFim() + " estão fora da área de acesso."
            );
            System.out.println("Endereço da tentativa de acesso: " + pc);

            Simulator.getInstance().stopExecution(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                }
            });
        } else {
            /*A MMU deve verificar os campos “índice” 
            e “deslocamento” do endereço virtual para 
            mapeá-lo para endereço físico, para isso 
            é necessário usar o endereço armazenado 
            em PC e dividi-lo nos 2 campos mencionados
            */

            /*Primeiro atribui o endereço atual 
            armazenado no pc para o endereço virtual*/
            String enderecoVirtual = Integer.toBinaryString(pc);
            procExec.getTabelaVirtual().adicionarElementoMemoria(
                pc, Integer.valueOf(enderecoVirtual.substring(0, 5))
            );
        }
    }

    public static int getTamPagVirtual() {
        return tamPagVirtual;
    }

    public static void setTamPagVirtual(int tamPagVirtual) {
        MemoryManager.tamPagVirtual = tamPagVirtual;
    }

    public static int getQntMaxBlocos() {
        return qntMaxBlocos;
    }

    public static void setQntMaxBlocos(int qntMaxBlocos) {
        MemoryManager.qntMaxBlocos = qntMaxBlocos;
    }

    public static String getAlgoritmoSubstituicao() {
        return algoritmoSubstituicao;
    }

    public static void setAlgoritmoSubstituicao(String algoritmoSubstituicao) {
        MemoryManager.algoritmoSubstituicao = algoritmoSubstituicao;
    }
}
