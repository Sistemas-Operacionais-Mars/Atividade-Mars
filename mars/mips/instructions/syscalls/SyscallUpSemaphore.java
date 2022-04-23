package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallUpSemaphore extends AbstractSyscall {
    public SyscallUpSemaphore() {
        super(66, "SyscallUpSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int enderecoVariavel = RegisterFile.getValue(4);

        try {
            Semaphore semaforo = SemaphoreList.obterSemaforoPorEndereco(enderecoVariavel);
            semaforo.incrementarValor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}