import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class Test implements ActionListener, ComponentListener
{
	private Frame f;
	private Frame fOut;
	private Button b;
	private TextField tf;
	private Label lab;
	private Label lab2;
	private Panel pa;
	private Panel p2;
	private Vector<Label> v = new Vector<Label>();
	
	public Test()
	{
		f = new Frame("Esercitazione 1");
		f.setLocation(100, 100);
		f.setSize(480, 340);
		Color bgcolor = new Color(210, 210, 210);
		f.setBackground(bgcolor);
		f.setResizable(true);
		
			WindowAdapter wa = new WindowAdapter()
			{
				public void windowClosing(WindowEvent we)
				{	
					we.getWindow().dispose();
				};
			};
			f.addWindowListener(wa);
		
		//Creo le specifiche dei componenti interattivi
		tf = new TextField();
		tf.setPreferredSize(new Dimension(200,23));
		b = new Button("Inserisci");
		b.addActionListener(this); 
		b.setSize(30, 23);
		f.addComponentListener(this);
		p2 = new Panel();
		
		//Layout
		f.setLayout(new BorderLayout());
		
		//Pannello superiore, container di etichette
		pa = new Panel();
		f.add(pa, BorderLayout.NORTH);
		
		//Pannello inferiore, container degli oggetti interattivi
		//Casella di testo e pulsante per l'inserimento delle stringhe
		p2.add(tf);
		p2.add(b);
		f.add(p2, BorderLayout.SOUTH);
		
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//Dimensione del carattere iniziale
		int Isize = 8;
		Font IFont = new Font("Times", Font.PLAIN, Isize);
		
		int position = 0;
		int size = 0;
		boolean check = false;
		String s2="";
		
		if ("Inserisci".equals(e.getActionCommand())) 
	    {
			//Converto tutto in minuscolo 
			//(Evito di avere due etichette con la medesima parola, ma con distinzioni di maiuscole).
			s2 = tf.getText();
			s2 = s2.toLowerCase();
			
			if(s2.length()>=1)
			{	
					//Cerco se già è stata immessa la parola
					for(int j=0; j<=v.size()-1; j++)
					{
						if(((Label) v.elementAt(j)).getText().equals(s2))
						{
							check = true;
							position = j;
						}
					}
					
					//Se la trovo, cambio solo la dimensione del carattere
					if(check == true)
					{
						size = (((Label) v.elementAt(position)).getFont()).getSize();
						((Label) v.elementAt(position)).setFont(new Font("Times", Font.PLAIN, size+1));
						
						f.setVisible(true);
						check = false;
					}
					else
					{
						//Se non trovo la parola, creo una nuova etichetta
						lab = new Label("");
				
						lab.setText(s2);
						lab.setFont(IFont);
						//pa.add(lab);
						v.addElement(lab);
					}
					
					//Ordino il vector, utilizzando la metodo comparator
					Comparator<Label> cm = new Comparator <Label>()
					{
						public int compare(Label l1, Label l2)
						{
							String s1;
							String s2;
							s1 = l1.getText();
							s2 = l2.getText();
							
							if(s1.equals(s2))
							{
								return 0;
							}
							else if((s1.compareTo(s2)>0))
							{
								return 1;
							}
							else
							{
								return -1;
							}
							
						}	
					};
					Collections.sort(v, cm);
					
					//Rimuovo tutti gli oggetti nel pannello
					pa.removeAll();
					
					//Aggiungo tutte le etichette al pannello
					for(int j=0; j<=v.size()-1; j++)
					{
						pa.add(v.elementAt(j));
					}
					
					//Agggiungo il pannello al frame
					f.add(pa);
					f.setVisible(true);
				
			}
			else
			{
				//Frame per indicare che non si è immessa alcuna parola all'interno della casella di testo
				fOut = new Frame("Attenzione!");
				lab2 = new Label("  Non hai immesso alcuna parola nella casella di testo.");
				
				lab2.setFont(new Font("Times", Font.PLAIN, 16));
				
				fOut.setLocation(140, 240);
				Color bgcolor = new Color(190, 190, 190);
				fOut.setBackground(bgcolor);
				fOut.setSize(400, 100);
				fOut.setVisible(true);
				
				fOut.setLayout(new BorderLayout());
				fOut.add(lab2, BorderLayout.CENTER);
				
				WindowAdapter wa2 = new WindowAdapter()
				{
					public void windowClosing(WindowEvent we2)
					{	
						we2.getWindow().dispose();
					};
				};
				fOut.addWindowListener(wa2);
				
			}
	    }
		
	
	}

	
	public static void main(String[] args) 
	{
		Runnable r = new Runnable()
		{
			public void run()
			{
				new Test();
			};
		};
		
		EventQueue.invokeLater(r);
	}


	public void componentResized(ComponentEvent arg0) 
	{
		Component c=arg0.getComponent();
		int w=c.getWidth();
		int h=c.getHeight();
		if (w<280) w=280;
		if (h<340) h=340;
		c.setSize(w, h);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}