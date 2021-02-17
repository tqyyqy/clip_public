package xyz.tqyyqy.clip_board;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("xyz.tqyyqy.clip_board.mapper")
@SpringBootApplication
public class ClipBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClipBoardApplication.class, args);
    }

}
