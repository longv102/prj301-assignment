/*
 * PRJ301_Assignment
 * Long Vu  * 
 */
package longvu.user;

import java.io.Serializable;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class UserError implements Serializable {
    
    private String usernameError;
    private String fullNameError;
    private String roleIdError;
    private String passwordError;
    private String confirmError;
    private String messageError;
    
    public UserError() {
        this.usernameError = "";
        this.fullNameError = "";
        this.roleIdError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.messageError = "";
    }

    public UserError(String usernameError, String fullNameError, String roleIdError, 
            String passwordError, String confirmError, String messageError) {
        this.usernameError = usernameError;
        this.fullNameError = fullNameError;
        this.roleIdError = roleIdError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.messageError = messageError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIdError() {
        return roleIdError;
    }

    public void setRoleIdError(String roleIdError) {
        this.roleIdError = roleIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
}
