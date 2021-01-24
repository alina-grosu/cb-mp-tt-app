package cb_mp_tt_app.db.repository;

import cb_mp_tt_app.db.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides database interaction for retrieval of {@link UserEntity}
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
