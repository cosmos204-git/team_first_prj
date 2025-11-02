package kr.co.sist.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.admin.dto.examItemDTO;
import kr.co.sist.admin.service.TestExamMgrService;
import kr.co.sist.admin.view.TestExamMgrDesign;

public class TestExamMgrDesignEvt extends WindowAdapter implements ActionListener,MouseListener{
	private TestExamMgrDesign temd;
	private List<examItemDTO> examItemList;
//	private TestExamMgrService tems;	
	
	public TestExamMgrDesignEvt(TestExamMgrDesign temd) {
		this.temd=temd;
	}//TestExamMgrDesignEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==temd.getJbtnReset()) {
			resetProcess();
		}//end if 
		if(ae.getSource()==temd.getJbtnModify()) {
			addProcess();
			searchEiListProcess();
//			resetProcess();
//			int row = temd.getJtExamList().getSelectedRow();
//			selectedEIList(row);
		}//end if 
//		if(ae.getSource()==temd.getJbtnAdd()) {
//			System.out.println("시험지보기/추가 - 추가");
//			addProcess();
//		}//end if 
//		if(ae.getSource()==temd.getJbtnDelete()) {
//			System.out.println("시험지보기/추가 - 삭제");
//			deleteProcess();
//		}//end if 
	}//ActionEvent e
	
	@Override
	public void windowClosing(WindowEvent e) {
		temd.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent me) {
		resetProcess();
		int row = temd.getJtExamList().getSelectedRow();
		selectedEIList(row);
	}//mouseClicked(MouseEvent e

	
	
	public void resetProcess() {
		temd.getJlblEICodeData().setText("");
		temd.getJlblExamNumData().setText("");
		temd.getJtaExamQuest().setText("");
		temd.getJtfExamChoice1().setText("");
		temd.getJtfExamChoice2().setText("");
		temd.getJtfExamChoice3().setText("");
		temd.getJtfExamChoice4().setText("");
		temd.getJtfCorrect().setText("");
	}//resetProcess
	
	public void addProcess() {
		try {
			String text = temd.getJlblEICodeData().getText();
			if(text ==null || text.isEmpty()) {
				JOptionPane.showMessageDialog(temd, "문제번호를 선택 후 등록하세요.");
				 return;
			}
			String[] check = {temd.getJtaExamQuest().getText()
					,temd.getJtfExamChoice1().getText()
					,temd.getJtfExamChoice2().getText()
					,temd.getJtfExamChoice3().getText()
					,temd.getJtfExamChoice4().getText()
					,temd.getJtfCorrect().getText()
					};
			int checkNum=0;
			for(String a: check) {
				boolean f = (a==null || a.equals(""));
				if(f) {
					checkNum++;
				}
			}
			System.out.println(checkNum);
			if(checkNum != 0) {
				JOptionPane.showMessageDialog(temd, "누락된 항목이 있습니다.");
				return;
			}
			int correct = 0 ;
//			if(temd.getJtfCorrect().getText() == null || temd.getJtfCorrect().getText().isEmpty()) {
//				System.out.println("dsalkfjsalk");
//				return;
//			}
			if(temd.getJtfCorrect().getText() != null && !temd.getJtfCorrect().getText().isEmpty()) {
				correct = Integer.parseInt(temd.getJtfCorrect().getText().trim());
				if(correct<1||correct>4) {
					JOptionPane.showMessageDialog(temd, "정답을 1~4 사이의 값으로 적으세요.");
					return;
				}//end if 
			}//end if 
		
			TestExamMgrService tems = new TestExamMgrService();
//			int row =  temd.getJtExamList().getSelectedRow();
//			int eiCode = examItemList.get(row).getExamCode();
			int eiCode = Integer.parseInt( temd.getJlblEICodeData().getText());
			examItemDTO eiDTO = new examItemDTO();
			eiDTO.setExamQuest(temd.getJtaExamQuest().getText());
			eiDTO.setExamChoice1(temd.getJtfExamChoice1().getText());
			eiDTO.setExamChoice2(temd.getJtfExamChoice2().getText());
			eiDTO.setExamChoice3(temd.getJtfExamChoice3().getText());
			eiDTO.setExamChoice4(temd.getJtfExamChoice4().getText());
			eiDTO.setExamCorrectTChoice(correct);
			int cnt = tems.modifyEI(eiDTO, eiCode);
			if(cnt ==1) {
				JOptionPane.showMessageDialog(temd, "등록	 되었습니다.");
				resetProcess();
			}
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(temd, "정답은 번호로만 입력 가능합니다.");
		}
		
			
		
	}//addProcess
	
	public void deleteProcess() {}//deleteProcess
	
	
	public void searchEiListProcess() {
		TestExamMgrService tems = new TestExamMgrService();
		temd.getJlblSubNameData().setText(temd.getSubName());
		DefaultTableModel dtm = temd.getDtmExamList();
		examItemList = tems.searchSubEI(temd.getSubCode(),temd.getCourseCode());
		dtm.setRowCount(0);
		String[] rowData = new String[3];
		int i=1;
		for(examItemDTO eiDTO : examItemList) {
			
			rowData[0] =String.valueOf(i);
			rowData[1] = String.valueOf(eiDTO.getExamCorrectTChoice()	) ;
			rowData[2] = String.valueOf( eiDTO.getExamCode());
			
			dtm.addRow(rowData);
			i++;
		}//end for
		
	}//searchEiListProcess
	
	public void selectedEIList(int row) {
		int index = row;
		String choice1 = examItemList.get(index).getExamChoice1();
		String choice2 = examItemList.get(index).getExamChoice2();
		String choice3 = examItemList.get(index).getExamChoice3();
		String choice4 = examItemList.get(index).getExamChoice4();
		String code = String.valueOf( examItemList.get(index).getExamCode());
		String correct = String.valueOf(examItemList.get(index).getExamCorrectTChoice());
		temd.getJlblExamNumData().setText((index+1)+"");
		temd.getJtaExamQuest().setText(examItemList.get(index).getExamQuest());
		temd.getJtfExamChoice1().setText(choice1);
		temd.getJtfExamChoice2().setText(choice2);
		temd.getJtfExamChoice3().setText(choice3);
		temd.getJtfExamChoice4().setText(choice4);
		temd.getJtfCorrect().setText(correct);
		temd.getJlblEICodeData().setText(code);
		

		if(temd.getJtfCorrect().getText().equals("0")) {
			temd.getJtfCorrect().setText("");
		}
	}//eiListselect
	
	
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}//class
