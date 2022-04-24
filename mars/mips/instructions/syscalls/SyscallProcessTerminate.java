package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.ProcessesTable;

public class SyscallProcessTerminate extends AbstractSyscall {
    public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        ProcessesTable.removerProcessoTopo();
    }
}