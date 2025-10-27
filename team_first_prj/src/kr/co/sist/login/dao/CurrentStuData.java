package kr.co.sist.login.dao;

import kr.co.sist.login.dto.LoginStudentDTO;

public class CurrentStuData {

    private static CurrentStuData csd; 

    private LoginStudentDTO logStuDTO;

    private CurrentStuData() {
    	
    }

    public static CurrentStuData getInstance() {
        if (csd == null) {
            csd = new CurrentStuData();
        }
        return csd;
    }

    public void setLogStuDTO(LoginStudentDTO logStuDTO) {
        this.logStuDTO = logStuDTO;
    }

    public LoginStudentDTO getLogStuDTO() {
        return logStuDTO;
    }

    public static int getStuNum() {
        if (getInstance().logStuDTO != null) {
            return getInstance().logStuDTO.getStuNum();
        }
        return 0;
    }

    public static String getStuName() {
        if (getInstance().logStuDTO != null) {
            return getInstance().logStuDTO.getStuName();
        }
        return "";
    }

    public static String getCourseName() {
        if (getInstance().logStuDTO != null) {
            return getInstance().logStuDTO.getStuCourseName();
        }
        return "";
    }

    public static int getCourseCode() {
        if (getInstance().logStuDTO != null) {
            return getInstance().logStuDTO.getStuCourseNum();
        }
        return 0;
    }
}
