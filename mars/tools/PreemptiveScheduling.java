package mars.tools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mars.*;

public class PreemptiveScheduling extends AbstractMarsToolAndApplication {
    
   private static String heading =  "Escalonamento preemptivo";
   private static String version = " Version 1.0";
   
    /** 
     * Simple constructor, likely used to run a stand-alone memory reference visualizer.
     * @param title String containing title for title bar
     * @param heading String containing text for heading shown in upper part of window.
     */
    public PreemptiveScheduling(String title, String heading) {
      super(title,heading);
   }
     
    public PreemptiveScheduling() {
      super (heading+", "+version, heading);
   }
         
    public static void main(String[] args) {
      new IntroToTools(heading+", "+version,heading).go();
    }
    
   // modifica para o nosso nome
    /**
      *  Required method to return Tool name.  
      *  @return  Tool name.  MARS will display this in menu item.
      */
    public String getName() {
      return "Preemptive Scheduling";
   }

    protected JComponent buildMainDisplayArea() {
      Box boxArea = Box.createHorizontalBox();

      JTextField textField = new JTextField();
      boxArea.add(textField);

      ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            textField.setText("Atualizou");
        }
      };

      JButton startButton = new JButton("Iniciar");
      startButton.addActionListener(
          new ActionListener() {
            @Override
              public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField.getText();
                textField.setEditable(false);
                Timer timer = new Timer(Integer.parseInt(textFieldValue), taskPerformer);
                timer.start();
              }
      });

      startButton.addKeyListener(new EnterKeyListener(startButton));
      boxArea.add(startButton);
      
      return new JScrollPane(boxArea);
   }
    
}

