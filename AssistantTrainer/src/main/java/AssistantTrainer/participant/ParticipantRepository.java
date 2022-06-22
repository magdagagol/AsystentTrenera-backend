package AssistantTrainer.participant;

import AssistantTrainer.attendance.Attendance;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository <Participant, Long> {
    @Query(value = "SELECT p FROM Participant p WHERE group_id = :group_id")
    List<Participant> findParticipantWithGroup(
            @Param("group_id") Long group_id
    );

    @Query(value = "SELECT p FROM Participant p WHERE group_id = :group_id or group_id = NULL")
    List<Participant> findParticipantWithoutGroup(
            @Param("group_id") Long group_id
    );
}
