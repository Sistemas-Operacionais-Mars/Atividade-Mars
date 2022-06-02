package mars.mips.SO.memory;

import java.util.ArrayList;
import java.util.List;

public class VirtualTable {
    
    // criar uma classe "tabela virtual" com uma coleção de "entradas da tabela virtual"

    // cada processo terá sua própria tabela virtual, então pode-se optar por implementar
    // um objeto "tabela virtual" para cada processo ou 1 único objeto que reserve uma 
    // quantidade fixa de "entradas da tabela" para cada processo
    
    private static List<VirtualTableEntry> entradasTabela = new ArrayList<VirtualTableEntry>();
	
    //---------------------------------------------------
    /*vetor limitado que representa a memória física. 
    Utiliza a variável qntMaximaBlocos para definir 
    a quantidade de molduras na memória.*/ 
    private static int[] memoriaFisica = new int[MemoryManager.getQntMaxBlocos()];
    
    
    //getters e setters
    public static List<VirtualTableEntry> getEntradasTabela() {
        return entradasTabela;
    }
    public static void setEntradasTabela(List<VirtualTableEntry> entradasTabela) {
        VirtualTable.entradasTabela = entradasTabela;
    }
    
    public static int[] getMemoriaFisica() {
        return memoriaFisica;
    }
    public static void setMemoriaFisica(int[] memoriaFisica) {
        VirtualTable.memoriaFisica = memoriaFisica;
    }
    
}
