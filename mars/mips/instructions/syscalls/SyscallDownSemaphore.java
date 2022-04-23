package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallDownSemaphore extends AbstractSyscall {
    public SyscallDownSemaphore() {
        super(65, "SyscallDownSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    }
}