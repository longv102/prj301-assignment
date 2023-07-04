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
public class UserDTO implements Serializable {
    
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String roleId;

    public UserDTO() {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.email = "";
        this.roleId = "";
    }

    public UserDTO(String username, String password, 
            String fullName, String email, String roleId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
}
