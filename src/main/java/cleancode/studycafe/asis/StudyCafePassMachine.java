package cleancode.studycafe.asis;

import cleancode.studycafe.asis.exception.AppException;
import cleancode.studycafe.asis.io.StudyCafeFileHandler;
import cleancode.studycafe.asis.io.StudyCafeIOHandler;
import cleancode.studycafe.asis.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();

    private final StudyCafeIOHandler studyCafeIOHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> ioHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> ioHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();
        List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askPassSelecting(passCandidates);

    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafePasses allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {

            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);


            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }

        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafePass pass) {
        StudyCafeLockerPasses allLockerPasses = studyCafeFileHandler.readLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }
}
