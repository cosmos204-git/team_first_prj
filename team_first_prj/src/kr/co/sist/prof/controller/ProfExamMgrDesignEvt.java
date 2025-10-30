package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.prof.dao.ProfExamMgrDAO;
import kr.co.sist.prof.dto.ExamListDTO;
import kr.co.sist.prof.view.ProfExamMgrDesign;
import kr.co.sist.prof.view.ShowExamDetailDesignDialog;

public class ProfExamMgrDesignEvt extends WindowAdapter implements ActionListener, ListSelectionListener {
	
	private ProfExamMgrDesign pemd;
	
	public ProfExamMgrDesignEvt(ProfExamMgrDesign pemd) {
		this.pemd = pemd;
        viewProfExamMgr(); // 초기 데이터 로딩
       
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == pemd.getJbtnExamDetail()) {
			 ShowExamDetailDesignDialog sddd =new ShowExamDetailDesignDialog(pemd,true);
			 // 시험지상세 보기 화면에 시험문제를 넣어서 보여준다.
			 
			 
		}

		if(e.getSource() == pemd.getJbtnModifyExam()) {
			try {
				updateExamItem(pemd);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	
	//과정-과목 리스트를 변경할 때 아래쪽 라벨과 콤보에 업데이트  
	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (!e.getValueIsAdjusting()) {
	        try {
	            viewExamSelect();
	        } catch (SQLException | IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	
	@Override
	public void windowClosing(WindowEvent e) {
		pemd.dispose();
	}
	
	   public void viewProfExamMgr() {
	        try {
	            // 1.DB에서 교수의 교번으로 과정명 + 시험과 불러오기
	        	ProfExamMgrDAO pemDAO = ProfExamMgrDAO.getInstance();
	            List<ExamListDTO> listExamListDTO = pemDAO.selectExamList();

//	            // 2. JTable에 과정명/시험과목/ 시험일정 채우기
	            DefaultTableModel model = pemd.getDtmStudent();
	            model.setRowCount(0); // 기존 행 삭제

	            for (ExamListDTO dto : listExamListDTO) {
	                Object[] row = {
	                    dto.getCourseName(),
	                    dto.getSubName(),
	                    dto.getExamOpen(),
	                    dto.getExamStart(),
	                    dto.getExamEnd()
	                };
	                model.addRow(row);
	            }


	            // JTable 리프레시
	            pemd.getJtCourseTable().revalidate();
	            pemd.getJtCourseTable().repaint();


	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	        
	    }// viewProfExamMgr


	   //
	   // JTable 선택 변경시 레이블/콤보박스에 값 세팅
	   private void viewExamSelect() throws SQLException, IOException {
			
		    int row = pemd.getJtCourseTable().getSelectedRow();
		    
		    if (row == -1) {
		        return; // 선택된 행이 없으면 종료
		    }

		    
	        pemd.getJtfExamCourseData().setText(pemd.getJtCourseTable().getValueAt(row, 0).toString());
	        pemd.getJtfExamSubjectData().setText(pemd.getJtCourseTable().getValueAt(row, 1).toString());

		    
		    // 시험오픈, 시험시작, 시험끝 시간이 비어 있을 경우 리턴 
		    Object val2 = pemd.getJtCourseTable().getValueAt(row, 2);
		    Object val3 = pemd.getJtCourseTable().getValueAt(row, 3);
		    Object val4 = pemd.getJtCourseTable().getValueAt(row, 4);

		    // 값이 null이거나 빈 문자열인 경우 체크
		    if (val2 == null || val2==" " ) {
		    	
		    } else {
		    	pemd.getJcbExamState().setSelectedItem(pemd.getJtCourseTable().getValueAt(row, 2).toString());
		    }
		    		
		    if (val3 == null || val3==" " ) {
		    	
		    } else {
		    	pemd.getJcbExamStart().setSelectedItem(pemd.getJtCourseTable().getValueAt(row, 3).toString());
		    }
		    		
		    if (val4 == null || val4==" " ) {
		    	
		    } else {
		    	pemd.getJcbExamEnd().setSelectedItem(pemd.getJtCourseTable().getValueAt(row, 4).toString());	        
		    }
		    
	        
		}//updateExam
	   
	   
	   // 변경된 내용으로 업데이트하고 리프레쉬
	   private void updateExamItem(ProfExamMgrDesign pemd2) throws SQLException, IOException {
		    try {
	            // Design에 있는 컴포넌트 값 읽기
		    	ExamListDTO uDTO = new ExamListDTO();
		    	uDTO.setCourseName(pemd2.getJtfExamCourseData().getText());
		    	uDTO.setSubName(pemd2.getJtfExamSubjectData().getText());
		    	uDTO.setExamOpen((String) pemd2.getJcbExamState().getSelectedItem());
		    	uDTO.setExamStart((String) pemd2.getJcbExamStart().getSelectedItem());
		    	uDTO.setExamEnd((String) pemd2.getJcbExamEnd().getSelectedItem());

		    	if ( pemd2.getJcbExamEnd().getSelectedIndex() < pemd2.getJcbExamStart().getSelectedIndex() ) {
	                JOptionPane.showMessageDialog(pemd2, " 시험 [종료시간]을 [시작시간]보다 늦게 설정해 주세요.");
	                return;
		    	}
		    	
	            // DAO 호출
	            ProfExamMgrDAO pDAO = ProfExamMgrDAO.getInstance();
	            int result = pDAO.updateExam(uDTO);

	            // 해당 코스의 과목은 유일하므로 Update는 1개만 되어야 한다.
	            if (result ==1 ) {
	                JOptionPane.showMessageDialog(pemd2, "시험 정보가 수정되었습니다.");
		            // JTable 리프레시 해준다.
	                viewProfExamMgr();
	            } else {
	                JOptionPane.showMessageDialog(pemd2, "수정할 데이터가 없거나 과정/과목이 중복됩니다.");
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(pemd2, "수정 중 오류가 발생했습니다.");
	        }
		    
	    }//updateExamItem		

} // class
