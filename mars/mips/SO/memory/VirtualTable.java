package mars.mips.SO.memory;

import java.util.ArrayList;
import java.util.List;

public class VirtualTable {
    private List<VirtualTableEntry> tabelaEntradas;
	
    //---------------------------------------------------
    /*vetor limitado que representa a memória física. 
    Utiliza a variável qntMaximaBlocos para definir 
    a quantidade de molduras na memória.*/ 
    private List<Integer> memoriaFisica;

    public VirtualTable(int quantidadeMaximaBlocos) {
        memoriaFisica = new ArrayList<>(quantidadeMaximaBlocos);
        tabelaEntradas = new ArrayList<>(quantidadeMaximaBlocos);
    }

    public void adicionarElementoTabela(VirtualTableEntry elemento) {
        tabelaEntradas.add(elemento);
    }

    public void removerElementoTabela(VirtualTableEntry elemento) {
        tabelaEntradas.remove(elemento);
    }

    public List<VirtualTableEntry> getTabelaEntradas() {
        return tabelaEntradas;
    }

    public void setTabelaEntradas(List<VirtualTableEntry> tabelaEntradas) {
        this.tabelaEntradas = tabelaEntradas;
    }
    
    public List<Integer> getMemoriaFisica() {
        return memoriaFisica;
    }

    public void setMemoriaFisica(List<Integer> memoriaFisica) {
        this.memoriaFisica = memoriaFisica;
    }
}
