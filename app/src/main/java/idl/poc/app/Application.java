package idl.poc.app;

import java.nio.channels.Channel;
import java.util.Arrays;

import com.idl.poc.example.SampleServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.micrometer.core.instrument.Timer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"idl.poc"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


            SampleServiceGrpc.SampleServiceBlockingStub sampleService = SampleServiceGrpc.newBlockingStub(ManagedChannelBuilder.forAddress("localhost", 6565).build());
        };
    }

}