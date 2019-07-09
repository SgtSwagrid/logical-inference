package logic;

public class Variable extends Expression {
    
    private String name;
    
    public Variable(String name) {
        this.name = name;
    }
    
    @Override
    public And toCNF() {
        return new And(this);
    }
    
    @Override
    public String toString() {
        return name;
    }
}