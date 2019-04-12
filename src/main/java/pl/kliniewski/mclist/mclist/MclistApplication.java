package pl.kliniewski.mclist.mclist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kliniewski.mclist.mclist.server.Server;
import pl.kliniewski.mclist.mclist.server.ServerRepository;

@SpringBootApplication
public class MclistApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MclistApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(ServerRepository serverRepository)
    {
        for (int i = 1; i <= 12; i++)
        {
            Server server = new Server();
            server.setName("nazwa " + i);
            serverRepository.save(server);
        }

        return null;
    }
}
