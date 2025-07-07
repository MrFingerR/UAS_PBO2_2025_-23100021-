/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject4;

/**
 *
 * @author ASUS
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mavenproject4 extends JFrame {

    private JTable visitTable;
    private DefaultTableModel tableModel;
    
    private JTextField nameField;
    private JTextField nimField;
    private JComboBox<String> studyProgramBox;
    private JComboBox<String> purposeBox;
    private JButton addButton;
    private JButton clearButton;
    
    private boolean actionColumnsAdded = false;
        private JTable table;
    
        public Mavenproject4() {
            setTitle("Library Visit Log");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
    
            JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
            nameField = new JTextField();
            nimField = new JTextField();
            studyProgramBox = new JComboBox<>(new String[] {"Sistem dan Teknologi Informasi", "Bisnis Digital", "Kewirausahaan"});
            purposeBox = new JComboBox<>(new String[] {"Membaca", "Meminjam/Mengembalikan Buku", "Research", "Belajar"});
            addButton = new JButton("Add");
            clearButton = new JButton("Clear");
    
            inputPanel.setBorder(BorderFactory.createTitledBorder("Visit Entry Form"));
            inputPanel.add(new JLabel("NIM:"));
            inputPanel.add(nimField);
            inputPanel.add(new JLabel("Name Mahasiswa:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Program Studi:"));
            inputPanel.add(studyProgramBox);
            inputPanel.add(new JLabel("Tujuan Kunjungan:"));
            inputPanel.add(purposeBox);
            inputPanel.add(addButton);
            inputPanel.add(clearButton);
    
            add(inputPanel, BorderLayout.NORTH);
    
            String[] columns = {"Waktu Kunjungan", "NIM", "Nama", "Program Studi", "Tujuan Kunjungan"};
            tableModel = new DefaultTableModel(columns, 0);
            table = new JTable(tableModel);
            table.setRowHeight(20);
            table.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
            table.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
            table.setSelectionBackground(new java.awt.Color(220, 240, 255));
            table.setSelectionForeground(new java.awt.Color(40, 40, 40));
            visitTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(visitTable);
        add(scrollPane, BorderLayout.CENTER);

        
        setVisible(true);
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("control G"), "showActions");

        getRootPane().getActionMap().put("showActions", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!actionColumnsAdded) {
                    addActionColumns();
                    actionColumnsAdded = true;
                }
            }
        });
    }

    private void tambahMahasiswa() {
        String name = nameField.getText();
        String nim = nimField.getText();
        if (name.isEmpty() || nim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;

            }
        int id = tableModel.getRowCount() + 1;
        tableModel.addRow(new Object[]{id, name, nim});
    }

    private void ambilDataMahasiswa() {
        // Kosongkan tabel (dummy, karena tidak ada backend)
        tableModel.setRowCount(0);
    }

    private void editProduk() {
        int selectedRow = visitTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit!");
            return;
        }
        String name = nameField.getText();
        String price = nimField.getText();
        if (name.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }
        tableModel.setValueAt(name, selectedRow, 1);
        tableModel.setValueAt(price, selectedRow, 2);
        clearInputFields();
        }
            
            private void clearInputFields() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'clearInputFields'");
            }
        
            private void addActionColumns() {
        tableModel.addColumn("Action");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt("Action", i, tableModel.getColumnCount() - 2);
        }

        visitTable.getColumn("Action").setCellRenderer(new ButtonRenderer());

        visitTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Mavenproject4::new);
    }
}
