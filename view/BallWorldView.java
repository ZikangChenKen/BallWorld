package hw06.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * The view class for the balls
 *
 * @param <TDropListItem> the type of object in the drop lists
 * @param <TDropListItem2> the type of object in another drop list 
 */
public class BallWorldView<TDropListItem, TDropListItem2> extends JFrame {

	/**
	 * Sets the serial version UID
	 */
	private static final long serialVersionUID = 1635572460960316299L;
	/**
	 * JPanel representing the content panel
	 */
	private JPanel contentPane;
	/**
	 * JPanel representing the panel with the controls
	 */
	private final JPanel pnlControl = new JPanel();
	/**
	 * JTextField representing the text field with the ball class
	 */
	private final JTextField tfUpdateStrategy = new JTextField();
	/**
	 * JButton for making a ball
	 */
	private final JButton btnMakeBall = new JButton("Make Ball");
	/**
	 * JButton for clearing the balls
	 */
	private final JButton btnClearBalls = new JButton("Clear Balls");

	/**
	 * IModelCtrlAdapter representing an adapter to the model control
	 */
	private IView2ModelAdapter<TDropListItem, TDropListItem2> modelAdpt;

	/**
	 * JPanel representing the canvas panel
	 */
	private final JPanel pnlCanvas = new JPanel() {

		/**
		 * Sets the serial version UID
		 */
		private static final long serialVersionUID = 1L;

		/**
		* Overridden paintComponent method to paint a ball in the panel.
		* @param g The Graphics object to paint on.
		**/
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			modelAdpt.paintBalls(g);
		}
	};

	/**
	 * A combo box to set the first ball strategy
	 */
	private final JComboBox<TDropListItem> cboBallType1 = new JComboBox<>();

	/**
	 * A panel for adding ball types to the lists
	 */
	private final JPanel pnlUpdateStrategies = new JPanel();

	/**
	 * A panel for choosing ball types and making balls
	 */
	private final JPanel pnlComboBoxes = new JPanel();

	/**
	 * A combo box to set the second ball strategy
	 */
	private final JComboBox<TDropListItem> cboBallType2 = new JComboBox<>();

	/**
	 * A button for adding a strategy to both lists
	 */
	private final JButton btnAddUpdate = new JButton("Add to Lists");

	/**
	 * A button for combining the two selected strategies into a new strategy
	 */
	private final JButton btnCombine = new JButton("Combine");

	/**
	 * A panel for making and switching switchers
	 */
	private final JPanel panelSwitcher = new JPanel();

	/**
	 * A button that switches the strategies of the switcher balls
	 */
	private final JButton btnSwitchBalls = new JButton("Switch!");

	/**
	 * A button that makes a switcher ball
	 */
	private final JButton btnMakeSwitcher = new JButton("Make Switcher");

	/**
	 * Label for the switcher control
	 */
	private final JLabel lblSwitcherControl = new JLabel("Switcher Control");

	/**
	 * A Panel containing the paint strategies 
	 */
	private final JPanel pnlPaintStrategies = new JPanel();

	/**
	 * A TextField for inputing a paint strategy
	 */
	private final JTextField tfPaintStrategy = new JTextField();

	/**
	 * Button for adding paint strategies to the lists
	 */
	private final JButton btnAddPaint = new JButton("Add to Lists");

	/**
	 * A panel containing the interact strategies
	 */
	private final JPanel pnlInteractStrategies = new JPanel();

	/**
	 * A TextField for inputing an interact strategy.
	 */
	private final JTextField tfInteractStrategy = new JTextField();
	
	/**
	 * Ball type drop list.
	 */
	private final JComboBox<TDropListItem2> listBallType = new JComboBox<>();
	
	/**
	 * A panel containing the BallAlgo
	 */
	private final JPanel pnlAlgo = new JPanel();

	/**
	 * A TextField for inputing an interact strategy.
	 */
	private final JTextField tfBallAlgo = new JTextField();
	
	/**
	 * Ball type drop list.
	 */
	//private final JComboBox<TDropListItem> listBallAlgo = new JComboBox<>();

	/**
	 * Button for adding interact strategies to the lists
	 */
	private final JButton btnAddInteract = new JButton("Add to Lists");
	/**
	 * ball type panel 
	 */
	private final JPanel panelBallType = new JPanel();
	/**
	 * 
	 */
	private final JTextField tfBallType = new JTextField();
	/**
	 * 
	 */
	private final JButton btnAddBallType = new JButton("Add");

	/**
	 * Create the frame.
	 * @param modelAdpt Adapter to model
	 */
	public BallWorldView(IView2ModelAdapter<TDropListItem, TDropListItem2> modelAdpt) {
		tfBallType.setText("Default");
		tfBallType.setToolTipText("type in the ball you want to add");
		tfBallType.setColumns(10);
		tfPaintStrategy.setToolTipText("Input Paint Strategy");
		tfPaintStrategy.setText("Ball");
		tfPaintStrategy.setColumns(10);
		pnlUpdateStrategies.setBackground(Color.YELLOW);
		pnlUpdateStrategies.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Update Strategies", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlUpdateStrategies.setToolTipText("Add Update Strategies Here");
		pnlUpdateStrategies.setLayout(new GridLayout(0, 1, 0, 0));
		pnlUpdateStrategies.add(tfUpdateStrategy);
		tfUpdateStrategy.setToolTipText("Input Update Strategy");
		tfUpdateStrategy.setText("Overlap");

		this.modelAdpt = modelAdpt;
		initGUI();
	}

	/**
	 * Initiates the GUI
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlControl.setToolTipText("Panel with Controls for the Balls");
		pnlControl.setBackground(Color.GRAY);

		contentPane.add(pnlControl, BorderLayout.NORTH);
		panelBallType.setToolTipText("Add ball type here");
		panelBallType.setBackground(new Color(132, 255, 132));
		panelBallType.setLayout(new GridLayout(0, 1, 0, 0));
		panelBallType.setBorder(
				new TitledBorder(null, "Ball Type", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		pnlControl.add(panelBallType);
		
		
		listBallType.setToolTipText("Choose from here the ball type you want to create.");
		
		panelBallType.add(tfBallType);
		btnAddBallType.setToolTipText("add the typed in ball type");
		btnAddBallType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Damn
				listBallType.insertItemAt(modelAdpt.addBallType(tfBallType.getText()), 0);
				listBallType.setSelectedIndex(0);
			}
		});
		
		panelBallType.add(btnAddBallType);
		panelBallType.add(listBallType);

		pnlControl.add(pnlUpdateStrategies);
		tfUpdateStrategy.setColumns(10);
		pnlPaintStrategies.setToolTipText("Add Paint Strategies Here");
		pnlPaintStrategies.setBackground(Color.CYAN);
		pnlPaintStrategies.setBorder(
				new TitledBorder(null, "Paint Strategies", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		pnlControl.add(pnlPaintStrategies);
		pnlPaintStrategies.setLayout(new GridLayout(0, 1, 0, 0));

		pnlPaintStrategies.add(tfPaintStrategy);
		btnAddPaint.setToolTipText("Add Above Paint Strategy to Lists");
		btnAddPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboBallType1.insertItemAt(modelAdpt.addPaintStrategy(tfPaintStrategy.getText()), 0);
				if (cboBallType1.getItemCount() > 0) cboBallType1.setSelectedIndex(0);
				cboBallType2.insertItemAt(modelAdpt.addPaintStrategy(tfPaintStrategy.getText()), 0);
			}
		});

		pnlPaintStrategies.add(btnAddPaint);
		
		pnlControl.add(pnlInteractStrategies);
		pnlInteractStrategies.setToolTipText("Add Update Strategies Here");
		pnlInteractStrategies.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Interact Strategies", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlInteractStrategies.setBackground(Color.PINK);
		pnlInteractStrategies.setLayout(new GridLayout(0, 1, 0, 0));
		tfInteractStrategy.setToolTipText("Input Update Strategy");
		tfInteractStrategy.setText("ExactBounce");
		tfInteractStrategy.setColumns(10);

		pnlInteractStrategies.add(tfInteractStrategy);

		btnAddInteract.setToolTipText("Click to Add Interact Strategy to the Lists");
		pnlInteractStrategies.add(btnAddInteract);
		btnAddInteract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboBallType1.insertItemAt(modelAdpt.addInteractStrategy(tfInteractStrategy.getText()), 0);
				cboBallType1.setSelectedIndex(0);
				cboBallType2.insertItemAt(modelAdpt.addInteractStrategy(tfInteractStrategy.getText()), 0);
			}

		});
		
		
		
		pnlControl.add(pnlAlgo);
		pnlAlgo.setToolTipText("Add Update Strategies Here");
		pnlAlgo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Ball Algorithms", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlAlgo.setBackground(Color.BLUE);
		pnlAlgo.setLayout(new GridLayout(0, 1, 0, 0));
		tfBallAlgo.setToolTipText("Input Update Strategy");
		tfBallAlgo.setText("Size");
		tfBallAlgo.setColumns(10);

		pnlAlgo.add(tfBallAlgo);

		JButton btnAddAlgo = new JButton("Add to Lists");
		btnAddAlgo.setToolTipText("Click to Add an algorithm to the Lists");
		pnlAlgo.add(btnAddAlgo);
		btnAddAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * turn black: 
				 * turn black config: 
				 * 都可以
				 * */
				TDropListItem item = modelAdpt.addBallAlgo(tfBallAlgo.getText());
				cboBallType1.insertItemAt(item, 0);
				cboBallType1.setSelectedIndex(0);
				cboBallType2.insertItemAt(item, 0);
				
			}

		});

		pnlComboBoxes.setToolTipText("Make Balls Here");

		pnlControl.add(pnlComboBoxes);
		pnlComboBoxes.setLayout(new GridLayout(0, 1, 0, 0));
		btnAddUpdate.setToolTipText("Click to Add Strategy to the Lists");
		pnlUpdateStrategies.add(btnAddUpdate);
		btnAddUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboBallType1.insertItemAt(modelAdpt.addUpdateStrategy(tfUpdateStrategy.getText()), 0);
				cboBallType1.setSelectedIndex(0);
				cboBallType2.insertItemAt(modelAdpt.addUpdateStrategy(tfUpdateStrategy.getText()), 0);
			}

		});
		pnlComboBoxes.add(btnMakeBall);
		btnMakeBall.setVerticalAlignment(SwingConstants.BOTTOM);
		btnMakeBall.setToolTipText("Click to Make Ball");

		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem2 ballFactory= listBallType.getItemAt(listBallType.getSelectedIndex());
				modelAdpt.loadBall(cboBallType1.getItemAt(cboBallType1.getSelectedIndex()), ballFactory );
				
			}
		});
		cboBallType1.setToolTipText("Choose Ball type 1");
		pnlComboBoxes.add(cboBallType1);
		cboBallType2.setToolTipText("Choose Ball type 2");

		pnlComboBoxes.add(cboBallType2);
		btnCombine.setToolTipText("Combine the Ball Types");
		pnlComboBoxes.add(btnCombine);
		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem newStrategy = modelAdpt.combineStrategies(
						cboBallType1.getItemAt(cboBallType1.getSelectedIndex()),
						cboBallType2.getItemAt(cboBallType2.getSelectedIndex()));
				cboBallType1.insertItemAt(newStrategy, 0);
				cboBallType1.setSelectedIndex(0);
				cboBallType2.insertItemAt(newStrategy, 0);
			}
		});
		btnClearBalls.setToolTipText("Click to Clear Balls");
		btnClearBalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.clearBalls();
			}
		});
		panelSwitcher.setToolTipText("Switcher Controls Here");

		pnlControl.add(panelSwitcher);
		panelSwitcher.setLayout(new GridLayout(0, 1, 0, 0));
		lblSwitcherControl.setHorizontalAlignment(SwingConstants.CENTER);

		panelSwitcher.add(lblSwitcherControl);
		btnMakeSwitcher.setToolTipText("Make a Switcher Ball");
		btnMakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem2 ballFactory= listBallType.getItemAt(listBallType.getSelectedIndex());
				modelAdpt.loadSwitcher(ballFactory);
			}
		});

		panelSwitcher.add(btnMakeSwitcher);
		btnSwitchBalls.setToolTipText("Switch the switcher balls to the selected type!");
		btnSwitchBalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TDropListItem2 ballFactory= listBallType.getItemAt(listBallType.getSelectedIndex());
				
				modelAdpt.switchStrategy(cboBallType1.getItemAt(cboBallType1.getSelectedIndex()),ballFactory);
			}
		});

		panelSwitcher.add(btnSwitchBalls);

		pnlControl.add(btnClearBalls);
		pnlCanvas.setToolTipText("Canvas containing the balls.");

		contentPane.add(pnlCanvas, BorderLayout.CENTER);
	}

	/**
	 * @return the canvas panel
	 */
	public Container getCanvas() {
		return pnlCanvas;
	}

	/**
	 * Updates the canvas by repainting
	 */
	public void update() {
		getCanvas().repaint();
	}

	/**
	 * Start the frame.
	 */
	public void start() {
		
		this.setVisible(true);
	}
}
