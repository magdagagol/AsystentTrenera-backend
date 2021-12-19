package AssistantTrainer.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantGroupRepository extends JpaRepository<ParticipantGroup, Long> {
}
