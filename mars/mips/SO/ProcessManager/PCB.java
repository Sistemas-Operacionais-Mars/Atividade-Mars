package mars.mips.SO.ProcessManager;
import mars.mips.hardware.RegisterFile;
import java.util.Random;

public class PCB {
    private int enderecoInicio;
    private int PID;
    private int prioridade;
    private String estadoProcesso;
    private int[] valorRegistros;
    private static final int numeroDeRegistradores = 34;
    
    //registradores de limite da memória
    private int regSuperior;
    private int regInferior;
    
    public PCB(int enderecoInicio, int enderecoFim, int prioridade){
        valorRegistros = new int[numeroDeRegistradores];
        this.enderecoInicio = enderecoInicio;

        //atribui valor do endereço inicial ao registrador superior
        setRegSuperior(enderecoInicio);
        //atribui valor do endereço final ao registrador inferior
        setRegInferior(enderecoFim);

        this.prioridade = prioridade;
        estadoProcesso = "Pronto";
        PID = new Random().nextInt(Integer.MAX_VALUE);
    }

    public int getRegSuperior() {
        return regSuperior;
    }

    public void setRegSuperior(int regSuperior) {
        this.regSuperior = regSuperior;
    }

    public int getRegInferior() {
        return regInferior;
    }

    public void setRegInferior(int regInferior) {
        this.regInferior = regInferior;
    }

    public void copiarRegistradoresParaPCB(){
        for(int i = 0; i < numeroDeRegistradores; i++){
            if (i == 31) valorRegistros[i] = RegisterFile.getProgramCounter();
            else if (i >= 32) valorRegistros[i] = RegisterFile.getValue(i+1);
            else valorRegistros[i] = RegisterFile.getValue(i);
        }
    }

    public void copiarPCBparaRegistradores(){
        for(int i = 0; i < numeroDeRegistradores; i++) {
            if(i == 31) continue;
                
            if (i >= 32) RegisterFile.updateRegister(i+1, valorRegistros[i]);
            else RegisterFile.updateRegister(i, valorRegistros[i]);
        }

        int pc = valorRegistros[31] == 0 ? enderecoInicio : valorRegistros[31];
        RegisterFile.setProgramCounter(pc);
    }

    public int getNumeroDeRegistradores() {
        return numeroDeRegistradores;
    }

    public int[] getValorRegistros() {
        return valorRegistros;
    }

    public void setValorRegistros(int[] valorRegistros) {
        this.valorRegistros = valorRegistros;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(String estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    public int getEnderecoInicio() {
        return enderecoInicio;
    }

    public void setEnderecoInicio(int enderecoInicio) {
        this.enderecoInicio = enderecoInicio;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

}