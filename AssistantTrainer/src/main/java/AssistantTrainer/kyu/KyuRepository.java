package AssistantTrainer.kyu;

import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KyuRepository extends JpaRepository <Kyu, Long> {

    @Query(value = "SELECT k FROM Kyu k WHERE participant_id = :participant_id")
    List<Kyu> findKyuWithParticipant(
            @Param("participant_id") Long group_id
    );
}
