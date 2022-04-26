package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.tools.PreemptiveScheduling;

public class SyscallProcessChange extends AbstractSyscall {
    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        Scheduler scheduler = new Scheduler(PreemptiveScheduling.getAlgoritmoSelecionado());
        scheduler.escalonar(false);
    }
}