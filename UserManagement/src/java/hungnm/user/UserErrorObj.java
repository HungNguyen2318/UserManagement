/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.user;

import java.io.Serializable;

/**
 *
 * @author SE130008
 */
public class UserErrorObj implements Serializable{
    private String userIdLengthError;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String usernameLengthError;
    private String emailLengthError;
    private String phoneLengthError;
    private String roleLength;
    private String imageEmpty;

    public UserErrorObj() {
    }

    /**
     * @return the userIdLengthError
     */
    public String getUserIdLengthError() {
        return userIdLengthError;
    }

    /**
     * @param userIdLengthError the userIdLengthError to set
     */
    public void setUserIdLengthError(String userIdLengthError) {
        this.userIdLengthError = userIdLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the usernameLengthError
     */
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    /**
     * @param usernameLengthError the usernameLengthError to set
     */
    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    /**
     * @return the emailLengthError
     */
    public String getEmailLengthError() {
        return emailLengthError;
    }

    /**
     * @param emailLengthError the emailLengthError to set
     */
    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    /**
     * @return the phoneLengthError
     */
    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    /**
     * @param phoneLengthError the phoneLengthError to set
     */
    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    /**
     * @return the roleLength
     */
    public String getRoleLength() {
        return roleLength;
    }

    /**
     * @param roleLength the roleLength to set
     */
    public void setRoleLength(String roleLength) {
        this.roleLength = roleLength;
    }

    /**
     * @return the imageEmpty
     */
    public String getImageEmpty() {
        return imageEmpty;
    }

    /**
     * @param imageEmpty the imageEmpty to set
     */
    public void setImageEmpty(String imageEmpty) {
        this.imageEmpty = imageEmpty;
    }
    
    
}
