package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallProcessTerminate extends AbstractSyscall {
    public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    }
    
}