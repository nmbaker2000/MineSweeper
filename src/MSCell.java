public class MSCell {
    private int bombsNear;
    private boolean isBomb,isFlagged, isHidden;

    public MSCell(){
        isHidden = true;
        isFlagged = false;
    }

    public void setBombsNear(int n){
        bombsNear = n;
    }

    public void setAsBomb(){
        isBomb = true;
    }

    public void toggleFlagged(){
        if(isFlagged)
            isFlagged = false;
        else
            isFlagged = true;
    }

    public void reveal(){
        isHidden = false;
    }

    public int getBombsNear(){
        return bombsNear;
    }

    public boolean isBomb(){
        return isBomb;
    }

    public boolean isFlagged(){
        return isFlagged;
    }

    public boolean isHidden(){
        return isHidden;
    }

}
