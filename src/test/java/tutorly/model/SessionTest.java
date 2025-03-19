package tutorly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import tutorly.model.person.Address;
import tutorly.model.person.Email;
import tutorly.model.person.Name;
import tutorly.model.person.Person;
import tutorly.model.person.Phone;
import tutorly.model.person.UniquePersonList;

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
        // Arrange
        UniquePersonList students = new UniquePersonList();
        students.add(new Person(new Name("Alice"), new Phone("12345678"), new Email("alice@example.com"), new Address("123 Street"), new HashSet<>()));
        int sessionId = 123;
        LocalDate date = LocalDate.of(2025, 3, 19);
        String subject = "Mathematics";

        Session session = new Session(sessionId, date, subject, students);

        // Act
        String expected = "Session{students=[tutorly.model.person" +
                ".Person{name=Alice, phone=12345678, email=alice@example.com" +
                ", address=123 Street, tags=[]}], sessionId=123" +
                ", date=2025-03-19, subject=Mathematics}";
        String actual = session.toString();

        // Assert
        assertEquals(expected, actual);
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
