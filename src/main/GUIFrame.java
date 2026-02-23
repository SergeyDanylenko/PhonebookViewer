package main;

import DAO.impl.EmployeeDAOImpl;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import logic.Employee;
import property.EditConfig;
import util.HibernateUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

import static DAO.Factory.getInstance;

public class GUIFrame {
    private JPanel rootPanel;
    private JTable table1 = new JTable() {
        public boolean isCellEditable(int rowIndex, int colIndex) {
            return false; //Disallow the editing of any cell
        }

        @Override
        public int getRowHeight() {
            return 25;
        }
    };
    private JTabbedPane tabbedPane1;
    private JPanel tabABC;
    private JPanel tabDepartment;
    private JTabbedPane tabbedPane2;
    private JPanel panelStatus;
    private JPanel rightPanel;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDel;
    private JButton btnSave;
    private JButton btnMail;
    private JButton btnExit;
    private JTextField textFieldSearch;
    private JButton btnSearch;
    private JScrollPane scrollEmployeesABC;
    private JPanel panelDetailsLast_name;
    private JPanel panelDetailsFirst_name;
    private JPanel panelDetailsMiddle_name;
    private JPanel panelDetailsPost_name;
    private JPanel panelDetailsCategory_name;
    private JPanel panelDetailsWork_phone;
    private JPanel panelDetailsHome_phone;
    private JPanel panelDetailsEmail_address;
    private JPanel panelDetailsNote_string;
    private JTextField txtFld_last;
    private JTextField txtFld_first;
    private JTextField txtFld_middle;
    private JPanel panelButtons;
    private JTextField txtFld_post;
    private JTextField txtFld_category;
    private JTextField txtFld_work;
    private JTextField txtFld_home;
    private JTextField txtFld_mail;
    private JTextField txtFld_note;
    private JList<String> listDepartments;
    private JPanel menuRootPanel;
    private JPanel panelJMenuBar;
    private JMenuBar mainBar;
    private JMenu menuFile;
    private JMenuItem importItem;
    private JMenuItem exportItem;
    private JMenuItem propsItem;
    private JMenuItem exitItem;
    private JMenu menuInfo;
    private JMenuItem infoItem;
    private JPanel panelDetails;
    private JPanel pan0;
    private JPanel pan1;
    private JPanel pan2;
    private JPanel pan3;
    private JPanel pan4;
    private JPanel pan5;
    private JPanel pan6;
    private JPanel pan7;
    private JPanel pan8;
    private JPanel pan9;
    private JPanel pan10;
    private JPanel pan11;
    private JPanel pan12;
    private JPanel pan13;
    private JPanel pan14;
    private JPanel pan15;
    private JPanel pan16;
    private JPanel pan17;
    private JPanel pan18;
    private JPanel pan19;
    private JPanel pan20;
    private JPanel pan21;
    private JPanel pan22;
    private JPanel pan23;
    private JPanel pan24;
    private JPanel pan25;
    private JPanel pan26;
    private JPanel pan27;
    private JPanel pan28;
    private JPanel pan29;
    private JList<String> list0;
    private JList<String> list1;
    private JList<String> list2;
    private JList<String> list3;
    private JList<String> list4;
    private JList<String> list5;
    private JList<String> list6;
    private JList<String> list7;
    private JList<String> list8;
    private JList<String> list9;
    private JList<String> list10;
    private JList<String> list11;
    private JList<String> list12;
    private JList<String> list13;
    private JList<String> list14;
    private JList<String> list15;
    private JList<String> list16;
    private JList<String> list17;
    private JList<String> list18;
    private JList<String> list19;
    private JList<String> list20;
    private JList<String> list21;
    private JList<String> list22;
    private JList<String> list23;
    private JList<String> list24;
    private JList<String> list25;
    private JList<String> list26;
    private JList<String> list27;
    private JList<String> list28;
    private JList<String> list29;
    private JList<String> selectedList;
    private ArrayList<Employee> selectedListEmployees;
    private int selectedID;
    private boolean isSelOtherList;
    private String selectedTabName;
    private SystemTray tray;
    private TrayIcon trayIcon;
    private boolean firstRoll = true;
    private DefaultTableCellRenderer centerRenderer;
    private Font font14pt = new Font("Segoe UI", Font.PLAIN, 14);
    private EditConfig editConfig = new EditConfig("config.ini");
    private ArrayList<Employee> allEmployees;
    private String[] abcTP = new String[]{"А", "Б", "В", "Г", "Д", "Е", "Є", "Ж", "З", "І", "Ї", "К", "Л", "М", "Н", "О",
            "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ю", "Я"};
    private JFrame mainFrame;

    public ArrayList<Employee> getAllEmployees() {
        return getInstance().getEmployeeDAO().getAllEmployees();
    }

    public ArrayList<Employee> getEmployeesByFirstChar(String firstChar) {
        ArrayList<Employee> employeesABC = new ArrayList<>();
        for (Employee allEmployee : allEmployees) {
            if (allEmployee.getShort_name().substring(0, 1).equals(firstChar)) {
                employeesABC.add(allEmployee);
            }
        }
        employeesABC.sort(Employee.COMPARE_BY_SHORT_NAME);
        return employeesABC;
    }

    public ArrayList<Employee> getEmployeesOther() {
        ArrayList<Employee> employeesABC = new ArrayList<>();
        ArrayList<String> availableCharsFromTP = new ArrayList<>();
        ArrayList<String> allCharsFromDB = new ArrayList<>();

        for (Employee allEmployee : allEmployees) {
            allCharsFromDB.add(allEmployee.getHeader_name());
        }
        TreeSet<String> treeSet = new TreeSet(allCharsFromDB);
        allCharsFromDB = new ArrayList<>(treeSet);
        allCharsFromDB.sort(Comparator.<String>naturalOrder());

        for (int i = 0; i < tabbedPane2.getTabCount(); i++) {
            if (!tabbedPane2.getTitleAt(i).equals("Інші")) {
                availableCharsFromTP.add(tabbedPane2.getTitleAt(i));
            }
        }
        availableCharsFromTP.sort(Comparator.<String>naturalOrder());

        for (String anAvailableCharsFromTP : availableCharsFromTP) {
            for (int j = 0; j < allCharsFromDB.size(); j++) {
                if (anAvailableCharsFromTP.equals(allCharsFromDB.get(j))) {
                    allCharsFromDB.remove(j);
                }
            }
        }

        for (String anAllCharsFromDB : allCharsFromDB) {
            employeesABC.addAll(getEmployeesByFirstChar(anAllCharsFromDB));
        }

        employeesABC.sort(Employee.COMPARE_BY_SHORT_NAME);
        return employeesABC;
    }

    public ArrayList<Employee> getEmployeesByDepartment(String departmentName) {
        ArrayList<Employee> employeesDep = new ArrayList<>();
        for (Employee allEmployee : allEmployees) {
            if (allEmployee.getCategory_name().equals(departmentName)) {
                employeesDep.add(allEmployee);
            }
        }
        employeesDep.sort(Employee.COMPARE_BY_SHORT_NAME);
        return employeesDep;
    }

    public ArrayList<Employee> getEmployeesListSearch(String searchQuery) {
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        ArrayList<Employee> employees = dao.getEmployeesByQuery(searchQuery);
        employees.sort(Employee.COMPARE_BY_SHORT_NAME);

        return employees;
    }

