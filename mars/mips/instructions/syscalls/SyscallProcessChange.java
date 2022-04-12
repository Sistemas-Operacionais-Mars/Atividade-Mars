package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public class SyscallProcessChange extends AbstractSyscall {
    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    
    }
    
}