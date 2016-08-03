package com.application.melanialani.fillall;

public class Data {

    //region variables
    private int             tinggi, lebar, posX, posY, posX2, posY2, level, coin, posPlay;
    private String[][]      map;
    private boolean         player2;
    private String          character;

    private final int[] priceCharacter = {0, 300, 300, 500, 500, 800, 800, 800, 300, 300};
    //endregion

    //region constanta animation
    private final int[] flatre_right = {R.drawable.flatre_ani0, R.drawable.right_flatre_ani1,
            R.drawable.right_flatre_ani2, R.drawable.right_flatre_ani3, R.drawable.right_flatre_ani4};
    private final int[] flatre_left = {R.drawable.flatre_ani0, R.drawable.left_flatre_ani1,
            R.drawable.left_flatre_ani2, R.drawable.left_flatre_ani3, R.drawable.left_flatre_ani4};
    private final int[] flatre_up = {R.drawable.flatre_ani0, R.drawable.up_flatre_ani1,
            R.drawable.up_flatre_ani2, R.drawable.up_flatre_ani3, R.drawable.up_flatre_ani4};
    private final int[] flatre_down = {R.drawable.flatre_ani0, R.drawable.down_flatre_ani1,
            R.drawable.down_flatre_ani2, R.drawable.down_flatre_ani3, R.drawable.down_flatre_ani4};

    private final int[] red_right = {R.drawable.red_ani0, R.drawable.right_red_ani1,
            R.drawable.right_red_ani2, R.drawable.right_red_ani3, R.drawable.right_red_ani4};
    private final int[] red_left = {R.drawable.red_ani0, R.drawable.left_red_ani1,
            R.drawable.left_red_ani2, R.drawable.left_red_ani3, R.drawable.left_red_ani4};
    private final int[] red_up = {R.drawable.red_ani0, R.drawable.up_red_ani1,
            R.drawable.up_red_ani2, R.drawable.up_red_ani3, R.drawable.up_red_ani4};
    private final int[] red_down = {R.drawable.red_ani0, R.drawable.down_red_ani1,
            R.drawable.down_red_ani2, R.drawable.down_red_ani3, R.drawable.down_red_ani4};

    private final int[] atu_right = {R.drawable.atu_ani0, R.drawable.right_atu_ani1,
            R.drawable.right_atu_ani2, R.drawable.right_atu_ani3, R.drawable.right_atu_ani4};
    private final int[] atu_left = {R.drawable.atu_ani0, R.drawable.left_atu_ani1,
            R.drawable.left_atu_ani2, R.drawable.left_atu_ani3, R.drawable.left_atu_ani4};
    private final int[] atu_up = {R.drawable.atu_ani0, R.drawable.up_atu_ani1,
            R.drawable.up_atu_ani2, R.drawable.up_atu_ani3, R.drawable.up_atu_ani4};
    private final int[] atu_down = {R.drawable.atu_ani0, R.drawable.down_atu_ani1,
            R.drawable.down_atu_ani2, R.drawable.down_atu_ani3, R.drawable.down_atu_ani4};

    private final int[] black_right = {R.drawable.black_ani0, R.drawable.right_black_ani1,
            R.drawable.right_black_ani2, R.drawable.right_black_ani3, R.drawable.right_black_ani4};
    private final int[] black_left = {R.drawable.black_ani0, R.drawable.left_black_ani1,
            R.drawable.left_black_ani2, R.drawable.left_black_ani3, R.drawable.left_black_ani4};
    private final int[] black_up = {R.drawable.black_ani0, R.drawable.up_black_ani1,
            R.drawable.up_black_ani2, R.drawable.up_black_ani3, R.drawable.up_black_ani4};
    private final int[] black_down = {R.drawable.black_ani0, R.drawable.down_black_ani1,
            R.drawable.down_black_ani2, R.drawable.down_black_ani3, R.drawable.down_black_ani4};

    private final int[] bulb_right = {R.drawable.bulb_ani0, R.drawable.right_bulb_ani1,
            R.drawable.right_bulb_ani2, R.drawable.right_bulb_ani3, R.drawable.right_bulb_ani4};
    private final int[] bulb_left = {R.drawable.bulb_ani0, R.drawable.left_bulb_ani1,
            R.drawable.left_bulb_ani2, R.drawable.left_bulb_ani3, R.drawable.left_bulb_ani4};
    private final int[] bulb_up = {R.drawable.bulb_ani0, R.drawable.up_bulb_ani1,
            R.drawable.up_bulb_ani2, R.drawable.up_bulb_ani3, R.drawable.up_bulb_ani4};
    private final int[] bulb_down = {R.drawable.bulb_ani0, R.drawable.down_bulb_ani1,
            R.drawable.down_bulb_ani2, R.drawable.down_bulb_ani3, R.drawable.down_bulb_ani4};