    public Vector<String> getShortNamesByFirstChar(String firstChar) {
        Vector<String> employeesABC = new Vector<>();
        for (Employee allEmployee : allEmployees) {
            if (allEmployee.getShort_name().substring(0, 1).equals(firstChar)) {
                employeesABC.add(allEmployee.getShort_name());
            }
        }
        employeesABC.sort(Comparator.<String>naturalOrder());
        return employeesABC;
    }

    public Vector<String> getShortNamesOther() {
        Vector<String> employeesABC = new Vector<>();
        ArrayList<String> allCharsFromDB = new ArrayList<>();
        for (Employee allEmployee : allEmployees) {
            allCharsFromDB.add(allEmployee.getHeader_name());
        }
        TreeSet<String> treeSet = new TreeSet(allCharsFromDB);
        allCharsFromDB = new ArrayList<>(treeSet);
        allCharsFromDB.sort(Comparator.<String>naturalOrder());

        for (String anAvailableCharsFromTP : abcTP) {
            for (int j = 0; j < allCharsFromDB.size(); j++) {
                if (anAvailableCharsFromTP.equals(allCharsFromDB.get(j))) {
                    allCharsFromDB.remove(j);
                }
            }
        }

        ArrayList<Employee> employeesALOther = new ArrayList<>();
        for (String anAllCharsFromDB : allCharsFromDB) {
            employeesALOther.addAll(getEmployeesByFirstChar(anAllCharsFromDB));
        }
        for (Employee anEmployeesALOther : employeesALOther) {
            employeesABC.add(anEmployeesALOther.getShort_name());
        }
        employeesABC.sort(Comparator.<String>naturalOrder());
        return employeesABC;
    }

    public Employee getEmployeeById(long id) {
        Employee employee = null;
        for (Employee allEmployee : allEmployees) {
            if (allEmployee.getId() == id) {
                employee = allEmployee;
            }
        }
        return employee;
    }

    public Vector<String> getDepartments() {
        ArrayList<Employee> employees = allEmployees;
        ArrayList<String> departamentsArrayList = new ArrayList<>();
        for (Employee employee : employees) {
            departamentsArrayList.add(employee.getCategory_name());
        }
        HashSet<String> departamentsHashSet = new HashSet<>(departamentsArrayList);
        Vector<String> departamentsVector = new Vector<>(departamentsHashSet);
        departamentsVector.sort(Comparator.<String>naturalOrder());
        return departamentsVector;
    }

    public ActionListener exitListener = e -> {
        Object[] options = {"Так", "Ні"};
        int n = JOptionPane.showOptionDialog(null, "Закрити програму?", "Підтвердження",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == 0) {
            mainFrame.setVisible(false);
            System.exit(0);
        }
    };

