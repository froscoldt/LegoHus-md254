/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Mark
 */
public class PieceList {
    
    private int[] fourByTwo = {0,0,0,0,0};
    private int[] twoByTwo =  {0,0,0,0,0};
    private int[] oneByTwo =  {0,0,0,0,0};

    public void total() {
        for (int i = 0; i < 4; i++) {
            fourByTwo[4] += fourByTwo[i];
            twoByTwo[4] += twoByTwo[i];
            oneByTwo[4] += oneByTwo[i];
        }
        
    }
    
    public int[] getFourByTwo() {
        return fourByTwo;
    }

    public int[] getTwoByTwo() {
        return twoByTwo;
    }

    public int[] getOneByTwo() {
        return oneByTwo;
    }
    
    public void addToFourByTwo(int index, int value) {
        fourByTwo[index] += value;
    }
    public void addToTwoByTwo(int index, int value) {
        twoByTwo[index] += value;
    }
    public void addToOneByTwo(int index, int value) {
        oneByTwo[index] += value;
    }

    
}
