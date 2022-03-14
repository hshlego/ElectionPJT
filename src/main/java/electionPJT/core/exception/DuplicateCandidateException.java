package electionPJT.core.exception;

public class DuplicateCandidateException extends RuntimeException{
    public DuplicateCandidateException() {
        super();
    }

    public DuplicateCandidateException(String message) {
        super(message);
    }

    public DuplicateCandidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCandidateException(Throwable cause) {
        super(cause);
    }
}
