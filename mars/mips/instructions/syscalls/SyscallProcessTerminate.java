package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.tools.PreemptiveScheduling;

public class SyscallProcessTerminate extends AbstractSyscall {
    public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        Scheduler scheduler = new Scheduler(PreemptiveScheduling.getAlgoritmoSelecionado());
        scheduler.escalonar(true);
    }
}