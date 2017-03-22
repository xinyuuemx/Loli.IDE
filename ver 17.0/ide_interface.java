/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

public class ide_interface extends javax.swing.JFrame  {

    /**
     * Creates new form ide_interface
     */
    
    String codee="";   //input
    String outputt="", objj=""; //output
    String temp="";
    boolean run;
    public void clock(){
        Thread clock= new Thread(){  
            public void run(){
                String ampm;
                try {
                    while(true){
                    Calendar cal= new GregorianCalendar();
                    int day=cal.get(Calendar.DAY_OF_MONTH);
                    int month=cal.get(Calendar.MONTH)+1;
                    int year=cal.get(Calendar.YEAR);
                    int second=cal.get(Calendar.SECOND);
                    int minute=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
                    int am_pm=cal.get(Calendar.AM_PM);
                    if(am_pm==1){
                        ampm="PM";
                    }
                    else{
                        ampm="AM";
                    }
                    DateTimeSetting.setText(hour+":"+minute+":"+second+" "+ampm+"    "+day+"/"+month+"/"+year+"   ");
                    sleep(1000);
                    }
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(ide_interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();
    }
    
    public ide_interface() {        
        super("Loli.IDE");
        initComponents();
        seticon();
        clock();
        shoUndo.setEnabled(false);
        shoRedo.setEnabled(false);
        jScrollPane5.setHorizontalScrollBarPolicy(jScrollPane5.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(jScrollPane5.VERTICAL_SCROLLBAR_NEVER);

        WorkArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                  public void undoableEditHappened(UndoableEditEvent e) {
                    undoManager.addEdit(e.getEdit());
                    updateButtons();
                  }
                }
            );
            shoUndo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        undoManager.undo();
                      } 
                      catch (CannotRedoException cre) {
                        cre.printStackTrace();
                      }
                    updateButtons();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            shoRedo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        undoManager.redo();
                      } 
                      catch (CannotRedoException cre) {
                        cre.printStackTrace();
                      }
                    updateButtons();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });   
    }
     class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
    {
        public MyHighlightPainter(Color color)
        {
            super(color);
        }
    }
    //Declaration of the color    
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.yellow);
     
    public void removeHighlights(JTextComponent textComp)
    {
        //takes present highlights which is the "textComp"
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        for(int i=0; i<hilites.length; i++)
        {
            if(hilites[i].getPainter() instanceof MyHighlightPainter)
            {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
    
    //Method for Highlighting 
    public void highlight(JTextComponent textComp,String pattern)
    {
        removeHighlights(textComp);
        try
        {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos=0;
            
            //this will highlight the word either it is in lowercase or uppercase
            while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0)
            {
                hilite.addHighlight(pos, pos+pattern.length(), myHighlightPainter);
                pos +=pattern.length();
               
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        LineNumber = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        WorkArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DisplayOutput = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        MenuButtons = new javax.swing.JPanel();
        shoNew = new javax.swing.JButton();
        shoSave = new javax.swing.JButton();
        shoUndo = new javax.swing.JButton();
        shoRedo = new javax.swing.JButton();
        shoOpen = new javax.swing.JButton();
        shoRun = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        DateTimeSetting = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        mNew = new javax.swing.JMenuItem();
        mOpen = new javax.swing.JMenuItem();
        mSave = new javax.swing.JMenuItem();
        mSaveas = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        eundo = new javax.swing.JMenuItem();
        eredo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lolitech.IDE");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(133, 197, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        LineNumber.setColumns(20);
        LineNumber.setRows(5);
        LineNumber.setText("1");
        LineNumber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        LineNumber.setEnabled(false);
        LineNumber.setFocusable(false);
        LineNumber.setOpaque(false);
        jScrollPane5.setViewportView(LineNumber);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTabbedPane1.setFont(new java.awt.Font("Xolonium", 0, 14)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane1MouseWheelMoved(evt);
            }
        });

        WorkArea.setColumns(20);
        WorkArea.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        WorkArea.setRows(5);
        WorkArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WorkAreaMouseExited(evt);
            }
        });
        WorkArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WorkAreaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                WorkAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(WorkArea);

        jTabbedPane1.addTab("CODE:", jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("MANOK");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        DisplayOutput.setEditable(false);
        DisplayOutput.setColumns(20);
        DisplayOutput.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        DisplayOutput.setRows(5);
        jScrollPane2.setViewportView(DisplayOutput);

        jToolBar1.setFloatable(false);

        jButton1.setFont(new java.awt.Font("Xolonium", 0, 14)); // NOI18N
        jButton1.setText("OUTPUT :");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        MenuButtons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        shoNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330535_file_add.png"))); // NOI18N
        shoNew.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoNew.setBorderPainted(false);
        shoNew.setContentAreaFilled(false);
        shoNew.setFocusPainted(false);
        shoNew.setIconTextGap(-40);
        shoNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoNewMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoNewMouseEntered(evt);
            }
        });
        shoNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoNewActionPerformed(evt);
            }
        });

        shoSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330542_diskette.png"))); // NOI18N
        shoSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoSave.setBorderPainted(false);
        shoSave.setContentAreaFilled(false);
        shoSave.setFocusPainted(false);
        shoSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoSaveMouseExited(evt);
            }
        });
        shoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoSaveActionPerformed(evt);
            }
        });

        shoUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330544_direction_left.png"))); // NOI18N
        shoUndo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoUndo.setBorderPainted(false);
        shoUndo.setContentAreaFilled(false);
        shoUndo.setFocusPainted(false);
        shoUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoUndoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoUndoMouseExited(evt);
            }
        });

        shoRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330550_direction_right.png"))); // NOI18N
        shoRedo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoRedo.setBorderPainted(false);
        shoRedo.setContentAreaFilled(false);
        shoRedo.setFocusPainted(false);
        shoRedo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoRedoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoRedoMouseExited(evt);
            }
        });

        shoOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330538_document_edit.png"))); // NOI18N
        shoOpen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoOpen.setBorderPainted(false);
        shoOpen.setContentAreaFilled(false);
        shoOpen.setFocusPainted(false);
        shoOpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoOpenMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoOpenMouseEntered(evt);
            }
        });
        shoOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoOpenActionPerformed(evt);
            }
        });

        shoRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1487330540_player_play.png"))); // NOI18N
        shoRun.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shoRun.setBorderPainted(false);
        shoRun.setContentAreaFilled(false);
        shoRun.setFocusPainted(false);
        shoRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                shoRunMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                shoRunMouseExited(evt);
            }
        });
        shoRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoRunActionPerformed(evt);
            }
        });

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuButtonsLayout = new javax.swing.GroupLayout(MenuButtons);
        MenuButtons.setLayout(MenuButtonsLayout);
        MenuButtonsLayout.setHorizontalGroup(
            MenuButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuButtonsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(shoNew, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shoOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shoSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(shoUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shoRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(shoRun, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(15, 15, 15))
        );
        MenuButtonsLayout.setVerticalGroup(
            MenuButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuButtonsLayout.createSequentialGroup()
                .addGroup(MenuButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shoRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shoRedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shoUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(shoOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(shoNew, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(shoSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MenuButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        DateTimeSetting.setBackground(new java.awt.Color(204, 204, 255));
        DateTimeSetting.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DateTimeSetting.setText("12:38 PM 10.02.2017        ");
        DateTimeSetting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        DateTimeSetting.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        DateTimeSetting.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(DateTimeSetting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(MenuButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(DateTimeSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menu.setText("File");

        mNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mNew.setText("New");
        mNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNewActionPerformed(evt);
            }
        });
        menu.add(mNew);

        mOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mOpen.setText("Open");
        mOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOpenActionPerformed(evt);
            }
        });
        menu.add(mOpen);

        mSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mSave.setText("Save");
        mSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSaveActionPerformed(evt);
            }
        });
        menu.add(mSave);

        mSaveas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mSaveas.setText("Save as...");
        menu.add(mSaveas);

        Menu.add(menu);

        edit.setText("Edit");

        eundo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        eundo.setText("Undo");
        edit.add(eundo);

        eredo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        eredo.setText("Redo");
        edit.add(eredo);

        Menu.add(edit);

        jMenu5.setText("Run");
        Menu.add(jMenu5);

        jMenu1.setText("Help");

        jMenuItem1.setText("Help Content");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        Menu.add(jMenu1);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String path, path2;
    int x=1;
    String line="", line2="";
    
    UndoManager undoManager = new UndoManager();
    
    public void seticon(){
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ico.png")));

    }
    
    private void mNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNewActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(WorkArea);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            File file = fc.getSelectedFile();
        } 
        else 
        {
            // User canceled the file chooser.
        } 
    }//GEN-LAST:event_mNewActionPerformed

    private void jScrollPane1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane1MouseWheelMoved
        // TODO add your handling code here:
         jScrollPane1.getVerticalScrollBar().setModel(jScrollPane2.getVerticalScrollBar().getModel());
    }//GEN-LAST:event_jScrollPane1MouseWheelMoved

    private void WorkAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WorkAreaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== 10){
            line="1";
            int l=WorkArea.getLineCount();
            for(int i=2;i<l+2;i++){
                line=line+"\n"+i;
            }
            LineNumber.setText(line);
        }
        else if (evt.getKeyCode()==8){
            int x=WorkArea.getCaretPosition();
            String s=WorkArea.getText();
            if(s.charAt(x-1)==10){
                line="1";
                int l=WorkArea.getLineCount();
                for(int i=2;i<l;i++){
                    line=line+"\n"+i;
                }
                LineNumber.setText(line);
            }
        }
        jScrollPane1.getVerticalScrollBar().setModel(jScrollPane5.getVerticalScrollBar().getModel());
    }//GEN-LAST:event_WorkAreaKeyPressed

    private void WorkAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WorkAreaKeyTyped
        if(!shoUndo.isEnabled()){
            shoUndo.setEnabled(true);
            shoRedo.setEnabled(true);
        }
    }//GEN-LAST:event_WorkAreaKeyTyped

    private void mOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOpenActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser =new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text only", "chkn");
        chooser.setFileFilter(filter);
            chooser.showOpenDialog(null);
            File f =chooser.getSelectedFile();
            String filename=f.getAbsolutePath();
            path=f.getAbsolutePath();
            int len=path.length();
            len=len-5;
            path=path.substring(0, len);
            path2=path; 

            try
            {
                FileReader reader = new FileReader(filename);
                BufferedReader br = new BufferedReader(reader);
                WorkArea.read(br, null);
                br.close();
                line="1";
                int l=WorkArea.getLineCount();
                for(int i=2;i<l+1;i++){
                    line=line+"\n"+i;
                }
                LineNumber.setText(line);
                WorkArea.requestFocus();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog (null, e);
            }
    }//GEN-LAST:event_mOpenActionPerformed

    private void mSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSaveActionPerformed
        // TODO add your handling code here:
        try
        {
            if(path==null){
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(WorkArea);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path=file.getAbsolutePath();
                    path2=file.getAbsolutePath();
                } else {
                    // User canceled the file chooser.
                }
            }
                String p=path+".chkn";
                BufferedWriter out = new BufferedWriter(new FileWriter(path2+".obj"));
                out.write(objj); 
                out.close();
                FileWriter tagasulat = new FileWriter(p);
                BufferedWriter bw = new BufferedWriter(tagasulat);
                WorkArea.write(bw);
                bw.close();
                FileWriter tagasulat2 = new FileWriter(path+".bak");
                BufferedWriter bw2 = new BufferedWriter(tagasulat2);
                WorkArea.write(bw2);
                bw2.close();
                WorkArea.requestFocus();
           
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_mSaveActionPerformed

    private void shoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoSaveActionPerformed
        // TODO add your handling code here:
        BufferedWriter writer = null;
        try
        {
            if(path==null){
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(WorkArea);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path=file.getAbsolutePath();
                    path2=file.getAbsolutePath();
                } else {
                    // User canceled the file chooser.
                }
            }
         
                String p=path+".chkn";
                BufferedWriter out = new BufferedWriter(new FileWriter(path2+".obj"));
                out.write(objj); 
                out.close();
                FileWriter tagasulat = new FileWriter(p);
                BufferedWriter bw = new BufferedWriter(tagasulat);
                WorkArea.write(bw);
                bw.close();
                FileWriter tagasulat2 = new FileWriter(path+".bak");
                BufferedWriter bw2 = new BufferedWriter(tagasulat2);
                WorkArea.write(bw2);
                bw2.close();
                WorkArea.requestFocus();
       
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);                  
        }
    }//GEN-LAST:event_shoSaveActionPerformed

    private void shoNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoNewMouseEntered
        // TODO add your handling code here:
        shoNew.setContentAreaFilled(true);
    }//GEN-LAST:event_shoNewMouseEntered

    private void shoNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoNewMouseExited
        // TODO add your handling code here:
        shoNew.setContentAreaFilled(false);
    }//GEN-LAST:event_shoNewMouseExited

    private void shoOpenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoOpenMouseEntered
        // TODO add your handling code here:
        shoOpen.setContentAreaFilled(true);
    }//GEN-LAST:event_shoOpenMouseEntered

    private void shoOpenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoOpenMouseExited
        // TODO add your handling code here:
        shoOpen.setContentAreaFilled(false);
    }//GEN-LAST:event_shoOpenMouseExited

    private void shoSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoSaveMouseEntered
        // TODO add your handling code here:
        shoSave.setContentAreaFilled(true);
    }//GEN-LAST:event_shoSaveMouseEntered

    private void shoSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoSaveMouseExited
        // TODO add your handling code here:
        shoSave.setContentAreaFilled(false);
    }//GEN-LAST:event_shoSaveMouseExited

    private void shoNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoNewActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(WorkArea);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            path=file.getAbsolutePath();
            path2=file.getAbsolutePath();
        } else {
            // User canceled the file chooser.
        }
    }//GEN-LAST:event_shoNewActionPerformed

    private void shoOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoOpenActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser =new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text only", "chkn");
        chooser.setFileFilter(filter);
            chooser.showOpenDialog(null);
            File f =chooser.getSelectedFile();
            String filename=f.getAbsolutePath();
            path=f.getAbsolutePath();
            int len=path.length();
            len=len-5;
            path=path.substring(0, len);
            path2=path;
            
            
            try
            {
                FileReader reader = new FileReader(filename);
                BufferedReader br = new BufferedReader(reader);
                WorkArea.read(br, null);
                br.close();
                line="1";
                int l=WorkArea.getLineCount();
                for(int i=2;i<l+1;i++){
                    line=line+"\n"+i;
                }
                LineNumber.setText(line);
                WorkArea.requestFocus();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog (null, e);
            }
    }//GEN-LAST:event_shoOpenActionPerformed
    
    private void shoUndoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoUndoMouseEntered
        // TODO add your handling code here:
        shoUndo.setContentAreaFilled(true);
    }//GEN-LAST:event_shoUndoMouseEntered

    private void shoUndoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoUndoMouseExited
        // TODO add your handling code here:
        shoUndo.setContentAreaFilled(false);
    }//GEN-LAST:event_shoUndoMouseExited

    private void shoRedoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoRedoMouseEntered
        // TODO add your handling code here:
        shoRedo.setContentAreaFilled(true);
    }//GEN-LAST:event_shoRedoMouseEntered

    private void shoRedoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoRedoMouseExited
        // TODO add your handling code here:
        shoRedo.setContentAreaFilled(false);
    }//GEN-LAST:event_shoRedoMouseExited

    private void shoRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoRunActionPerformed
        DisplayOutput.setText("");
        codee=WorkArea.getText();
        run=true; 
         if(run){
            runcode(codee);
            run=false;
        }
    }//GEN-LAST:event_shoRunActionPerformed

    private void shoRunMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoRunMouseEntered
        // TODO add your handling code here:
        shoRun.setContentAreaFilled(true);
    }//GEN-LAST:event_shoRunMouseEntered

    private void shoRunMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoRunMouseExited
        // TODO add your handling code here:
        shoRun.setContentAreaFilled(false);
    }//GEN-LAST:event_shoRunMouseExited

    private void WorkAreaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WorkAreaMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_WorkAreaMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(jTextField1.getText().equals("")){
            
        }
        else{
         highlight(WorkArea,jTextField1.getText());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        removeHighlights(WorkArea);
        jTextField1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Help h= new Help();
        h.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    public void updateButtons() {
        //shoUndo.setText(undoManager.getUndoPresentationName());
        //shoRedo.setText(undoManager.getRedoPresentationName());
        shoUndo.setEnabled(undoManager.canUndo());
        shoRedo.setEnabled(undoManager.canRedo());
    }
    
    public void runcode(String s){
        //Pass code input to MainP class
        MainP mp=new MainP();System.out.println("RUN: "+mp.runn);
        temp=WorkArea.getText();
        mp.exe(s,temp);
        outputt=mp.output;
        objj=mp.o;
        System.out.println(outputt);
        System.out.println(objj);
        if(mp.hasErrors){
            DisplayOutput.setForeground(Color.red);
            DisplayOutput.setText(outputt);
        }
        else{
            DisplayOutput.setForeground(Color.black);
            DisplayOutput.setText(outputt);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ide_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ide_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ide_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ide_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        ide_interface call= new ide_interface();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ide_interface().setVisible(true);
                
            }
        });
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateTimeSetting;
    private javax.swing.JTextArea DisplayOutput;
    private javax.swing.JTextArea LineNumber;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JPanel MenuButtons;
    private javax.swing.JTextArea WorkArea;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem eredo;
    private javax.swing.JMenuItem eundo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mNew;
    private javax.swing.JMenuItem mOpen;
    private javax.swing.JMenuItem mSave;
    private javax.swing.JMenuItem mSaveas;
    private javax.swing.JMenu menu;
    private javax.swing.JButton shoNew;
    private javax.swing.JButton shoOpen;
    private javax.swing.JButton shoRedo;
    private javax.swing.JButton shoRun;
    private javax.swing.JButton shoSave;
    private javax.swing.JButton shoUndo;
    // End of variables declaration//GEN-END:variables
}