    private final int[] geeks_right = {R.drawable.geeks_ani0, R.drawable.right_geeks_ani1,
            R.drawable.right_geeks_ani2, R.drawable.right_geeks_ani3, R.drawable.right_geeks_ani4};
    private final int[] geeks_left = {R.drawable.geeks_ani0, R.drawable.left_geeks_ani1,
            R.drawable.left_geeks_ani2, R.drawable.left_geeks_ani3, R.drawable.left_geeks_ani4};
    private final int[] geeks_up = {R.drawable.geeks_ani0, R.drawable.up_geeks_ani1,
            R.drawable.up_geeks_ani2, R.drawable.up_geeks_ani3, R.drawable.up_geeks_ani4};
    private final int[] geeks_down = {R.drawable.geeks_ani0, R.drawable.down_geeks_ani1,
            R.drawable.down_geeks_ani2, R.drawable.down_geeks_ani3, R.drawable.down_geeks_ani4};

    private final int[] oce_right = {R.drawable.oce_ani0, R.drawable.right_oce_ani1,
            R.drawable.right_oce_ani2, R.drawable.right_oce_ani3, R.drawable.right_oce_ani4};
    private final int[] oce_left = {R.drawable.oce_ani0, R.drawable.left_oce_ani1,
            R.drawable.left_oce_ani2, R.drawable.left_oce_ani3, R.drawable.left_oce_ani4};
    private final int[] oce_up = {R.drawable.oce_ani0, R.drawable.up_oce_ani1,
            R.drawable.up_oce_ani2, R.drawable.up_oce_ani3, R.drawable.up_oce_ani4};
    private final int[] oce_down = {R.drawable.oce_ani0, R.drawable.down_oce_ani1,
            R.drawable.down_oce_ani2, R.drawable.down_oce_ani3, R.drawable.down_oce_ani4};

    private final int[] pika_right = {R.drawable.pika_ani0, R.drawable.right_pika_ani1,
            R.drawable.right_pika_ani2, R.drawable.right_pika_ani3, R.drawable.right_pika_ani4};
    private final int[] pika_left = {R.drawable.pika_ani0, R.drawable.left_pika_ani1,
            R.drawable.left_pika_ani2, R.drawable.left_pika_ani3, R.drawable.left_pika_ani4};
    private final int[] pika_up = {R.drawable.pika_ani0, R.drawable.up_pika_ani1,
            R.drawable.up_pika_ani2, R.drawable.up_pika_ani3, R.drawable.up_pika_ani4};
    private final int[] pika_down = {R.drawable.pika_ani0, R.drawable.down_pika_ani1,
            R.drawable.down_pika_ani2, R.drawable.down_pika_ani3, R.drawable.down_pika_ani4};

    private final int[] pur_right = {R.drawable.pur_ani0, R.drawable.right_pur_ani1,
            R.drawable.right_pur_ani2, R.drawable.right_pur_ani3, R.drawable.right_pur_ani4};
    private final int[] pur_left = {R.drawable.pur_ani0, R.drawable.left_pur_ani1,
            R.drawable.left_pur_ani2, R.drawable.left_pur_ani3, R.drawable.left_pur_ani4};
    private final int[] pur_up = {R.drawable.pur_ani0, R.drawable.up_pur_ani1,
            R.drawable.up_pur_ani2, R.drawable.up_pur_ani3, R.drawable.up_pur_ani4};
    private final int[] pur_down = {R.drawable.pur_ani0, R.drawable.down_pur_ani1,
            R.drawable.down_pur_ani2, R.drawable.down_pur_ani3, R.drawable.down_pur_ani4};

    private final int[] lemon_right = {R.drawable.lemon_ani0, R.drawable.right_lemon_ani1,
            R.drawable.right_lemon_ani2, R.drawable.right_lemon_ani3, R.drawable.right_lemon_ani4};
    private final int[] lemon_left = {R.drawable.lemon_ani0, R.drawable.left_lemon_ani1,
            R.drawable.left_lemon_ani2, R.drawable.left_lemon_ani3, R.drawable.left_lemon_ani4};
    private final int[] lemon_up = {R.drawable.lemon_ani0, R.drawable.up_lemon_ani1,
            R.drawable.up_lemon_ani2, R.drawable.up_lemon_ani3, R.drawable.up_lemon_ani4};
    private final int[] lemon_down = {R.drawable.lemon_ani0, R.drawable.down_lemon_ani1,
            R.drawable.down_lemon_ani2, R.drawable.down_lemon_ani3, R.drawable.down_lemon_ani4};
    //endregion

