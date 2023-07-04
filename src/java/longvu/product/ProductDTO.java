package longvu.product;

import java.io.Serializable;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class ProductDTO implements Serializable {
    
    private String id;
    private String name;
    private String image;
    private double price;
    private int quantity;
    private String categoryId;
    private boolean status;
    
    public ProductDTO() {
        this.id = "";
        this.name = "";
        this.image = "";
        this.price = 0;
        this.quantity = 0;
        this.categoryId = "";
        this.status = true;
    }

    public ProductDTO(String id, String name, String image, double price, 
            int quantity, String categoryId, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
