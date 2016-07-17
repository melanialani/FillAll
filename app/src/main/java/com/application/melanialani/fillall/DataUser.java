package com.application.melanialani.fillall;

public class DataUser {

    private boolean hasUnlockedStage2, hasUnlockedStage3;
    private int id, coins;

    public DataUser() {}

    public DataUser(int coins, boolean hasUnlockedStage2, boolean hasUnlockedStage3){
        this.coins = coins;
        this.hasUnlockedStage2 = hasUnlockedStage2;
        this.hasUnlockedStage3 = hasUnlockedStage3;
    }

    //region GETTERS
    public int getCoins() {
        return coins;
    }

    public boolean getHasUnlockedStage2() {
        return hasUnlockedStage2;
    }

    public boolean getHasUnlockedStage3() {
        return hasUnlockedStage3;
    }
    //endregion

    //region SETTERS
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setHasUnlockedStage2(boolean hasUnlockedStage2) {
        this.hasUnlockedStage2 = hasUnlockedStage2;
    }

    public void setHasUnlockedStage3(boolean hasUnlockedStage3) {
        this.hasUnlockedStage3 = hasUnlockedStage3;
    }
    //endregion
}
