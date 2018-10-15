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
public class Order {
    
    private int width;
    private int length;
    private int height;
    private int id;
    private String status;

    public Order(int width, int length, int height, int id) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.id = id;
    }
    
    public Order(int width, int length, int height, int id, String status) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.id = id;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
