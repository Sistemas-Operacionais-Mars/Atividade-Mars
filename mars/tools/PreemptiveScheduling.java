package mars.tools;
import javax.swing.*;

import mars.ProcessingException;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.mips.SO.memory.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;



import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Memory;
import mars.mips.hardware.MemoryAccessNotice;
import mars.mips.hardware.RegisterFile;
 
/*
Copyright (c) 2003-2006,  Pete Sanderson and Kenneth Vollmar

Developed by Pete Sanderson (psanderson@otterbein.edu)
and Kenneth Vollmar (kenvollmar@missouristate.edu)

Permission is hereby granted, free of charge, to any person obtaining 
a copy of this software and associated documentation files (the 
"Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, 
distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject 
to the following conditions:

The above copyright notice and this permission notice shall be 
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

(MIT license, http://www.opensource.org/licenses/mit-license.html)
*/

@SuppressWarnings("serial")
public class PreemptiveScheduling extends AbstractMarsToolAndApplication {
    
   private static String heading =  "Escalonamento preemptivo";
   private static String version = " Version 1.0";
   public static boolean canExec = true;
   private int ultimoPC = 0;

   private JComboBox<String> selecaoAlgoritmo = new JComboBox<String>(new String[]{"FIFO","PFixa", "Loteria"});
   private static String algoritmoSelecionado = "FIFO";
			
    /** 
     * Simple constructor, likely used to run a stand-alone memory reference visualizer.
     * @param title String containing title for title bar
     * @param heading String containing text for heading shown in upper part of window.
     */
    public PreemptiveScheduling(String title, String heading) {
      super(title,heading);
   }
     
     /**
      *  Simple constructor, likely used by the MARS Tools menu mechanism
      */
    public PreemptiveScheduling() {
      super (heading+", "+version, heading);
   }
         
         
    /**
     * Main provided for pure stand-alone use.  Recommended stand-alone use is to write a 
     * driver program that instantiates a MemoryReferenceVisualization object then invokes its go() method.
     * "stand-alone" means it is not invoked from the MARS Tools menu.  "Pure" means there
     * is no driver program to invoke the application.
     */
    public static void main(String[] args) {
      new PreemptiveScheduling(heading+", "+version,heading).go();
   }
    
   // modifica para o nosso nome
    /**
      *  Required method to return Tool name.  
      *  @return  Tool name.  MARS will display this in menu item.
      */
    public String getName() {
      return "Preemptive Scheduling";
   }

   public static String getAlgoritmoSelecionado() {
	   return algoritmoSelecionado;
   }
    
	protected int lastAddress = -1; // comparativo de endereço
    /**
     * Number of instructions executed until now.
     */
	private JTextField counterField;
	/**
     * Timer definition.
     */
	protected int countTimer = 10; // Timer de interrupção
	/**
     * Number of interruptions until now.
     */
	protected int countInter = 0; // contador de interrupções
	private JTextField counterInterField;
    /**
     * Number of instructions until interruption.
     */
    protected int countInst = 3;
	private JProgressBar progressbarInst;
	
	/**
	 * Configuration tools
	*/
	private JToggleButton timerOn;
	private JSpinner timerConfig;
	
	@Override
	protected JComponent buildMainDisplayArea() {
		JPanel panel = new JPanel(new GridBagLayout());

		counterField = new JTextField("0", 10);
		counterField.setEditable(false);
		
		counterInterField = new JTextField("0", 10);
		counterInterField.setEditable(false);
		
		progressbarInst = new JProgressBar(JProgressBar.HORIZONTAL);
		progressbarInst.setStringPainted(true);

		timerOn = new JToggleButton("ON/OFF"); 
		timerOn.setToolTipText("Enable interruption");
		
		timerConfig = new JSpinner();
		timerConfig.setModel(new SpinnerNumberModel(10, 2, 100, 1));
		timerConfig.setToolTipText("Sets the time for the interruption");
		
		// Fields
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 17, 0);
		panel.add(counterField, c);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridy++;
		panel.add(counterInterField, c);
		
