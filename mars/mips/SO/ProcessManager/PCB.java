package mars.mips.SO.ProcessManager;
import mars.mips.hardware.RegisterFile;
import java.util.Random;

public class PCB {
    
    private int enderecoInicio;
    private int PID;
    private String estadoProcesso;
    private int[] valorRegistros;
    private static final int numeroDeRegistradores = 34;

    public PCB(int enderecoInicio){
        valorRegistros = new int [ numeroDeRegistradores ];
        setEnderecoInicio( enderecoInicio );
        setEstadoProcesso( "Pronto" );
        PID = new Random().nextInt( Integer.MAX_VALUE );
    }

    public void copiarRegistradoresParaPCB(){
        for( int i = 0; i < numeroDeRegistradores; i++ ){
            if( i >= 32 ) valorRegistros[i] = RegisterFile.getValue( i + 1 );
            else valorRegistros[i] = RegisterFile.getValue( i );
        }
    }

    public void copiarPCBparaRegistradores(){
        for( int i = 0; i < numeroDeRegistradores; i++ ){
            if (i >= 32) RegisterFile.updateRegister( i + 1, valorRegistros[i] );
            else RegisterFile.updateRegister( i, valorRegistros[i] );
        }
    }

    public int getNumeroDeRegistradores() {
        return numeroDeRegistradores;
    }

    public int[] getValorRegistros() {
        return valorRegistros;
    }

    public void setValorRegistros( int[] valorRegistros ) {
        this.valorRegistros = valorRegistros;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso( String estadoProcesso ) {
        this.estadoProcesso = estadoProcesso;
    }

    public int getEnderecoInicio() {
        return enderecoInicio;
    }

    public void setEnderecoInicio( int enderecoInicio ) {
        this.enderecoInicio = enderecoInicio;
    }

    public int getPID() {
        return PID;
    }

    public void setPID( int PID ) {
        this.PID = PID;
    }
}