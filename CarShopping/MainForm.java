import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainForm extends JFrame {
	static ArrayList<String> ownersArrayList;
	static ArrayList<String> carsArrayList;

	private JTextField txtOwnerName;
	private JTextField txtOwnerFamily;
	private JTextField txtOwnerJob;
	private JTextField txtOwnerId;

	JPanel rightPanel;
	JPanel firstPanel;
	JPanel newOwnerPanel;
	JPanel newCarPanel;
	JPanel ownersListPanel;
	JPanel carsListPanel;
	JPanel searchPanel;
	JPanel footerPanel;
	private JTextField txtCarNumber;
	private JTextField txtCarBrand;
	private JTextField txtCarProduceDate;
	private JTextField txtSrcById;
	private JTextField txtSrcOldestCarByName;
	private JTextField txtSrcOldestCarByFamily;
	private JTextField txtSrcOldestCarById;
	private JTextField txtSrcNumberOfCarsById;
	private JTextField txtSrcByFamily;
	private JTextField txtSrcByName;

	JButton btnSave;
	JButton btnEdit;
	JButton btnDelete;
	JButton btnSearch;
	JButton btnCancel;

	JRadioButton rbtnSrcById;
	JRadioButton rbtnSrcByName;
	JRadioButton rbtnSrcByFamily;
	JRadioButton rbtnSrcNumberOfCarsById;
	JRadioButton rbtnSrcOldestCar;
	JRadioButton rbtnSrcOldestCarById;
	JRadioButton rbtnSrcOldestCarByName;
	JRadioButton rbtnSrcOldestCarByFamily;

	JList<String> ownersList;
	JList<String> carsList;
	JComboBox<String> cbxCarOwnerId;
	private String selectedOwner;
	private String selectedCar;

	public MainForm() {
		initComponents();

	}

	private static void loadData() {
		ArrayList<String> ownerData = new ArrayList<>();
		File ownerFile = new File("owner.csv");
		try {
			if (ownerFile.createNewFile()) {
				FileWriter writer = new FileWriter(ownerFile);
				writer.append("Id,Name,Family,Job\n");
				writer.close();
			} else {
				Scanner reader = new Scanner(ownerFile);
				while (reader.hasNext())
					ownerData.add(reader.nextLine());
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fillOwnersList(ownerData);

		ArrayList<String> carsData = new ArrayList<>();
		File carFile = new File("car.csv");
		try {
			if (carFile.createNewFile()) {
				FileWriter writer = new FileWriter(carFile);
				writer.append("Car Number,Car Brand,Produce Date,Owner Id\n");
				writer.close();
			} else {
				Scanner reader = new Scanner(carFile);
				while (reader.hasNext())
					carsData.add(reader.nextLine());
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fillCarsList(carsData);

	}

	private static void fillOwnersList(ArrayList<String> data) {
		ownersArrayList = new ArrayList<>();
		for (int i = 1; i < data.size(); i++) {
			String[] splitted = data.get(i).split(",");
			ownersArrayList.add("  " + splitted[0] + " - " + splitted[1] + " - " + splitted[2] + " - " + splitted[3]);
		}
	}

	private static void fillCarsList(ArrayList<String> cars) {
		carsArrayList = new ArrayList<>();
		for (int i = 1; i < cars.size(); i++) {
			String[] splitted = cars.get(i).split(",");
			carsArrayList.add("  " + splitted[0] + " - " + splitted[1] + " - " + splitted[2] + " - " + splitted[3]);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Cars Shopping");
		setName("MainForm");
		setSize(600, 400);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 20);

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowWeights = new double[] { 1.0 };
		gbl_mainPanel.rowHeights = new int[] { 360 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_mainPanel.columnWidths = new int[] { 60, 300 };
		mainPanel.setLayout(gbl_mainPanel);

		JPanel leftPanel = new JPanel();
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(0, 0, 5, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		mainPanel.add(leftPanel, gbc_leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 5));

		JButton btnNewOwner = new JButton("New Owner");
		btnNewOwner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showNewOwnerPanel();
			}
		});
		leftPanel.add(btnNewOwner);

		JButton btnNewCar = new JButton("New Car");
		btnNewCar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showNewCarPanel();
			}
		});
		leftPanel.add(btnNewCar);

		JButton btnOwnersList = new JButton("Owners List");
		btnOwnersList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showOwnersListPanel();
			}
		});
		leftPanel.add(btnOwnersList);

		JButton btnCarsList = new JButton("Cars List");
		btnCarsList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCarsListPanel();
			}
		});
		leftPanel.add(btnCarsList);

		JButton btnShowSearchPanel = new JButton("Search Panel");
		btnShowSearchPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showSearchPanel();
			}
		});
		leftPanel.add(btnShowSearchPanel);

		rightPanel = new JPanel();
		rightPanel.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.weighty = 1.0;
		gbc_rightPanel.insets = new Insets(5, 0, 5, 5);
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
		mainPanel.add(rightPanel, gbc_rightPanel);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] { 465, 0 };
		gbl_rightPanel.rowHeights = new int[] { 300, 20 };
		gbl_rightPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_rightPanel.rowWeights = new double[] { 0.0, 0.0 };
		rightPanel.setLayout(gbl_rightPanel);

		JPanel operationPanel = new JPanel();
		GridBagConstraints gbc_operationPanel = new GridBagConstraints();
		gbc_operationPanel.fill = GridBagConstraints.BOTH;
		gbc_operationPanel.weightx = 1.0;
		gbc_operationPanel.weighty = 1.0;
		gbc_operationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_operationPanel.gridx = 0;
		gbc_operationPanel.gridy = 0;
		rightPanel.add(operationPanel, gbc_operationPanel);
		operationPanel.setLayout(new CardLayout());

		firstPanel = new JPanel();
		operationPanel.add(firstPanel, "messagePanel");
		firstPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblMessagePanel = new JLabel("Select a Menu from left bar.");
		lblMessagePanel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMessagePanel.setHorizontalAlignment(SwingConstants.CENTER);
		firstPanel.add(lblMessagePanel, BorderLayout.CENTER);

		newOwnerPanel = new JPanel();
		operationPanel.add(newOwnerPanel, "newOwnerPanel");
		newOwnerPanel.setVisible(false);
		GridBagLayout gbl_newOwnerPanel = new GridBagLayout();
		gbl_newOwnerPanel.columnWidths = new int[] { 0, 0 };
		gbl_newOwnerPanel.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40, 20 };
		gbl_newOwnerPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_newOwnerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		newOwnerPanel.setLayout(gbl_newOwnerPanel);

		JLabel lblOwnerId = new JLabel("ID:");
		GridBagConstraints gbc_lblOwnerId = new GridBagConstraints();
		gbc_lblOwnerId.anchor = GridBagConstraints.EAST;
		gbc_lblOwnerId.insets = new Insets(0, 0, 0, 5);
		gbc_lblOwnerId.gridx = 0;
		gbc_lblOwnerId.gridy = 0;
		newOwnerPanel.add(lblOwnerId, gbc_lblOwnerId);

		txtOwnerId = new JTextField();
		txtOwnerId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtOwnerId = new GridBagConstraints();
		gbc_txtOwnerId.fill = GridBagConstraints.BOTH;
		gbc_txtOwnerId.gridx = 1;
		gbc_txtOwnerId.gridy = 0;
		newOwnerPanel.add(txtOwnerId, gbc_txtOwnerId);
		txtOwnerId.setColumns(10);

		JLabel lblOwnerName = new JLabel("Owner Name:");
		GridBagConstraints gbc_lblOwnerName = new GridBagConstraints();
		gbc_lblOwnerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnerName.anchor = GridBagConstraints.EAST;
		gbc_lblOwnerName.gridx = 0;
		gbc_lblOwnerName.gridy = 1;
		newOwnerPanel.add(lblOwnerName, gbc_lblOwnerName);

		txtOwnerName = new JTextField();
		txtOwnerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtOwnerName = new GridBagConstraints();
		gbc_txtOwnerName.insets = new Insets(0, 0, 5, 0);
		gbc_txtOwnerName.fill = GridBagConstraints.BOTH;
		gbc_txtOwnerName.gridx = 1;
		gbc_txtOwnerName.gridy = 1;
		newOwnerPanel.add(txtOwnerName, gbc_txtOwnerName);
		txtOwnerName.setColumns(10);

		JLabel lblOwnerFamily = new JLabel(" Owner Family:");
		GridBagConstraints gbc_lblOwnerFamily = new GridBagConstraints();
		gbc_lblOwnerFamily.anchor = GridBagConstraints.EAST;
		gbc_lblOwnerFamily.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnerFamily.gridx = 0;
		gbc_lblOwnerFamily.gridy = 2;
		newOwnerPanel.add(lblOwnerFamily, gbc_lblOwnerFamily);

		txtOwnerFamily = new JTextField();
		txtOwnerFamily.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtOwnerFamily = new GridBagConstraints();
		gbc_txtOwnerFamily.insets = new Insets(0, 0, 5, 0);
		gbc_txtOwnerFamily.fill = GridBagConstraints.BOTH;
		gbc_txtOwnerFamily.gridx = 1;
		gbc_txtOwnerFamily.gridy = 2;
		newOwnerPanel.add(txtOwnerFamily, gbc_txtOwnerFamily);
		txtOwnerFamily.setColumns(10);

		JLabel lblOwnerJob = new JLabel("Job:");
		GridBagConstraints gbc_lblOwnerJob = new GridBagConstraints();
		gbc_lblOwnerJob.anchor = GridBagConstraints.EAST;
		gbc_lblOwnerJob.insets = new Insets(0, 0, 5, 5);
		gbc_lblOwnerJob.gridx = 0;
		gbc_lblOwnerJob.gridy = 3;
		newOwnerPanel.add(lblOwnerJob, gbc_lblOwnerJob);

		txtOwnerJob = new JTextField();
		txtOwnerJob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtOwnerJob = new GridBagConstraints();
		gbc_txtOwnerJob.insets = new Insets(0, 0, 5, 0);
		gbc_txtOwnerJob.fill = GridBagConstraints.BOTH;
		gbc_txtOwnerJob.gridx = 1;
		gbc_txtOwnerJob.gridy = 3;
		newOwnerPanel.add(txtOwnerJob, gbc_txtOwnerJob);
		txtOwnerJob.setColumns(10);

		newCarPanel = new JPanel();
		operationPanel.add(newCarPanel, "newCarPanel");
		newCarPanel.setVisible(false);
		GridBagLayout gbl_newCarPanel = new GridBagLayout();
		gbl_newCarPanel.columnWidths = new int[] { 0, 0 };
		gbl_newCarPanel.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40, 20 };
		gbl_newCarPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_newCarPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		newCarPanel.setLayout(gbl_newCarPanel);

		JLabel lblCarNumber = new JLabel(" Car Number:");
		GridBagConstraints gbc_lblCarNumber = new GridBagConstraints();
		gbc_lblCarNumber.anchor = GridBagConstraints.EAST;
		gbc_lblCarNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarNumber.gridx = 0;
		gbc_lblCarNumber.gridy = 0;
		newCarPanel.add(lblCarNumber, gbc_lblCarNumber);

		txtCarNumber = new JTextField();
		txtCarNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtCarNumber = new GridBagConstraints();
		gbc_txtCarNumber.insets = new Insets(0, 0, 5, 0);
		gbc_txtCarNumber.fill = GridBagConstraints.BOTH;
		gbc_txtCarNumber.gridx = 1;
		gbc_txtCarNumber.gridy = 0;
		newCarPanel.add(txtCarNumber, gbc_txtCarNumber);
		txtCarNumber.setColumns(10);

		JLabel lblCarBrand = new JLabel("Car Brand:");
		GridBagConstraints gbc_lblCarBrand = new GridBagConstraints();
		gbc_lblCarBrand.anchor = GridBagConstraints.EAST;
		gbc_lblCarBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarBrand.gridx = 0;
		gbc_lblCarBrand.gridy = 1;
		newCarPanel.add(lblCarBrand, gbc_lblCarBrand);

		txtCarBrand = new JTextField();
		txtCarBrand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtCarBrand = new GridBagConstraints();
		gbc_txtCarBrand.insets = new Insets(0, 0, 5, 0);
		gbc_txtCarBrand.fill = GridBagConstraints.BOTH;
		gbc_txtCarBrand.gridx = 1;
		gbc_txtCarBrand.gridy = 1;
		newCarPanel.add(txtCarBrand, gbc_txtCarBrand);
		txtCarBrand.setColumns(10);

		JLabel lblCarProduceDate = new JLabel(" Produce Date:");
		GridBagConstraints gbc_lblCarProduceDate = new GridBagConstraints();
		gbc_lblCarProduceDate.anchor = GridBagConstraints.EAST;
		gbc_lblCarProduceDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarProduceDate.gridx = 0;
		gbc_lblCarProduceDate.gridy = 2;
		newCarPanel.add(lblCarProduceDate, gbc_lblCarProduceDate);

		txtCarProduceDate = new JTextField();
		txtCarProduceDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtCarProduceDate = new GridBagConstraints();
		gbc_txtCarProduceDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtCarProduceDate.fill = GridBagConstraints.BOTH;
		gbc_txtCarProduceDate.gridx = 1;
		gbc_txtCarProduceDate.gridy = 2;
		newCarPanel.add(txtCarProduceDate, gbc_txtCarProduceDate);
		txtCarProduceDate.setColumns(10);

		JLabel lblCarOwnerId = new JLabel("Owner Id:");
		GridBagConstraints gbc_lblCarOwnerId = new GridBagConstraints();
		gbc_lblCarOwnerId.anchor = GridBagConstraints.EAST;
		gbc_lblCarOwnerId.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarOwnerId.gridx = 0;
		gbc_lblCarOwnerId.gridy = 3;
		newCarPanel.add(lblCarOwnerId, gbc_lblCarOwnerId);

		cbxCarOwnerId = new JComboBox<String>(new ComboboxModel());

		GridBagConstraints gbc_cbxCarOwnerId = new GridBagConstraints();
		gbc_cbxCarOwnerId.insets = new Insets(0, 0, 5, 0);
		gbc_cbxCarOwnerId.fill = GridBagConstraints.BOTH;
		gbc_cbxCarOwnerId.gridx = 1;
		gbc_cbxCarOwnerId.gridy = 3;
		newCarPanel.add(cbxCarOwnerId, gbc_cbxCarOwnerId);

		ownersListPanel = new JPanel();
		operationPanel.add(ownersListPanel, "ownersListPanel");
		GridBagLayout gbl_ownersListPanel = new GridBagLayout();
		gbl_ownersListPanel.columnWidths = new int[] { 450 };
		gbl_ownersListPanel.rowHeights = new int[] { 20, 298 };
		gbl_ownersListPanel.columnWeights = new double[] { 1.0 };
		gbl_ownersListPanel.rowWeights = new double[] { 0.0, 1.0 };
		ownersListPanel.setLayout(gbl_ownersListPanel);

		JLabel lblOwnersListTitle = new JLabel("  ID - Name - Family - Job");
		lblOwnersListTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblOwnersListTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblOwnersListTitle = new GridBagConstraints();
		gbc_lblOwnersListTitle.anchor = GridBagConstraints.WEST;
		gbc_lblOwnersListTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblOwnersListTitle.gridx = 0;
		gbc_lblOwnersListTitle.gridy = 0;
		ownersListPanel.add(lblOwnersListTitle, gbc_lblOwnersListTitle);

		ownersList = new JList<String>();
		ownersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrolPane = new JScrollPane();
		scrolPane.setViewportView(ownersList);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		ownersListPanel.add(scrolPane, gbc_list);

		carsListPanel = new JPanel();
		operationPanel.add(carsListPanel, "carsListPanel");
		GridBagLayout gbl_carsListPanel = new GridBagLayout();
		gbl_carsListPanel.columnWidths = new int[] { 450 };
		gbl_carsListPanel.rowHeights = new int[] { 20, 298 };
		gbl_carsListPanel.columnWeights = new double[] { 1.0 };
		gbl_carsListPanel.rowWeights = new double[] { 0.0, 1.0 };
		carsListPanel.setLayout(gbl_carsListPanel);

		JLabel lblCarsListTitle = new JLabel("  Car Number - Car Brand - Produce Date - Owner ID");
		lblCarsListTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCarsListTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCarsListTitle = new GridBagConstraints();
		gbc_lblCarsListTitle.anchor = GridBagConstraints.WEST;
		gbc_lblCarsListTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarsListTitle.gridx = 0;
		gbc_lblCarsListTitle.gridy = 0;
		carsListPanel.add(lblCarsListTitle, gbc_lblCarsListTitle);

		carsList = new JList<String>();
		carsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrolPaneCar = new JScrollPane();
		scrolPaneCar.setViewportView(carsList);
		GridBagConstraints gbc_carslist = new GridBagConstraints();
		gbc_carslist.fill = GridBagConstraints.BOTH;
		gbc_carslist.gridx = 0;
		gbc_carslist.gridy = 1;
		carsListPanel.add(scrolPaneCar, gbc_carslist);

		searchPanel = new JPanel();
		operationPanel.add(searchPanel, "searchPanel");
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[] { 0, 0 };
		gbl_searchPanel.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40, 30 };
		gbl_searchPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_searchPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0 };
		searchPanel.setLayout(gbl_searchPanel);

		ButtonGroup grpSrcButtons = new ButtonGroup();

		rbtnSrcById = new JRadioButton("Search Owner By Id:");
		GridBagConstraints gbc_rbtnSrcOwnerId = new GridBagConstraints();
		gbc_rbtnSrcOwnerId.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcOwnerId.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcOwnerId.gridx = 0;
		gbc_rbtnSrcOwnerId.gridy = 0;
		searchPanel.add(rbtnSrcById, gbc_rbtnSrcOwnerId);

		grpSrcButtons.add(rbtnSrcById);

		txtSrcById = new JTextField();
		txtSrcById.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcById = new GridBagConstraints();
		gbc_txtSrcById.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcById.fill = GridBagConstraints.BOTH;
		gbc_txtSrcById.gridx = 1;
		gbc_txtSrcById.gridy = 0;
		txtSrcById.setEnabled(false);
		searchPanel.add(txtSrcById, gbc_txtSrcById);
		txtSrcById.setColumns(10);

		rbtnSrcByName = new JRadioButton("Search Owner By Name:");
		GridBagConstraints gbc_rbtnSrcByName = new GridBagConstraints();
		gbc_rbtnSrcByName.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcByName.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcByName.gridx = 0;
		gbc_rbtnSrcByName.gridy = 1;
		searchPanel.add(rbtnSrcByName, gbc_rbtnSrcByName);

		grpSrcButtons.add(rbtnSrcByName);

		txtSrcByName = new JTextField();
		txtSrcByName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcByName = new GridBagConstraints();
		gbc_txtSrcByName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcByName.fill = GridBagConstraints.BOTH;
		gbc_txtSrcByName.gridx = 1;
		gbc_txtSrcByName.gridy = 1;
		txtSrcByName.setEnabled(false);
		searchPanel.add(txtSrcByName, gbc_txtSrcByName);
		txtSrcByName.setColumns(10);

		rbtnSrcByFamily = new JRadioButton("Search Owner By Family:");
		GridBagConstraints gbc_rbtnSrcByFamily = new GridBagConstraints();
		gbc_rbtnSrcByFamily.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcByFamily.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcByFamily.gridx = 0;
		gbc_rbtnSrcByFamily.gridy = 2;
		searchPanel.add(rbtnSrcByFamily, gbc_rbtnSrcByFamily);

		grpSrcButtons.add(rbtnSrcByFamily);

		txtSrcByFamily = new JTextField();
		txtSrcByFamily.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcByFamily = new GridBagConstraints();
		gbc_txtSrcByFamily.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcByFamily.fill = GridBagConstraints.BOTH;
		gbc_txtSrcByFamily.gridx = 1;
		gbc_txtSrcByFamily.gridy = 2;
		txtSrcByFamily.setEnabled(false);
		searchPanel.add(txtSrcByFamily, gbc_txtSrcByFamily);
		txtSrcByFamily.setColumns(10);

		rbtnSrcNumberOfCarsById = new JRadioButton("Number of Owner's Cars By Id:");
		GridBagConstraints gbc_rbtnSrcNumberOfCarsById = new GridBagConstraints();
		gbc_rbtnSrcNumberOfCarsById.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcNumberOfCarsById.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcNumberOfCarsById.gridx = 0;
		gbc_rbtnSrcNumberOfCarsById.gridy = 3;
		searchPanel.add(rbtnSrcNumberOfCarsById, gbc_rbtnSrcNumberOfCarsById);

		grpSrcButtons.add(rbtnSrcNumberOfCarsById);

		txtSrcNumberOfCarsById = new JTextField();
		txtSrcNumberOfCarsById.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcNumberOfCarsById = new GridBagConstraints();
		gbc_txtSrcNumberOfCarsById.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcNumberOfCarsById.fill = GridBagConstraints.BOTH;
		gbc_txtSrcNumberOfCarsById.gridx = 1;
		gbc_txtSrcNumberOfCarsById.gridy = 3;
		txtSrcNumberOfCarsById.setEnabled(false);
		searchPanel.add(txtSrcNumberOfCarsById, gbc_txtSrcNumberOfCarsById);
		txtSrcNumberOfCarsById.setColumns(10);

		rbtnSrcOldestCar = new JRadioButton("Find Oldest Car");
		GridBagConstraints gbc_rbtnSrcOldestCar = new GridBagConstraints();
		gbc_rbtnSrcOldestCar.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcOldestCar.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcOldestCar.gridx = 0;
		gbc_rbtnSrcOldestCar.gridy = 4;
		searchPanel.add(rbtnSrcOldestCar, gbc_rbtnSrcOldestCar);

		grpSrcButtons.add(rbtnSrcOldestCar);

		rbtnSrcOldestCarById = new JRadioButton("Find Oldest Car By Id:");
		GridBagConstraints gbc_rbtnSrcOldestCarById = new GridBagConstraints();
		gbc_rbtnSrcOldestCarById.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcOldestCarById.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcOldestCarById.gridx = 0;
		gbc_rbtnSrcOldestCarById.gridy = 5;
		searchPanel.add(rbtnSrcOldestCarById, gbc_rbtnSrcOldestCarById);

		grpSrcButtons.add(rbtnSrcOldestCarById);

		txtSrcOldestCarById = new JTextField();
		txtSrcOldestCarById.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcOldestCarById = new GridBagConstraints();
		gbc_txtSrcOldestCarById.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcOldestCarById.fill = GridBagConstraints.BOTH;
		gbc_txtSrcOldestCarById.gridx = 1;
		gbc_txtSrcOldestCarById.gridy = 5;
		txtSrcOldestCarById.setEnabled(false);
		searchPanel.add(txtSrcOldestCarById, gbc_txtSrcOldestCarById);
		txtSrcOldestCarById.setColumns(10);

		rbtnSrcOldestCarByName = new JRadioButton("Find Oldest Car By Name:");
		GridBagConstraints gbc_rbtnSrcOldestCarByName = new GridBagConstraints();
		gbc_rbtnSrcOldestCarByName.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcOldestCarByName.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnSrcOldestCarByName.gridx = 0;
		gbc_rbtnSrcOldestCarByName.gridy = 6;
		searchPanel.add(rbtnSrcOldestCarByName, gbc_rbtnSrcOldestCarByName);

		grpSrcButtons.add(rbtnSrcOldestCarByName);

		txtSrcOldestCarByName = new JTextField();
		txtSrcOldestCarByName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_txtSrcOldestCarByName = new GridBagConstraints();
		gbc_txtSrcOldestCarByName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSrcOldestCarByName.fill = GridBagConstraints.BOTH;
		gbc_txtSrcOldestCarByName.gridx = 1;
		gbc_txtSrcOldestCarByName.gridy = 6;
		txtSrcOldestCarByName.setEnabled(false);
		searchPanel.add(txtSrcOldestCarByName, gbc_txtSrcOldestCarByName);
		txtSrcOldestCarByName.setColumns(10);

		rbtnSrcOldestCarByFamily = new JRadioButton("Find Oldest Car By Family");
		GridBagConstraints gbc_rbtnSrcOldestCarByFamily = new GridBagConstraints();
		gbc_rbtnSrcOldestCarByFamily.anchor = GridBagConstraints.WEST;
		gbc_rbtnSrcOldestCarByFamily.insets = new Insets(0, 0, 0, 5);
		gbc_rbtnSrcOldestCarByFamily.gridx = 0;
		gbc_rbtnSrcOldestCarByFamily.gridy = 7;
		searchPanel.add(rbtnSrcOldestCarByFamily, gbc_rbtnSrcOldestCarByFamily);

		grpSrcButtons.add(rbtnSrcOldestCarByFamily);

		txtSrcOldestCarByFamily = new JTextField();
		GridBagConstraints gbc_txtSrcOldestCaryFamily = new GridBagConstraints();
		gbc_txtSrcOldestCaryFamily.fill = GridBagConstraints.BOTH;
		gbc_txtSrcOldestCaryFamily.gridx = 1;
		gbc_txtSrcOldestCaryFamily.gridy = 7;
		txtSrcOldestCarByFamily.setEnabled(false);
		searchPanel.add(txtSrcOldestCarByFamily, gbc_txtSrcOldestCaryFamily);
		txtSrcOldestCarByFamily.setColumns(10);

		footerPanel = new JPanel();
		footerPanel.setBackground(Color.GRAY);
		footerPanel.setVisible(false);
		GridBagConstraints gbc_footerPanel = new GridBagConstraints();
		gbc_footerPanel.anchor = GridBagConstraints.NORTH;
		gbc_footerPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_footerPanel.gridx = 0;
		gbc_footerPanel.gridy = 1;
		rightPanel.add(footerPanel, gbc_footerPanel);

		rbtnSrcById.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcById.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcByName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcByName.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcByFamily.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcByFamily.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcNumberOfCarsById.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcNumberOfCarsById.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcOldestCar.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcById.setEnabled(false);
				txtSrcByName.setEnabled(false);
				txtSrcByFamily.setEnabled(false);
				txtSrcOldestCarById.setEnabled(false);
				txtSrcOldestCarByName.setEnabled(false);
				txtSrcOldestCarByFamily.setEnabled(false);
			}
		});

		rbtnSrcOldestCarById.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcOldestCarById.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcOldestCarByName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcOldestCarByName.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		rbtnSrcOldestCarByFamily.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtSrcOldestCarByFamily.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
			}
		});

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
		footerPanel.add(btnSave);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editAction();
			}
		});
		btnEdit.setVisible(false);
		footerPanel.add(btnEdit);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		btnDelete.setVisible(false);
		footerPanel.add(btnDelete);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchAction();
			}
		});
		btnSearch.setVisible(false);
		footerPanel.add(btnSearch);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showFirstPanel();
			}
		});
		footerPanel.add(btnCancel);

	}

	private void showNewOwnerPanel() {
		firstPanel.setVisible(false);
		newCarPanel.setVisible(false);
		ownersListPanel.setVisible(false);
		carsListPanel.setVisible(false);
		searchPanel.setVisible(false);

		newOwnerPanel.setVisible(true);
		footerPanel.setVisible(true);
		btnSave.setVisible(true);
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
		btnSearch.setVisible(false);

		if (selectedOwner == null) {
			txtOwnerId.setEnabled(true);
			txtOwnerId.setText("");
			txtOwnerName.setText("");
			txtOwnerFamily.setText("");
			txtOwnerJob.setText("");
		}
	}

	private void showNewCarPanel() {
		if (ownersArrayList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Add atleast one Owner before adding a new car.");
			return;
		}

		firstPanel.setVisible(false);
		newOwnerPanel.setVisible(false);
		ownersListPanel.setVisible(false);
		carsListPanel.setVisible(false);
		searchPanel.setVisible(false);

		newCarPanel.setVisible(true);
		footerPanel.setVisible(true);
		btnSave.setVisible(true);
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
		btnSearch.setVisible(false);

		ArrayList<String> ownersIds = new ArrayList<>();
		for (int i = 0; i < ownersArrayList.size(); i++) {
			String[] splitted = ownersArrayList.get(i).split("-");
			ownersIds.add(splitted[0].trim());
		}
		((ComboboxModel) cbxCarOwnerId.getModel()).updateIds(ownersIds);
		if (selectedCar == null) {
			txtCarNumber.setEnabled(true);
			txtCarNumber.setText("");
			txtCarBrand.setText("");
			txtCarProduceDate.setText("");
		}
	}

	private void showSearchPanel() {
		firstPanel.setVisible(false);
		newOwnerPanel.setVisible(false);
		newCarPanel.setVisible(false);
		ownersListPanel.setVisible(false);
		carsListPanel.setVisible(false);

		searchPanel.setVisible(true);
		footerPanel.setVisible(true);
		btnSearch.setVisible(true);
		btnSave.setVisible(false);
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
	}

	private void showOwnersListPanel() {
		ownersList.setModel(new AbstractListModel<String>() {

			@Override
			public int getSize() {
				return ownersArrayList.size();
			}

			@Override
			public String getElementAt(int index) {
				// TODO Auto-generated method stub
				return ownersArrayList.get(index);
			}
		});
		firstPanel.setVisible(false);
		newOwnerPanel.setVisible(false);
		newCarPanel.setVisible(false);
		carsListPanel.setVisible(false);
		searchPanel.setVisible(false);

		ownersListPanel.setVisible(true);
		footerPanel.setVisible(true);
		btnSave.setVisible(false);
		btnSearch.setVisible(false);
		btnEdit.setVisible(true);
		btnDelete.setVisible(true);

	}

	private void showCarsListPanel() {
		carsList.setModel(new AbstractListModel<String>() {

			@Override
			public int getSize() {
				return carsArrayList.size();
			}

			@Override
			public String getElementAt(int index) {
				// TODO Auto-generated method stub
				return carsArrayList.get(index);
			}
		});
		firstPanel.setVisible(false);
		newOwnerPanel.setVisible(false);
		newCarPanel.setVisible(false);
		ownersListPanel.setVisible(false);
		searchPanel.setVisible(false);

		carsListPanel.setVisible(true);
		footerPanel.setVisible(true);
		btnSave.setVisible(false);
		btnSearch.setVisible(false);
		btnEdit.setVisible(true);
		btnDelete.setVisible(true);

	}

	private void showFirstPanel() {
		newOwnerPanel.setVisible(false);
		newCarPanel.setVisible(false);
		ownersListPanel.setVisible(false);
		carsListPanel.setVisible(false);
		searchPanel.setVisible(false);
		firstPanel.setVisible(true);
		footerPanel.setVisible(false);

	}

	private void saveAction() {
		if (newOwnerPanel.isVisible())
			saveNewOwner();
		else if (newCarPanel.isVisible())
			saveNewCar();
	}

	private void saveNewOwner() {
		if (selectedOwner == null) {
			String strId = txtOwnerId.getText();
			if (strId.isBlank() || strId.length() != 2) {
				JOptionPane.showMessageDialog(this, "Invalid Id Number. \nEnter 2 digit for owner ID.");
				return;
			}

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] splitted = ownersArrayList.get(i).split("-");
				if (strId.equals(splitted[0].trim())) {
					JOptionPane.showMessageDialog(this, "Duplicate Id number. Enter new Id number.");
					return;
				}
			}

			try {
				Integer.parseInt(strId);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid Id Number. \nEnter 2 digits integer number.");
				return;
			}
			String strName = txtOwnerName.getText();
			if (strName.isBlank() || strName.length() > 8) {
				JOptionPane.showMessageDialog(this, "Invalid Name. \nEnter maximum 8 characters for owner name.");
				return;
			}

			String strFamily = txtOwnerFamily.getText();
			if (strFamily.isBlank() || strFamily.length() > 8) {
				JOptionPane.showMessageDialog(this, "Invalid Family. \nEnter maximum 8 characters for owner family.");
				return;
			}

			String strJob = txtOwnerJob.getText();
			if (strJob.isBlank() || strJob.length() > 6) {
				JOptionPane.showMessageDialog(this, "Invalid Job. \nEnter maximum 6 characters for owner job.");
				return;
			}

			String newOwner = strId + "," + strName + "," + strFamily + "," + strJob;

			FileWriter writer;
			try {
				writer = new FileWriter(new File("owner.csv"), true);
				writer.append(newOwner + "\n");
				writer.close();
				String[] splitted = newOwner.split(",");
				ownersArrayList
						.add("  " + splitted[0] + " - " + splitted[1] + " - " + splitted[2] + " - " + splitted[3]);
				JOptionPane.showMessageDialog(this, "New Owner Saved");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showFirstPanel();
		} else {
			String strId = txtOwnerId.getText();
			String strName = txtOwnerName.getText();
			if (strName.isBlank() || strName.length() > 8) {
				JOptionPane.showMessageDialog(this, "Invalid Name. \nEnter maximum 8 characters for owner name.");
				return;
			}

			String strFamily = txtOwnerFamily.getText();
			if (strFamily.isBlank() || strFamily.length() > 8) {
				JOptionPane.showMessageDialog(this, "Invalid Family. \nEnter maximum 8 characters for owner family.");
				return;
			}

			String strJob = txtOwnerJob.getText();
			if (strJob.isBlank() || strJob.length() > 6) {
				JOptionPane.showMessageDialog(this, "Invalid Job. \nEnter maximum 6 characters for owner job.");
				return;
			}
			String editedOwner = "  " + strId + " - " + strName + " - " + strFamily + " - " + strJob;
			ownersArrayList.set(ownersArrayList.indexOf(selectedOwner), editedOwner);

			try {
				File temp = new File("owner-temp.csv");
				File ownerFile = new File("owner.csv");
				FileWriter writer = new FileWriter(temp, true);
				writer.append("Id,Name,Family,Job\n");
				for (int i = 0; i < ownersArrayList.size(); i++) {
					String[] splitted = ownersArrayList.get(i).split("-");
					String newOwner = splitted[0].trim() + "," + splitted[1].trim() + "," + splitted[2].trim() + ","
							+ splitted[3].trim();
					writer.append(newOwner + "\n");
				}
				writer.close();
				ownerFile.delete();
				temp.renameTo(ownerFile);
				JOptionPane.showMessageDialog(this, "Owner Edited.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectedOwner = null;
			showOwnersListPanel();

		}
	}

	private void saveNewCar() {
		if (selectedCar == null) {
			String strNumber = txtCarNumber.getText();
			if (strNumber.isBlank() || strNumber.length() != 3) {
				JOptionPane.showMessageDialog(this, "Invalid Car Number. \nEnter 3 digit for Car Number.");
				return;
			}

			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				if (strNumber.equals(splitted[0].trim())) {
					JOptionPane.showMessageDialog(this, "Duplicate Car number. Enter new Car number.");
					return;
				}
			}

			try {
				Integer.parseInt(strNumber);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid Car Number. \nEnter 3 digits integer number.");
				return;
			}
			String strBrand = txtCarBrand.getText();
			if (strBrand.isBlank() || strBrand.length() > 5) {
				JOptionPane.showMessageDialog(this, "Invalid Car Brand. \nEnter maximum 5 characters for Car Brand.");
				return;
			}

			String strDate = txtCarProduceDate.getText();
			if (strDate.isBlank() || strDate.length() != 4) {
				JOptionPane.showMessageDialog(this, "Invalid Produce Date. \nEnter 4 digits for Car Produce Date.");
				return;
			}

			try {
				Integer.parseInt(strDate);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid Produce Date. \nEnter 4 digits as poduce date.");
				return;
			}

			String strOwnerId = (String) cbxCarOwnerId.getSelectedItem();
			if (strOwnerId == null || strOwnerId.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid Owner Id. \nSelect an Owner ID for Car.");
				return;
			}

			String newCar = strNumber + "," + strBrand + "," + strDate + "," + strOwnerId;

			FileWriter writer;
			try {
				writer = new FileWriter(new File("car.csv"), true);
				writer.append(newCar + "\n");
				writer.close();
				String[] splitted = newCar.split(",");
				carsArrayList.add("  " + splitted[0] + " - " + splitted[1] + " - " + splitted[2] + " - " + splitted[3]);
				JOptionPane.showMessageDialog(this, "New Car Saved");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showFirstPanel();
		} else {
			String strNumber = txtCarNumber.getText();

			String strBrand = txtCarBrand.getText();
			if (strBrand.isBlank() || strBrand.length() > 5) {
				JOptionPane.showMessageDialog(this, "Invalid Car Brand. \nEnter maximum 5 characters for Car Brand.");
				return;
			}

			String strDate = txtCarProduceDate.getText();
			if (strDate.isBlank() || strDate.length() != 4) {
				JOptionPane.showMessageDialog(this, "Invalid Produce Date. \nEnter 4 digits for Car Produce Date.");
				return;
			}

			try {
				Integer.parseInt(strDate);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid Produce Date. \nEnter 4 digits as poduce date.");
				return;
			}

			String strOwnerId = (String) cbxCarOwnerId.getSelectedItem();
			if (strOwnerId == null || strOwnerId.isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid Owner Id. \nSelect an Owner ID for Car.");
				return;
			}

			String editedCar = "  " + strNumber + " - " + strBrand + " - " + strDate + " - " + strOwnerId;
			carsArrayList.set(carsArrayList.indexOf(selectedCar), editedCar);

			try {
				File temp = new File("car-temp.csv");
				File carFile = new File("car.csv");
				FileWriter writer = new FileWriter(temp, true);
				writer.append("Car Number,Car Brand,Produce Date,Owner Id\n");
				for (int i = 0; i < carsArrayList.size(); i++) {
					String[] splitted = carsArrayList.get(i).split("-");
					String newCar = splitted[0].trim() + "," + splitted[1].trim() + "," + splitted[2].trim() + ","
							+ splitted[3].trim();
					writer.append(newCar + "\n");
				}
				writer.close();
				carFile.delete();
				temp.renameTo(carFile);
				JOptionPane.showMessageDialog(this, "Car Edited.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectedCar = null;
			showCarsListPanel();
		}
	}

	private void editAction() {
		if (ownersListPanel.isVisible())
			editOwner();
		else if (carsListPanel.isVisible())
			editCar();
	}

	private void editOwner() {
		selectedOwner = ownersList.getSelectedValue();
		if (selectedOwner == null) {
			JOptionPane.showMessageDialog(this, "Select an Owner to Edit it");
			return;
		}
		int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit?", "Editing Owner",
				JOptionPane.YES_NO_OPTION);
		if (option != JOptionPane.YES_OPTION)
			return;

		txtOwnerId.setEnabled(false);
		String[] editingOwnerData = selectedOwner.split("-");
		txtOwnerId.setText(editingOwnerData[0].trim());
		txtOwnerName.setText(editingOwnerData[1].trim());
		txtOwnerFamily.setText(editingOwnerData[2].trim());
		txtOwnerJob.setText(editingOwnerData[3].trim());
		showNewOwnerPanel();
	}

	private void editCar() {
		selectedCar = carsList.getSelectedValue();
		if (selectedCar == null) {
			JOptionPane.showMessageDialog(this, "Select a Car to Edit it");
			return;
		}
		int option = JOptionPane.showConfirmDialog(this, "Are you sure to edit?", "Editing Car",
				JOptionPane.YES_NO_OPTION);
		if (option != JOptionPane.YES_OPTION)
			return;

		txtCarNumber.setEnabled(false);
		String[] editingCarData = selectedCar.split("-");
		txtCarNumber.setText(editingCarData[0].trim());
		txtCarBrand.setText(editingCarData[1].trim());
		txtCarProduceDate.setText(editingCarData[2].trim());
		showNewCarPanel();
	}

	private void deleteAction() {
		if (ownersListPanel.isVisible())
			deleteOwner();
		else if (carsListPanel.isVisible())
			deleteCar();
	}

	private void deleteOwner() {
		String selectedOwner = ownersList.getSelectedValue();
		if (selectedOwner == null) {
			JOptionPane.showMessageDialog(this, "Select an Owner to delete it");
			return;
		}
		int deletingOption = JOptionPane.showConfirmDialog(this,
				"Owner and related Cars will be deleted.\n Are you sure?", "Deleting Owner", JOptionPane.YES_NO_OPTION);
		if (deletingOption != JOptionPane.YES_OPTION)
			return;

		ownersArrayList.remove(selectedOwner);

		try {
			File temp = new File("owner-temp.csv");
			File ownerFile = new File("owner.csv");
			FileWriter writer = new FileWriter(temp, true);
			writer.append("Id,Name,Family,Job\n");
			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] splitted = ownersArrayList.get(i).split("-");
				String newOwner = splitted[0].trim() + "," + splitted[1].trim() + "," + splitted[2].trim() + ","
						+ splitted[3].trim();
				writer.append(newOwner + "\n");
			}
			writer.close();
			ownerFile.delete();
			deleteOwnerCars(selectedOwner);
			temp.renameTo(ownerFile);
			JOptionPane.showMessageDialog(this, "Owner deleted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedOwner = null;
		showOwnersListPanel();
	}

	private void deleteOwnerCars(String selectedOwner) {
		String strId = selectedOwner.split("-")[0].trim();
		ArrayList<String> ownersCars = new ArrayList<>();
		for (int i = 0; i < carsArrayList.size(); i++) {
			String[] splitted = carsArrayList.get(i).split("-");
			if (strId.equals(splitted[3].trim()))
				ownersCars.add(carsArrayList.get(i));
		}

		if (ownersCars.isEmpty())
			return;
		carsArrayList.removeAll(ownersCars);
		try {
			File temp = new File("car-temp.csv");
			File carFile = new File("car.csv");
			FileWriter writer = new FileWriter(temp, true);
			writer.append("Car Number,Car Brand,Produce Date,Owner Id\n");
			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				String newCar = splitted[0].trim() + "," + splitted[1].trim() + "," + splitted[2].trim() + ","
						+ splitted[3].trim();
				writer.append(newCar + "\n");
			}
			writer.close();
			carFile.delete();
			temp.renameTo(carFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteCar() {
		selectedCar = carsList.getSelectedValue();
		if (selectedCar == null) {
			JOptionPane.showMessageDialog(this, "Select a Car to delete it");
			return;
		}
		int deletingOption = JOptionPane.showConfirmDialog(this, "Are you sure?", "Deleting Car",
				JOptionPane.YES_NO_OPTION);
		if (deletingOption != JOptionPane.YES_OPTION)
			return;

		carsArrayList.remove(selectedCar);

		try {
			File temp = new File("car-temp.csv");
			File carFile = new File("car.csv");
			FileWriter writer = new FileWriter(temp, true);
			writer.append("Car Number,Car Brand,Produce Date,Owner Id\n");
			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				String newCar = splitted[0].trim() + "," + splitted[1].trim() + "," + splitted[2].trim() + ","
						+ splitted[3].trim();
				writer.append(newCar + "\n");
			}
			writer.close();
			carFile.delete();
			temp.renameTo(carFile);
			JOptionPane.showMessageDialog(this, "Car deleted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedCar = null;
		showCarsListPanel();
	}

	private void searchAction() {

		if (rbtnSrcById.isSelected()) {
			String id = txtSrcById.getText();
			if (id.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter an ID to search an Owner with.");
				return;
			}
			String result = "No such Owner found.";

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] splitted = ownersArrayList.get(i).split("-");
				if (id.trim().equals(splitted[0].trim())) {
					result = "Owner found data:";
					result += "\n---------------------------";
					result += "\n - Name: " + splitted[1];
					result += "\n - Family: " + splitted[2];
					result += "\n - Job: " + splitted[3];
					result += "\n - ID: " + splitted[0];
					result += "\n---------------------------";
					break;
				}
			}
			JOptionPane.showMessageDialog(this, result);

		} else if (rbtnSrcByName.isSelected()) {
			String name = txtSrcByName.getText();
			if (name.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter a Name to search an Owner with.");
				return;
			}
			String result = "";

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] splitted = ownersArrayList.get(i).split("-");
				if (name.trim().equals(splitted[1].trim())) {
					result += "\nOwner found data:";
					result += "\n---------------------------";
					result += "\n - Name: " + splitted[1];
					result += "\n - Family: " + splitted[2];
					result += "\n - Job: " + splitted[3];
					result += "\n - ID: " + splitted[0];
					result += "\n---------------------------";
				}
			}
			if (result.isBlank())
				result = "No such Owner found.";
			JOptionPane.showMessageDialog(this, result);
		} else if (rbtnSrcByFamily.isSelected()) {
			String family = txtSrcByFamily.getText();
			if (family.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter a Family to search an Owner with.");
				return;
			}
			String result = "";

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] splitted = ownersArrayList.get(i).split("-");
				if (family.trim().equals(splitted[2].trim())) {
					result += "\nOwner found data:";
					result += "\n---------------------------";
					result += "\n - Name: " + splitted[1];
					result += "\n - Family: " + splitted[2];
					result += "\n - Job: " + splitted[3];
					result += "\n - ID: " + splitted[0];
					result += "\n---------------------------";
				}
			}
			if (result.isBlank())
				result = "No such Owner found.";
			JOptionPane.showMessageDialog(this, result);
		} else if (rbtnSrcNumberOfCarsById.isSelected()) {
			String id = txtSrcNumberOfCarsById.getText();
			if (id.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter an ID to search Number of Cars of Owner.");
				return;
			}
			int numberOfCar = 0;

			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				if (id.trim().equals(splitted[3].trim()))
					numberOfCar++;
			}
			if (numberOfCar == 0)
				JOptionPane.showMessageDialog(this, " There is no car for this owner ID.");
			else
				JOptionPane.showMessageDialog(this, " Number of Car for ID " + id + " is " + numberOfCar);
		} else if (rbtnSrcOldestCar.isSelected()) {
			if (carsArrayList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "There is No car added to find oldest.");
				return;
			}
			int oldestYear = Integer.MAX_VALUE;
			String result = "";

			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				int carProduceDate = Integer.parseInt(splitted[2].trim());
				if (carProduceDate <= oldestYear) {
					oldestYear = carProduceDate;
					result = "The Oldest Car is " + carsArrayList.get(i);
				}
			}
			JOptionPane.showMessageDialog(this, result);
		} else if (rbtnSrcOldestCarById.isSelected()) {
			if (carsArrayList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "There is No car added to find oldest.");
				return;
			}

			String id = txtSrcOldestCarById.getText();
			if (id.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter an ID to search Oldest Car of Owner.");
				return;
			}

			int oldestYear = Integer.MAX_VALUE;
			String result = "";

			for (int i = 0; i < carsArrayList.size(); i++) {
				String[] splitted = carsArrayList.get(i).split("-");
				if (id.equals(splitted[3].trim())) {
					int carProduceDate = Integer.parseInt(splitted[2].trim());
					if (carProduceDate <= oldestYear) {
						oldestYear = carProduceDate;
						result = "The Oldest Car for ID " + id + " is " + carsArrayList.get(i);
					}
				}
			}
			if (result.isBlank())
				JOptionPane.showMessageDialog(this, " There is no car for this owner ID.");
			else
				JOptionPane.showMessageDialog(this, result);
		} else if (rbtnSrcOldestCarByName.isSelected()) {
			if (carsArrayList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "There is No car added to find oldest.");
				return;
			}

			String name = txtSrcOldestCarByName.getText();
			if (name.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter a Name to search Oldest Car of Owner.");
				return;
			}

			int oldestYear = Integer.MAX_VALUE;
			String result = "";
			String id = "";

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] ownerSplitted = ownersArrayList.get(i).split("-");
				if (name.equals(ownerSplitted[1].trim()))
					id = ownerSplitted[0].trim();
					for (int j = 0; j < carsArrayList.size(); j++) {
						String[] splitted = carsArrayList.get(j).split("-");
						if (id.equals(splitted[3].trim())) {
							int carProduceDate = Integer.parseInt(splitted[2].trim());
							if (carProduceDate <= oldestYear) {
								oldestYear = carProduceDate;
								result = "The Oldest Car for Name "+ name + " is " + carsArrayList.get(j);
							}
						}
					}
			}
			if (result.isBlank())
				JOptionPane.showMessageDialog(this, " There is no car for this owner Name.");
			else
				JOptionPane.showMessageDialog(this, result);
		} else if (rbtnSrcOldestCarByFamily.isSelected()) {
			if (carsArrayList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "There is No car added to find oldest.");
				return;
			}

			String family = txtSrcOldestCarByFamily.getText();
			if (family.isBlank()) {
				JOptionPane.showMessageDialog(this, "Enter a Family to search Oldest Car of Owner.");
				return;
			}

			int oldestYear = Integer.MAX_VALUE;
			String result = "";
			String id = "";

			for (int i = 0; i < ownersArrayList.size(); i++) {
				String[] ownerSplitted = ownersArrayList.get(i).split("-");
				if (family.equals(ownerSplitted[2].trim()))
					id = ownerSplitted[0].trim();
					for (int j = 0; j < carsArrayList.size(); j++) {
						String[] splitted = carsArrayList.get(j).split("-");
						if (id.equals(splitted[3].trim())) {
							int carProduceDate = Integer.parseInt(splitted[2].trim());
							if (carProduceDate <= oldestYear) {
								oldestYear = carProduceDate;
								result = "The Oldest Car for Family "+ family + " is " + carsArrayList.get(j);
							}
						}
					}
			}
			if (result.isBlank())
				JOptionPane.showMessageDialog(this, " There is no car for this owner Family.");
			else
				JOptionPane.showMessageDialog(this, result);
		}
		else
			JOptionPane.showMessageDialog(this, "Select an option to search.");
	}

	public static void main(String[] args) {
		loadData();
		new MainForm().setVisible(true);
	}

	private class ComboboxModel extends DefaultComboBoxModel<String> {
		private List<String> ids;

		public ComboboxModel() {
			ids = new ArrayList<>();
		}

		@Override
		public int getSize() {

			return ids.size();
		}

		@Override
		public String getElementAt(int index) {
			// TODO Auto-generated method stub
			return ids.get(index);
		}

		@Override
		public int getIndexOf(Object anObject) {
			// TODO Auto-generated method stub
			return ids.indexOf(anObject);
		}

		public void updateIds(ArrayList<String> newIds) {
			ids.clear();
			ids.addAll(newIds);
		}
	}
}
