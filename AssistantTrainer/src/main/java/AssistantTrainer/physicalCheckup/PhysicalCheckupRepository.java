package AssistantTrainer.physicalCheckup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalCheckupRepository  extends JpaRepository<PhysicalCheckup, Long> {
}
