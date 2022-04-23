package mars.mips.instructions.syscalls;


import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoresList;
import mars.mips.hardware.RegisterFile;

public class SyscallDownSemaphore extends AbstractSyscall {
    public SyscallDownSemaphore() {
        super(65, "SyscallDownSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int enderecoVariavel = RegisterFile.getValue(4);

        try {
            Semaphore semaforo = SemaphoresList.obterSemaforoPorEndereco(enderecoVariavel);
            semaforo.decrementarValor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}