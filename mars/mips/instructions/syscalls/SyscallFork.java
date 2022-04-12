package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;
import mars.*;

public class SyscallFork extends AbstractSyscall {
    public SyscallFork() {
        super(60, "SyscallFork");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
    
        try {
            ProcessesTable.criarProcesso(RegisterFile.getValue(4));
        } catch (AddressErrorException e) {
            throw new ProcessingException(statement, e);
        }
    }
    
}