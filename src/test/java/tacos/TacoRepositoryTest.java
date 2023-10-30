package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import tacos.Taco;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataCassandraTest
public class TacoRepositoryTest {

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Test
    public void testSaveAndRetrieveTaco() {
        // Create a Taco object
        Taco taco = new Taco();
        taco.setId(UUID.randomUUID()); // Set a UUID (you might have your own logic for this)
        taco.setName("Test Taco");

        // Save the Taco object to the database using the CassandraTemplate
        cassandraTemplate.insert(taco);

        // Retrieve the Taco from the database using its ID
        Taco retrievedTaco = cassandraTemplate.selectOneById(taco.getId(), Taco.class);

        // Check if the retrieved Taco is not null and has the same name
        assertNotNull(retrievedTaco);
        assertEquals(taco.getName(), retrievedTaco.getName());
    }
}
