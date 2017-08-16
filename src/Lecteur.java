import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lecteur extends JFrame implements ActionListener{

	// attribut
	
	private static final long serialVersionUID = 1L;
	protected JPanel pan = new JPanel();
	private JButton play = new JButton("Play");
	private JButton quitter = new JButton("Quitter");
	public JButton stop = new JButton("Stop");
	public JButton pause = new JButton("Pause");
	
	public Lecteur(){
		pan.setLayout(null);
		
		// fennetre
		
		this.setVisible(true);
		this.setTitle("Lecteur de Musique");
		this.setSize(650,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		/* Bouton */
		
		pan.add(play);
		this.setContentPane(pan);
		this.setVisible(true);
		play.setBounds(50, 50, 100, 25);
		play.addActionListener(this);
		
		pan.add(pause);
		this.setContentPane(pan);
		this.setVisible(true);
		pause.setBounds(200, 50, 100, 25);
		pause.addActionListener(this);

		pan.add(stop);
		this.setContentPane(pan);
		this.setVisible(true);
		stop.setBounds(350, 50, 100, 25);
		stop.addActionListener(this);
		
		pan.add(quitter);
		this.setContentPane(pan);
		this.setVisible(true);
		quitter.setBounds(400, 300, 200, 25);
		quitter.addActionListener(this);
				
	}	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()== play){
            Thread thread1 = new Thread(new fnc_lecture());
            thread1.start();
            this.setVisible(false);  
		}
		if(ev.getSource() == quitter){
		System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new Lecteur();
	}
}