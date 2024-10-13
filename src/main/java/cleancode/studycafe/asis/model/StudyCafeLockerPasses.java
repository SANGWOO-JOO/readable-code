package cleancode.studycafe.asis.model;

import cleancode.studycafe.asis.model.StudyCafeLockerPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lokcerPasses;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> lokcerPasses) {
        this.lokcerPasses = lokcerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses){
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafePass pass) {
        return lokcerPasses.stream()
                .filter(pass::isSameDurationType)
                .findFirst();
    }

//    public List<StudyCafeLockerPass> findPassBy(StudyCafeLockerPass studyCafePassType) {
//        return lokcerPasses.stream()
//                .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
//                .toList();
//    }
}