    public GUIFrame(int selectedTabPane2Index, String s) {
        allEmployees = getAllEmployees();
        mainFrame = new JFrame();
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage());
        }

        URL imgURL = GUIFrame.class.getResource("book.png");
        ImageIcon icon = new ImageIcon(imgURL);
        mainFrame.setTitle("Телефонний довідник ПВРЗ");
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Відкрити");

            ActionListener maximize = (e -> {
                mainFrame.setVisible(true);
                mainFrame.setExtendedState(JFrame.NORMAL);
            });
            defaultItem.addActionListener(maximize);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Закрити");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            trayIcon = new TrayIcon(icon.getImage(), "Телефонний довідник ПВРЗ", popup);
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (e.getClickCount() == 2) {
                            mainFrame.setVisible(true);
                            mainFrame.setExtendedState(JFrame.NORMAL);
                        }
                    }
                }
            });
            mainFrame.addWindowStateListener(e -> {
                if (e.getNewState() == JFrame.ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        if (firstRoll) {
                            trayIcon.displayMessage("Phonebook", "Application still working!",
                                    TrayIcon.MessageType.INFO);
                            firstRoll = false;
                        }
                        mainFrame.setVisible(false);
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
                }
                if (e.getNewState() == 7) {
                    try {
                        tray.add(trayIcon);
                        mainFrame.setVisible(false);
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
                }
                if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
                    tray.remove(trayIcon);
                    mainFrame.setVisible(true);
                }
                if (e.getNewState() == JFrame.NORMAL) {
                    tray.remove(trayIcon);
                    mainFrame.setVisible(true);
                }
            });
        }
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setMinimumSize(new Dimension(1000, 750));
        mainFrame.setPreferredSize(new Dimension(1000, 750));
        mainFrame.setSize(new Dimension(1000, 750));
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener closeFrame = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Так", "Ні"};
                int n = JOptionPane.showOptionDialog(e.getWindow(), "Закрити програму?",
                        "Підтвердження", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (n == 0) {
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
        };
        mainFrame.addWindowListener(closeFrame);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        $$$setupUI$$$();

        mainFrame.setContentPane(rootPanel);
        mainFrame.setVisible(true);

        btnExit.addActionListener(exitListener);
        propsItem.addActionListener(e -> {
            Options options = new Options();
            if (options.isUpdate()) {
                HibernateUtil.reloadSessionFactory();
                mainFrame.dispose();
                new GUIFrame(0, "А");
            }
        });
        exitItem.addActionListener(exitListener);
        tabbedPane2.setSelectedIndex(selectedTabPane2Index);
        tabbedPane2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                selectedTabName = tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex());
                switch (selectedTabName) {
                    case "А":
                        selectedList = list0;
                        break;
                    case "Б":
                        selectedList = list1;
                        break;
                    case "В":
                        selectedList = list2;
                        break;
                    case "Г":
                        selectedList = list3;
                        break;
                    case "Д":
                        selectedList = list4;
                        break;
                    case "Е":
                        selectedList = list5;
                        break;
                    case "Є":
                        selectedList = list6;
                        break;
                    case "Ж":
                        selectedList = list7;
                        break;
                    case "З":
                        selectedList = list8;
                        break;
                    case "І":
                        selectedList = list9;
                        break;
                    case "Ї":
                        selectedList = list10;
                        break;
                    case "К":
                        selectedList = list11;
                        break;
                    case "Л":
                        selectedList = list12;
                        break;
                    case "М":
                        selectedList = list13;
                        break;
                    case "Н":
                        selectedList = list14;
                        break;
                    case "О":
                        selectedList = list15;
                        break;
                    case "П":
                        selectedList = list16;
                        break;
                    case "Р":
                        selectedList = list17;
                        break;
                    case "С":
                        selectedList = list18;
                        break;
                    case "Т":
                        selectedList = list19;
                        break;
                    case "У":
                        selectedList = list20;
                        break;
                    case "Ф":
                        selectedList = list21;
                        break;
                    case "Х":
                        selectedList = list22;
                        break;
                    case "Ц":
                        selectedList = list23;
                        break;
                    case "Ч":
                        selectedList = list24;
                        break;
                    case "Ш":
                        selectedList = list25;
                        break;
                    case "Щ":
                        selectedList = list26;
                        break;
                    case "Ю":
                        selectedList = list27;
                        break;
                    case "Я":
                        selectedList = list28;
                        break;
                    case "Інші":
                        selectedList = list29;
                        break;
                }
                if (selectedList == list29) {
                    table1.setModel(employeesDefaultTableModel(getEmployeesOther()));
                    isSelOtherList = true;
                    selectedListEmployees = getEmployeesOther();
                } else {
                    table1.setModel(employeesDefaultTableModel(getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()))));

                    isSelOtherList = false;
                    selectedListEmployees = getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()));
                }
                setTableColsWidth();
                selectedList.addMouseListener(listAdapter);
                if (selectedList.getSelectedIndex() == -1) {
                    table1.changeSelection(0, 0, false, false);
                    selectedList.setSelectedIndex(0);
                } else {
                    table1.changeSelection(selectedList.getSelectedIndex(), 0, false, false);
                }
                changeSelectedEmployeeDetails();
            }
        });

        switch (s) {
            case "А":
                selectedList = list0;
                break;
            case "Б":
                selectedList = list1;
                break;
            case "В":
                selectedList = list2;
                break;
            case "Г":
                selectedList = list3;
                break;
            case "Д":
                selectedList = list4;
                break;
            case "Е":
                selectedList = list5;
                break;
            case "Є":
                selectedList = list6;
                break;
            case "Ж":
                selectedList = list7;
                break;
            case "З":
                selectedList = list8;
                break;
            case "І":
                selectedList = list9;
                break;
            case "Ї":
                selectedList = list10;
                break;
            case "К":
                selectedList = list11;
                break;
            case "Л":
                selectedList = list12;
                break;
            case "М":
                selectedList = list13;
                break;
            case "Н":
                selectedList = list14;
                break;
            case "О":
                selectedList = list15;
                break;
            case "П":
                selectedList = list16;
                break;
            case "Р":
                selectedList = list17;
                break;
            case "С":
                selectedList = list18;
                break;
            case "Т":
                selectedList = list19;
                break;
            case "У":
                selectedList = list20;
                break;
            case "Ф":
                selectedList = list21;
                break;
            case "Х":
                selectedList = list22;
                break;
            case "Ц":
                selectedList = list23;
                break;
            case "Ч":
                selectedList = list24;
                break;
            case "Ш":
                selectedList = list25;
                break;
            case "Щ":
                selectedList = list26;
                break;
            case "Ю":
                selectedList = list27;
                break;
            case "Я":
                selectedList = list28;
                break;
            default:
                selectedList = list29;
                break;
        }
        selectedList.addMouseListener(listAdapter);

        listDepartments.setSelectedIndex(0);

        if (s.equals("Інші")) {
            selectedListEmployees = getEmployeesOther();
            table1.setModel(employeesDefaultTableModel(getEmployeesOther()));
        } else {
            selectedListEmployees = getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()));
            table1.setModel(employeesDefaultTableModel(getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()))));
        }
        table1.changeSelection(0, 0, false, false);
        setTableColsWidth();

        btnSearch.addActionListener(searchAction);
        btnExit.addActionListener(exitListener);
        textFieldSearch.addActionListener(searchAction);
        btnEdit.addActionListener(e -> {
            if (table1.getSelectedRow() != -1) {
                editConfig = new EditConfig("config.ini");
                if (editConfig.getPassword().equals("admin")) {
                    long id = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                    Employee employee = getEmployeeById(id);
                    new DetailsEmployee(employee, 0);
                    mainFrame.dispose();
                    employee = getEmployeeById(id);

                    int indexTabP2 = 0;
                    String headerName = employee.getHeader_name();
                    switch (employee.getHeader_name()) {
                        case "А":
                            indexTabP2 = 0;
                            break;
                        case "Б":
                            indexTabP2 = 1;
                            break;
                        case "В":
                            indexTabP2 = 2;
                            break;
                        case "Г":
                            indexTabP2 = 3;
                            break;
                        case "Д":
                            indexTabP2 = 4;
                            break;
                        case "Е":
                            indexTabP2 = 5;
                            break;
                        case "Є":
                            indexTabP2 = 6;
                            break;
                        case "Ж":
                            indexTabP2 = 7;
                            break;
                        case "З":
                            indexTabP2 = 8;
                            break;
                        case "І":
                            indexTabP2 = 9;
                            break;
                        case "Ї":
                            indexTabP2 = 10;
                            break;
                        case "К":
                            indexTabP2 = 11;
                            break;
                        case "Л":
                            indexTabP2 = 12;
                            break;
                        case "М":
                            indexTabP2 = 13;
                            break;
                        case "Н":
                            indexTabP2 = 14;
                            break;
                        case "О":
                            indexTabP2 = 15;
                            break;
                        case "П":
                            indexTabP2 = 16;
                            break;
                        case "Р":
                            indexTabP2 = 17;
                            break;
                        case "С":
                            indexTabP2 = 18;
                            break;
                        case "Т":
                            indexTabP2 = 19;
                            break;
                        case "У":
                            indexTabP2 = 20;
                            break;
                        case "Ф":
                            indexTabP2 = 21;
                            break;
                        case "Х":
                            indexTabP2 = 22;
                            break;
                        case "Ц":
                            indexTabP2 = 23;
                            break;
                        case "Ч":
                            indexTabP2 = 24;
                            break;
                        case "Ш":
                            indexTabP2 = 25;
                            break;
                        case "Щ":
                            indexTabP2 = 26;
                            break;
                        case "Ю":
                            indexTabP2 = 27;
                            break;
                        case "Я":
                            indexTabP2 = 28;
                            break;
                        default:
                            indexTabP2 = 29;
                            headerName = "Інші";
                            break;
                    }
                    new GUIFrame(indexTabP2, headerName);

                    if (!Objects.equals(textFieldSearch.getText(), "")) {
                        table1.setModel(employeesDefaultTableModel(getEmployeesListSearch(textFieldSearch.getText())));
                        selectedID = Integer.parseInt(table1.getModel().getValueAt(0, 5).toString());
                        table1.changeSelection(0, 0, false, false);
                        setTableColsWidth();
                    }
                }
            }
        });

        exitItem.addActionListener(exitListener);
        btnAdd.addActionListener(e -> {
            editConfig = new EditConfig("config.ini");
            if (editConfig.getPassword().equals("admin")) {
                new DetailsEmployee(new Employee(), 1);
                mainFrame.dispose();
                new GUIFrame(tabbedPane2.getSelectedIndex(), tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()));
            }
        });

        btnDel.addActionListener(e -> {
            editConfig = new EditConfig("config.ini");
            if (editConfig.getPassword().equals("admin")) {
                Employee employee = getEmployeeById(Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString()));
                getInstance().getEmployeeDAO().deleteEmployee(employee);

                mainFrame.dispose();
                new GUIFrame(tabbedPane2.getSelectedIndex(), employee.getHeader_name());
            }
        });

        tabbedPane1.addChangeListener(e -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            table1.changeSelection(0, 0, false, false);
            if (index == 0) {
                if (Objects.equals(tabbedPane2.getComponentAt(tabbedPane2.getSelectedIndex()).getName(), "Інші")) {
                    table1.setModel(employeesDefaultTableModel(getEmployeesOther()));
                } else {
                    table1.setModel(employeesDefaultTableModel(getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()))));
                }
                if (table1.getModel().getRowCount() > 0) {
                    selectedID = Integer.parseInt(table1.getModel().getValueAt(0, 5).toString());
                    table1.changeSelection(0, 0, false, false);
                }
                changeSelectedEmployeeDetails();
                setTableColsWidth();
            } else {
                table1.setModel(employeesDefaultTableModel(getEmployeesByDepartment(listDepartments.getModel().getElementAt(listDepartments.getSelectedIndex()))));
                selectedID = Integer.parseInt(table1.getModel().getValueAt(0, 5).toString());
                table1.changeSelection(0, 0, false, false);
                changeSelectedEmployeeDetails();
                setTableColsWidth();
            }
        });

        infoItem.addActionListener(e -> {
            new InfoDialog();

        });
        btnMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Доделать отправку контакта*/
