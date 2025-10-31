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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.controller.AdminScoreMgrDesignEvt;

public class AdminScoreMgrDesign extends JDialog {
	private DefaultTableModel dtmAdminScoreMgr ; 
	private JTable jtAdminScoreMgr;
	private DefaultComboBoxModel<String> dcbmSub , dcbmCourse;
	private JComboBox<String> jcbSub , jcbCourse;
	private JTextField jtfStuNum;
	private JButton jbtnSearchStuNum;
	private JScrollPane jps ;
	
	public AdminScoreMgrDesign(AdminInfoDesign aid, boolean modal) {
		super(aid,"관리자 - 성적 관리",modal);
		
		Font font = new Font("맑은 고딕" , Font.BOLD,15);
		
		String[] columNames = {"학번", "학생명","과목명","점수"};
		
		
		dtmAdminScoreMgr = new DefaultTableModel(columNames,0);
		
		jtAdminScoreMgr = new JTable(dtmAdminScoreMgr);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 또는 
		jtAdminScoreMgr.setDefaultRenderer(Object.class, centerRenderer);
		
		jps = new JScrollPane(jtAdminScoreMgr);
		
		jtAdminScoreMgr.setRowHeight(20);
		jtAdminScoreMgr.setAlignmentX(CENTER_ALIGNMENT);
		dcbmCourse = new DefaultComboBoxModel<String>();
		
		jcbCourse = new JComboBox<String>(dcbmCourse);
		jcbCourse.setPreferredSize(new Dimension(120,20));
		dcbmSub = new DefaultComboBoxModel<String>();
		jcbSub = new JComboBox<String>(dcbmSub);
		jcbSub.setPreferredSize(new Dimension(120,20));
		
		JLabel jlblstuCode = new JLabel("              학번 :");
		jlblstuCode.setFont(font);
		jtfStuNum = new JTextField();
		jtfStuNum.setPreferredSize(new Dimension(120,30));
		jbtnSearchStuNum = new JButton("검색");
		jbtnSearchStuNum.setFont(font);
//		jbtnSearchStuNum.setPreferredSize(new Dimension(60,30));
		
		JPanel jpNorth = new JPanel();
		jpNorth.add(jcbCourse);
		jpNorth.add(jcbSub);
		jpNorth.add(jlblstuCode);
		jpNorth.add(jtfStuNum);
		jpNorth.add(jbtnSearchStuNum);
		
		JPanel jpWest = new JPanel();
		JPanel jpEast = new JPanel();
		JPanel jpSouth = new JPanel();
		jpWest.setPreferredSize(new Dimension(30,100));
		jpEast.setPreferredSize(new Dimension(30,100));
		jpSouth.setPreferredSize(new Dimension(700,30));
		
		add("South",jpSouth);
		add("West",jpWest);
		add("East",jpEast);
		
		add("North",jpNorth);
		add("Center",jps);
		
		//리스너 추가 
		AdminScoreMgrDesignEvt  asmde = new AdminScoreMgrDesignEvt(this);
		jbtnSearchStuNum.addActionListener(asmde);
		jcbCourse.addActionListener(asmde);
		addWindowListener(asmde);
		asmde.searchCourseProcess();
		asmde.seacrhAllScore();
		
		
		setSize(700,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdminScoreMrgDesign
	

	public DefaultTableModel getDtmAdminScoreMgr() {
		return dtmAdminScoreMgr;
	}

	public JTable getJtAdminScoreMgr() {
		return jtAdminScoreMgr;
	}

	public DefaultComboBoxModel<String> getDcbmSub() {
		return dcbmSub;
	}

	public DefaultComboBoxModel<String> getDcbmCourse() {
		return dcbmCourse;
	}

	public JComboBox<String> getJcbSub() {
		return jcbSub;
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

	public JScrollPane getJps() {
		return jps;
	}
	public static void main(String[] args) {
		
	}
	
}//class
