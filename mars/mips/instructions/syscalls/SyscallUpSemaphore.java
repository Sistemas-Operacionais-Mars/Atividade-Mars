package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallUpSemaphore extends AbstractSyscall {
    public SyscallUpSemaphore() {
        super(66, "SyscallUpSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    }
}