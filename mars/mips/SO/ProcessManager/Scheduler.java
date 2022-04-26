package mars.mips.SO.ProcessManager;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Scheduler {
    public static void escalonar(){
        PCB processoExecutando = ProcessesTable.removerProcessoTopo();
        
        if(!processoExecutando.getEstadoProcesso().equals("Bloqueado")) {
            ProcessesTable.adicionarProcesso(processoExecutando);
        }
    }


    public static boolean fifo() {
		if(ProcessesTable.getProcessoTopo() != null) { // tem um no topo?
			ProcessesTable.getProcessListReady().add(ProcessesTable.getProcessoTopo()); // pega o processo rodando e coloca em pronto
		}
		if(ProcessesTable.getProcessListReady().size() > 0) {
			((PCB) ProcessesTable.getProcessListReady()).setEstadoProcesso("running"); // mudando o estado do processo
			ProcessesTable.getProcessListReady().remove(ProcessesTable.getProcessoTopo()); // retirando da lista de processo
			return true;
		}
		return false;
	}
	
	public static boolean fixedPriority() {
		if(ProcessesTable.getProcessoTopo() != null) {
			ProcessesTable.getProcessListReady().add(ProcessesTable.getProcessoTopo()); // adicionando processo
		}
		if(ProcessesTable.getProcessListReady().size() > 0) {
			ProcessesTable.getProcessListReady().peek(); // pegando processo por prioridade no topo da queue 
			((PCB) ProcessesTable.getProcessListReady()).setEstadoProcesso("running"); // alta prioridade
			ProcessesTable.getProcessListReady().remove(ProcessesTable.getProcessoTopo()); // retirando da lista de processo de prontos
			return true;
		}
		return false;
	}
	
	public static boolean lottery() {
		if(ProcessesTable.getProcessoTopo() != null) {
			ProcessesTable.getProcessListReady().add(ProcessesTable.getProcessoTopo()); // adicionando processo
		}
		
		if(ProcessesTable.getProcessListReady().size() > 0) {
			// fazer processo na sorte da loteria
			Random r = new Random();
			
			// escolhendo elemento aleatoriamente
			int element = r.nextInt(ProcessesTable.getProcessListReady().size());
						
			((PCB) ProcessesTable.getProcessListReady()).setEstadoProcesso("running");
			ProcessesTable.getProcessListReady().remove(element); // retirando elemento na loteria
			return true;
		}
		return false;
	}
}