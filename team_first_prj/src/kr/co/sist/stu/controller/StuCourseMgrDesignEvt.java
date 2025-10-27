package kr.co.sist.stu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.login.dao.CurrentStuData;
import kr.co.sist.stu.view.StuCourseMgrDesign;
import kr.co.sist.stu.view.StuExamDesign;
import kr.co.sist.stu.view.StuReportDesign;

public class StuCourseMgrDesignEvt extends WindowAdapter implements ActionListener, MouseListener {
    private final StuCourseMgrDesign scmd;

    public StuCourseMgrDesignEvt(StuCourseMgrDesign scmd) {
        this.scmd = scmd;
        viewStuInfo();
        showExamProcess();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == scmd.getJbtnShowStuReport()) {
            showReportProcess();
        } else if (e.getSource() == scmd.getJbtnShowExam()) {
            showExamProcess();
        } else if (e.getSource() == scmd.getJbtnclose()) {
            scmd.dispose();
        }
    }

    public void viewAllCourse() {
        JTable table = scmd.getJtStuCourseMgr();
        int row = table.getSelectedRow();
        if (row == -1) return;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String course   = String.valueOf(model.getValueAt(row, 0));
        String subject  = String.valueOf(model.getValueAt(row, 1));
        String examTime = String.valueOf(model.getValueAt(row, 2));
        System.out.println(course + ", " + subject + ", " + examTime);

        if ("시험불가".equals(examTime)) {
            JOptionPane.showMessageDialog(scmd, "시험불가, 시간이 등록된 과목을 선택하세요.");
            return;
        }
    }

    public void viewStuInfo() {
        CurrentStuData csd = CurrentStuData.getInstance();
        scmd.getjlblStuNameData().setText(csd.getLogStuDTO().getStuName());
        scmd.getjlblStuNumData().setText(Integer.toString(csd.getLogStuDTO().getStuNum()));
    }

    @Override public void windowClosing(WindowEvent e) { scmd.dispose(); }

    public void showExamProcess() {
        JTable table = scmd.getJtStuCourseMgr();
        int row = table.getSelectedRow();
        if (row == -1) return;

        DefaultTableModel m = (DefaultTableModel) table.getModel();
        String course = m.getValueAt(row, 0).toString();
        String subject = m.getValueAt(row, 1).toString();
        String examTime = m.getValueAt(row, 2).toString();
        if ("시험불가".equals(examTime)) return;

        String stuName = scmd.getJlblStuNameData().getText();
        int stuNum = Integer.parseInt(scmd.getJlblStuNumData().getText().trim());

        kr.co.sist.stu.service.StuExamService svc = new kr.co.sist.stu.service.StuExamService();
        Integer testCode = svc.findTestCode(course, subject);
        if (testCode == null) return;


        if (svc.alreadyTaken(stuNum, testCode)) {
            JOptionPane.showMessageDialog(scmd, "이미 응시한 시험입니다.");
            return;
        }

        java.util.List<kr.co.sist.stu.dto.ExamItemDTO> items = svc.getExamItems(testCode);
        if (items == null || items.isEmpty()) return;

        StuExamDesign examDialog =
        	    new kr.co.sist.stu.view.StuExamDesign(scmd, true, stuNum, stuName, subject, items, testCode);
        	examDialog.setVisible(true);

    }

    public void showReportProcess() {
        int stuNum = CurrentStuData.getStuNum();
        String stuName = CurrentStuData.getStuName();
        new StuReportDesign(scmd, true, stuNum, stuName);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            try { viewAllCourse(); }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(scmd, "선택값을 가져오는 중 오류가 발생했습니다.");
            }
        }
    }
    @Override public void mousePressed(MouseEvent e) {
    	
    	
    }
    @Override public void mouseReleased(MouseEvent e) {
    	
    	
    }
    @Override public void mouseEntered(MouseEvent e) {
    	
    	
    }
    @Override public void mouseExited(MouseEvent e) 
    
    {}
}
