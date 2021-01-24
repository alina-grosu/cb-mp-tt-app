package cb_mp_tt_app.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A DTO which includes a superset of attributes needed for
 * data transfer for both encryption/decryption endpoints.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsDTO {

    private Long id;
    private String encryptedUserDetails;
    private String decryptedUserDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncryptedUserDetails() {
        return encryptedUserDetails;
    }

    public void setEncryptedUserDetails(String encryptedUserDetails) {
        this.encryptedUserDetails = encryptedUserDetails;
    }

    public String getDecryptedUserDetails() {
        return decryptedUserDetails;
    }

    public void setDecryptedUserDetails(String decryptedUserDetails) {
        this.decryptedUserDetails = decryptedUserDetails;
    }
}
