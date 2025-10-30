package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import kr.co.sist.login.dao.CurrentProfData;
import kr.co.sist.prof.dao.ProfStuStateDAO;
import kr.co.sist.prof.dto.ProfStuStateDTO;
import kr.co.sist.prof.view.ProfStuStateDesign;

public class ProfStuStateDesignEvt extends WindowAdapter implements ActionListener {

    private ProfStuStateDesign pssd;

    public ProfStuStateDesignEvt(ProfStuStateDesign pssd) {
        this.pssd = pssd;
        viewProfStuState(); // 초기 데이터 로딩
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == pssd.getJbtnClose()) {
            pssd.dispose();
        } 
        // 콤보박스 선택 변경 시
        else if (obj == pssd.getJcbStuState()) {
            String selectedCourse = (String) pssd.getJcbStuState().getSelectedItem();
            if (selectedCourse != null && !selectedCourse.isEmpty()) {
                try {
					updateTableByCourse(selectedCourse);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }
    
    
    @Override
    public void windowClosing(WindowEvent e) {
        pssd.dispose();
    }

    public void viewProfStuState() {
        try {
            // 1️.현재 로그인한 교수 이름 표시
            CurrentProfData cpd = CurrentProfData.getInstance();
            pssd.getJtfName().setText(cpd.getLogProfDTO().getProfName());

                        
            // 2.DB에서 과정명 + 학생목록 불러오기
            ProfStuStateDAO pssDAO = ProfStuStateDAO.getInstance();
            List<ProfStuStateDTO> listProfStuState = pssDAO.selectCourseStudent();

            // 3. 콤보박스에 과정명 채우기
            pssd.updateComboBoxCourses(listProfStuState);
            
            // 4.JTable 모델 갱신
            DefaultTableModel model = pssd.getDtmStudent();
            model.setRowCount(0); // 기존 행 삭제

            
            for (ProfStuStateDTO dto : listProfStuState) {
            	
            	Object[] row = {
                    dto.getStuNum(),
                    dto.getStuName(),
                    dto.getStuTel()
                };
            	
            	// 학번이 있는 경우에만 추가
            	if (dto.getStuNum() != 0 ) {
                    model.addRow(row);
                }            	
            	
            }


            // JTable 즉시 리프레시
            pssd.getJtStudent().revalidate();
            pssd.getJtStudent().repaint();


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        
    }// viewProfStuState
    
    private void updateTableByCourse(String courseName) throws SQLException, IOException {
	    ProfStuStateDAO pssDAO = ProfStuStateDAO.getInstance();
		List<ProfStuStateDTO> list = pssDAO.selectStudentsByCourse(courseName);

		DefaultTableModel model = pssd.getDtmStudent();
		model.setRowCount(0); // 기존 데이터 초기화

		for (ProfStuStateDTO dto : list) {
		    Object[] row = {
		        dto.getStuNum(),
		        dto.getStuName(),
		        dto.getStuTel()
		    };
        	
        	// 학번이 있는 경우에만 추가
        	if (dto.getStuNum() != 0 ) {
                model.addRow(row);
            } 
		}
	}//updateTableByCourse
    
    
}//class

