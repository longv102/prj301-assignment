package longvu.product;

import java.io.Serializable;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class ProductError implements Serializable {
    
    private String idError;
    private String nameError;
    private String imageError;
    private String messageError;

    public ProductError() {
        this.idError = "";
        this.nameError = "";
        this.imageError = "";
        this.messageError = "";
    }

    public ProductError(String idError, String nameError, String imageError, String messageError) {
        this.idError = idError;
        this.nameError = nameError;
        this.imageError = imageError;
        this.messageError = messageError;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
}
