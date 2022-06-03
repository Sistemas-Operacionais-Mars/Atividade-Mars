package mars.mips.SO.memory;

import java.util.Date;

public class VirtualTableEntry {
    private int numMolduraMapeada;
    private boolean paginaModificada;     
    private boolean paginaReferenciada;
 
    private boolean r;
    private boolean w; 
    private boolean x;

    private boolean paginaPresente;
    private Date ultimaUtilizacao;

    public VirtualTableEntry(
        int numMolduraMapeada, boolean paginaModificada,
        boolean paginaReferenciada
    ) {
        this.numMolduraMapeada = numMolduraMapeada;
        this.paginaModificada = paginaModificada;
        this.paginaReferenciada = paginaReferenciada;
        ultimaUtilizacao = new Date();
    }

    public boolean getPaginaReferenciada() {
        return paginaReferenciada;
    }

    public void setPaginaReferenciada(boolean paginaReferenciada) {
        this.paginaReferenciada = paginaReferenciada;
    }

    public boolean getPaginaModificada() {
        return paginaModificada;
    }

    public void setPaginaModificada(boolean paginaModificada) {
        this.paginaModificada = paginaModificada;
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

    public boolean getPaginaPresente() {
        return paginaPresente;
    }

    public void setPaginaPresente(boolean paginaPresente) {
        this.paginaPresente = paginaPresente;
        if(paginaPresente) ultimaUtilizacao = new Date();
    }

    public int getNumMolduraMapeada() {
        return numMolduraMapeada;
    }

    public void setNumMolduraMapeada(int numMolduraMapeada) {
        this.numMolduraMapeada = numMolduraMapeada;
    }

    public Date getUltimaUtilizacao() {
        return ultimaUtilizacao;
    }
}
