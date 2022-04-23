package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallCreateSemaphore extends AbstractSyscall {
    public SyscallCreateSemaphore() {
        super(63, "SyscallCreateSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        
    }
}