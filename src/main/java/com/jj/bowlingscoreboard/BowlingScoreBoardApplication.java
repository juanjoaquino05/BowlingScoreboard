package com.jj.bowlingscoreboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.jj.bowlingscoreboard.objects.Board;

@SpringBootApplication
public class BowlingScoreBoardApplication implements CommandLineRunner  {
	// private static Logger LOG = LoggerFactory
	// 	.getLogger(SpringBootConsoleApplication.class);
	
	@Autowired
	private Board myBoard;

	public static void main(String[] args) {
		SpringApplication.run(BowlingScoreBoardApplication.class, args);
	}

	@Override
    public void run(String... args) {
        myBoard.loadFrames("score.dat");
        
        myBoard.printScore();
    }
}
