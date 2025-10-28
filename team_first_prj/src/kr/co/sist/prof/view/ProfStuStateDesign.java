package kr.co.sist.prof.view;

import java.awt.Font;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.prof.controller.ProfStuStateDesignEvt;
import kr.co.sist.prof.dto.ProfStuStateDTO;

public class ProfStuStateDesign extends JDialog {

    private JLabel jlblProfName, jlblProfCourse;
    private JTextField jtfProfName;
    private DefaultTableModel dtmStudent;
    private JTable jtStudent;
    private JScrollPane jspStudent;
    private JButton jbtnSearch, jbtnClose;
    private DefaultComboBoxModel<String> dcbmStuState;
    private JComboBox<String> jcbStuState;

    public ProfStuStateDesign(ProfInfoDesign pid, boolean modal) {
        super(pid, "학생관리", modal);

        // 컬럼 정의
        String[] columnNames = {"학번", "학생 이름", "휴대폰번호"};
        dtmStudent = new DefaultTableModel(columnNames, 0);
        jtStudent = new JTable(dtmStudent);
        jspStudent = new JScrollPane(jtStudent);

        jlblProfName = new JLabel("교수명");
        jlblProfCourse = new JLabel("과정명");
        jtfProfName = new JTextField();
        jbtnSearch = new JButton("검색");
        jbtnClose = new JButton("닫기");

        dcbmStuState = new DefaultComboBoxModel<>();
        jcbStuState = new JComboBox<>(dcbmStuState);

        // 폰트
        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Arrays.asList(jlblProfName, jlblProfCourse, jtfProfName, jtStudent, jbtnSearch, jbtnClose)
                .forEach(c -> c.setFont(font));
        jtStudent.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));

        // 테이블 설정
        TableColumnModel tcm = jtStudent.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(60);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(120);
        jtStudent.setRowHeight(25);

        // 배치
        setLayout(null);
        jlblProfName.setBounds(60, 65, 80, 30);
        jtfProfName.setBounds(130, 65, 120, 30);
        jlblProfCourse.setBounds(280, 65, 80, 30);
        jcbStuState.setBounds(360, 65, 150, 30);
        jspStudent.setBounds(60, 110, 460, 350);
        jbtnClose.setBounds(420, 480, 100, 35);

        add(jlblProfName);
        add(jtfProfName);
        add(jlblProfCourse);
        add(jcbStuState);
        add(jspStudent);
        add(jbtnClose);

        // 이벤트 연결
        ProfStuStateDesignEvt pssde = new ProfStuStateDesignEvt(this);
        jbtnClose.addActionListener(pssde);
        jcbStuState.addActionListener(pssde); // 콤보박스 선택 이벤트
        addWindowListener(pssde);
        
        
        setSize(600, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        
    }//ProfStuStateDesign

    // 콤보박스에 과정명 목록 갱신
    public void updateComboBoxCourses(List<ProfStuStateDTO> listProfStuState) {
        dcbmStuState.removeAllElements(); // 기존 제거
        Set<String> courseSet = new HashSet<>();

        for (ProfStuStateDTO dto : listProfStuState) {
            if (dto.getCourseName() != null && !courseSet.contains(dto.getCourseName())) {
                courseSet.add(dto.getCourseName());
                dcbmStuState.addElement(dto.getCourseName());
            }
        }

        // 첫 번째 과정 자동 선택
        if (dcbmStuState.getSize() > 0) {
            jcbStuState.setSelectedIndex(0);
        }
    }//updateComboBoxCourses

    // Getter
    public JTextField getJtfName() { 
    	return jtfProfName; 
    }
    public DefaultTableModel getDtmStudent() {
    	return dtmStudent; 
    }
    public JTable getJtStudent() {
    	return jtStudent; 
    }
    public JButton getJbtnSearch() {
    	return jbtnSearch; 
    }
    public JButton getJbtnClose() {
    	return jbtnClose; 
    }

	public JComboBox<String> getJcbStuState() {
		 return jcbStuState;	}
    
}//class
