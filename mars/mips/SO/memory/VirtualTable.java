package mars.mips.SO.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
        tabelaEntradas = new ArrayList<>();
    }

    private VirtualTableEntry obterElementoFIFO() {
        return tabelaEntradas.get(0);
    }

    private VirtualTableEntry obterElementoNRU() {
        List<VirtualTableEntry> tabelaProvisoria = new ArrayList<>();
        tabelaProvisoria.addAll(tabelaEntradas);

        Collections.sort(tabelaProvisoria, new Comparator<VirtualTableEntry>() {
            @Override
            public int compare(VirtualTableEntry left, VirtualTableEntry right) {
                int leftValue = (left.getPaginaReferenciada() ? 1 : 0) + 
                (left.getPaginaModificada() ? 1 : 0);

                int rightValue = (right.getPaginaReferenciada() ? 1 : 0) + 
                (right.getPaginaModificada() ? 1 : 0);

                if(leftValue == rightValue) return 0;
                if(leftValue < rightValue) return -1;
                return 1;
            }
        });

        return tabelaProvisoria.get(0);
    }

    private VirtualTableEntry obterElementoSegundaChance() {
        VirtualTableEntry elementoIterativo = obterElementoFIFO();

        while(elementoIterativo.getPaginaReferenciada()) {
            tabelaEntradas.remove(elementoIterativo);
            tabelaEntradas.add(elementoIterativo);
            elementoIterativo = obterElementoFIFO();
        }

        return elementoIterativo;
    }

    private VirtualTableEntry obterElementoLRU() {
        List<VirtualTableEntry> tabelaProvisoria = new ArrayList<>();
        tabelaProvisoria.addAll(tabelaEntradas);

        Collections.sort(tabelaProvisoria, new Comparator<VirtualTableEntry>() {
            @Override
            public int compare(VirtualTableEntry left, VirtualTableEntry right) {
                Date leftValue = left.getUltimaUtilizacao();
                Date rightValue = right.getUltimaUtilizacao();

                return leftValue.compareTo(rightValue);
            }
        });

        return tabelaProvisoria.get(0);
    }

    private VirtualTableEntry obterElementoParaSubstituir() {
        switch(MemoryManager.getAlgoritmoSubstituicao()) {
            case "NRU": return obterElementoNRU();
            case "FIFO": return obterElementoFIFO();
            case "Segunda Chance": return obterElementoSegundaChance();
            case "LRU": return obterElementoLRU();
            default: return obterElementoFIFO();
        }
    }

    public void adicionarElementoMemoria(int memoria, int memoriaVirtual) {
        int tamanhoMemoriaFisica = memoriaFisica.size();

        if(tamanhoMemoriaFisica == MemoryManager.getQntMaxBlocos()) {
            VirtualTableEntry novaEntrada = new VirtualTableEntry(
                memoriaVirtual, false, false
            );

            VirtualTableEntry elementoParaSubstituir = obterElementoParaSubstituir();
            int memoriaFisicaEncontrada = MemoryManagementUnit.traduzirParaEnderecoFisico(
                elementoParaSubstituir.getNumMolduraMapeada()
            );
            
            memoriaFisica.remove(memoriaFisicaEncontrada);
            tabelaEntradas.remove(elementoParaSubstituir);
        } else memoriaFisica.add(memoria);
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
