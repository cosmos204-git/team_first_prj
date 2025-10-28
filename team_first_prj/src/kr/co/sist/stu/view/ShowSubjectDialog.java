package kr.co.sist.stu.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.controller.ShowSubjectDialogEvt;

public class ShowSubjectDialog extends JDialog {
	
	private DefaultTableModel dtmShowSubDialog;
	private JTable jtShowSubDialog;
	private JButton jbtnClose;
	
	
	public ShowSubjectDialog( CourseSelectDesign csd, boolean modal ) {
		
		super(csd, "상세 과목", modal);
		
		setLayout(new BorderLayout(10,10));
		
		//JTable
		String[] columnNames = {"상세 과목"};
		String[][] rowData = {{"java"},{"sql"},{"html5"}};
		dtmShowSubDialog = new DefaultTableModel(rowData, columnNames);
		jtShowSubDialog = new JTable(dtmShowSubDialog);
		jtShowSubDialog.setRowHeight(25);
		
		JScrollPane jspTable = new JScrollPane(jtShowSubDialog);
		
		JPanel jpCenter = new JPanel(new BorderLayout());
		jpCenter.add(jspTable, BorderLayout.CENTER);
		jpCenter.setBorder(new EmptyBorder(15, 20, 0, 20)); 
		
		jbtnClose = new JButton("닫기");
		JPanel jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpSouth.add(jbtnClose);
		jpSouth.setBorder(new EmptyBorder(0, 0, 10, 10));
		
		add(jpCenter, BorderLayout.CENTER);
		add(jpSouth, BorderLayout.SOUTH);

		ShowSubjectDialogEvt ssde = new ShowSubjectDialogEvt(this);
		jbtnClose.addActionListener(ssde);
		addWindowListener(ssde);
		
		
		
		setBounds(csd.getX()+50,csd.getY()+50,300,300);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//ShowSubjectDialog


	public DefaultTableModel getDtmShowSubDialog() {
		return dtmShowSubDialog;
	}


	public JTable getJtShowSubDialog() {
		return jtShowSubDialog;
	}


	public JButton getJbtnClose() {
		return jbtnClose;
	}
	
	

}//class
