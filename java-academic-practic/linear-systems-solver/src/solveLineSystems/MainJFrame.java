/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package solveLineSystems;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainJFrame extends javax.swing.JFrame {

    private int n = 9; // Размерность системы
    /**
     * Конструктор формы
     */
    public MainJFrame() {
        initComponents(); 
        
        initCustomComponents(); 
        
        initListeners();
    }

    private void initListeners() {
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton1.setText("Получить исходную и симметричную матрицы");
        jButton2.setText("Получить точное решение");
        jButton3.setText("Получить приближенное решение");
        jLabel1.setText("");
        jLabel2.setText("");
    }

    private void initCustomComponents() {
        try {
            initTableHeaders(jTable1, n);
            initTableHeaders(jTable2, n);
            initVectorHeader(jTable3, "B");
            initVectorHeader(jTable4, "X");
            initVectorHeader(jTable5, "abs(Ax-B)");
            initVectorHeader(jTable6, "Bc");
            initVectorHeader(jTable7, "Xc");
            initVectorHeader(jTable8, "abs(AcAx-Bc)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTableHeaders(JTable table, int cols) {
        String[] h = new String[cols];
        for(int i=0; i<cols; i++) h[i] = String.valueOf(i);
        
        DefaultTableModel model = new DefaultTableModel(h, n);
        table.setModel(model);
    }
    
    private void initVectorHeader(JTable table, String name) {
        DefaultTableModel model = new DefaultTableModel(new String[]{name}, n);
        table.setModel(model);
    }

    private void setDataJTableA(JTable jTable, double[][] a) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                jTable.setValueAt(String.format("%.3f", a[i][j]), i, j);
            }
        }
    }

    private void setDataJTableB(JTable jTable, double[] b) {
        for (int i = 0; i < n; i++) {
            jTable.setValueAt(String.format("%.5f", b[i]), i, 0);
        }
    }

    private double[][] getDataJTableA(JTable jTable) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Object val = jTable.getValueAt(i, j);
                if (val == null || val.toString().trim().isEmpty()) a[i][j] = 0.0;
                else a[i][j] = Double.parseDouble(val.toString().replace(",", "."));
            }
        }
        return a;
    }

    private double[] getDataJTableB(JTable jTable) {
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            Object val = jTable.getValueAt(i, 0);
            if (val == null || val.toString().trim().isEmpty()) b[i] = 0.0;
            else b[i] = Double.parseDouble(val.toString().replace(",", "."));
        }
        return b;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        double[][] a = Utils.getMatrixA(n);
        double[] b = Utils.getMatrixB(n);
        setDataJTableA(jTable1, a);
        setDataJTableB(jTable3, b);

        double[][] ac = Utils.getMatrixAc(a, n);
        double[] bc = Utils.getMatrixBc(a, b, n);
        setDataJTableA(jTable2, ac);
        setDataJTableB(jTable6, bc);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            double[][] a = getDataJTableA(jTable1);
            double[] b = getDataJTableB(jTable3);
            if (a[0][0] == 0) { JOptionPane.showMessageDialog(this, "Сгенерируйте матрицу!"); return; }
            
            double[] x = new LU().getSolveSystem(a, b, n);
            setDataJTableB(jTable4, x);
            setDataJTableB(jTable5, Utils.getMatrixNevjazka(a, b, x, n));

            double[][] ac = getDataJTableA(jTable2);
            double[] bc = getDataJTableB(jTable6);
            double[] xc = new LU().getSolveSystem(ac, bc, n);
            setDataJTableB(jTable7, xc);
            setDataJTableB(jTable8, Utils.getMatrixNevjazka(ac, bc, xc, n));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка LU: " + e.getMessage());
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            Zeidel zeidel = new Zeidel(10000, 1.E-05);

            double[][] a = getDataJTableA(jTable1);
            double[] b = getDataJTableB(jTable3);
            if (a[0][0] == 0) { JOptionPane.showMessageDialog(this, "Сгенерируйте матрицу!"); return; }

            double[] x = zeidel.getSolveSystem(a, b, n);
            setDataJTableB(jTable4, x);
            setDataJTableB(jTable5, Utils.getMatrixNevjazka(a, b, x, n));
            jLabel1.setText("Итер: " + zeidel.getNi());

            double[][] ac = getDataJTableA(jTable2);
            double[] bc = getDataJTableB(jTable6);
            double[] xc = zeidel.getSolveSystem(ac, bc, n);
            setDataJTableB(jTable7, xc);
            setDataJTableB(jTable8, Utils.getMatrixNevjazka(ac, bc, xc, n));
            jLabel2.setText("Итер: " + zeidel.getNi());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка Зейдель: " + e.getMessage());
        }
    }                        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Элементы исходной матрицы");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setToolTipText("пвапвапвап");

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "A", "0", "1", "2", "3", "4", "5", "6", "7", "8"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 320, 190));

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ac", "0", "1", "2", "3", "4", "5", "6", "7", "8"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 320, 190));

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", "B"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 190, 190));

        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 160, 190));

        jTable5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 170, 190));

        jTable6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 190, 190));

        jTable7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 160, 190));

        jTable8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTable8);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 170, 190));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 390, -1));

        jButton2.setText("jButton2");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 140, -1));

        jButton3.setText("jButton3");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 140, -1));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, -1));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    // End of variables declaration//GEN-END:variables

public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
}
