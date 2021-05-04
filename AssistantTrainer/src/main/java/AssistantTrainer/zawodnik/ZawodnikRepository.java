package AssistantTrainer.zawodnik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZawodnikRepository extends JpaRepository <Zawodnik, Long> {


}
