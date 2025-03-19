package tutorly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void testSessionConstructorAndGetters() {
        int sessionId = 12345;
        LocalDate date = LocalDate.of(2025, 3, 12);
        String subject = "Math";
        Session session = new Session(sessionId, date, subject);

        assertEquals(sessionId, session.getSessionId()); // Use getSessionId() instead of getStudentId()
        assertEquals(date, session.getDate());
        assertEquals(subject, session.getSubject());
    }

    @Test
    void testToString() {
        Session session = new Session(67890, LocalDate.of(2025, 3, 15), "Physics");
        String expected = "Session{sessionId=67890, date=2025-03-15, subject=Physics, students=" + session.getStudents() + "}";
        assertEquals(expected, session.toString());
    }

    @Test
    void testDifferentDates() {
        Session session1 = new Session(123, LocalDate.of(2025, 3, 12), "Math");
        Session session2 = new Session(123, LocalDate.of(2025, 3, 13), "Math");

        assertNotEquals(session1, session2);
    }

    @Test
    void testHashCodeConsistency() {
        Session session = new Session(789, LocalDate.of(2025, 3, 12), "English");
        int hash1 = session.hashCode();
        int hash2 = session.hashCode();

        assertEquals(hash1, hash2);
    }

    @Test
    void testIncrementalSessionId() {
        Session session1 = new Session(123, LocalDate.of(2025, 3, 12), "Math");
        Session session2 = new Session(124, LocalDate.of(2025, 3, 13), "Math");

        // Ensure the session IDs increment by 1
        assertEquals(session1.getSessionId() + 1, session2.getSessionId());
    }
}