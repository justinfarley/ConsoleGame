public class Achievement{
    private Condition unlockCondition;
    private boolean isLocked;
    public Achievement(){
        unlockCondition = null;
        isLocked = true;
    }
    public Achievement(Condition unlockCondition){
        this.unlockCondition = unlockCondition;
        isLocked = true;
        if(this.unlockCondition.isMet()) isLocked = false; //check initially if the condition is already met on creation
    }
    public boolean locked(){
        return isLocked;
    }

}