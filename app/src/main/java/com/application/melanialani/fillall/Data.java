package com.application.melanialani.fillall;

/**
 * Created by melan on 7/9/2016.
 */
public class Data {
    private int         tinggi, lebar, posX, posY;
    private String[][]  map;

    public Data(){}

    private void level1(){
        this.tinggi = 1;
        this.lebar = 4;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 0;
        this.posY = 0;
        map[posX][posY] = "1";
    }

    private void level2(){
        this.tinggi = 4;
        this.lebar = 4;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 0;
        this.posY = 0;
        map[posX][posY] = "1";
    }

    private void level3(){
        this.tinggi = 5;
        this.lebar = 5;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 2;
        this.posY = 2;
        map[posX][posY] = "1";

        // set blocks
        map[0][4] = "#";
        map[2][1] = "#";
        map[2][3] = "#";
        map[3][2] = "#";
        map[4][0] = "#";
        map[4][4] = "#";
    }

    private void level4(){
        this.tinggi = 5;
        this.lebar = 5;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 0;
        this.posY = 4;
        map[posX][posY] = "1";

        // set blocks
        map[0][0] = "#";
        map[1][2] = "#";
        map[3][1] = "#";
        map[4][3] = "#";
        map[4][4] = "#";
    }

    private void level5(){
        this.tinggi = 5;
        this.lebar = 6;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 0;
        this.posY = 0;
        map[posX][posY] = "1";

        // set blocks
        map[0][2] = "#";
        map[1][0] = "#";
        map[1][4] = "#";
        map[2][0] = "#";
        map[2][1] = "#";
        map[3][3] = "#";
        map[4][5] = "#";
    }

    private void level11(){
        this.tinggi = 5;
        this.lebar = 6;

        this.map = new String[tinggi][lebar];

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    this.map[x][y] = "0";
                } catch (Exception ex){

                }
            }
        }

        // set default player position
        this.posX = 0;
        this.posY = 0;
        map[posX][posY] = "1";

        // set blocks
        map[0][2] = "#";
        map[1][0] = "#";
        map[1][4] = "#";
        map[2][0] = "#";
        map[2][1] = "#";
        map[3][3] = "#";
        map[4][5] = "#";
    }

    public void setLevel(int level){
        if (level == 1) this.level1();
        else if (level == 2) this.level2();
        else if (level == 3) this.level3();
        else if (level == 4) this.level4();
        else if (level == 5) this.level5();
    }

    public int getLebar() {
        return lebar;
    }

    public String[][] getMap() {
        return map;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getTinggi() {
        return tinggi;
    }
}
