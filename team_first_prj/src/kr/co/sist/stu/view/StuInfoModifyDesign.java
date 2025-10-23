package kr.co.sist.stu.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.co.sist.stu.controller.StuInfoModifyDesignEvt;

public class StuInfoModifyDesign extends JDialog{
   private JLabel jlblStuImg;
   private JTextField jtfStuNumData,jtfStuNameData,jtfStuTelData,jtfStuEmail, jtfStuAddr, jtfStuAddr2;
   private JButton jbtnSelectImage, jbtnModifyPw , jbtnModifyStuInfo , jbtnClose ;
   private StuInfoDesign sid;
   public StuInfoModifyDesign(StuInfoDesign sid, boolean modal) {
      super(sid,"학생 개인정보 변경",modal);
      this.sid=sid;
      
      JLabel jlblStuName = new JLabel("이름");
      JLabel jlblStuNum = new JLabel("학번");
      JLabel jlblStuTel = new JLabel("전화번호");
      JLabel jlblStuEmail= new JLabel("이메일주소");
      JLabel jlblStuAddr = new JLabel("주소");
      JLabel jlblStuAddr2 = new JLabel("상세주소");
      
      
      
      jlblStuImg = new JLabel();
      ImageIcon ii = new ImageIcon();
      jtfStuNameData = new JTextField();
      jtfStuNameData.setEditable(false);
      jtfStuNumData = new JTextField();
      jtfStuNumData.setEditable(false);
      jtfStuTelData = new JTextField();
      jtfStuTelData.setEditable(false);
      jtfStuEmail = new JTextField();
      jtfStuAddr = new JTextField();
      jtfStuAddr2 = new JTextField();
      
      jbtnSelectImage = new JButton("사진 선택");
      jbtnModifyPw = new JButton("비밀번호변경");
      jbtnModifyStuInfo = new JButton("개인정보 수정");       
      jbtnClose  = new JButton("닫기");
      
      Font font = new Font("맑은고딕",Font.BOLD,15);
      
      setLayout(null);
      
      jtfStuNameData.setSize(150,30);
      jtfStuNameData.setLocation(250,30);
      jtfStuNameData.setFont(font);
      add(jtfStuNameData);

      
      jtfStuNumData.setSize(150,30);
      jtfStuNumData.setLocation(250,70);
      jtfStuNumData.setFont(font);
      add(jtfStuNumData);
      
      
      jtfStuTelData.setSize(150,30);
      jtfStuTelData.setLocation(250,110);
      jtfStuTelData.setFont(font);
      add(jtfStuTelData);
      
      
      jtfStuEmail.setSize(150,30);
      jtfStuEmail.setLocation(250,150);
      jtfStuEmail.setFont(font);
      add(jtfStuEmail);
      
      
      jtfStuAddr.setSize(150,30);
      jtfStuAddr.setLocation(250,190);
      jtfStuAddr.setFont(font);
      add(jtfStuAddr);
      
      
      jtfStuAddr2.setSize(150,30);
      jtfStuAddr2.setLocation(250,230);
      jtfStuAddr2.setFont(font);
      add(jtfStuAddr2);
      
      
      jlblStuName.setSize(200,50);
      jlblStuName.setLocation(140,20);
      jlblStuName.setFont(font);
      add(jlblStuName);
   
      
      jlblStuNum.setSize(200,50);
      jlblStuNum.setLocation(140,60);
      jlblStuNum.setFont(font);
      add(jlblStuNum);
      
      jlblStuTel.setSize(200,50);
      jlblStuTel.setLocation(140,100);
      jlblStuTel.setFont(font);
      add(jlblStuTel);
      
      jlblStuEmail.setSize(200,50);
      jlblStuEmail.setLocation(140,140);
      jlblStuEmail.setFont(font);
      add(jlblStuEmail);
      
      jlblStuAddr.setSize(200,50);
      jlblStuAddr.setLocation(140,180);
      jlblStuAddr.setFont(font);
      add(jlblStuAddr);
      
      jlblStuAddr2.setSize(200,50);
      jlblStuAddr2.setLocation(140,220);
      jlblStuAddr2.setFont(font);
      add(jlblStuAddr2);
      
      jlblStuImg.setSize(100,120);
      jlblStuImg.setLocation(20, 38);
      jlblStuImg.setFont(font);
      jlblStuImg.setBackground(Color.BLACK);
      jlblStuImg.setOpaque(true);
      jlblStuImg.setHorizontalAlignment(SwingConstants.CENTER);
      add(jlblStuImg);
      
      jbtnSelectImage.setSize(100,30);
      jbtnSelectImage.setLocation(20, 170);
      jbtnSelectImage.setFont(font);
      add(jbtnSelectImage);
      
      jbtnModifyPw.setSize(150,30);
      jbtnModifyPw.setLocation(420,30);
      jbtnModifyPw.setFont(font);      
      add(jbtnModifyPw);
      
      
      jbtnModifyStuInfo.setSize(150,30);
      jbtnModifyStuInfo.setLocation(420,70);
      jbtnModifyStuInfo.setFont(font);      
      add(jbtnModifyStuInfo);
      
      jbtnClose.setSize(150,30);
      jbtnClose.setLocation(420,110);
      jbtnClose.setFont(font);      
      add(jbtnClose);
      
      StuInfoModifyDesignEvt simde = new StuInfoModifyDesignEvt(this);
      jbtnClose.addActionListener(simde);
      jbtnModifyPw.addActionListener(simde);
      jbtnModifyStuInfo.addActionListener(simde);
      jbtnSelectImage.addActionListener(simde);
      addWindowListener(simde);
      
      setBounds(sid.getX()+50,sid.getY()+50,600,350);
      setVisible(true);
   }

   public StuInfoDesign getSid() {
	return sid;
}

   public void setSid(StuInfoDesign sid) {
	this.sid = sid;
   }

   public JLabel getJlblStuImg() {
	return jlblStuImg;
   }

   public JTextField getJtfStuNumData() {
	return jtfStuNumData;
   }

   public JTextField getJtfStuNameData() {
	return jtfStuNameData;
   }

   public JTextField getJtfStuTelData() {
	return jtfStuTelData;
   }

   public JTextField getJtfStuEmail() {
	return jtfStuEmail;
   }

   public JTextField getJtfStuAddr() {
	return jtfStuAddr;
   }

   public JTextField getJtfStuAddr2() {
	return jtfStuAddr2;
   }

   public JButton getJbtnSelectImage() {
	return jbtnSelectImage;
   }

   public JButton getJbtnModifyPw() {
	return jbtnModifyPw;
   }

   public JButton getJbtnModifyStuInfo() {
	return jbtnModifyStuInfo;
   }

   public JButton getJbtnClose() {
	return jbtnClose;
   }
   
   


}
