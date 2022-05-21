package mars.mips.instructions.syscalls;

import java.util.ArrayList;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.assembler.SourceLine;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;

public class SyscallFork extends AbstractSyscall {
    public SyscallFork() {
        super(60, "SyscallFork");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        if(ProcessesTable.getUltimoEnderecoPrograma() == 0) {
            int linhaChamada = statement.getSourceLine();
            int enderecoChamada = statement.getAddress();

            ArrayList<SourceLine> linhasPrograma = statement.getSourceMIPSprogram().getSourceLineList();
            int ultimaLinhaProgramaIndex = linhasPrograma.size() - 1;
            int ultimaLinhaPrograma = linhasPrograma.get(ultimaLinhaProgramaIndex).getLineNumber();

            int distanciaFinal = ultimaLinhaPrograma - linhaChamada;
            ProcessesTable.setUltimoEnderecoPrograma(enderecoChamada + distanciaFinal*8);
        }

        ProcessesTable.criarProcesso(RegisterFile.getValue(4), RegisterFile.getValue(5), RegisterFile.getValue(6));
    }
}