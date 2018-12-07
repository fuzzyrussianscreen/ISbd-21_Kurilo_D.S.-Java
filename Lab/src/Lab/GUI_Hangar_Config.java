package Lab;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseMotionAdapter;

public class GUI_Hangar_Config extends JDialog {
	private JPanel contentPane;
	public boolean rest;
	public IAircraft fighter;
	public boolean res() {
		setVisible(true);
		return rest;
	}
	public Color selectColor(String s) {
		switch (s) {
		case "gray":
			return Color.GRAY;
		case "black":
			return Color.BLACK;
		case "red":
			return Color.RED;
		case "green":
			return Color.GREEN;
		case "orange":
			return Color.ORANGE;
		case "pink":
			return Color.PINK;
		case "blue":
			return Color.BLUE;
		case "yellow":
			return Color.YELLOW;
		}
		return null;
	}
	public void draw(JPanel panel, IAircraft fighter) {
		if (fighter != null) {
			Graphics gr = panel.getGraphics();
			gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			fighter.SetPosition(30, 65, panel.getWidth(), panel.getHeight());
			fighter.DrawFighter(gr);
		}
	}
	public GUI_Hangar_Config(JFrame parent) {
		super(parent, true);
		this.getContentPane().setBackground(SystemColor.controlHighlight);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 503, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rest = true;
				dispose();
			}
		});
		buttonAdd.setBounds(37, 143, 74, 35);
		contentPane.add(buttonAdd);
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rest = false;
				dispose();
			}
		});
		buttonCancel.setBounds(37, 191, 74, 25);
		contentPane.add(buttonCancel);
		Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel lblSportfighterane = new JLabel("Fighter");
		lblSportfighterane.setHorizontalAlignment(SwingConstants.CENTER);
		lblSportfighterane.setBorder(solidBorder);
		lblSportfighterane.setBounds(10, 62, 122, 56);
		contentPane.add(lblSportfighterane);
		
		JLabel lblfighterane = new JLabel("Plane");
		lblfighterane.setHorizontalAlignment(SwingConstants.CENTER);
		lblfighterane.setBorder(solidBorder);
		lblfighterane.setBounds(10, 13, 122, 56);
		contentPane.add(lblfighterane);
		
		JPanel panelBorder = new JPanel();
		panelBorder.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		panelBorder.setBounds(168, 22, 157, 123);
		panelBorder.setBorder(solidBorder);
		panelBorder.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 155, 121);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent e) {
				try {
					for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
						String str = (String)e.getTransferable().getTransferData(df);
						if (e.getTransferable().getTransferData(df) == "Plane") {
							fighter = new  Plane(100, 100, Color.GRAY, true, true);
						} else if (e.getTransferable().getTransferData(df) == "Fighter") {
							fighter = new Fighter(100, 100, Color.GRAY, Color.BLACK, true, true, true, true, true);
						}
						draw(panel, fighter);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panelBorder.add(panel);
		contentPane.add(panelBorder);
		
		JLabel lblMainColor = new JLabel("Main color");
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBorder(solidBorder);
		lblMainColor.setBounds(168, 158, 159, 49);
		lblMainColor.setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent e) {
				if (fighter != null) {
					try {
						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							((Plane) fighter).SetMainColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, fighter);
						}
					} catch (Exception ex) {
						System.out.println(ex + "FF");
					}
				}
			}
			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(lblMainColor);
		
		JLabel lblAddColor = new JLabel("Dop color");
		lblAddColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddColor.setBorder(solidBorder);
		lblAddColor.setBounds(168, 212, 159, 49);
		lblAddColor.setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent e) {
				if (fighter != null) {
					try {
						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							((Fighter) fighter).SetDopColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, fighter);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(lblAddColor);
		MouseListener mouseL = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				JComponent jc = (JComponent) e.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, e, TransferHandler.COPY);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		};
		lblfighterane.addMouseListener(mouseL);
		lblSportfighterane.addMouseListener(mouseL);
		lblfighterane.setTransferHandler(new TransferHandler("text"));
		lblSportfighterane.setTransferHandler(new TransferHandler("text"));
		
		JPanel panelGray = new JPanel();
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(433, 24, 40, 40);
		panelGray.setName("gray");
		contentPane.add(panelGray);
		
		JPanel panelBlack = new JPanel();
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(385, 24, 40, 40);
		panelBlack.setName("black");
		contentPane.add(panelBlack);
		
		JPanel panelRed = new JPanel();
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(433, 158, 40, 40);
		panelRed.setName("red");
		contentPane.add(panelRed);
		
		JPanel panelOrange = new JPanel();
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(385, 158, 40, 40);
		panelOrange.setName("orange");
		contentPane.add(panelOrange);
		
		JPanel panelPink = new JPanel();
		panelPink.setBackground(Color.PINK);
		panelPink.setBounds(385, 221, 40, 40);
		panelPink.setName("pink");
		contentPane.add(panelPink);
		
		JPanel panelBlue = new JPanel();
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(385, 91, 40, 40);
		panelBlue.setName("blue");
		contentPane.add(panelBlue);
		
		JPanel panelYellow = new JPanel();
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(433, 221, 40, 40);
		panelYellow.setName("yellow");
		contentPane.add(panelYellow);
		
		JPanel panelGreen = new JPanel();
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(433, 91, 40, 40);
		panelGreen.setName("green");
		contentPane.add(panelGreen);
		
		panelGray.addMouseListener(mouseL);
		panelGray.setTransferHandler(new TransferHandler("name"));
		
		panelBlack.addMouseListener(mouseL);
		panelBlack.setTransferHandler(new TransferHandler("name"));
		
		panelRed.addMouseListener(mouseL);
		panelRed.setTransferHandler(new TransferHandler("name"));
		
		panelOrange.addMouseListener(mouseL);
		panelOrange.setTransferHandler(new TransferHandler("name"));
		
		panelPink.addMouseListener(mouseL);
		panelPink.setTransferHandler(new TransferHandler("name"));
		
		panelBlue.addMouseListener(mouseL);
		panelBlue.setTransferHandler(new TransferHandler("name"));
		
		panelYellow.addMouseListener(mouseL);
		panelYellow.setTransferHandler(new TransferHandler("name"));
		
		panelGreen.addMouseListener(mouseL);
		panelGreen.setTransferHandler(new TransferHandler("name"));
	}
}
