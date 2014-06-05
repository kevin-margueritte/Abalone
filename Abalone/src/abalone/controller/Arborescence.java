package abalone.controller;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Classe permettant de recuperer et de creer des fichiers
 * 
 * @author Groupe projet
 * @mail kevin.margueritte@gmail.com
 *
 */

@SuppressWarnings("serial")
public class Arborescence extends JFrame {
	@SuppressWarnings("unused")
	private JTextField jtf_path;
 
	public Arborescence() {
		@SuppressWarnings("unused")
		JFileChooser chooser = new JFileChooser();
		this.setVisible(true);
	}
	
	/**
	 * Ouvre un fichier avec l'extension .aba, les dossiers sont aussi sont filtrés
	 * @return
	 */
	public File openFile(){
		JTextField jtf_path = new JTextField(40);;
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();                
                return name.endsWith(".aba") || f.isDirectory();
              }
   
              public String getDescription() {
                return ".aba";
              }
            });
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            jtf_path.setText(file.getPath());
            this.exit();
            return file;
        }
        else
        	this.exit();
        	return null;
	}
	
	/**
	 * Recupere le chemin brut du fichier, l'extension prit en compte est le .aba
	 * @return
	 */
	@SuppressWarnings("static-access")
	public File savFile() {
		   JFileChooser chooser = new JFileChooser();
		  // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		   chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
	            public boolean accept(File f) {
	                String name = f.getName().toLowerCase();                
	                return name.endsWith(".aba") || f.isDirectory();
	              }
	   
	              public String getDescription() {
	                return ".aba";
	              }
	            });
		   int result = chooser.showSaveDialog(this);
		   if (result == chooser.APPROVE_OPTION) {
			   String name = chooser.getSelectedFile().toString();
			   File f = (name.endsWith(".aba")) ? new File(name) : new File( name + ".aba");
			   this.exit();
		      return f;
		   } else {
			   this.exit();
		      return null;
		   }
	}
	
	/**
	 * Quitte le choisisseur
	 */
	public void exit(){
		this.dispose();
	}
 
}