    public Data(){ }

    //region data mapping levels
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

    private void level13(){
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
        map[3][0] = "#";
        map[1][1] = "#";
        map[2][3] = "#";
    }

    private void level14(){
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
        this.posX = 3;      this.posX2 = 1;
        this.posY = 1;      this.posY2 = 2;
        map[posX][posY] = "1";
        map[posX2][posY2] = "1";

        // set blocks
        map[1][4] = "#";
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
    //endregion

    public void setLevel(int level){
        this.level = level;

        if (level == 1) this.level1();
        else if (level == 2) this.level2();
        else if (level == 3) this.level3();
        else if (level == 4) this.level4();
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
        this.posPlay=level;
    }

    //region GETTERS for mapping level
    public int getLevel() {
        return level;
    }

    public int getTinggi() {
        return tinggi;
    }

    public int getLebar() {
        return lebar;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
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

    public String[][] getMap() {
        return map;
    }
    //endregion

    //region GETTERS for character's pictures
    public int getResId_atuRight(int index) {
        return atu_right[index];
    }

    public int getResId_atuLeft(int index) {
        return atu_left[index];
    }

    public int getResId_atuUp(int index) {
        return atu_up[index];
    }

    public int getResId_atuDown(int index) {
        return atu_down[index];
    }


    public int getResId_blackRight(int index) {
        return black_right[index];
    }

    public int getResId_blackLeft(int index) {
        return black_left[index];
    }

    public int getResId_blackUp(int index) {
        return black_up[index];
    }

    public int getResId_blackDown(int index) {
        return black_down[index];
    }


    public int getResId_bulbRight(int index) {
        return bulb_right[index];
    }

    public int getResId_bulbLeft(int index) {
        return bulb_left[index];
    }

    public int getResId_bulbUp(int index) {
        return bulb_up[index];
    }

    public int getResId_bulbDown(int index) {
        return bulb_down[index];
    }


    public int getResId_flatreRight(int index) {
        return flatre_right[index];
    }

    public int getResId_flatreLeft(int index) {
        return flatre_left[index];
    }

    public int getResId_flatreUp(int index) {
        return flatre_up[index];
    }

    public int getResId_flatreDown(int index) {
        return flatre_down[index];
    }


    public int getResId_geeksRight(int index) {
        return geeks_right[index];
    }

    public int getResId_geeksLeft(int index) {
        return geeks_left[index];
    }

    public int getResId_geeksUp(int index) {
        return geeks_up[index];
    }

    public int getResId_geeksDown(int index) {
        return geeks_down[index];
    }


    public int getResId_lemonRight(int index) {
        return lemon_right[index];
    }

    public int getResId_lemonLeft(int index) {
        return lemon_left[index];
    }

    public int getResId_lemonUp(int index) {
        return lemon_up[index];
    }

    public int getResId_lemonDown(int index) {
        return lemon_down[index];
    }


    public int getResId_oceRight(int index) {
        return oce_right[index];
    }

    public int getResId_oceLeft(int index) {
        return oce_left[index];
    }

    public int getResId_oceUp(int index) {
        return oce_up[index];
    }

    public int getResId_oceDown(int index) {
        return oce_down[index];
    }


    public int getResId_pikaRight(int index) {
        return pika_right[index];
    }

    public int getResId_pikaLeft(int index) {
        return pika_left[index];
    }

    public int getResId_pikaUp(int index) {
        return pika_up[index];
    }

    public int getResId_pikaDown(int index) {
        return pika_down[index];
    }


    public int getResId_purRight(int index) {
        return pur_right[index];
    }

    public int getResId_purLeft(int index) {
        return pur_left[index];
    }

    public int getResId_purUp(int index) {
        return pur_up[index];
    }

    public int getResId_purDown(int index) {
        return pur_down[index];
    }


    public int getResId_redRight(int index) {
        return red_right[index];
    }

    public int getResId_redLeft(int index) {
        return red_left[index];
    }

    public int getResId_redUp(int index) {
        return red_up[index];
    }

    public int getResId_redDown(int index) {
        return red_down[index];
    }
    //endregion

    public int getPosPlay() {
        return posPlay;
    }

    public int getpriceCharacter(int idx){
        return this.priceCharacter[idx];
    }
}
