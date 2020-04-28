package com.jj.bowlingscoreboard;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "data")
@Validated
public class AppConfig {
     
    @NotNull
    private String directory;
 
    // standard getters and setters
    public void setDirectory(String dir){
        this.directory = dir;
    }

    public String getDirectory(){
        return this.directory;
    }
}