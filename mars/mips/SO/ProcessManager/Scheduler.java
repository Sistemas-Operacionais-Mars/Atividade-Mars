package mars.mips.SO.ProcessManager;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {

    private static Queue<PCB> procProntos = new PriorityQueue<PCB>();
    
    public void escalonar(PCB novoPCB){
        
        procProntos.remove();
        procProntos.add(novoPCB);

    }

}