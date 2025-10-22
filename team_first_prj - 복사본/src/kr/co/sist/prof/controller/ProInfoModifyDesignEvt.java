package kr.co.sist.prof.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import kr.co.sist.prof.view.ProfInfoModifyDesign;
import kr.co.sist.prof.view.ProfPwMdfDialog;

public class ProInfoModifyDesignEvt extends WindowAdapter implements ActionListener{

	private ProfInfoModifyDesign pimd;
	
	public ProInfoModifyDesignEvt(ProfInfoModifyDesign pimd) {
		this.pimd = pimd;
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==pimd.getJbtnAddImg()) {
			modfiyProfPicProcess();
		}
		if(e.getSource()==pimd.getJbtnModifyProfInfo()) {
			modifyProfInfoProcess();		
		}
		if(e.getSource()==pimd.getJbtnModifyProfPW()) {
			modifyProfPwProcess();		
		}
		
		if(e.getSource()==pimd.getJbtnClose()) {
			
			pimd.dispose();
		}
	/*
        Object obj = e.getSource();

        // 이미지 처리 작업을 위한 FileChooser - 나중에 작업해야 함..
        if (obj == jbtnAddImg) {
            int result = jfcAddProfPic.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = jfcAddProfPic.getSelectedFile().getAbsolutePath();
                jlblProfImg.setIcon(new ImageIcon(filePath));
                jlblProfImg.setText(null);
            }

        } else if (obj == jbtnModifyProfPW) {
            JOptionPane.showMessageDialog(this, "비밀번호 변경 화면으로 이동합니다.");

        } else if (obj == jbtnModifyProfInfo) {
            jtfProfTelData.setEditable(true);
            jtfProfEmailData.setEditable(true);
            JOptionPane.showMessageDialog(this, "휴대폰 번호와 이메일을 수정합니다.");

        } else if (obj == jbtnClose) {
            dispose();
        }
    */
    }
	
	public void modfiyProfPicProcess() {
		JOptionPane.showMessageDialog(pimd, "사진 바꾸는 기능 구현 필요");
	}
	public void modifyProfPwProcess() {
		new ProfPwMdfDialog(pimd, true);
	}
	public void modifyProfInfoProcess() {
		JOptionPane.showMessageDialog(pimd, "변경 완료 기능 구현 필요");
	}

	
}
