package com.sparta.individualassignment;

import com.sparta.individualassignment.model.Blog;
import com.sparta.individualassignment.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@ServletComponentScan
@EnableJpaAuditing
@SpringBootApplication
public class IndividualAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualAssignmentApplication.class, args);
    }

//        # main 아래에 삽입
    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    @Bean
    public CommandLineRunner demo(BlogRepository blogRepository) {
        return (args) -> {
            blogRepository.save(new Blog("선원1", "항해99", "어푸어푸"));
        };
    }

}
