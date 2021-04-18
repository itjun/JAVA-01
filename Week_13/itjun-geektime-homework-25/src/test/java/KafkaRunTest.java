import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.itjun.kafka.ApplicationRun;
import io.itjun.kafka.client.KafkaConsumer;
import io.itjun.kafka.client.KafkaProducer;

@SpringBootTest(classes = ApplicationRun.class)
public class KafkaRunTest {

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    public void runTest() {
        kafkaProducer.send("topic1","Hello");
    }
}
