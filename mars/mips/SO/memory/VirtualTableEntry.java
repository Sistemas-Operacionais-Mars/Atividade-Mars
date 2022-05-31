package mars.mips.SO.memory;

public class VirtualTableEntry {
    
    //Criar uma classe "entrada da tabela virtual" com, no mínimo, os atributos:
    // Página Referenciada, Página Modificada, Proteção (bits R, W e X), bit Presente/ausente
    // e Número da moldura mapeada

    //Obs.: variáveis que representam bits serão do tipo boolean
    
    //------------------------------------------------------
    // numero da moldura mapeada / numero do quadro de pagina
    /*
    O campo mais importante é o Número do quadro de página. 
    Afinal, a meta do mapeamento de páginas é localizar 
    esse valor.
    */
    int numMoldura;

    //------------------------------------------------------
    // bits que verificam se está referenciada ou modificada 

    /*Ao escrever na página, o hardware automaticamente 
    configura o bit Modificada. Esse bit é importante 
    quando o sistema operacional decide recuperar um 
    quadro de página. Se a página dentro do quadro foi 
    modificada (isto é, está “suja”), ela também deve 
    ser atualizada no disco. Se ela não foi modificada 
    (isto é, está “limpa”), ela pode ser abandonada, 
    tendo em vista que a cópia em disco ainda é válida. 
    O bit às vezes é chamado de bit sujo, já que ele 
    reflete o estado da página.*/ 
    boolean modifiedPage;     

    /* 
    O bit Referenciada é configurado sempre que uma 
    página é referenciada, seja para leitura ou para escrita. 
    Seu valor é usado para ajudar o sistema operacional a 
    escolher uma página a ser substituída quando uma falta 
    de página ocorrer. Páginas que não estão sendo usadas 
    são candidatas muito melhores do que as páginas que 
    estão sendo, e esse bit desempenha um papel importante 
    em vários dos algoritmos de substituição de páginas
    */
    boolean referencedPage;
        
    //------------------------------------------------------
    // bits de proteção
    /*Os bits Proteção dizem quais tipos de acesso são 
    permitidos. Na forma mais simples, esse campo contém 
    1 bit, com 0 para ler/escrever e 1 para ler somente. 
    Um arranjo mais sofisticado é ter 3 bits, para 
    habilitar a leitura, escrita e execução da página*/  
    boolean r;
    boolean w; 
    boolean x;

    //------------------------------------------------------
    // presente/ausente
    /*Se esse bit for 1, a entrada é válida e pode ser usada. 
    Se ele for 0, a página virtual à qual a entrada pertence 
    não está atualmente na memória*/
    boolean PresentAway;

    //getters and setters
    public boolean getReferencedPage() {
        return referencedPage;
    }

    public void setReferencedPage(boolean referencedPage) {
        this.referencedPage = referencedPage;
    }

    public boolean getModifiedPage() {
        return modifiedPage;
    }

    public void setModifiedPage(boolean modifiedPage) {
        this.modifiedPage = modifiedPage;
    }

    public boolean getR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean getW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public boolean getX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

    public boolean isPresentAway() {
        return PresentAway;
    }

    public void setPresentAway(boolean presentAway) {
        PresentAway = presentAway;
    }

    public int getNumMoldura() {
        return numMoldura;
    }

    public void setNumMoldura(int numMoldura) {
        this.numMoldura = numMoldura;
    }

}
