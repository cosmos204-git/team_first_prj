package kr.co.sist.admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.controller.TestExamMgrDesignEvt;

public class TestExamMgrDesign extends JDialog {
	private DefaultTableModel dtmExamList ; 
	private JTable jtExamList;
	private JLabel jlblExamList,jlblSubName,jlblSubNameData,jlblExamQuest,jlblExamNum,jlblExamNumData,jlblExamChoice1
	,jlblExamChoice2,jlblExamChoice3,jlblExamChoice4,jlblCorrect, jlblEICodeData;
	private JButton jbtnReset, jbtnModify;
	private JTextField jtfExamChoice1,jtfExamChoice2,jtfExamChoice3,jtfExamChoice4, jtfCorrect;
	private JTextArea jtaExamQuest;
	private int subCode , courseCode;
	private String subName;
	
	
	public TestExamMgrDesign(AdminSubjectMgrDesign asmd, boolean modal ,int subCode, int courseCode , String subName) {
		super(asmd,"시험지 관리",modal);
		this.subCode = subCode;
		this.courseCode = courseCode;
		this.subName = subName;
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		Font font1 = new Font("맑은 고딕",Font.BOLD,13);
		UIManager.put("Label.font", font);


		String[] columNames = {"문제번호", "정답" , "문제코드"};
		dtmExamList = new DefaultTableModel(columNames,0);
		jtExamList = new JTable(dtmExamList);                                                                         
		JScrollPane jspExamList = new JScrollPane(jtExamList);
		
		jlblEICodeData = new JLabel("");
		jlblSubName = new JLabel("과목");
		jlblSubNameData = new JLabel();
		jlblExamNum = new JLabel("번호");
		jlblExamNumData = new JLabel();
		jlblExamQuest = new JLabel("문제");
		jlblExamChoice1 = new JLabel("1.");
		jlblExamChoice2 = new JLabel("2.");
		jlblExamChoice3 = new JLabel("3.");
		jlblExamChoice4 = new JLabel("4.");
		jlblCorrect = new JLabel("정답");
		jlblExamList = new JLabel("문제 리스트");
		
		jtfExamChoice1 = new JTextField();
		jtfExamChoice2 = new JTextField();
		jtfExamChoice3 = new JTextField();
		jtfExamChoice4 = new JTextField();
		jtfCorrect = new JTextField();
		
		jtaExamQuest = new JTextArea();
		JScrollPane jspQuest = new JScrollPane(jtaExamQuest);
		
		jbtnReset = new JButton("초기화");
		jbtnReset.setFont(font1);
		jbtnModify = new JButton("등록");
		jbtnModify.setFont(font1);
		
	
		//리스너 추가
		TestExamMgrDesignEvt temde = new TestExamMgrDesignEvt(this);
		jbtnReset.addActionListener(temde);
		jbtnModify.addActionListener(temde);
		jtExamList.addMouseListener(temde);
		addWindowListener(temde);
		temde.searchEiListProcess();
		
		jbtnReset.setBackground(new Color(0xE6E6E6));
		jbtnModify.setBackground(new Color(0xE6E6E6));
		
		jtExamList.setRowHeight(20);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setOpaque(true);
		centerRenderer.setBackground(new Color(0xF8F9FA));
		jtExamList.setDefaultRenderer(Object.class, centerRenderer);
		
		jspExamList.getViewport().setBackground(new Color(0xF8F9FA)); 
		jspExamList.setBorder(new LineBorder(new Color(0x000000), 2));
		
		jtExamList.getColumnModel().getColumn(1).setPreferredWidth(40);;

		
		
		setLayout(null);
		
		jlblExamList.setBounds(50,20,100,20);
		jspExamList.setBounds(50,50,190,240);
		jlblSubName.setBounds(280,20,50,20);
//		jlblEICodeData.setBounds(400,20,70,20);
		jlblSubNameData.setBounds(330,20,50,20);
		jlblExamNum.setBounds(280,55,50,20);
		jlblExamNumData.setBounds(330,55,50,20);
		jlblExamQuest.setBounds(280,90,50,20);
		jspQuest.setBounds(330,90,280,55);
		jlblExamChoice1.setBounds(280,150,50,20);
		jtfExamChoice1.setBounds(330,150,280,25);
		jlblExamChoice2.setBounds(280,180,50,20);
		jtfExamChoice2.setBounds(330,180,280,25);
		jlblExamChoice3.setBounds(280,210,50,20);
		jtfExamChoice3.setBounds(330,210,280,25);
		jlblExamChoice4.setBounds(280,240,50,20);
		jtfExamChoice4.setBounds(330,240,280,25);
		jlblCorrect.setBounds(280,270,50,20);
		jtfCorrect.setBounds(330,270,70,25);
		jbtnReset.setBounds(390,310,75,30);
		jbtnModify.setBounds(480,310,75,30);
//		jbtnAdd.setBounds(430,230,65,20);
//		jbtnDelete.setBounds(500,230,65,20);
		
//		add(jlblEICodeData);
		add(jlblCorrect);
		add(jlblExamList);
		add(jspExamList);
		add(jlblSubName);
		add(jlblExamNum);
		add(jlblExamChoice1);
		add(jlblExamChoice2);
		add(jlblExamChoice3);
		add(jlblExamChoice4);
		add(jlblExamQuest);
		add(jlblCorrect);
		add(jbtnReset);
		add(jbtnModify);
//		add(jbtnAdd);
//		add(jbtnDelete);
		add(jtfExamChoice1);
		add(jtfExamChoice2);
		add(jtfExamChoice3);
		add(jtfExamChoice4);
		add(jspQuest);
		add(jtfCorrect);
		add(jlblExamNumData);
		add(jlblSubNameData);
		
	
		
		setSize(700,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}//TestExamMgrDesign
//	public static void main(String[] args) {
//		new TestExamMgrDesign();
//	}
	
	public int getCourseCode() {
		return courseCode;
	}
	
	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public String getSubName() {
		return subName;
	}

	public int getSubCode() {
		return subCode;
	}
	public DefaultTableModel getDtmExamList() {
		return dtmExamList;
	}


	public JTable getJtExamList() {
		return jtExamList;
	}


	public JLabel getJlblExamList() {
		return jlblExamList;
	}


	public JLabel getJlblSubName() {
		return jlblSubName;
	}


	public JLabel getJlblSubNameData() {
		return jlblSubNameData;
	}


	public JLabel getJlblExamQuest() {
		return jlblExamQuest;
	}


	public JLabel getJlblExamNum() {
		return jlblExamNum;
	}


	public JLabel getJlblExamNumData() {
		return jlblExamNumData;
	}


	public JLabel getJlblExamChoice1() {
		return jlblExamChoice1;
	}


	public JLabel getJlblExamChoice2() {
		return jlblExamChoice2;
	}


	public JLabel getJlblExamChoice3() {
		return jlblExamChoice3;
	}


	public JLabel getJlblExamChoice4() {
		return jlblExamChoice4;
	}


	public JLabel getJlblCorrect() {
		return jlblCorrect;
	}


	public JButton getJbtnReset() {
		return jbtnReset;
	}

	public JTextField getJtfExamChoice1() {
		return jtfExamChoice1;
	}


	public JTextField getJtfExamChoice2() {
		return jtfExamChoice2;
	}


	public JTextField getJtfExamChoice3() {
		return jtfExamChoice3;
	}


	public JTextField getJtfExamChoice4() {
		return jtfExamChoice4;
	}


	public JTextField getJtfCorrect() {
		return jtfCorrect;
	}


	public JTextArea getJtaExamQuest() {
		return jtaExamQuest;
	}

	public JLabel getJlblEICodeData() {
		return jlblEICodeData;
	}

	
	

		
	
}//class
