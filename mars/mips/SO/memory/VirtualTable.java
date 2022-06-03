package mars.mips.SO.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class VirtualTable {    
    private static List<VirtualTableEntry> tabelaEntradas = new ArrayList<VirtualTableEntry>();
	
    //---------------------------------------------------
    /*vetor limitado que representa a memória física. 
    Utiliza a variável qntMaximaBlocos para definir 
    a quantidade de molduras na memória.*/ 
    private static int[] memoriaFisica = new int[MemoryManager.getQntMaxBlocos()];
    
    public static VirtualTableEntry obterElementoFIFO() {
        return tabelaEntradas.get(0);
    }

    public static VirtualTableEntry obterElementoNRU() {
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

    public static VirtualTableEntry obterElementoSegundaChance() {
        VirtualTableEntry elementoIterativo = obterElementoFIFO();

        while(elementoIterativo.getPaginaReferenciada()) {
            tabelaEntradas.remove(elementoIterativo);
            tabelaEntradas.add(elementoIterativo);
            elementoIterativo = obterElementoFIFO();
        }

        return elementoIterativo;
    }

    public static VirtualTableEntry obterElementoLRU() {
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

    public static void adicionarElementoTabela(VirtualTableEntry elemento) {
        tabelaEntradas.add(elemento);
    }

    public static void removerElementoTabela(VirtualTableEntry elemento) {
        tabelaEntradas.remove(elemento);
    }

    public static List<VirtualTableEntry> getTabelaEntradas() {
        return tabelaEntradas;
    }

    public static void setTabelaEntradas(List<VirtualTableEntry> tabelaEntradas) {
        VirtualTable.tabelaEntradas = tabelaEntradas;
    }
    
    public static int[] getMemoriaFisica() {
        return memoriaFisica;
    }

    public static void setMemoriaFisica(int[] memoriaFisica) {
        VirtualTable.memoriaFisica = memoriaFisica;
    }
}
