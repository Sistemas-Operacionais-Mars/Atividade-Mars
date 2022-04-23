package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallTerminateSemaphore extends AbstractSyscall {
    public SyscallTerminateSemaphore() {
        super(64, "SyscallTerminateSemaphore");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    }
}