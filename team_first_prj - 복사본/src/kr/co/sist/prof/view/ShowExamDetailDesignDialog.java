package kr.co.sist.prof.view;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ShowExamDetailDesignDialog extends JDialog {
	
	JLabel jlbSubject;
	JTextArea jtlReport;
	
	
	//부모 Frame 변경 필요
	
	public ShowExamDetailDesignDialog(ProfExamMgrDesign  pemd, boolean modal) {
		
		super(pemd, "시험지 상세 보기", modal);		
		jlbSubject = new JLabel("java");
		JScrollPane jspReport = new JScrollPane(jtlReport);
		
		jlbSubject.setFont(new Font("맑은고딕",Font.BOLD,24));
		
		add("North", jlbSubject);
		add("Center", jspReport);
		
		
		setBounds(pemd.getX()+100,pemd.getY()+50,400,700);		
		setVisible(true);		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
	}


}
