package mars.mips.SO.ProcessManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import mars.mips.hardware.RegisterFile;

public class Scheduler {
	private String tipoEscalonamento;

	private Queue<PCB> baixaPrioridade = null;
	private Queue<PCB> mediaPrioridade = null;
	private Queue<PCB> altaPrioridade = null;

	public Scheduler(String tipoEscalonamento) {
		this.tipoEscalonamento = tipoEscalonamento;
	}

	// Utilizado para setar processo para o início (Executando).
	private void trocarPosicoesDaLista(int posicao) {
		Collections.swap(ProcessesTable.getListaProcessos(), 0, posicao);
	}

    public void escalonar(boolean encerrarProcesso) {
		if(ProcessesTable.getTamanhoLista() == 0) return;
		PCB processoAntigo = ProcessesTable.getProcessoExecutando();

		boolean unicoElemento = ProcessesTable.getTamanhoLista() == 1;
		if(!unicoElemento) {
			RegisterFile.setProgramCounter(processoAntigo.getEnderecoInicio());
			switch (tipoEscalonamento) {
				case "FIFO": {
					fifo(processoAntigo);
					break;
				}
			
				case "PFixa": {
					prioridadeFixa();
					break;
				}
			
				case "Loteria": {
					loteria();
					break;
				}
					
				default: System.out.println("Tipo escolhido inválido");
			}	
		}

		if(processoAntigo != null) {
			if(encerrarProcesso && !unicoElemento) 
				ProcessesTable.removerProcesso(processoAntigo);
			else {
				processoAntigo.setEstadoProcesso("Pronto");
				processoAntigo.copiarRegistradoresParaPCB();
			}
		}

		PCB processoExecutando = ProcessesTable.getProcessoTopo();
		processoExecutando.setEstadoProcesso("Executando");
        processoExecutando.copiarPCBparaRegistradores();
    }

	// Funções FIFO.
    private void fifo(PCB processoAntigo) {
		if(processoAntigo != null) {
			ProcessesTable.removerProcessoTopo();
			ProcessesTable.adicionarProcesso(processoAntigo);
		}
	}

	// Funções prioridade fixa.
	private void adicionarNaFilaDePrioridade(PCB processo) {
		switch(processo.getPrioridade()) {
			case 0: {
				if(baixaPrioridade == null) {
					baixaPrioridade = new LinkedList<PCB>();
				}

				baixaPrioridade.add(processo);
				break;
			}
			case 1: {
				if(mediaPrioridade == null) {
					mediaPrioridade = new LinkedList<PCB>();
				}

				mediaPrioridade.add(processo);
				break;
			}
			case 2: {
				if(altaPrioridade == null) {
					altaPrioridade = new LinkedList<PCB>();
				}

				altaPrioridade.add(processo);
				break;
			}
			default: return;
		}
	}
	
	private void criarFilasDePrioridade() {
		List<PCB> listaProcessos = ProcessesTable.getListaProcessos();

		for(PCB processo : listaProcessos) {
			adicionarNaFilaDePrioridade(processo);
		}
	}

	private PCB obterProcessoPrioritario(Queue<PCB> filaPrioridade) {
		if(filaPrioridade == null) return null;

		PCB topoPrioridade = filaPrioridade.peek();
		return topoPrioridade;
	}

	private void prioridadeFixa() {
		criarFilasDePrioridade();
		PCB maiorPrioridade = obterProcessoPrioritario(altaPrioridade);

		if(maiorPrioridade == null) {
			maiorPrioridade = obterProcessoPrioritario(mediaPrioridade);

			if(maiorPrioridade == null) {
				maiorPrioridade = obterProcessoPrioritario(baixaPrioridade);
			}
		}

		int indexProcessoPrioritario = ProcessesTable.getListaProcessos().indexOf(maiorPrioridade);
		trocarPosicoesDaLista(indexProcessoPrioritario);
	}
	
	// Funções loteria.
	private void loteria() {
		Random random = new Random();
		int valorAleatorio = random.nextInt(ProcessesTable.getTamanhoLista()-1)+1;

		trocarPosicoesDaLista(valorAleatorio);
	}
}