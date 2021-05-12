package AssistantTrainer.kyu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyuRepository extends JpaRepository <Kyu, Long> {
}
