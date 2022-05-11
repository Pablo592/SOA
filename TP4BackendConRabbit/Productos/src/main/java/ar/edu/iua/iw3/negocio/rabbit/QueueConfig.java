package ar.edu.iua.iw3.negocio.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("rabbitQueue");
    }
}
