package recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.module.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
