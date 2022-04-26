package mars.mips.SO.ProcessManager;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.transform.Source;

import mars.mips.SO.*;
import mars.mips.hardware.RegisterFile;

public class ProcessesTable {
    private static Queue<PCB> filaProcessos = new LinkedList<PCB>();

    public static Queue<PCB> getProcessListReady() {
		return filaProcessos;
	}
	public static void setProcessListReady(Queue<PCB> processListReady) {
		ProcessesTable.filaProcessos = processListReady;
	} 
    
    public static void adicionarProcesso(PCB processo) {
        processo.setEstadoProcesso("Pronto");
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
            processoTopo.copiarPCBparaRegistradores();
        }

        return processoRemovido;
    }

    public static String algoritmoPadrão = "FIFO"; // default

    
	public static String getTypeAlgoritmo() {
		return algoritmoPadrão;
	}

	public static void setTypeAlgoritmo(String x) {
		ProcessesTable.algoritmoPadrão = x;
	}

    private static PCB rodando; // processo rodando

    public static void processChange(String metodo) {
		
        if(getProcessoTopo() != null) { // processo sendo executado
			System.out.println("Salvando");
			
			rodando.setEstadoProcesso("ready"); // mudando meu estado
			rodando.setEnderecoInicio(RegisterFile.getProgramCounter());
			
			rodando.copiarRegistradoresParaPCB();  // salvando contexto
		}
		
        switch (metodo) {
            case "FIFO":
			
			if(Scheduler.fifo()) {
				RegisterFile.setProgramCounter(rodando.getNumeroDeRegistradores());
				
				System.out.println("Indo para: " + RegisterFile.getProgramCounter());
				rodando.copiarPCBparaRegistradores();
			}
                break;
        
            case "PFixa":    
            if(Scheduler.fixedPriority()) {
				for(int i = 0;  i < rodando.getValorRegistros().length; i++) {
					RegisterFile.updateRegister(i, rodando.getEnderecoInicio());
				}
			}
			if(rodando != null) {
				RegisterFile.setProgramCounter(rodando.getEnderecoInicio());
			}
		
        case "Loteria": 
        if(Scheduler.lottery()) {
            for(int i = 0;  i < rodando.getValorRegistros().length; i++) {
                RegisterFile.updateRegister(i, rodando.getEnderecoInicio());
            }
        }
        if(rodando != null) {
            RegisterFile.setProgramCounter(rodando.getEnderecoInicio());
        }
            default:
            System.out.println("indo parar aqui");
                break;
         }
    }
}
        
		

