package com.shaunstudies12.gmail.com.SpringProject.configuration;
import com.shaunstudies12.gmail.com.SpringProject.ProdDB;
import com.shaunstudies12.gmail.com.SpringProject.DevDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue ="development")
    public DevDB getDevDBBean(){
        return new DevDB();
    }
    @Bean
    @ConditionalOnProperty(name = "project.mode", havingValue ="production")
    public ProdDB getProdDBBean(){
        return new ProdDB();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
