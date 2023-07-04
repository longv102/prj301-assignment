package longvu.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longvu.utils.DBHelpers;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class ProductDAO implements Serializable {
    
    // CRUD methods with the entity Product
    // One admin will be responsible for managing products
    
    private final String SQL_SEARCH_V1 = "SELECT ProductID, ProductName, ProductImage, "
            + "Price, Quantity, CategoryID "
            + "FROM Product "
            + "WHERE ProductName LIKE ? AND Status = ?";
    
    private final String SQL_SEARCH_V2 = "SELECT ProductID, ProductName, ProductImage, "
            + "Price, Quantity, CategoryID "
            + "FROM Product "
            + "WHERE Status = ?";
    
    private final String SQL_DELETE = "UPDATE Product "
            + "SET Status = ? "
            + "WHERE ProductID = ?";
    
    private final String SQL_UPDATE = "UPDATE Product "
            + "SET ProductName = ?, Price = ?, Quantity = ?, CategoryID = ? "
            + "WHERE ProductID = ?";
    
    private final String SQL_DUPLICATE = "SELECT ProductID "
            + "FROM Product "
            + "WHERE ProductID = ?";
    
    private final String SQL_INSERT = "INSERT INTO "
            + "Product (ProductID, ProductName, ProductImage, Price, Quantity, "
            + "CategoryID, Status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private final String SQL_GET_QUANTITY = "SELECT Quantity "
            + "FROM Product "
            + "WHERE ProductID = ?";
    
    private final String SQL_UPDATE_QUANTITY = "UPDATE Product "
            + "SET Quantity = ? "
            + "WHERE ProductID = ?";
    
    private final String SQL_GET_PRODUCT_BY_ID = "SELECT ProductID, ProductName, "
            + "Price, Quantity "
            + "FROM Product "
            + "WHERE ProductID = ?";
    
    private List<ProductDTO> products;
    
    public List<ProductDTO> getProducts() {
        return products;
    }
    
    public void getListProducts(String searchValue) 
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_SEARCH_V1);
                stm.setString(1, "%" + searchValue + "%");
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String id = rs.getString("ProductID");
                    String name = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    String categoryId = rs.getString("CategoryID");
                    String image = rs.getNString("ProductImage");
                    if (products == null)
                        this.products = new ArrayList<>();
                    this.products.add(new ProductDTO(id, name, image, 
                            price, quantity, categoryId, true));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
    
    public void getListProducts() 
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_SEARCH_V2);
                stm.setBoolean(1, true);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String id = rs.getString("ProductID");
                    String name = rs.getString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    String categoryId = rs.getString("CategoryID");
                    String image = rs.getNString("ProductImage");
                    if (products == null)
                        this.products = new ArrayList<>();
                    this.products.add(new ProductDTO(id, name, image, 
                            price, quantity, categoryId, true));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
    
    public boolean deleteAProduct(String id) 
            throws ClassNotFoundException, NamingException, SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            stm = con.prepareStatement(SQL_DELETE);
            stm.setBoolean(1, false);
            stm.setString(2, id);
            
            int value = stm.executeUpdate();
            if (value > 0)
                check = true;
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return check;
    }
    
    public boolean updateAProduct(ProductDTO product)
            throws SQLException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_UPDATE);
                stm.setString(1, product.getName());
                stm.setDouble(2, product.getPrice());
                stm.setInt(3, product.getQuantity());
                stm.setString(4, product.getCategoryId());
                stm.setString(5, product.getId());
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
    
    public boolean checkDuplicate(String id) 
            throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_DUPLICATE);
                stm.setString(1, id);
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

    public boolean addAProduct(ProductDTO product) 
            throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_INSERT);
                stm.setString(1, product.getId());
                stm.setString(2, product.getName());
                stm.setString(3, product.getImage());
                stm.setDouble(4, product.getPrice());
                stm.setInt(5, product.getQuantity());
                stm.setString(6, product.getCategoryId());
                stm.setBoolean(7, product.isStatus());
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

    public int getProductQuantity(String productId) 
            throws SQLException, ClassNotFoundException, NamingException {
        int quantity = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_GET_QUANTITY);
                stm.setString(1, productId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("Quantity");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return quantity;
    }

    public boolean updateProductQuantity(ProductDTO product) 
            throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_UPDATE_QUANTITY);
                stm.setInt(1, product.getQuantity());
                stm.setString(2, product.getId());
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
    
    public ProductDTO getProductInfoById(String id) 
            throws SQLException, ClassNotFoundException, NamingException {
        ProductDTO product = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(SQL_GET_PRODUCT_BY_ID);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productId = rs.getString("ProductID");
                    String productName = rs.getNString("ProductName");
                    double price = rs.getDouble("Price");
                    int quantity = rs.getInt("Quantity");
                    product = new ProductDTO(productId, productName, "", 
                            price, quantity, "", true);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return product;
    }
}
