package com.jj.bowlingscoreboard.objects;

import com.jj.bowlingscoreboard.helpers.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Frame {
    private int index;
    private List<String> boxes = new ArrayList<String>();
    private int total = 0;
    
    private boolean strike;
    private boolean spare;
    
    
    public Frame(int index, String pointsFromBall){
        this.index = index;
        boxes.add(pointsFromBall);
    }
    
    public boolean isComplete(){
        
        boolean firstWasAStrike = strike = boxes.get(0).equals("10");
        boolean maxBoxSize;
        
        // evaluate spare
        int[] boxesValues = getBoxesWithValue();
        
        boolean gotSpareOnSecondBall = spare = (!strike) && IntStream.of(boxesValues).sum() == 10;
        
        // frames 1 to 9
        if(index < 10){
            maxBoxSize = boxes.size() > 1;
            
            return maxBoxSize || firstWasAStrike;
        }
        
        // only frame 10 
        maxBoxSize = boxes.size() > 2;
        return  maxBoxSize || !firstWasAStrike && gotSpareOnSecondBall;
    }
    
    public void addBox(String pinsFromBall){
        boxes.add(pinsFromBall);
    }
    
    public void calculateTotal(List<Frame> frames){
        int[] boxes = getBoxesWithValue();
        
        int previousFrameScore = previousFrameTotal(frames);
        total += previousFrameScore;
        
        if(isStrike()){
            total += 10 + getNextTwoBoxes(frames); 
            return;
        }else if(isSpare()){
            total += 10 + getNextBox(frames, 1);
            return;
        }else {
            total += boxes[0] + boxes[1];
        }
    }
    
    private int previousFrameTotal(List<Frame> frames){
        if(index == 1) return 0;
        
        return frames.get(index - 2).total;
    }
    
    private int getNextTwoBoxes(List<Frame> frames) {

        Frame frame = frames.stream()
                .filter(p -> p.index == index + 1).findAny().orElse(null);
            
        if(frame != null){
            int[] boxes = frame.getBoxesWithValue();

            if(frame.isStrike()) return boxes[0] + frame.getNextBox(frames, 0);

            return boxes[0] + boxes[1];
        }

        if(index == 10){
            int[] boxes = getBoxesWithValue();
            
            return boxes[1] + boxes[2];
        }
        
        return 0;
    }
    
    private int getNextBox(List<Frame> frames, int actualbox){
        
        if(index == 10){
            int[] boxes = getBoxesWithValue();
            return boxes[actualbox + 1];
        }
        
        Frame frame = frames.stream()
            .filter(p -> p.index == index + 1).findAny().orElse(null);

        if(frame != null){
            int box = Helper.numberValue(frame.boxes.get(0));

            return (frame.isStrike()) ? 10 : box;
        }
        return 0;
    }
    
    public void printBoxes(){
        System.out.print("\t");
        
        for (int i = 0; i < boxes.size(); i++) {
            if(isStrike() && boxes.get(i).equals("10")) {
                System.out.print("  X");
                continue;
            }
            
            if(isSpare() && i == 1) System.out.print("/");
            else System.out.print(boxes.get(i) + " ");
        }
    }
    
    public int[] getBoxesWithValue(){

        int[] values = new int [boxes.size()];

        for (int i = 0; i < boxes.size(); i++) {
            values[i] = Helper.numberValue(boxes.get(i));
        }
        
        return values;
    }

    // Getters And Setters
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<String> boxes) {
        this.boxes = boxes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public boolean isSpare() {
        return spare;
    }

    public void setSpare(boolean spare) {
        this.spare = spare;
    }
    
}
