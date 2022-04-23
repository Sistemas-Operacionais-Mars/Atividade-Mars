package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallTerminateSemaphore extends AbstractSyscall {
    public SyscallTerminateSemaphore() {
        super(64, "SyscallTerminateSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int enderecoVariavel = RegisterFile.getValue(4);

        try {
            Semaphore semaforo = SemaphoreList.obterSemaforoPorEndereco(enderecoVariavel);
            semaforo.eliminarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}