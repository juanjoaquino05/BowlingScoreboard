package com.jj.bowlingscoreboard.objects;

import com.jj.bowlingscoreboard.helpers.Helper;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public int index;
    public List<String> boxes = new ArrayList<String>();
    public int total = 0;
    
    public boolean strike;
    public boolean spare;
    
    
    public Frame(int index, String pointsFromBall){
        this.index = index;
        boxes.add(pointsFromBall);
    }
    
    public boolean isComplete(){
        
        boolean firstWasAStrike = strike = boxes.get(0).equals("10");
        boolean maxBoxSize;
        
        // evaluate spare
        int[] boxesValues = getBoxesWithValue();
        int box = boxesValues[0];
        int box2 = boxesValues[1];
        
        boolean gotSpareOnSecondBall = spare = (!strike) && (box + box2) == 10;
        
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
    
//    public int getIndex(){
//        return this.index;
//    }
    
    public void calculateTotal(List<Frame> frames){
        int[] boxes = getBoxesWithValue();
        
        int previousFrameScore = previousFrameTotal(frames);
        total += previousFrameScore;
        if(index == 9) 
            System.out.print("");
        if(strike){
            total += 10 + getNextTwoBoxes(frames); 
            return;
        }else if(spare){
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
    
    private int getTotal(List<Frame> frames){
        int[] boxes = getBoxesWithValue();
        if(strike){
            return 10 + getNextTwoBoxes(frames); 
        }
            
        if(spare){
            return 10 + getNextBox(frames, 1);
        }
        
        return boxes[0] + boxes[1];
        
    }
    
    private int getNextTwoBoxes(List<Frame> frames){

        Frame frame = frames.stream()
                .filter(p -> p.index == index + 1).findAny().orElse(null);
            
        if(frame != null){
            int[] boxes = frame.getBoxesWithValue();

            if(frame.strike) return boxes[0] + frame.getNextBox(frames, 0);

            return boxes[0] + boxes[1];
        }
//                        System.out.print (index);

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

            return (frame.strike) ? 10 : box;
        }
        return 0;
    }
    
    public void printBoxes(){
        System.out.print("\t");
        
        for (int i = 0; i < boxes.size(); i++) {
            if(strike && boxes.get(i).equals("10")) {
                System.out.print("X ");
                continue;
            }
            
            if(spare && i == 1) System.out.print("/");
            else System.out.print(boxes.get(i) + " ");
        }
    }
    
    public int[] getBoxesWithValue(){
        int box = Helper.numberValue(boxes.get(0));
        int box2 = 0;
        
        // try to get box 2 if exists
        try {
            box2 = Helper.numberValue(boxes.get(1));
        }
        catch (IndexOutOfBoundsException e) { }
        
        if(index == 10){
            int box3 = 0;
        
            // try to get box 2 if exists
            try {
                box3 = Helper.numberValue(boxes.get(2));
            }
            catch (IndexOutOfBoundsException e) { }
            
            return new int[]{box, box2, box3};
        }
        
        return new int[]{box, box2};
    }
}
