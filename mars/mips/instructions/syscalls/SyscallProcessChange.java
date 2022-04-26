package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;

public class SyscallProcessChange extends AbstractSyscall {
    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        PCB processoTopo = ProcessesTable.getProcessoTopo();

        if(!processoTopo.getEstadoProcesso().equals("Executando")) {
            processoTopo.setEstadoProcesso("Executando");
            processoTopo.copiarPCBparaRegistradores();
        } else {
            processoTopo.copiarRegistradoresParaPCB();

            Scheduler scheduler = new Scheduler("FIFO");
            scheduler.escalonar(false);
        }
    }
}