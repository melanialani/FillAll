package com.application.melanialani.fillall;

public class Data {
    private int         tinggi, lebar, posX, posY, posX2, posY2;
    private String[][]  map;
    private boolean     player2;

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

    private void level6(){
        this.tinggi = 3;
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
        this.posX = 0;      this.posX2 = 0;
        this.posY = 0;      this.posY2 = 3;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[1][1] = "#";
        map[1][4] = "#";
    }

    private void level7(){
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
        this.posX = 2;      this.posX2 = 3;
        this.posY = 3;      this.posY2 = 4;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[0][2] = "#";
        map[0][3] = "#";
        map[0][4] = "#";
        map[0][5] = "#";
        map[1][5] = "#";
        map[2][1] = "#";
    }

    private void level8(){
        this.tinggi = 4;
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
        this.posX = 3;      this.posX2 = 3;
        this.posY = 0;      this.posY2 = 1;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[1][1] = "#";
        map[2][3] = "#";
    }

    private void level9(){
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
        this.posX = 1;      this.posX2 = 3;
        this.posY = 0;      this.posY2 = 1;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[1][4] = "#";
    }

    private void level10(){
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
        this.posX = 4;      this.posX2 = 4;
        this.posY = 2;      this.posY2 = 3;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[2][2] = "#";
        map[0][4] = "#";
        map[0][5] = "#";
        map[2][4] = "#";
        map[3][4] = "#";
    }

    private void level11(){
        this.tinggi = 4;
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
        this.posX = 1;      this.posX2 = 2;
        this.posY = 2;      this.posY2 = 2;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[1][1] = "#";
        map[2][3] = "#";
    }

    private void level12(){
        this.tinggi = 7;
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
        this.posX = 3;      this.posX2 = 5;
        this.posY = 1;      this.posY2 = 3;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[0][2] = "#";
        map[2][0] = "#";
        map[2][1] = "#";
        map[5][1] = "#";
        map[5][2] = "#";
    }

    private void level13(){
        this.tinggi = 6;
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
        this.posX = 1;      this.posX2 = 3;
        this.posY = 2;      this.posY2 = 1;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[5][2] = "#";
    }

    private void level14(){
        this.tinggi = 4;
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
        this.posX = 1;      this.posX2 = 2;
        this.posY = 2;      this.posY2 = 4;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[2][0] = "#";
    }

    private void level15(){
        this.tinggi = 6;
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
        this.posX = 2;      this.posX2 = 3;
        this.posY = 2;      this.posY2 = 4;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[0][3] = "#";
        map[4][2] = "#";
    }

    public void setLevel(int level){
        if (level == 1) this.level1();
        else if (level == 2) this.level2();
        else if (level == 3) this.level3();
        else if (level == 4) this.level4();
        else if (level == 5) this.level5();
        else if (level == 5) this.level5();
        else if (level == 6) this.level6();
        else if (level == 7) this.level7();
        else if (level == 8) this.level8();
        else if (level == 9) this.level9();
        else if (level == 10) this.level10();
        else if (level == 11) this.level11();
        else if (level == 12) this.level12();
        else if (level == 13) this.level13();
        else if (level == 14) this.level14();
        else if (level == 15) this.level15();

        if (level >= 1 && level <= 5) this.player2 = false;
        else this.player2 = true;
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

    public int getPosX2() {
        return posX2;
    }

    public int getPosY2() {
        return posY2;
    }

    public boolean getPlayer2() {
        return player2;
    }
}
