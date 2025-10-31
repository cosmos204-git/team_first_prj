package kr.co.sist.prof.view;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ShowStuReportInfoDesignDialog extends JDialog {
	
	JTextArea jtlReport;
	StringBuilder report;

	
	public ShowStuReportInfoDesignDialog(ProfScoreMgrDesign psmd, boolean modal, StringBuilder report) {
		
		super(psmd, "시험지 상세 보기", modal);
		
		this.report = report;
		jtlReport = new JTextArea();
		Font font = new Font("맑은 고딕",Font.BOLD,15);
		JScrollPane jspReport = new JScrollPane(jtlReport);
		
		
		
		jtlReport.setFont(font);
		jtlReport.setText(report.toString());
		
		add("Center", jspReport);
		
		
		setBounds(psmd.getX()+100,psmd.getY()+50,400,700);		
		setVisible(true);		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
	}


	public JTextArea getJtlReport() {
		return jtlReport;
	}

	public void setJtlReport(JTextArea jtlReport) {
		this.jtlReport = jtlReport;
	}

	public StringBuilder getReport() {
		return report;
	}

}
