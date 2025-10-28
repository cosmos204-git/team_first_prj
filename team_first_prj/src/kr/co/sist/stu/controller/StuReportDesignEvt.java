package kr.co.sist.stu.controller;

import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.stu.dto.StuReportDTO;
import kr.co.sist.stu.service.StuReportService;
import kr.co.sist.stu.view.StuReportDesign;

public class StuReportDesignEvt extends WindowAdapter implements ActionListener {

    private final StuReportDesign view;
    private final StuReportService svc = new StuReportService();

    public StuReportDesignEvt(StuReportDesign view) { this.view = view; }

    @Override
    public void windowOpened(WindowEvent e) {
        DefaultTableModel dtm = view.getDtmStuReport();
        dtm.setRowCount(0);

        int stuNum = view.getStuNum();            
        List<StuReportDTO> list = svc.searchScoreByStuNum(stuNum);
        if (list == null) return;

        for (StuReportDTO dto : list) {
            dtm.addRow(new Object[]{
                dto.getStuName(), dto.getSubName(), dto.getStuScore(), dto.getStuRank()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getJbtnClose()) {
            view.dispose();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) { view.dispose(); }
}