//                InfoDialog.launchMail("?SUBJECT=contact " + sele);
            }
        });
    }

    public ActionListener searchAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Employee> employeesSearch;

            if (!textFieldSearch.getText().equals("")) {
                employeesSearch = getEmployeesListSearch(textFieldSearch.getText());

                employeesSearch.sort(Employee.COMPARE_BY_SHORT_NAME);

                if (employeesSearch.size() > 0) {
                    table1.setModel(employeesDefaultTableModel(employeesSearch));
                    if (table1.getModel().getRowCount() > 0) {
                        table1.changeSelection(0, 0, false, false);
                        selectedID = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                        changeSelectedEmployeeDetails();
                    } else
                        removeSelectedEmployeeDetails();
                } else table1.setModel(employeesDefaultTableModel(new ArrayList<>()));
                setTableColsWidth();
            }
        }
    };

    MouseAdapter listAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            table1.setModel(employeesDefaultTableModel(getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()))));
            if (isSelOtherList) {
                table1.setModel(employeesDefaultTableModel(getEmployeesOther()));
            } else {
                table1.setModel(employeesDefaultTableModel(getEmployeesByFirstChar(tabbedPane2.getTitleAt(tabbedPane2.getSelectedIndex()))));
            }

            Employee selectedEmployee;
            if (selectedList.getModel().getSize() > 0) {
                selectedEmployee = selectedListEmployees.get(selectedList.getSelectedIndex());
                selectedID = Integer.parseInt(selectedEmployee.getId().toString());
                for (int i = 0; i < table1.getModel().getRowCount(); i++) {
                    if (selectedID == Integer.parseInt(table1.getModel().getValueAt(i, 5).toString())) {
                        table1.changeSelection(i, 0, false, false);
                        break;
                    }
                }
            }
            setTableColsWidth();
            changeSelectedEmployeeDetails();
        }
    };

    public void setTableColsWidth() {
        table1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table1.getColumnModel().getColumn(5).setPreferredWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(6).setPreferredWidth(0);
        table1.getColumnModel().getColumn(6).setMinWidth(0);
        table1.getColumnModel().getColumn(6).setMaxWidth(0);
        table1.getColumnModel().getColumn(7).setPreferredWidth(0);
        table1.getColumnModel().getColumn(7).setMinWidth(0);
        table1.getColumnModel().getColumn(7).setMaxWidth(0);
        table1.getColumnModel().getColumn(8).setPreferredWidth(0);
        table1.getColumnModel().getColumn(8).setMinWidth(0);
        table1.getColumnModel().getColumn(8).setMaxWidth(0);
        table1.getColumnModel().getColumn(9).setPreferredWidth(0);
        table1.getColumnModel().getColumn(9).setMinWidth(0);
        table1.getColumnModel().getColumn(9).setMaxWidth(0);
        table1.getColumnModel().getColumn(10).setPreferredWidth(0);
        table1.getColumnModel().getColumn(10).setMinWidth(0);
        table1.getColumnModel().getColumn(10).setMaxWidth(0);
    }

    public void initializeLists() {
        list0 = new JList<>(getShortNamesByFirstChar("А"));
        list1 = new JList<>(getShortNamesByFirstChar("Б"));
        list2 = new JList<>(getShortNamesByFirstChar("В"));
        list3 = new JList<>(getShortNamesByFirstChar("Г"));
        list4 = new JList<>(getShortNamesByFirstChar("Д"));
        list5 = new JList<>(getShortNamesByFirstChar("Е"));
        list6 = new JList<>(getShortNamesByFirstChar("Є"));
        list7 = new JList<>(getShortNamesByFirstChar("Ж"));
        list8 = new JList<>(getShortNamesByFirstChar("З"));
        list9 = new JList<>(getShortNamesByFirstChar("І"));
        list10 = new JList<>(getShortNamesByFirstChar("Ї"));
        list11 = new JList<>(getShortNamesByFirstChar("К"));
        list12 = new JList<>(getShortNamesByFirstChar("Л"));
        list13 = new JList<>(getShortNamesByFirstChar("М"));
        list14 = new JList<>(getShortNamesByFirstChar("Н"));
        list15 = new JList<>(getShortNamesByFirstChar("О"));
        list16 = new JList<>(getShortNamesByFirstChar("П"));
        list17 = new JList<>(getShortNamesByFirstChar("Р"));
        list18 = new JList<>(getShortNamesByFirstChar("С"));
        list19 = new JList<>(getShortNamesByFirstChar("Т"));
        list20 = new JList<>(getShortNamesByFirstChar("У"));
        list21 = new JList<>(getShortNamesByFirstChar("Ф"));
        list22 = new JList<>(getShortNamesByFirstChar("Х"));
        list23 = new JList<>(getShortNamesByFirstChar("Ц"));
        list24 = new JList<>(getShortNamesByFirstChar("Ч"));
        list25 = new JList<>(getShortNamesByFirstChar("Ш"));
        list26 = new JList<>(getShortNamesByFirstChar("Щ"));
        list27 = new JList<>(getShortNamesByFirstChar("Ю"));
        list28 = new JList<>(getShortNamesByFirstChar("Я"));
        list29 = new JList<>(getShortNamesOther());

        list0.addMouseListener(listAdapter);
        list1.addMouseListener(listAdapter);
        list2.addMouseListener(listAdapter);
        list3.addMouseListener(listAdapter);
        list4.addMouseListener(listAdapter);
        list5.addMouseListener(listAdapter);
        list6.addMouseListener(listAdapter);
        list7.addMouseListener(listAdapter);
        list8.addMouseListener(listAdapter);
        list9.addMouseListener(listAdapter);
        list10.addMouseListener(listAdapter);
        list11.addMouseListener(listAdapter);
        list12.addMouseListener(listAdapter);
        list13.addMouseListener(listAdapter);
        list14.addMouseListener(listAdapter);
        list15.addMouseListener(listAdapter);
        list16.addMouseListener(listAdapter);
        list17.addMouseListener(listAdapter);
        list18.addMouseListener(listAdapter);
        list19.addMouseListener(listAdapter);
        list20.addMouseListener(listAdapter);
        list21.addMouseListener(listAdapter);
        list22.addMouseListener(listAdapter);
        list23.addMouseListener(listAdapter);
        list24.addMouseListener(listAdapter);
        list25.addMouseListener(listAdapter);
        list26.addMouseListener(listAdapter);
        list27.addMouseListener(listAdapter);
        list28.addMouseListener(listAdapter);
        list29.addMouseListener(listAdapter);
    }

    public DefaultTableModel employeesDefaultTableModel(ArrayList<Employee> employees) {
        Vector<String> vHeaders = new Vector<>();
        vHeaders.add("Прізвище");
        vHeaders.add("Ім'я");
        vHeaders.add("По батькові");
        vHeaders.add("Підрозділ");
        vHeaders.add("Роб.телефон");
        vHeaders.add("id");
        vHeaders.add("email");
        vHeaders.add("home");
        vHeaders.add("note");
        vHeaders.add("post");
        vHeaders.add("short");

        Vector<Vector<Object>> tableData = new Vector<>();
        for (Employee employee : employees) {
            Vector<Object> oneRow = new Vector<>();
            oneRow.add(employee.getLast_name());
            oneRow.add(employee.getFirst_name());
            oneRow.add(employee.getMiddle_name());
            oneRow.add(employee.getCategory_name());
            oneRow.add(employee.getWork_phone());
            oneRow.add(employee.getId());
            oneRow.add(employee.getEmail_address());
            oneRow.add(employee.getHome_phone());
            oneRow.add(employee.getNote_string());
            oneRow.add(employee.getPost_name());
            oneRow.add(employee.getShort_name());
            tableData.add(oneRow);
        }

        return new DefaultTableModel(tableData, vHeaders) {
            public Class<?> getColumnClass(int column) {
                return String.class;
            }
        };
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIFrame(0, "А");
            }
        });
    }

    private void createUIComponents() {
        initializeLists();

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        listDepartments = new JList<>(getDepartments());
        listDepartments.setSelectedIndex(0);
        listDepartments.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                table1.setModel(employeesDefaultTableModel(getEmployeesByDepartment(listDepartments.getModel().getElementAt(listDepartments.getSelectedIndex()))));
                selectedID = Integer.parseInt(table1.getModel().getValueAt(0, 5).toString());
                table1.changeSelection(0, 0, false, false);
                setTableColsWidth();
                changeSelectedEmployeeDetails();
            }
        });

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getSelectionModel().addListSelectionListener(new SharedListSelectionHandler());

        makePanelStatus();
    }

    public void makePanelStatus() {
        panelStatus = new JPanel();
        JLabel labelEmplCount = new JLabel("Всього працівників: ");
        labelEmplCount.setFont(font14pt);
        labelEmplCount.setText(labelEmplCount.getText() + allEmployees.size());
        panelStatus.add(labelEmplCount);
        mainFrame.add(panelStatus);
    }

    public void changeSelectedEmployeeDetails() {
        if (table1.getRowCount() > 0) {
            if (table1.getSelectedRow() != -1) {
                txtFld_last.setText(table1.getValueAt(table1.getSelectedRow(), 0).toString());
                txtFld_first.setText(table1.getValueAt(table1.getSelectedRow(), 1).toString());
                txtFld_middle.setText(table1.getValueAt(table1.getSelectedRow(), 2).toString());
                txtFld_category.setText(table1.getValueAt(table1.getSelectedRow(), 3) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 3).toString());
                txtFld_work.setText(table1.getValueAt(table1.getSelectedRow(), 4) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 4).toString());

                txtFld_mail.setText(table1.getValueAt(table1.getSelectedRow(), 6) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 6).toString());
                txtFld_home.setText(table1.getValueAt(table1.getSelectedRow(), 7) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 7).toString());
                txtFld_note.setText(table1.getValueAt(table1.getSelectedRow(), 8) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 8).toString());
                txtFld_post.setText(table1.getValueAt(table1.getSelectedRow(), 9) == null ? "" : table1.getValueAt(table1.getSelectedRow(), 9).toString());
            }
        }
    }

    public void removeSelectedEmployeeDetails() {
        txtFld_last.setText("");
        txtFld_first.setText("");
        txtFld_middle.setText("");
        txtFld_category.setText("");
        txtFld_post.setText("");
        txtFld_home.setText("");
        txtFld_work.setText("");
        txtFld_mail.setText("");
        txtFld_note.setText("");
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 6, 6, 6), 0, 0));
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setFont(new Font("Times New Roman", tabbedPane1.getFont().getStyle(), 14));
        rootPanel.add(tabbedPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 620), null, null, 0, false));
        tabABC = new JPanel();
        tabABC.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("За алфавітом", tabABC);
        tabbedPane2 = new JTabbedPane();
        tabbedPane2.setFont(new Font("Times New Roman", tabbedPane2.getFont().getStyle(), 13));
        tabbedPane2.setTabPlacement(2);
        tabABC.add(tabbedPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(260, 550), new Dimension(281, 200), null, 0, false));
        pan0 = new JPanel();
        pan0.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan0.setFont(new Font("Times New Roman", pan0.getFont().getStyle(), pan0.getFont().getSize()));
        tabbedPane2.addTab("А", pan0);
        final JScrollPane scrollPane1 = new JScrollPane();
        pan0.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list0.setEnabled(true);
        list0.setFont(new Font("Times New Roman", list0.getFont().getStyle(), 14));
        list0.setSelectionMode(0);
        scrollPane1.setViewportView(list0);
        pan1 = new JPanel();
        pan1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan1.setFont(new Font("Times New Roman", pan1.getFont().getStyle(), pan1.getFont().getSize()));
        tabbedPane2.addTab("Б", pan1);
        final JScrollPane scrollPane2 = new JScrollPane();
        pan1.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list1.setFont(new Font("Times New Roman", list1.getFont().getStyle(), 14));
        list1.setSelectionMode(0);
        scrollPane2.setViewportView(list1);
        pan2 = new JPanel();
        pan2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan2.setFont(new Font("Times New Roman", pan2.getFont().getStyle(), pan2.getFont().getSize()));
        tabbedPane2.addTab("В", pan2);
        final JScrollPane scrollPane3 = new JScrollPane();
        pan2.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list2.setFont(new Font("Times New Roman", list2.getFont().getStyle(), 14));
        scrollPane3.setViewportView(list2);
        pan3 = new JPanel();
        pan3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan3.setFont(new Font("Times New Roman", pan3.getFont().getStyle(), pan3.getFont().getSize()));
        tabbedPane2.addTab("Г", pan3);
        final JScrollPane scrollPane4 = new JScrollPane();
        pan3.add(scrollPane4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list3.setFont(new Font("Times New Roman", list3.getFont().getStyle(), 14));
        scrollPane4.setViewportView(list3);
        pan4 = new JPanel();
        pan4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan4.setFont(new Font("Times New Roman", pan4.getFont().getStyle(), pan4.getFont().getSize()));
        tabbedPane2.addTab("Д", pan4);
        final JScrollPane scrollPane5 = new JScrollPane();
        pan4.add(scrollPane5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list4.setFont(new Font("Times New Roman", list4.getFont().getStyle(), 14));
        scrollPane5.setViewportView(list4);
        pan5 = new JPanel();
        pan5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan5.setFont(new Font("Times New Roman", pan5.getFont().getStyle(), pan5.getFont().getSize()));
        tabbedPane2.addTab("Е", pan5);
        final JScrollPane scrollPane6 = new JScrollPane();
        pan5.add(scrollPane6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list5.setFont(new Font("Times New Roman", list5.getFont().getStyle(), 14));
        scrollPane6.setViewportView(list5);
        pan6 = new JPanel();
        pan6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan6.setFont(new Font("Times New Roman", pan6.getFont().getStyle(), pan6.getFont().getSize()));
        tabbedPane2.addTab("Є", pan6);
        final JScrollPane scrollPane7 = new JScrollPane();
        pan6.add(scrollPane7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list6.setFont(new Font("Times New Roman", list6.getFont().getStyle(), 14));
        scrollPane7.setViewportView(list6);
        pan7 = new JPanel();
        pan7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan7.setFont(new Font("Times New Roman", pan7.getFont().getStyle(), pan7.getFont().getSize()));
        tabbedPane2.addTab("Ж", pan7);
        final JScrollPane scrollPane8 = new JScrollPane();
        pan7.add(scrollPane8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list7.setFont(new Font("Times New Roman", list7.getFont().getStyle(), 14));
        scrollPane8.setViewportView(list7);
        pan8 = new JPanel();
        pan8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan8.setFont(new Font("Times New Roman", pan8.getFont().getStyle(), pan8.getFont().getSize()));
        tabbedPane2.addTab("З", pan8);
        final JScrollPane scrollPane9 = new JScrollPane();
        pan8.add(scrollPane9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list8.setFont(new Font("Times New Roman", list8.getFont().getStyle(), 14));
        scrollPane9.setViewportView(list8);
        pan9 = new JPanel();
        pan9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan9.setFont(new Font("Times New Roman", pan9.getFont().getStyle(), pan9.getFont().getSize()));
        tabbedPane2.addTab("І", pan9);
        final JScrollPane scrollPane10 = new JScrollPane();
        pan9.add(scrollPane10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list9.setFont(new Font("Times New Roman", list9.getFont().getStyle(), 14));
        scrollPane10.setViewportView(list9);
        pan10 = new JPanel();
        pan10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan10.setFont(new Font("Times New Roman", pan10.getFont().getStyle(), pan10.getFont().getSize()));
        tabbedPane2.addTab("Ї", pan10);
        final JScrollPane scrollPane11 = new JScrollPane();
        pan10.add(scrollPane11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list10.setFont(new Font("Times New Roman", list10.getFont().getStyle(), 14));
        scrollPane11.setViewportView(list10);
        pan11 = new JPanel();
        pan11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan11.setFont(new Font("Times New Roman", pan11.getFont().getStyle(), pan11.getFont().getSize()));
        tabbedPane2.addTab("К", pan11);
        final JScrollPane scrollPane12 = new JScrollPane();
        pan11.add(scrollPane12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list11.setFont(new Font("Times New Roman", list11.getFont().getStyle(), 14));
        scrollPane12.setViewportView(list11);
        pan12 = new JPanel();
        pan12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan12.setFont(new Font("Times New Roman", pan12.getFont().getStyle(), pan12.getFont().getSize()));
        tabbedPane2.addTab("Л", pan12);
        final JScrollPane scrollPane13 = new JScrollPane();
        pan12.add(scrollPane13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list12.setFont(new Font("Times New Roman", list12.getFont().getStyle(), 14));
        scrollPane13.setViewportView(list12);
        pan13 = new JPanel();
        pan13.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan13.setFont(new Font("Times New Roman", pan13.getFont().getStyle(), pan13.getFont().getSize()));
        tabbedPane2.addTab("М", pan13);
        final JScrollPane scrollPane14 = new JScrollPane();
        pan13.add(scrollPane14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list13.setFont(new Font("Times New Roman", list13.getFont().getStyle(), 14));
        scrollPane14.setViewportView(list13);
        pan14 = new JPanel();
        pan14.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan14.setFont(new Font("Times New Roman", pan14.getFont().getStyle(), pan14.getFont().getSize()));
        tabbedPane2.addTab("Н", pan14);
        final JScrollPane scrollPane15 = new JScrollPane();
        pan14.add(scrollPane15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list14.setFont(new Font("Times New Roman", list14.getFont().getStyle(), 14));
        scrollPane15.setViewportView(list14);
        pan15 = new JPanel();
        pan15.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan15.setFont(new Font("Times New Roman", pan15.getFont().getStyle(), pan15.getFont().getSize()));
        tabbedPane2.addTab("О", pan15);
        final JScrollPane scrollPane16 = new JScrollPane();
        pan15.add(scrollPane16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list15.setFont(new Font("Times New Roman", list15.getFont().getStyle(), 14));
        scrollPane16.setViewportView(list15);
        pan16 = new JPanel();
        pan16.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan16.setFont(new Font("Times New Roman", pan16.getFont().getStyle(), pan16.getFont().getSize()));
        tabbedPane2.addTab("П", pan16);
        final JScrollPane scrollPane17 = new JScrollPane();
        pan16.add(scrollPane17, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list16.setFont(new Font("Times New Roman", list16.getFont().getStyle(), 14));
        scrollPane17.setViewportView(list16);
        pan17 = new JPanel();
        pan17.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan17.setFont(new Font("Times New Roman", pan17.getFont().getStyle(), pan17.getFont().getSize()));
        tabbedPane2.addTab("Р", pan17);
        final JScrollPane scrollPane18 = new JScrollPane();
        pan17.add(scrollPane18, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list17.setFont(new Font("Times New Roman", list17.getFont().getStyle(), 14));
        scrollPane18.setViewportView(list17);
        pan18 = new JPanel();
        pan18.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan18.setFont(new Font("Times New Roman", pan18.getFont().getStyle(), pan18.getFont().getSize()));
        tabbedPane2.addTab("С", pan18);
        final JScrollPane scrollPane19 = new JScrollPane();
        pan18.add(scrollPane19, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list18.setFont(new Font("Times New Roman", list18.getFont().getStyle(), 14));
        scrollPane19.setViewportView(list18);
        pan19 = new JPanel();
        pan19.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan19.setFont(new Font("Times New Roman", pan19.getFont().getStyle(), pan19.getFont().getSize()));
        tabbedPane2.addTab("Т", pan19);
        final JScrollPane scrollPane20 = new JScrollPane();
        pan19.add(scrollPane20, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list19.setFont(new Font("Times New Roman", list19.getFont().getStyle(), 14));
        scrollPane20.setViewportView(list19);
        pan20 = new JPanel();
        pan20.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan20.setFont(new Font("Times New Roman", pan20.getFont().getStyle(), pan20.getFont().getSize()));
        tabbedPane2.addTab("У", pan20);
        final JScrollPane scrollPane21 = new JScrollPane();
        pan20.add(scrollPane21, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list20.setFont(new Font("Times New Roman", list20.getFont().getStyle(), 14));
        scrollPane21.setViewportView(list20);
        pan21 = new JPanel();
        pan21.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan21.setFont(new Font("Times New Roman", pan21.getFont().getStyle(), pan21.getFont().getSize()));
        tabbedPane2.addTab("Ф", pan21);
        final JScrollPane scrollPane22 = new JScrollPane();
        pan21.add(scrollPane22, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list21.setFont(new Font("Times New Roman", list21.getFont().getStyle(), 14));
        scrollPane22.setViewportView(list21);
        pan22 = new JPanel();
        pan22.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan22.setFont(new Font("Times New Roman", pan22.getFont().getStyle(), pan22.getFont().getSize()));
        tabbedPane2.addTab("Х", pan22);
        final JScrollPane scrollPane23 = new JScrollPane();
        pan22.add(scrollPane23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list22.setFont(new Font("Times New Roman", list22.getFont().getStyle(), 14));
        scrollPane23.setViewportView(list22);
        pan23 = new JPanel();
        pan23.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan23.setFont(new Font("Times New Roman", pan23.getFont().getStyle(), pan23.getFont().getSize()));
        tabbedPane2.addTab("Ц", pan23);
        final JScrollPane scrollPane24 = new JScrollPane();
        pan23.add(scrollPane24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list23.setFont(new Font("Times New Roman", list23.getFont().getStyle(), 14));
        scrollPane24.setViewportView(list23);
        pan24 = new JPanel();
        pan24.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan24.setFont(new Font("Times New Roman", pan24.getFont().getStyle(), pan24.getFont().getSize()));
        tabbedPane2.addTab("Ч", pan24);
        final JScrollPane scrollPane25 = new JScrollPane();
        pan24.add(scrollPane25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list24.setFont(new Font("Times New Roman", list24.getFont().getStyle(), 14));
        scrollPane25.setViewportView(list24);
        pan25 = new JPanel();
        pan25.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan25.setFont(new Font("Times New Roman", pan25.getFont().getStyle(), pan25.getFont().getSize()));
        tabbedPane2.addTab("Ш", pan25);
        final JScrollPane scrollPane26 = new JScrollPane();
        pan25.add(scrollPane26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list25.setFont(new Font("Times New Roman", list25.getFont().getStyle(), 14));
        scrollPane26.setViewportView(list25);
        pan26 = new JPanel();
        pan26.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan26.setFont(new Font("Times New Roman", pan26.getFont().getStyle(), pan26.getFont().getSize()));
        tabbedPane2.addTab("Щ", pan26);
        final JScrollPane scrollPane27 = new JScrollPane();
        pan26.add(scrollPane27, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list26.setFont(new Font("Times New Roman", list26.getFont().getStyle(), 14));
        scrollPane27.setViewportView(list26);
        pan27 = new JPanel();
        pan27.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan27.setFont(new Font("Times New Roman", pan27.getFont().getStyle(), pan27.getFont().getSize()));
        tabbedPane2.addTab("Ю", pan27);
        final JScrollPane scrollPane28 = new JScrollPane();
        pan27.add(scrollPane28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list27.setFont(new Font("Times New Roman", list27.getFont().getStyle(), 14));
        scrollPane28.setViewportView(list27);
        pan28 = new JPanel();
        pan28.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan28.setFont(new Font("Times New Roman", pan28.getFont().getStyle(), pan28.getFont().getSize()));
        tabbedPane2.addTab("Я", pan28);
        final JScrollPane scrollPane29 = new JScrollPane();
        pan28.add(scrollPane29, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list28.setFont(new Font("Times New Roman", list28.getFont().getStyle(), 14));
        scrollPane29.setViewportView(list28);
        pan29 = new JPanel();
        pan29.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pan29.setFont(new Font("Times New Roman", pan29.getFont().getStyle(), pan29.getFont().getSize()));
        tabbedPane2.addTab("Інші", pan29);
        final JScrollPane scrollPane30 = new JScrollPane();
        pan29.add(scrollPane30, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list29.setFont(new Font("Times New Roman", list29.getFont().getStyle(), 14));
        scrollPane30.setViewportView(list29);
        tabDepartment = new JPanel();
        tabDepartment.setLayout(new GridLayoutManager(1, 1, new Insets(3, 3, 3, 3), -1, -1));
        tabbedPane1.addTab("За підрозділом", tabDepartment);
        final JScrollPane scrollPane31 = new JScrollPane();
        tabDepartment.add(scrollPane31, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listDepartments.setFont(new Font("Times New Roman", listDepartments.getFont().getStyle(), 14));
        scrollPane31.setViewportView(listDepartments);
        panelStatus.setFont(new Font("Times New Roman", panelStatus.getFont().getStyle(), 14));
        rootPanel.add(panelStatus, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayoutManager(3, 1, new Insets(3, 3, 3, 3), -1, -1));
        rootPanel.add(rightPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayoutManager(1, 10, new Insets(0, 0, 0, 0), -1, -1));
        rightPanel.add(panelButtons, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAdd = new JButton();
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/icon/add.png")));
        btnAdd.setText("");
        btnAdd.setToolTipText("Додати");
        panelButtons.add(btnAdd, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelButtons.add(spacer1, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnEdit = new JButton();
        btnEdit.setIcon(new ImageIcon(getClass().getResource("/icon/edit.png")));
        btnEdit.setText("");
        btnEdit.setToolTipText("Редагувати");
        panelButtons.add(btnEdit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnDel = new JButton();
        btnDel.setIcon(new ImageIcon(getClass().getResource("/icon/delete.png")));
        btnDel.setText("");
        btnDel.setToolTipText("Видалити");
        panelButtons.add(btnDel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSave = new JButton();
        btnSave.setEnabled(false);
        btnSave.setIcon(new ImageIcon(getClass().getResource("/icon/save.png")));
        btnSave.setText("");
        panelButtons.add(btnSave, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelButtons.add(spacer2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnMail = new JButton();
        btnMail.setEnabled(true);
        btnMail.setIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        btnMail.setText("");
        panelButtons.add(btnMail, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnExit = new JButton();
        btnExit.setIcon(new ImageIcon(getClass().getResource("/icon/exit.png")));
        btnExit.setText("");
        btnExit.setToolTipText("Вихід");
        panelButtons.add(btnExit, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldSearch = new JTextField();
        textFieldSearch.setFont(new Font("Times New Roman", textFieldSearch.getFont().getStyle(), 16));
        panelButtons.add(textFieldSearch, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btnSearch = new JButton();
        btnSearch.setIcon(new ImageIcon(getClass().getResource("/icon/search.png")));
        btnSearch.setText("");
        btnSearch.setToolTipText("Пошук");
        panelButtons.add(btnSearch, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollEmployeesABC = new JScrollPane();
        scrollEmployeesABC.setFont(new Font("Times New Roman", scrollEmployeesABC.getFont().getStyle(), 14));
        rightPanel.add(scrollEmployeesABC, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1.setAutoCreateRowSorter(true);
        table1.setFillsViewportHeight(true);
        table1.setFont(new Font("Times New Roman", table1.getFont().getStyle(), 16));
        table1.setIntercellSpacing(new Dimension(1, 1));
        table1.setPreferredScrollableViewportSize(new Dimension(-1, -1));
        scrollEmployeesABC.setViewportView(table1);
        panelDetails = new JPanel();
        panelDetails.setLayout(new GridLayoutManager(9, 1, new Insets(10, 0, 3, 3), -1, 1));
        rightPanel.add(panelDetails, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelDetails.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-2302756)), null));
        panelDetailsLast_name = new JPanel();
        panelDetailsLast_name.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsLast_name, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(692, 24), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsLast_name.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Times New Roman", label1.getFont().getStyle(), 16));
        label1.setText("Прізвище");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsLast_name.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_last = new JTextField();
        txtFld_last.setEditable(false);
        txtFld_last.setEnabled(true);
        txtFld_last.setFont(new Font("Times New Roman", txtFld_last.getFont().getStyle(), 18));
        panel2.add(txtFld_last, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsFirst_name = new JPanel();
        panelDetailsFirst_name.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsFirst_name, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setFont(new Font("Times New Roman", panel3.getFont().getStyle(), 16));
        panelDetailsFirst_name.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Times New Roman", label2.getFont().getStyle(), 16));
        label2.setText("Ім'я");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsFirst_name.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_first = new JTextField();
        txtFld_first.setEditable(false);
        txtFld_first.setEnabled(true);
        txtFld_first.setFont(new Font("Times New Roman", txtFld_first.getFont().getStyle(), 18));
        panel4.add(txtFld_first, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsMiddle_name = new JPanel();
        panelDetailsMiddle_name.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsMiddle_name, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsMiddle_name.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Times New Roman", label3.getFont().getStyle(), 16));
        label3.setText("По батькові");
        panel5.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsMiddle_name.add(panel6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_middle = new JTextField();
        txtFld_middle.setEditable(false);
        txtFld_middle.setEnabled(true);
        txtFld_middle.setFont(new Font("Times New Roman", txtFld_middle.getFont().getStyle(), 18));
        panel6.add(txtFld_middle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsPost_name = new JPanel();
        panelDetailsPost_name.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsPost_name, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsPost_name.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font("Times New Roman", label4.getFont().getStyle(), 16));
        label4.setText("Посада");
        panel7.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsPost_name.add(panel8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_post = new JTextField();
        txtFld_post.setEditable(false);
        txtFld_post.setEnabled(true);
        txtFld_post.setFont(new Font("Times New Roman", txtFld_post.getFont().getStyle(), 18));
        panel8.add(txtFld_post, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsCategory_name = new JPanel();
        panelDetailsCategory_name.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsCategory_name, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setFont(new Font("Times New Roman", panel9.getFont().getStyle(), 16));
        panelDetailsCategory_name.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Times New Roman", label5.getFont().getStyle(), 16));
        label5.setText("Підрозділ");
        panel9.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsCategory_name.add(panel10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_category = new JTextField();
        txtFld_category.setEditable(false);
        txtFld_category.setEnabled(true);
        txtFld_category.setFont(new Font("Times New Roman", txtFld_category.getFont().getStyle(), 18));
        panel10.add(txtFld_category, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsWork_phone = new JPanel();
        panelDetailsWork_phone.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsWork_phone, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsWork_phone.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font("Times New Roman", label6.getFont().getStyle(), 16));
        label6.setText("Роб.телефон");
        panel11.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsWork_phone.add(panel12, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_work = new JTextField();
        txtFld_work.setEditable(false);
        txtFld_work.setEnabled(true);
        txtFld_work.setFont(new Font("Times New Roman", txtFld_work.getFont().getStyle(), 18));
        panel12.add(txtFld_work, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsHome_phone = new JPanel();
        panelDetailsHome_phone.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsHome_phone, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsHome_phone.add(panel13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Times New Roman", label7.getFont().getStyle(), 16));
        label7.setText("Дом.телефон");
        panel13.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsHome_phone.add(panel14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_home = new JTextField();
        txtFld_home.setEditable(false);
        txtFld_home.setEnabled(true);
        txtFld_home.setFont(new Font("Times New Roman", txtFld_home.getFont().getStyle(), 18));
        panel14.add(txtFld_home, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsEmail_address = new JPanel();
        panelDetailsEmail_address.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsEmail_address, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsEmail_address.add(panel15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font("Times New Roman", label8.getFont().getStyle(), 16));
        label8.setText("E-mail");
        panel15.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsEmail_address.add(panel16, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_mail = new JTextField();
        txtFld_mail.setEditable(false);
        txtFld_mail.setEnabled(true);
        txtFld_mail.setFont(new Font("Times New Roman", txtFld_mail.getFont().getStyle(), 18));
        panel16.add(txtFld_mail, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelDetailsNote_string = new JPanel();
        panelDetailsNote_string.setLayout(new GridLayoutManager(1, 2, new Insets(0, 5, 0, 0), -1, -1));
        panelDetails.add(panelDetailsNote_string, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsNote_string.add(panel17, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setFont(new Font("Times New Roman", label9.getFont().getStyle(), 16));
        label9.setText("Примітка");
        panel17.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelDetailsNote_string.add(panel18, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(565, 24), null, null, 0, false));
        txtFld_note = new JTextField();
        txtFld_note.setEditable(false);
        txtFld_note.setEnabled(true);
        txtFld_note.setFont(new Font("Times New Roman", txtFld_note.getFont().getStyle(), 18));
        panel18.add(txtFld_note, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        menuRootPanel = new JPanel();
        menuRootPanel.setLayout(new BorderLayout(0, 0));
        rootPanel.add(menuRootPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 25), null, null, 0, false));
        panelJMenuBar = new JPanel();
        panelJMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelJMenuBar.setFont(new Font("Times New Roman", panelJMenuBar.getFont().getStyle(), 14));
        menuRootPanel.add(panelJMenuBar, BorderLayout.CENTER);
        mainBar = new JMenuBar();
        mainBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelJMenuBar.add(mainBar);
        menuFile = new JMenu();
        menuFile.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        menuFile.setArmed(false);
        menuFile.setBackground(new Color(-1513240));
        menuFile.setFont(new Font("Times New Roman", menuFile.getFont().getStyle(), 14));
        menuFile.setHideActionText(false);
        menuFile.setHorizontalAlignment(0);
        menuFile.setHorizontalTextPosition(0);
        menuFile.setPopupMenuVisible(false);
        menuFile.setSelected(false);
        menuFile.setText("Файл");
        menuFile.setMnemonic('Ф');
        menuFile.setDisplayedMnemonicIndex(0);
        mainBar.add(menuFile);
        importItem = new JMenuItem();
        importItem.setFont(new Font("Times New Roman", importItem.getFont().getStyle(), 14));
        importItem.setIcon(new ImageIcon(getClass().getResource("/icon/import.png")));
        importItem.setText("Імпорт");
        importItem.setMnemonic('І');
        importItem.setDisplayedMnemonicIndex(0);
        menuFile.add(importItem);
        exportItem = new JMenuItem();
        exportItem.setFont(new Font("Times New Roman", exportItem.getFont().getStyle(), 14));
        exportItem.setIcon(new ImageIcon(getClass().getResource("/icon/export.png")));
        exportItem.setText("Експорт");
        menuFile.add(exportItem);
        propsItem = new JMenuItem();
        propsItem.setFont(new Font("Times New Roman", propsItem.getFont().getStyle(), 14));
        propsItem.setIcon(new ImageIcon(getClass().getResource("/icon/options.png")));
        propsItem.setText("Налаштування");
        menuFile.add(propsItem);
        exitItem = new JMenuItem();
        exitItem.setFont(new Font("Times New Roman", exitItem.getFont().getStyle(), 14));
        exitItem.setIcon(new ImageIcon(getClass().getResource("/icon/tb_exit.png")));
        exitItem.setText("Вихід");
        menuFile.add(exitItem);
        menuInfo = new JMenu();
        menuInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        menuInfo.setFont(new Font("Times New Roman", menuInfo.getFont().getStyle(), 14));
        menuInfo.setHorizontalAlignment(0);
        menuInfo.setHorizontalTextPosition(0);
        menuInfo.setPopupMenuVisible(false);
        menuInfo.setText("Довідка");
        menuInfo.setMnemonic('Д');
        menuInfo.setDisplayedMnemonicIndex(0);
        mainBar.add(menuInfo);
        infoItem = new JMenuItem();
        infoItem.setFont(new Font("Times New Roman", infoItem.getFont().getStyle(), 14));
        infoItem.setIcon(new ImageIcon(getClass().getResource("/icon/information.png")));
        infoItem.setText("Про програму");
        menuInfo.add(infoItem);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            boolean isAdjusting = e.getValueIsAdjusting();
            if (isAdjusting) {
                if (!lsm.isSelectionEmpty()) {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            selectedID = Integer.parseInt(table1.getModel().getValueAt(i, 5).toString());
                        } else {
                            selectedID = Integer.parseInt(new EmployeeDAOImpl().getAllEmployeesByABC("А").get(0).getId().toString());
                        }
                    }
                    for (int i = 0; i < selectedList.getModel().getSize(); i++) {
                        if (selectedList.getModel().getElementAt(i).equals(table1.getValueAt(table1.getSelectedRow(), 10))) {
                            selectedList.setSelectedIndex(i);
                        }
                    }
                    changeSelectedEmployeeDetails();
                } else {
                    if (table1.getModel().getRowCount() > 0) {
                        selectedID = Integer.parseInt(table1.getModel().getValueAt(0, 5).toString());
                    } else {
                        removeSelectedEmployeeDetails();
                    }
                }
            }
        }
    }

}
