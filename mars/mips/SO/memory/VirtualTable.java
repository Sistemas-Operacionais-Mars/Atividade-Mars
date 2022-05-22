package mars.mips.SO.memory;

import java.util.ArrayList;
import java.util.List;

public class VirtualTable {
    
    // criar uma classe "tabela virtual" com uma coleção de "entradas da tabela virtual"

    // cada processo terá sua própria tabela virtual, então pode-se optar por implementar
    // um objeto "tabela virtual" para cada processo ou 1 único objeto que reserve uma 
    // quantidade fixa de "entradas da tabela" para cada processo
    
    private static List<VirtualTableEntry> tabelaVirtual = new ArrayList<VirtualTableEntry>();
	private static int[] memory = new int[MemoryManager.getTamPagVirtual()];
}
