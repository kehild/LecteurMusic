import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class fnc_lecture extends Lecteur implements Runnable {

	// Attribut
	
	private static final long serialVersionUID = 1L;
	//static JOptionPane msgErreur;
	long toto;
	
	@Override
	public void run() {
		
		JFileChooser chooser = new JFileChooser();
		
		chooser.showSaveDialog(null);
		File f=chooser.getSelectedFile();
		String Filename = f.getAbsolutePath();
			
		/* taille de fichier */ // mais pas encore totalement au point
		
		int size = (int) (f.length() / 1024) + 1;
        if (size > 1024) {      
        	JLabel toto1= new JLabel((size / 1024) + " Mo");
        	pan.add(toto1);
        	this.setContentPane(pan);
        	this.setVisible(true);
        	toto1.setBounds(5, 25, 200, 25);
        
        } else {
            JLabel toto2= new JLabel(size + " ko");
            pan.add(toto2);
            this.setContentPane(pan);
    		this.setVisible(true);
    		toto2.setBounds(200, 50, 200, 25);
        }
		
        /* nom fichier */
        
        String nomCourt = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
        JLabel toto = new JLabel(nomCourt);
        pan.add(toto);
        this.setContentPane(pan);
		this.setVisible(true);
		toto.setBounds(5, 5, 240, 25);
        	
		/* Jouer de la musique */
		try{
			FileInputStream fil = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fil);
			try{
				/* Stopper la musique */
				
				stop.addActionListener(new ActionListener() {	
					@Override
		        	public void actionPerformed(ActionEvent arg0) {
						try {
							Player stopper = new Player(bis);
							stopper.close();
						} catch (JavaLayerException e) {
							e.printStackTrace();
						}
					}					
		        });
				
				/* Mettre en pause la musique */ 
				
				/* La pause par un stop du buffer mais la reprise reprend du début et pas la ou il c arrêter. 
				 * Le thread ne reprend pas */ 
				pause.addActionListener(new ActionListener() {	
					@Override
		        	public void actionPerformed(ActionEvent arg0) {
					
						int bleu=1;
						while(bleu ==1){
							try {
								AdvancedPlayer pausee = new AdvancedPlayer(bis);
								
								pausee.getPlayBackListener();
							
							} catch (JavaLayerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						//	Thread.currentThread().interrupt();
							//System.out.println("Test");
							
						}
					
					
					
					}					
		        });
					
				Player player = new Player(bis);
				player.play();	
							
			}catch(JavaLayerException ex){
			//	msgErreur = new JOptionPane();
			//	JOptionPane.showMessageDialog(null, "Impossible de jouer la musique", "Erreur", JOptionPane.WARNING_MESSAGE);
			}	
		}catch(IOException e){
			//msgErreur = new JOptionPane();
			//JOptionPane.showMessageDialog(null, "Impossible de jouer la musique", "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}
	}