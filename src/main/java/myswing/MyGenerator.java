package myswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/4/26 16:35
 * Modified By:
 * Description:
 */
public class MyGenerator {

    public static void main(String[] args) {

        final DatabaseConnect databaseConnect = new DatabaseConnect();
        databaseConnect.init();

        List<String> databases = databaseConnect.getDatabase();
        Vector<String> v = new Vector<>(databases);

        JFrame jFrame = new JFrame("MyBatis Generator");
        jFrame.setLayout(null);
        jFrame.setSize(480, 540);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanelDatabase = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        jPanelDatabase.setLayout(borderLayout);
        jPanelDatabase.setBounds(10, 20, 200, 400);

        final JList jListDatabase = new JList();
        jListDatabase.setForeground(new Color(232, 221, 203));
        jListDatabase.setBackground(new Color(3, 22, 52));
        jListDatabase.setListData(v);
        jPanelDatabase.add(jListDatabase, BorderLayout.CENTER);

        final JPanel jPanelTable = new JPanel();
        jPanelTable.setLayout(borderLayout);
        jPanelTable.setBounds(250, 20, 200, 400);
        jPanelTable.setBackground(new Color(182, 194, 154));

        final JList jListTable = new JList();
        jListTable.setForeground(new Color(220, 87, 18));
        jListTable.setBackground(new Color(182, 194, 154));

        jListDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (2 == e.getClickCount()) {
                    String database = (String) jListDatabase.getSelectedValue();
                    List<String> tables = databaseConnect.getTable(database);
                    Vector<String> v = new Vector<>(tables);
                    jListTable.setListData(v);
                    jPanelTable.add(jListTable, BorderLayout.CENTER);
                }
            }
        });

        Button btnUpdateXml = new Button("UpdateXml");
        btnUpdateXml.setForeground(Color.black);
        btnUpdateXml.setBackground(new Color(255, 66, 93));
        btnUpdateXml.setBounds(50, 440, 100, 50);

        btnUpdateXml.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String table = (String) jListTable.getSelectedValue();
                String database = (String) jListDatabase.getSelectedValue();
                MyUtil.generate(database, table);
            }
        });

        Button btnGenerate = new Button("Generate");
        btnGenerate.setForeground(Color.black);
        btnGenerate.setBackground(new Color(154, 255, 114));
        btnGenerate.setBounds(320, 440, 100, 50);

        btnGenerate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new File("run.bat"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jFrame.add(jPanelDatabase);
        jFrame.add(jPanelTable);
        jFrame.add(btnUpdateXml);
        jFrame.add(btnGenerate);
        jFrame.setVisible(true);
    }
}
