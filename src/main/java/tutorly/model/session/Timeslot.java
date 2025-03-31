package tutorly.model.session;

import static tutorly.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import tutorly.commons.util.ToStringBuilder;

/**
 * Represents a Session timeslot.
 */
public class Timeslot {

    public static final String MESSAGE_END_BEFORE_START_DATETIME = "Start datetime must not be after end datetime.";

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a Timeslot with the given start and end datetimes. Start datetime must not be after end datetime.
     *
     * @param startTime The start datetime of the timeslot.
     * @param endTime   The end datetime of the timeslot.
     */
    public Timeslot(LocalDateTime startTime, LocalDateTime endTime) {
        requireAllNonNull(startTime, endTime);
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException(MESSAGE_END_BEFORE_START_DATETIME);
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Checks if this timeslot overlaps with another timeslot.
     *
     * @param other The other timeslot to check against.
     * @return True if the timeslots overlap, false otherwise.
     */
    public boolean isOverlapping(Timeslot other) {
        return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Timeslot otherTimeslot)) {
            return false;
        }

        return startTime.equals(otherTimeslot.startTime) && endTime.equals(otherTimeslot.endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}
