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
public class OrderToPieceList {

    private PieceList pieceList;
    private int length;
    private int width;
    private int height;

    public PieceList getPieceList() {
        return pieceList;
    }

    public PieceList makePieceList(Order order) throws LoginSampleException {
        try {

            length = order.getLength();
            width = order.getWidth();
            height = order.getHeight();

            pieceList = new PieceList();
            for (int i = 0; i < height; i++) {
                int currentLength;
                int currentWidth;

                if (i % 2 == 1) {
                    currentLength = length - 4;
                    currentWidth = width;
                } else {
                    currentLength = length;
                    currentWidth = width - 4;
                }

                pieceList.addToFourByTwo(0, currentLength / 4);
                pieceList.addToTwoByTwo(0, currentLength % 4 / 2);
                pieceList.addToOneByTwo(0, currentLength % 2);
                // we know that the side opposite to this is an exact mirror
                pieceList.addToFourByTwo(2, currentLength / 4);
                pieceList.addToTwoByTwo(2, currentLength % 4 / 2);
                pieceList.addToOneByTwo(2, currentLength % 2);

                // same process as above but now with the other 2 sides and using Width instead of Length
                pieceList.addToFourByTwo(1, currentWidth / 4);
                pieceList.addToTwoByTwo(1, currentWidth % 4 / 2);
                pieceList.addToOneByTwo(1, currentWidth % 2);

                pieceList.addToFourByTwo(3, currentWidth / 4);
                pieceList.addToTwoByTwo(3, currentWidth % 4 / 2);
                pieceList.addToOneByTwo(3, currentWidth % 2);

            }
            pieceList.total();

            return pieceList;

        } catch (Exception e) {
            throw new LoginSampleException("Stykliste fejl");
        }

    }

}
