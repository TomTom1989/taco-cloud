package tacos.data;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos.Taco;

import java.util.UUID;

public interface TacoRepository extends CassandraRepository<Taco, UUID> {
}