		// progress bar
		c.gridy++;
		panel.add(progressbarInst,c);
		// spinner
		c.gridy++;
		panel.add(timerConfig, c);
		
		// Labels
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 17, 0);
		panel.add(new JLabel("Instructions so far: "), c);
		
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy++;
		panel.add(new JLabel("Interruptions so far: "), c);
		c.gridy++;
		panel.add(new JLabel("Time so far: "), c);
		c.gridy++;
		panel.add(new JLabel("Timer: "), c);
		
		// lock
		c.insets = new Insets(3, 3, 3, 3);
		c.gridx = 4;
		c.gridy = 2;
		panel.add(timerOn, c);

		selecaoAlgoritmo.setSelectedItem(0);
		selecaoAlgoritmo.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				algoritmoSelecionado = selecaoAlgoritmo.getSelectedItem().toString();
			}
		});

		panel.add(selecaoAlgoritmo);  // para selecionar qual vai ser
		// parte dois tarefa 1.3
		
   		return panel;
	}
	
//	@Override
	protected void addAsObserver() {
		addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
	}
	
//  @Override (Esse aqui é o antigo!!!!!)
	/*protected void processMIPSUpdate(Observable memory, AccessNotice notice) {
		canExec = true;
		if (notice.getAccessType() != AccessNotice.READ) return;
		MemoryAccessNotice m = (MemoryAccessNotice) notice;
		int a = m.getAddress();
		if (a == lastAddress) return;
		if (ProcessesTable.getProcessoTopo() != null) {
			lastAddress = a;
			++counter;
			if(timerOn.isSelected()) {
				++countInst;
				if (countInst > (int)timerConfig.getValue()) {
					canExec = false;
					SystemIO.printString("-- Hora de trocar!\n");
					++countInter; // incrementa interrupções
					countInst = 0; // zera o progressBar				
				//ProcessesTable.setProcessoExecutando("roteamento");  // aqui era processChange
        Scheduler.escalonar(); // vai ser esse?
				}
			}
			updateDisplay();
		}
	}*/
	
	protected void processMIPSUpdate(Observable memory, AccessNotice notice) {
		if(ultimoPC != 0) {
			RegisterFile.setProgramCounter(ultimoPC);
			ultimoPC = 0;
		}

		if (!notice.accessIsFromMIPS()) return;
		if (notice.getAccessType() != AccessNotice.READ) return;
		
		MemoryAccessNotice m = (MemoryAccessNotice) notice;
		int a = m.getAddress();
		if (a == lastAddress) return;

		lastAddress = a;

		if(
			ProcessesTable.getProcessoExecutando() == null || 
			!timerOn.isSelected()
		) {
			return;
		}

		MemoryManager.verificarMemoria();
		
		//Verifica a quantidade de instruções ultrapassou o limite do timer
		if(++countInst >= (int)timerConfig.getValue()){
			countInter++; // incrementa qnt de interrupções
			countInst = 0; //zera o contador de instruções

			Scheduler scheduler = new Scheduler(algoritmoSelecionado);
			scheduler.escalonar(false);
			ultimoPC = RegisterFile.getProgramCounter();
		}

		updateDisplay();
	}
	
//  @Override
	protected void initializePreGUI() {
		countInst = 0;
		countTimer = 10;
		countInter = 0;
		lastAddress = -1;
	}
	
//  @Override
	protected void reset() {
		countInst = 0;
		countTimer = 10;
		countInter = 0;
		lastAddress = -1;
		updateDisplay();
	}
//  @Override
	protected void updateDisplay() {
		counterField.setText(String.valueOf(countInst));
		counterInterField.setText(String.valueOf(countInter));
		progressbarInst.setValue(countInst);
		progressbarInst.setMaximum((int)timerConfig.getValue());
	}
}
