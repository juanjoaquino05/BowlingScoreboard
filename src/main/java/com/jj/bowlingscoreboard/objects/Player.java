package com.jj.bowlingscoreboard.objects;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    public List<Frame> frames = new ArrayList<Frame>();
    
    public Player(String name){
        this.name = name;
    }
    
    public void throwBall(String pins){
        Frame frame = frames.stream()
                .filter(p -> p.index == frames.size()).findAny().orElse(null);
        
        if(frame == null || frame.isComplete()){
            Frame newFrame = new Frame(frames.size() + 1, pins);
            frames.add(newFrame);
        }else{
            frame.addBox(pins);
        }
    }
    
    void printFrames(){
        for (int i = 0; i < frames.size(); i++) {
            frames.get(i).printBoxes();
        }
        
        System.out.println();
//        System.out.print("Total");
        for (int i = 0; i < frames.size(); i++) {
            frames.get(i).calculateTotal(frames);
            
            System.out.print("\t" + frames.get(i).total);
        }
        System.out.print("\t" + frames.get(frames.size() -1).total);
        System.out.print("\n");
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
