package org.hanna.woodbadge.gameshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by timhanna on 5/17/16.
 */
@SpringBootApplication
public class GameShowApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(GameShowController.class, args);
    }
}
