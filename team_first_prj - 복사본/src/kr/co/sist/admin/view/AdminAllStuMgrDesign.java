package kr.co.sist.admin.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.controller.AdminAllStuMgrDesignEvt;

public class AdminAllStuMgrDesign extends JDialog {
	
	
	private DefaultComboBoxModel<String> dcbmCoure;
	private JComboBox<String> jcbCourse;
	private JTextField jtfStuNum;
	private JButton jbtnSearchStuNum;
	private DefaultTableModel dtmStudent;
	private JTable jtAdminSubjectMgr;
	
	public AdminAllStuMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 입과관리",modal);
		
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		
		dcbmCoure = new DefaultComboBoxModel<String>();
		jcbCourse = new JComboBox<String>(dcbmCoure);
		jcbCourse.setPreferredSize(new Dimension(100,20));
		jtfStuNum = new JTextField();
		jtfStuNum.setPreferredSize(new Dimension(100,20));
		JLabel jlblStu = new JLabel("   학번 :  ");
		jlblStu.setFont(font);
		JLabel jlblCorse = new JLabel("과정 :  ");
		jlblCorse.setFont(font);
		jbtnSearchStuNum = new JButton("검색");
		jbtnSearchStuNum.setFont(font);
		
		JPanel jpNorth = new JPanel();
		jpNorth.add(jlblCorse);
		jpNorth.add(jcbCourse);
		jpNorth.add(jlblStu);
		
		jpNorth.add(jtfStuNum);
		jpNorth.add(jbtnSearchStuNum);
		jpNorth.setFont(font);
		
		String[] columNames = {"과정명","교수명","학번","학생명","등록일"};
		dtmStudent = new DefaultTableModel(columNames,0);
		jtAdminSubjectMgr = new JTable(dtmStudent);
		JScrollPane jsp = new JScrollPane(jtAdminSubjectMgr);
		jtAdminSubjectMgr.setFont(font);
		add("North",jpNorth);
		add("Center",jsp);
		
		//리스너 추가 
		AdminAllStuMgrDesignEvt aamd= new AdminAllStuMgrDesignEvt(this);
		jbtnSearchStuNum.addActionListener(aamd);
		addWindowListener(aamd);
		
		
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}//AdminScoreMrgDesign

	public DefaultComboBoxModel<String> getDcbmCoure() {
		return dcbmCoure;
	}

	public JComboBox<String> getJcbCourse() {
		return jcbCourse;
	}

	public JTextField getJtfStuNum() {
		return jtfStuNum;
	}

	public JButton getJbtnSearchStuNum() {
		return jbtnSearchStuNum;
	}

	public DefaultTableModel getDtmStudent() {
		return dtmStudent;
	}

	public JTable getJtAdminSubjectMgr() {
		return jtAdminSubjectMgr;
	}
	
	
	
//	public static void main(String[] args) {
//		new AdminAllStuMgrDesign();
//	}
	
	
}//class
