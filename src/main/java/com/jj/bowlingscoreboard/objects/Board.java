package com.jj.bowlingscoreboard.objects;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.jj.bowlingscoreboard.AppConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Board {
    private List<Player> players = new ArrayList<Player>();
	
	@Autowired
	private AppConfig config;

    public Board(){
        
    }

    public void addFrame(String[] line) {
        
        String playerName = line[0];
        String pins = line[1];
        
        Player player = players.stream()
                .filter(p -> p.getName().equals(playerName)).findAny().orElse(null);
        
        if(player == null){
            player = new Player(playerName);
            players.add(player);   
        }
            
        player.throwBall(pins);
    }
    
    public void printScore(){
        System.out.print("Frame\t\t");
        for (int i = 1; i < 11; i++) {
            System.out.print(i + "\t" );
        }
        System.out.println("Total");
        
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName());
            players.get(i).printFrames();
        }
    }

    public void loadFrames(String filename){
        try {
            File myObj = new File(config.getDirectory().concat(filename));

            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.trim().split(" ");
                
                if(line.length != 2) continue;
              
                this.addFrame(line);
            }
            
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
