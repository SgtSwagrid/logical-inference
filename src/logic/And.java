package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class And extends Expression {
    
    private List<Expression> conjuncts = new ArrayList<>();
    
    public And(Expression... conjuncts) {
        this.conjuncts = Arrays.asList(conjuncts);
    }
    
    public And(List<Expression> conjuncts) {
        this.conjuncts = new ArrayList<>(conjuncts);
    }
    
    public List<Expression> getConjuncts() { return conjuncts; }

    @Override
    public And toCNF() {
        
        if(conjuncts.size() == 0) return new True().toCNF();
        
        return new And(conjuncts.stream()
            .map(Expression::toCNF)
            .collect(toList())).flatten();
    }
    
    public And flatten() {
        
        return new And(Stream.concat(
            conjuncts.stream()
                .filter(c -> c instanceof And)
                .flatMap(c -> ((And)c).flatten().conjuncts.stream()),
            conjuncts.stream()
                .filter(c -> !(c instanceof And)))
            .collect(toList()));
    }
    
    @Override
    public String toString() {
        
        return "(" + conjuncts.stream()
            .map(Object::toString)
            .collect(joining(" & ")) + ")";
    }
}