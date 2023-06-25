/*
 * PRJ301_Assignment
 * Long Vu  * 
 */
package longvu.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import longvu.utils.DBHelpers;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class UserDAO implements Serializable {
    // username + password
    private final String SQL_LOGIN = "SELECT Fullname, RoleID, Username, Password "
            + "FROM Users "
            + "WHERE Username = ? AND Password = ?";
    
    private final String SQL_SEARCH = "SELECT Username, FullName, Email, RoleID "
            + "FROM Users "
            + "WHERE Fullname LIKE ? AND Status = ?";
    
    private final String SQL_DELETE = "UPDATE Users "
            + "SET Status = ? "
            + "WHERE Username = ?";
    
    private final String SQL_UPDATE = "UPDATE "
            + "Users "
            + "SET Fullname = ?, Email = ?, RoleID = ? "
            + "WHERE Username = ?";
    
    private final String SQL_DUPLICATE = "SELECT Username "
            + "FROM Users "
            + "WHERE Username = ?";
    
    private final String SQL_INSERT = "INSERT INTO "
            + "Users (Username, FullName, Password, Email, RoleID, Status) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    public UserDTO checkLogin(String username, String password) 
            throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_LOGIN);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                
                if (rs.next()) {
                    if (username.equals(rs.getString("Username")) && password.equals(rs.getString("Password"))) {
                        String fullName = rs.getString("Fullname");
                        String roleId = rs.getString("RoleID");
                        user = new UserDTO(username, "", fullName, "", roleId);
                    }
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return user;
    }
    
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }
    
    public void getListUsers(String searchValue) 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_SEARCH);
                stm.setString(1, "%" + searchValue + "%");
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String fullName = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    String roleId = rs.getString("RoleID");
                    String password = "***";
                    if (users == null)
                        this.users = new ArrayList<>();
                    this.users.add(new UserDTO(username, password, fullName, email, roleId));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
        
    public boolean deleteAUser(String username) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_DELETE);
                stm.setBoolean(1, false);
                stm.setString(2, username);
                int value = stm.executeUpdate();
                if (value > 0)
                    result = true;
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return result;
    }
    
    public boolean updateAUser(UserDTO user) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_UPDATE);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getEmail());
                stm.setString(3, user.getRoleId());
                stm.setString(4, user.getUsername());
                int value = stm.executeUpdate();
                if (value > 0)
                    result = true;
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return result;
    }
    
    public boolean checkDuplicate(String username) 
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_DUPLICATE);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next())
                    check = true;
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return check;
    }
    
    public boolean createUser(UserDTO user) 
            throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_INSERT);
                stm.setString(1, user.getUsername());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getRoleId());
                stm.setBoolean(6, true);
                int value = stm.executeUpdate();
                if (value > 0)
                    check = true;
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return check;
    }
    
}
