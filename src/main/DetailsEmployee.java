package main;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import logic.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import static DAO.Factory.getInstance;

public class DetailsEmployee extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtFld_last;
    private JTextField txtFld_first;
    private JTextField txtFld_middle;
    private JTextField txtFld_work;
    private JTextField txtFld_home;
    private JTextField txtFld_mail;
    private JTextField txtFld_note;
    private JComboBox<String> comboPosts;
    private JComboBox comboCategories;
    private Vector<String> posts;
    private Vector<String> categories;
    private Employee employee;
    private String FirstChar;

    public DetailsEmployee() {
        $$$setupUI$$$();
    }

    public void updateEmployee(long id, String last_name, String first_name, String middle_name, String post_name,
                               String category_name, String mail, String home_tel, String work_tel, String note) {

        Employee employee = getInstance().getEmployeeDAO().getEmployee(id);
        employee.setId(id);
        employee.setLast_name(last_name);
        employee.setFirst_name(first_name);
        employee.setMiddle_name(middle_name);
        employee.setShort_name(last_name + " " + (first_name.equals("") ? "" : first_name.substring(0, 1)) + " ." + (middle_name.equals("") ? "" : middle_name.substring(0, 1)) + ".");
        employee.setHeader_name(last_name.substring(0, 1));
        employee.setPost_name(post_name);
        employee.setCategory_name(category_name);
        employee.setEmail_address(mail);
        employee.setHome_phone(home_tel);
        employee.setWork_phone(work_tel);
        employee.setNote_string(note);
        getInstance().getEmployeeDAO().updateEmployee(employee);
    }

    public static void addEmployee(String last_name, String first_name, String middle_name, String category,
                                   String post, String work_tel, String home_tel, String mail, String note) {

        Employee employee = new Employee();
        employee.setLast_name(last_name);
        employee.setFirst_name(first_name);
        employee.setMiddle_name(middle_name);
        employee.setCategory_name(category);
        employee.setPost_name(post);
        employee.setWork_phone(work_tel);
        employee.setHome_phone(home_tel);
        employee.setEmail_address(mail);
        employee.setNote_string(note);
        employee.setHeader_name(last_name.substring(0, 1));
        employee.setShort_name(last_name + " " + first_name.substring(0, 1) + "." + middle_name.substring(0, 1) + ".");
        getInstance().getEmployeeDAO().addEmployee(employee);
    }

    public ActionListener alEdit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtFld_last.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Прізвище\" обов'язкове для заповнення!");
            } else if (txtFld_first.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Ім'я\" обов'язкове для заповнення!");
            } else if (txtFld_middle.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"По батькові\" обов'язкове для заповнення!");
            } else if (txtFld_work.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Роб.телефон\" обов'язкове для заповнення!");
            } else {
                String comCatVal;
                if (comboCategories.getSelectedItem() != null) {
                    comCatVal = comboCategories.getSelectedItem().equals("") ? "Без відділу" : comboCategories.getSelectedItem().toString();
                } else comCatVal = "Без відділу";
                String comPosVal;
                if (comboPosts.getSelectedItem() != null) {
                    comPosVal = comboPosts.getSelectedItem().equals("") ? "Без посади" : comboPosts.getSelectedItem().toString();
                } else comPosVal = "Без посади";
                updateEmployee(employee.getId(), txtFld_last.getText(), txtFld_first.getText(), txtFld_middle.getText(), comPosVal,
                        comCatVal, txtFld_mail.getText(), txtFld_home.getText(), txtFld_work.getText(), txtFld_note.getText());
                setVisible(false);
            }
        }
    };

    public ActionListener alAdd = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtFld_last.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Прізвище\" обов'язкове для заповнення!");
            } else if (txtFld_first.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Ім'я\" обов'язкове для заповнення!");
            } else if (txtFld_middle.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"По батькові\" обов'язкове для заповнення!");
            } else if (txtFld_work.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Поле \"Роб.телефон\" обов'язкове для заповнення!");
            } else {
                String comCatVal;
                if (comboCategories.getSelectedItem() != null) {
                    comCatVal = comboCategories.getSelectedItem().equals("") ? "Без відділу" : comboCategories.getSelectedItem().toString();
                } else comCatVal = "Без відділу";
                String comPosVal;
                if (comboPosts.getSelectedItem() != null) {
                    comPosVal = comboPosts.getSelectedItem().equals("") ? "Без посади" : comboPosts.getSelectedItem().toString();
                } else comPosVal = "Без посади";
                addEmployee(txtFld_last.getText(), txtFld_first.getText(), txtFld_middle.getText(),
                        comCatVal, comPosVal, txtFld_work.getText(), txtFld_home.getText(),
                        txtFld_mail.getText(), txtFld_note.getText());
                setVisible(false);
                FirstChar = txtFld_last.getText().substring(0, 1);
            }
        }
    };

    public DetailsEmployee(Employee employee, int typeEdit) {
        this.employee = employee;
        $$$setupUI$$$();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(400, 345));
        setSize(new Dimension(400, 345));
        setPreferredSize(new Dimension(400, 345));
        pack();
        if (typeEdit == 0) {//update action
            txtFld_last.setText(employee.getLast_name());
            txtFld_first.setText(employee.getFirst_name());
            txtFld_middle.setText(employee.getMiddle_name());
            if (employee.getPost_name() != null && !employee.getCategory_name().equals("")) {
                for (int i = 0; i < posts.size(); i++) {
                    if (employee.getPost_name().equals(posts.get(i))) {
                        comboPosts.setSelectedIndex(i);
                    }
                }
            }
            if (employee.getCategory_name() != null && !employee.getCategory_name().equals("")) {
                for (int i = 0; i < categories.size(); i++) {
                    if (employee.getCategory_name().equals(categories.get(i))) {
                        comboCategories.setSelectedIndex(i);
                    }
                }
            }

            txtFld_work.setText(employee.getWork_phone());
            txtFld_home.setText(employee.getHome_phone());
            txtFld_mail.setText(employee.getEmail_address());
            txtFld_note.setText(employee.getNote_string());
            setTitle("Зміна даних робітника");
            buttonOK.addActionListener(alEdit);
            buttonOK.setText("Змінити");
        } else { //add action
            buttonOK.addActionListener(alAdd);
            setTitle("Додати нового робітника");
            buttonOK.setText("Додати");
        }

        URL imgURL = GUIFrame.class.getResource("book.png");
        ImageIcon icon = new ImageIcon(imgURL);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setVisible(true);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DetailsEmployee::new);
    }

    private void createUIComponents() {
        posts = new Vector<>();
        ArrayList<String> postsDAO = getInstance().getEmployeeDAO().getPosts();
        for (String aPostsDAO : postsDAO) {
            posts.add(aPostsDAO);
        }
        comboPosts = new JComboBox<>(posts);

        categories = new Vector<>();
        ArrayList<String> categoriesDAO = getInstance().getEmployeeDAO().getCategories();
        for (String aCategoriesDAO : categoriesDAO) {
            categories.add(aCategoriesDAO);
        }
        comboCategories = new JComboBox<>(categories);
    }

    public String getFirstChar() {
        return FirstChar;
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(10, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setFont(new Font("Times New Roman", buttonOK.getFont().getStyle(), 16));
        buttonOK.setText("Змінити");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setFont(new Font("Times New Roman", buttonCancel.getFont().getStyle(), 16));
        buttonCancel.setText("Відмінити");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Times New Roman", label1.getFont().getStyle(), 16));
        label1.setText("Прізвище");
        panel5.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_last = new JTextField();
        txtFld_last.setFont(new Font("Times New Roman", txtFld_last.getFont().getStyle(), 16));
        panel4.add(txtFld_last, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setFont(new Font("Times New Roman", panel7.getFont().getStyle(), 16));
        panel6.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 14));
        label2.setText("Ім'я");
        panel7.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_first = new JTextField();
        txtFld_first.setFont(new Font("Times New Roman", txtFld_first.getFont().getStyle(), 16));
        panel6.add(txtFld_first, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Times New Roman", label3.getFont().getStyle(), 16));
        label3.setText("По батькові");
        panel9.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_middle = new JTextField();
        txtFld_middle.setFont(new Font("Times New Roman", txtFld_middle.getFont().getStyle(), 16));
        panel8.add(txtFld_middle, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel10, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.setFont(new Font("Times New Roman", panel11.getFont().getStyle(), 16));
        panel10.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), label4.getFont().getStyle(), 14));
        label4.setText("Посада");
        panel11.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboPosts.setEditable(true);
        comboPosts.setFont(new Font("Times New Roman", comboPosts.getFont().getStyle(), 16));
        panel10.add(comboPosts, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel12, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.add(panel13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Times New Roman", label5.getFont().getStyle(), 16));
        label5.setText("Підрозділ");
        panel13.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboCategories.setEditable(true);
        comboCategories.setFont(new Font("Times New Roman", comboCategories.getFont().getStyle(), 16));
        panel12.add(comboCategories, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel14, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font("Times New Roman", label6.getFont().getStyle(), 16));
        label6.setText("Роб.телефон");
        panel15.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_work = new JTextField();
        txtFld_work.setFont(new Font("Times New Roman", txtFld_work.getFont().getStyle(), 16));
        panel14.add(txtFld_work, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel16, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel16.add(panel17, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Times New Roman", label7.getFont().getStyle(), 16));
        label7.setText("Дом.телефон");
        panel17.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_home = new JTextField();
        txtFld_home.setFont(new Font("Times New Roman", txtFld_home.getFont().getStyle(), 16));
        panel16.add(txtFld_home, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel18, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel19.setFont(new Font("Times New Roman", panel19.getFont().getStyle(), 16));
        panel18.add(panel19, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font(label8.getFont().getName(), label8.getFont().getStyle(), 14));
        label8.setText("E-mail");
        panel19.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_mail = new JTextField();
        txtFld_mail.setFont(new Font("Times New Roman", txtFld_mail.getFont().getStyle(), 16));
        panel18.add(txtFld_mail, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel20, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel20.add(panel21, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, 24), new Dimension(100, 24), new Dimension(100, 24), 0, false));
        final JLabel label9 = new JLabel();
        label9.setFont(new Font("Times New Roman", label9.getFont().getStyle(), 16));
        label9.setText("Примітка");
        panel21.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtFld_note = new JTextField();
        txtFld_note.setFont(new Font("Times New Roman", txtFld_note.getFont().getStyle(), 16));
        panel20.add(txtFld_note, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 24), new Dimension(-1, 24), new Dimension(-1, 24), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
