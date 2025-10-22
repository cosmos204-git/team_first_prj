package kr.co.sist.prof.view;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ShowStuReportInfoDesignDialog extends JDialog {
	
	JLabel jlbSubject;
	JTextArea jtlReport;
	
	public ShowStuReportInfoDesignDialog(ProfScoreMgrDesign psmd, boolean modal) {
		
		super(psmd, "시험지 상세 보기", modal);		
		
		jlbSubject = new JLabel("java");
		JScrollPane jspReport = new JScrollPane(jtlReport);
		
		jlbSubject.setFont(new Font("맑은고딕",Font.BOLD,24));
		
		add("North", jlbSubject);
		add("Center", jspReport);
		
		
		setBounds(psmd.getX()+100,psmd.getY()+50,400,700);		
		setVisible(true);		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
	}


}
