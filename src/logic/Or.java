package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Or extends Expression {
    
    private List<Expression> disjuncts = new ArrayList<>();
    
    public Or(Expression... disjuncts) {
        this.disjuncts = Arrays.asList(disjuncts);
    }
    
    public Or(List<Expression> disjuncts) {
        this.disjuncts = new ArrayList<>(disjuncts);
    }
    
    public List<Expression> getDisjuncts() { return disjuncts; }

    @Override
    public And toCNF() {
        
        if(disjuncts.size() == 0) return new False().toCNF();
        if(disjuncts.size() == 1) return new And(disjuncts.get(0).toCNF());
        
        return new And(separate().disjuncts.get(0).toCNF().getConjuncts().stream()
            .flatMap(d1 -> separate().disjuncts.get(1).toCNF().getConjuncts().stream()
                .map(d2 -> new Or(d1, d2).flatten()))
            .collect(toList())).flatten();
    }
    
    public Or flatten() {
        
        return new Or(Stream.concat(
            disjuncts.stream()
                .filter(c -> c instanceof Or)
                .flatMap(c -> ((Or)c).flatten().disjuncts.stream()),
            disjuncts.stream()
                .filter(c -> !(c instanceof Or)))
            .collect(toList()));
    }
    
    public Or separate() {
        
        if(disjuncts.size() <= 2) return new Or(disjuncts);
        
        return new Or(disjuncts.get(0), new Or(
                disjuncts.subList(1, disjuncts.size())).separate());
    }
    
    @Override
    public String toString() {
        
        return "(" + disjuncts.stream()
            .map(Object::toString)
            .collect(joining(" | ")) + ")";
    }
}