package tacos.data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

public interface OrderRepository extends MongoRepository<TacoOrder, String> {
}