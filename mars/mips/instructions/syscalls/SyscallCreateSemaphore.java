package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.AddressErrorException;
import mars.mips.hardware.Memory;
import mars.mips.hardware.RegisterFile;

public class SyscallCreateSemaphore extends AbstractSyscall {
    public SyscallCreateSemaphore() {
        super(63, "SyscallCreateSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        int enderecoVariavel = RegisterFile.getValue(4);
        try {
            int valorVariavel = Memory.getInstance().get(enderecoVariavel, 4);
            SemaphoreList.criarSemaforo(valorVariavel, enderecoVariavel);
        } catch (AddressErrorException e) {
            e.printStackTrace();
        }
    }
}