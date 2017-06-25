package gui_Intro;

import java.awt.*;

import javax.swing.*;

public class SwingFenster {
	
	 private JFrame fenster;
	 private JPanel spielerEins;
	 private JPanel score;
	 private JPanel spielerZwei;
	 private JPanel anzeigeSpielerEins;
	 private JPanel spielfeld;
	 private JPanel anzeigeSpielerZwei;
	 private JPanel gameLog;
	 private JPanel resetArea;
	 private GridBagLayout aufteilung;
	 private GridBagConstraints dimensionen;
	 private JLabel nameSpielerEins;
	 private JLabel nameSpielerZwei;
	 private String nameEins;
	 private String nameZwei;
	 private int count;

	
	public SwingFenster() {
		fensterVorbereiten();
	}
	public static void main(String[] args) {
		SwingFenster meinFenster = new SwingFenster();
	}
	
	public void fensterVorbereiten() {
		count = 1;
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
		makeLabel(nameSpielerEins, Color.red, nameEins, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.gridx = 1;
		dimensionen.gridy = 0;
		makePanel(score, Color.black, aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.gridx = 2;
		dimensionen.gridy = 0;
		makeLabel(nameSpielerZwei, Color.blue, nameZwei,  aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 0;
		dimensionen.gridy = 1; 
		makePanel(anzeigeSpielerEins, Color.green, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 1;
		dimensionen.gridy = 1;
		makePanel(spielfeld, Color.darkGray, aufteilung, dimensionen);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 2;
		dimensionen.gridy = 1; 
		makePanel(anzeigeSpielerZwei, Color.magenta, aufteilung, dimensionen);
		dimensionen.weightx = 1.0;
		dimensionen.weighty = 0.2;
		dimensionen.gridx = 1;
		dimensionen.gridy = 2;
		makePanel(gameLog, Color.orange , aufteilung, dimensionen);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		turnPlayer1(anzeigeSpielerEins, anzeigeSpielerZwei);
		fenster.setVisible(true);
	}
	
	public void makePanel (JPanel panel, Color color, GridBagLayout gridbag, GridBagConstraints c) {
		panel = new JPanel();
		gridbag.setConstraints(panel, c);
		panel.setBackground(color);
		fenster.add(panel);
	}
	
	public void makeLabel (JLabel label, Color color, String name, GridBagLayout gridbag, GridBagConstraints c) {
			label = new JLabel();
			if (name == null) {
				name = "Spieler " + count; 
			}
			label.setText(name);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 64));
			gridbag.setConstraints(label, c);
			label.setForeground(color);
			label.setBackground(new Color(255, 255, 255));
			label.setOpaque(true);
			fenster.add(label);
			count++;
	}		

	
	public void turnPlayer1(JPanel panelEins, JPanel panelZwei) {
		fenster.remove(panelEins);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 0;
		dimensionen.gridy = 1; 
		makePanel(panelEins, Color.red, aufteilung, dimensionen);
		fenster.remove(panelZwei);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 2;
		dimensionen.gridy = 1; 
		makePanel(panelZwei, Color.white, aufteilung, dimensionen);
	}
	
	public void turnPlayer2(JPanel panelEins, JPanel panelZwei) {
		fenster.remove(panelEins);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 0;
		dimensionen.gridy = 1; 
		makePanel(panelEins, Color.white, aufteilung, dimensionen);
		fenster.remove(panelZwei);
		dimensionen.weightx = 0.2;
		dimensionen.weighty = 1.0;
		dimensionen.gridx = 2;
		dimensionen.gridy = 1; 
		makePanel(panelZwei, Color.blue, aufteilung, dimensionen);
	}
	
	public void setNameEins(String name) {
		nameEins = name;
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
