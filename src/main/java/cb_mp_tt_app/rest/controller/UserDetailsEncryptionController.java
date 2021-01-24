package cb_mp_tt_app.rest.controller;

import cb_mp_tt_app.ciphering.EncryptionDecryptionService;
import cb_mp_tt_app.db.model.UserEntity;
import cb_mp_tt_app.db.repository.UserRepository;
import cb_mp_tt_app.rest.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Main REST controller for encryption/decryption of user details
 */
@RestController
@RequestMapping(value = "/userdetails",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDetailsEncryptionController {

    @Autowired
    private EncryptionDecryptionService encryptionDecryptionService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/getencrypted")
    public UserDetailsDTO encryptUserDetails(@RequestBody UserDetailsDTO userDetailsDTO) {
        Long userId = userDetailsDTO.getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with id=%s was not found in the database!", userId)));
        return toDto(dto -> dto.setEncryptedUserDetails(encryptionDecryptionService.encrypt(userEntity.getUserName())));
    }

    @PostMapping("/getdecrypted")
    public UserDetailsDTO decryptUserDetails(@RequestBody UserDetailsDTO userDetailsDTO) {
        return Optional.ofNullable(userDetailsDTO.getEncryptedUserDetails())
                .map(encrypted -> toDto(dto -> dto.setDecryptedUserDetails(encryptionDecryptionService.decrypt(encrypted))))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Encrypted data can not be null!"));
    }

    private UserDetailsDTO toDto(Consumer<UserDetailsDTO> mutator) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        mutator.accept(userDetailsDTO);
        return userDetailsDTO;
    }

    public void setEncryptionDecryptionService(EncryptionDecryptionService encryptionDecryptionService) {
        this.encryptionDecryptionService = encryptionDecryptionService;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
