package recipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.module.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
