package tacos.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import tacos.Taco;

public interface TacoRepository extends MongoRepository<Taco, String> {
}
