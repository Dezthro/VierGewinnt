package gui_Intro;

import java.awt.*;

import javax.swing.*;

public class SwingFenster {
	
	 private JFrame fenster;
	 private JPanel spieler1;
	 private JPanel score;
	 private JPanel spieler2;
	 private JPanel anzeigeSpieler1;
	 private JPanel spielfeld;
	 private JPanel anzeigeSpieler2;
	 private JPanel gameLog;
	 private JPanel resetArea;
	 private JLabel feld;
	 private GridBagLayout aufteilung;
	 private GridBagConstraints dimensionen;
	
	public SwingFenster() {
		fensterVorbereiten();
	}
	public static void main(String[] args) {
		SwingFenster meinFenster = new SwingFenster();
	}
	
	public void fensterVorbereiten() {
		fenster = new JFrame();
		aufteilung = new GridBagLayout();
		fenster.setTitle("Fenster mit Vogel F");
		fenster.setLocation(160, 90);
		fenster.setSize(1600, 900);
		fenster.setBackground(Color.gray);
		fenster.setLayout(aufteilung);
		dimensionen = new GridBagConstraints();
		dimensionen.fill = GridBagConstraints.BOTH;
		dimensionen.anchor = GridBagConstraints.CENTER;
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 0.2;
		dimensionen.gridx = 0;
		dimensionen.gridy = 0;
		makePanel(spieler1, Color.red, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.gridx = 1;
		dimensionen.gridy = 0;
		makePanel(score, Color.black, aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.gridx = 2;
		dimensionen.gridy = 0;
		makePanel(spieler2, Color.blue, aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 0;
		dimensionen.gridy = 1; 
		makePanel(anzeigeSpieler1, Color.green, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 1;
		dimensionen.gridy = 1;
		makePanel(spielfeld, Color.darkGray, aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 2;
		dimensionen.gridy = 1; 
		makePanel(anzeigeSpieler1, Color.magenta, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.weighty = 0.2;
		dimensionen.gridx = 1;
		dimensionen.gridy = 2;
		makePanel(gameLog, Color.orange , aufteilung, dimensionen);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setVisible(true);
	}
	
	public void makePanel (JPanel panel, Color color, GridBagLayout gridbag, GridBagConstraints c) {
		panel = new JPanel();
		gridbag.setConstraints(panel, c);
		panel.setBackground(color);
		fenster.add(panel);
	}
	
	
	
	/**public void feldAnzeigen()  {
		feld = new JLabel();
		feld.setText("Dies ist ein Text");
		feld.setForeground(Color.red);
		feld.setVerticalAlignment(JLabel.CENTER);
		feld.setOpaque(true);
		fenster.setVisible(true);
	}
	*/
}
