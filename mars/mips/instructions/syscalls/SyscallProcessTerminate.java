package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;

public class SyscallProcessTerminate extends AbstractSyscall {
    public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        PCB processoParaFinalizar = ProcessesTable.getProcessoExecutando();
        
        Scheduler.escalonar();
        ProcessesTable.removerProcessoPronto(processoParaFinalizar);
    }
    
}