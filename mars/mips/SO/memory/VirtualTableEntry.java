package mars.mips.SO.memory;

public class VirtualTableEntry {
    
 //Criar uma classe "entrada da tabela virtual" com, no mínimo, os atributos:
 // Página Referenciada, Página Modificada, Proteção (bits R, W e X), bit Presente/ausente
 // e Número da moldura mapeada
 
 // oq seria pagina refernciada e modificada? string?
    String referencedPage;
    String modifiedPage; 

// oq seria a proteção?  
    String r;
    String w; 
    String x;

 //presente ausente => provavelmete boolean
    boolean PresentAway;

 // numero da moldura => int
    int NumMoldura;

 //getters and setters
 public String getReferencedPage() {
        return referencedPage;
    }

    public void setReferencedPage(String referencedPage) {
        this.referencedPage = referencedPage;
    }

    public String getModifiedPage() {
        return modifiedPage;
    }

    public void setModifiedPage(String modifiedPage) {
        this.modifiedPage = modifiedPage;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public boolean isPresentAway() {
        return PresentAway;
    }

    public void setPresentAway(boolean presentAway) {
        PresentAway = presentAway;
    }

    public int getNumMoldura() {
        return NumMoldura;
    }

    public void setNumMoldura(int numMoldura) {
        NumMoldura = numMoldura;
    }

}
