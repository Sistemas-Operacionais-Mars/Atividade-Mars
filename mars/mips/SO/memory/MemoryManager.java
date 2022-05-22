package mars.mips.SO.memory;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;
import mars.simulator.Simulator;
import mars.util.SystemIO;

public class MemoryManager {
    private static int tamPagVirtual = 32;
    private static int qntMaxBlocos = 4;
    private static String algoritmoSubstituicao;
    
    public static void verificarMemoria() {
        PCB procExec = ProcessesTable.getProcessoExecutando();
        if(procExec == null) return;

        int pc = RegisterFile.getProgramCounter();
        if (
            procExec.getEnderecoInicio() > pc || 
            procExec.getEnderecoFim() < pc
        ) {
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
