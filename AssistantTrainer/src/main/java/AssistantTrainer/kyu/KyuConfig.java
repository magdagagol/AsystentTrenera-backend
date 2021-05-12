package AssistantTrainer.kyu;

import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class KyuConfig {
    CommandLineRunner commandLineRunner (KyuRepository repository){
        return args -> {
            Kyu kyu1= new Kyu(
                    "10.05.2021",
                    "zielony"
            );
            repository.saveAll(
                    List.of(kyu1)
            );
        };
    }
}
