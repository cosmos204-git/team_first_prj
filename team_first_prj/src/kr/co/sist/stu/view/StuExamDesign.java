package kr.co.sist.stu.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import kr.co.sist.stu.controller.StuExamDesignEvt;
import kr.co.sist.stu.dto.ExamItemDTO;

public class StuExamDesign extends JDialog {

    private final int stuNum;
    private final int testCode;
    private final String stuName;
    private final String subjectName;

    private final JButton jbtnSubmit = new JButton("제출");
    private final List<ButtonGroup> groups = new ArrayList<>();
    private final List<Integer> examCodes = new ArrayList<>();

    public StuExamDesign(StuCourseMgrDesign owner,
                         boolean modal,
                         int stuNum,
                         String stuName,
                         String subjectName,
                         List<ExamItemDTO> items,
                         int testCode) {
        super(owner, "시험지", modal);
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.subjectName = subjectName;
        this.testCode = testCode;

        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        north.add(new JLabel("이름 : "));
        north.add(new JLabel(stuName));
        north.add(Box.createHorizontalStrut(24));
        north.add(new JLabel("과목 : "));
        north.add(new JLabel(subjectName));
        add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        for (ExamItemDTO it : items) {
            JPanel qPanel = new JPanel();
            qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));
            qPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            JLabel q = new JLabel(it.getExamQuest());
            JRadioButton c1 = new JRadioButton("1) " + it.getExamChoice1());
            JRadioButton c2 = new JRadioButton("2) " + it.getExamChoice2());
            JRadioButton c3 = new JRadioButton("3) " + it.getExamChoice3());
            JRadioButton c4 = new JRadioButton("4) " + it.getExamChoice4());

            ButtonGroup bg = new ButtonGroup();
            bg.add(c1); bg.add(c2); bg.add(c3); bg.add(c4);

            qPanel.add(q);
            qPanel.add(c1);
            qPanel.add(c2);
            qPanel.add(c3);
            qPanel.add(c4);

            center.add(qPanel);

            groups.add(bg);
            examCodes.add(it.getExamCode());
        }

        JScrollPane jsp = new JScrollPane(center);
        add(jsp, BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        south.add(jbtnSubmit);
        add(south, BorderLayout.SOUTH);

        StuExamDesignEvt evt = new StuExamDesignEvt(this);
        jbtnSubmit.addActionListener(evt);
        addWindowListener(evt);

        setSize(700, 500);
        setLocationRelativeTo(owner);
    }

    public JButton getJbtnSubmit() {
    	return jbtnSubmit; 
    	
    }
    public int getStuNum() {
    	return stuNum; 
    	
    }
    public int getTestCode() {
    	return testCode; 
    	
    }
    public String getStuName() {
    	return stuName; 
    	
    }
    public String getSubjectName() {
    	return subjectName; 
    	
    }
    public List<ButtonGroup> getGroups() {
    	return groups; 
    	
    }
    public List<Integer> getExamCodes() {
    	return examCodes; 
    	
    }
}
