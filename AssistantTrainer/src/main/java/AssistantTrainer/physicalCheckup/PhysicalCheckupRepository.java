package AssistantTrainer.physicalCheckup;

import AssistantTrainer.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalCheckupRepository  extends JpaRepository<PhysicalCheckup, Long> {
    @Query(value = "SELECT p FROM PhysicalCheckup p WHERE participant_id = :participant_id")
    List<PhysicalCheckup> findPhysicalCheckupWithParticipant(
            @Param("participant_id") Long group_id
    );
}
