package com.server.churchdatabaseAPI.Model;

import com.server.churchdatabaseAPI.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.AUGUST;
@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            //String username, String password, String firstName,
            // String surname, String email, String role, Date createdDate, String status
            User_ mariam = new User_("Mariam","mariam","Halimah","Akande",
                    "Mariam@yahoo.com","member",LocalDate.of(2004,AUGUST, 4),"Married");
            User_ jacob = new User_("jacob","jacob","Olu","Ajao",
                    "jacob@yahoo.com","member",LocalDate.of(2004,AUGUST, 4),"Single");

            repository.saveAll(List.of(mariam,jacob));

        };
    }
}


