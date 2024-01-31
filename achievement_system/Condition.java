public abstract class Condition {
    private Type type;

    public Condition(){
        type = null;
    }

    public Condition(Type typeOfCondition){
        type = typeOfCondition;
    }
    public abstract boolean isMet();
}
