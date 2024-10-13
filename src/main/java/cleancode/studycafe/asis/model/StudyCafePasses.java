package cleancode.studycafe.asis.model;

import cleancode.studycafe.asis.model.StudyCafePass;
import cleancode.studycafe.asis.model.StudyCafePasses;
import cleancode.studycafe.asis.model.StudyCafePass;


import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> passes;

    public StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes){
      return new StudyCafePasses(passes);
    }

    public List<StudyCafePass> findPassBy(StudyCafePassType studyCafePassType) {
        return passes.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
                .toList();
    }
}
