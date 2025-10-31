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

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);
        Font titleFont = new Font("맑은 고딕", Font.BOLD, 15);

        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        JLabel lblNameTitle = new JLabel("이름 : ");
        lblNameTitle.setFont(titleFont);
        JLabel lblName = new JLabel(stuName);
        lblName.setFont(font);

        JLabel lblSubTitle = new JLabel("과목 : ");
        lblSubTitle.setFont(titleFont);
        JLabel lblSub = new JLabel(subjectName);
        lblSub.setFont(font);

        north.add(lblNameTitle);
        north.add(lblName);
        north.add(Box.createHorizontalStrut(24));
        north.add(lblSubTitle);
        north.add(lblSub);
        add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        for (ExamItemDTO it : items) {
            JPanel qPanel = new JPanel();
            qPanel.setLayout(new BoxLayout(qPanel, BoxLayout.Y_AXIS));
            qPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            JLabel q = new JLabel(it.getExamQuest());
            q.setFont(titleFont); 

            JRadioButton c1 = new JRadioButton("1. "+it.getExamChoice1());
            JRadioButton c2 = new JRadioButton("2. "+it.getExamChoice2());
            JRadioButton c3 = new JRadioButton("3. "+it.getExamChoice3());
            JRadioButton c4 = new JRadioButton("4. "+it.getExamChoice4());

            c1.setFont(font);
            c2.setFont(font);
            c3.setFont(font);
            c4.setFont(font);

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
        
        jbtnSubmit.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        Color btnColor = new Color(0x6A, 0xBD, 0xE5);
        jbtnSubmit.setBackground(btnColor);

        jbtnSubmit.setForeground(Color.WHITE);
        
        getContentPane().setFont(font);

        getContentPane().setBackground(new Color(247, 247, 249));
        north.setBackground(new Color(247, 247, 249));
        south.setBackground(new Color(247, 247, 249));

        setSize(700, 500);
        setLocationRelativeTo(owner);
    }

    public JButton getJbtnSubmit() { return jbtnSubmit; }
    public int getStuNum() { return stuNum; }
    public int getTestCode() { return testCode; }
    public String getStuName() { return stuName; }
    public String getSubjectName() { return subjectName; }
    public List<ButtonGroup> getGroups() { return groups; }
    public List<Integer> getExamCodes() { return examCodes; }
}
