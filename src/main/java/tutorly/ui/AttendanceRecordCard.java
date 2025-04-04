package tutorly.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import tutorly.model.attendancerecord.AttendanceRecord;
import tutorly.model.person.Person;

/**
 * An UI component that displays information of a {@code AttendanceRecord}.
 */
public class AttendanceRecordCard extends UiPart<Region> {

    private static final String FXML = "AttendanceRecordListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final AttendanceRecord record;

    @FXML
    private HBox cardPane;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private VBox container;

    /**
     * Creates a {@code AttendanceRecordCard} with the given {@code AttendanceRecord} and index to display.
     */
    public AttendanceRecordCard(AttendanceRecord record, Person student, boolean isSelected,
            Callback<Boolean, ?> toggleCallback) {
        super(FXML);
        this.record = record;
        checkbox.setSelected(record.getAttendance());
        checkbox.setOnAction(event -> toggleCallback.call(!record.getAttendance()));
        id.setText(student.getId() + ". ");
        name.setText(student.getName().fullName);
        name.setWrapText(isSelected);

        if (!record.getFeedback().value.isBlank()) {
            container.getChildren().add(
                    new IconLabel(Icons.getMemoIcon(), record.getFeedback().value, isSelected).getRoot());
        }
    }
}
