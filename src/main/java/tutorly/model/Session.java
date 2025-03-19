package tutorly.model;

import static tutorly.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import tutorly.commons.util.ToStringBuilder;
import tutorly.model.person.UniquePersonList;

/**
 * Represents a Session in the tutorly.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Session {

    public static final String MESSAGE_CONSTRAINTS = "Session must have a valid session ID and at least one student.";

    private int sessionId; // sessionId field is effectively final
    private final LocalDate date;
    private final String subject;
    private final UniquePersonList students;

    /**
     * Every field must be present and not null.
     */
    public Session(int sessionId, LocalDate date, String subject, UniquePersonList students) {
        requireAllNonNull(sessionId, date, subject, students);
        this.sessionId = sessionId;
        this.date = date;
        this.subject = subject;
        this.students = students;
    }

    /**
     * The constructor without students when creating a new session.
     */
    public Session(int sessionId, LocalDate date, String subject) {
        requireAllNonNull(sessionId, date, subject);
        this.sessionId = sessionId;
        this.date = date;
        this.subject = subject;
        this.students = new UniquePersonList();
    }

    public void setId(int sessionId) {
        if (this.sessionId != 0) {
            throw new IllegalStateException("Session ID has already been set for this session.");
        }

        if (sessionId < 1) {
            throw new IllegalArgumentException("Session ID must be a positive integer.");
        }

        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public UniquePersonList getStudents() {
        return students;
    }

    /**
     * Returns true if both sessions have the same identity and data fields.
     * This defines a stronger notion of equality between two sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session otherSession)) {
            return false;
        }

        return sessionId == otherSession.sessionId
                && date.equals(otherSession.date)
                && subject.equals(otherSession.subject)
                && students.equals(otherSession.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, date, subject, students);
    }

    @Override
    public String toString() {
        return "Session{students=" + students + ", sessionId="
                + sessionId + ", date=" + date + ", subject=" + subject + "}";
    }
}
