package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.User;

/**
 * The UI component that is responsible for receiving user' data.
 */
public class EnterUserDataWindow extends UiPart<Stage> {

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String MESSAGE_MISSING_DATA = "Your details are missing. \nTo use Sharkie, "
            + "please enter your details: ";
    public static final String MESSAGE_INVALID_DETAILS = "The details you keyed in are invalid. \n"
            + "Kindly re-enter your details: \n";
    public static final String MESSAGE_SHOWING_WINDOW = "Showing window to enter user data.";
    public static final String MESSAGE_STORE_DATA_SUCCESS = "User data stored.";
    public static final String MESSAGE_INVALID_NAME = "Your name should not be empty or start with a whitespace!\n";
    public static final String MESSAGE_INVALID_PHONE = "Your phone number should consist of 3 or more digits!\n";
    public static final String MESSAGE_INVALID_EMAIL = "Your email should be a valid email!\n";

    private static final Logger logger = LogsCenter.getLogger(EnterUserDataWindow.class);
    private static final String FXML = "EnterUserDataWindow.fxml";

    public final UserDataStorageManager userDataStorageManager;

    @FXML
    protected Label instructionMessage;

    @FXML
    private Label errorMessageName;

    @FXML
    private Label errorMessagePhone;

    @FXML
    private Label errorMessageEmail;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField userPhoneTextField;

    @FXML
    private TextField userEmailTextField;

    @FXML
    private Button submitButton;

    /**
     * Creates a new EnterUserDataWindow.
     *
     * @param root Stage to use as the root of the EnterUserDataWindow.
     */
    public EnterUserDataWindow(Stage root, UserDataStorageManager userDataStorageManager) {
        super(FXML, root);
        this.userDataStorageManager = userDataStorageManager;
        instructionMessage.setText(MESSAGE_MISSING_DATA);

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        userNameTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userNameTextField));
        userPhoneTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userPhoneTextField));
        userEmailTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userEmailTextField));
    }

    /**
     * Creates a new EnterUserDataWindow.
     */
    public EnterUserDataWindow(UserDataStorageManager userDataStorageManager) {
        this(new Stage(), userDataStorageManager);

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        userNameTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userNameTextField));
        userPhoneTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userPhoneTextField));
        userEmailTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault(userEmailTextField));
    }

    /**
     * Shows the window to enter user data.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application
     *                               Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine(MESSAGE_SHOWING_WINDOW);
        getRoot().show();
        getRoot().centerOnScreen();
        errorMessageName.setText("");
    }

    /**
     * Returns true if the window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Hides the window to enter user data.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Handles the Submit button pressed event.
     */
    @FXML
    private void handleUserData() throws IOException {
        try {
            userDataStorageManager.storeUserData(userNameTextField.getText(),
                    userPhoneTextField.getText(), userEmailTextField.getText());
            logger.info(MESSAGE_STORE_DATA_SUCCESS);
            hide();
        } catch (IllegalArgumentException e) {
            show();
            setStyleToIndicateInputFailure();
        }
    }

    /**
     * Sets the text field style to use the default style.
     */
    private void setStyleToDefault(TextField textField) {
        textField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the text fields style to indicate a failed command.
     */
    public void setStyleToIndicateInputFailure() {
        instructionMessage.setText(MESSAGE_INVALID_DETAILS);
        errorMessageName.setText("");
        errorMessagePhone.setText("");
        errorMessageEmail.setText("");

        ObservableList<String> styleClassName = userNameTextField.getStyleClass();
        ObservableList<String> styleClassPhone = userPhoneTextField.getStyleClass();
        ObservableList<String> styleClassEmail = userEmailTextField.getStyleClass();

        if (!Name.isValidName(userNameTextField.getText())) {
            styleClassName.add(ERROR_STYLE_CLASS);
            errorMessageName.setText(MESSAGE_INVALID_NAME);
            userNameTextField.setText("");
            userNameTextField.setPromptText("Invalid name!");
        }

        if (!Phone.isValidPhone(userPhoneTextField.getText())) {
            styleClassPhone.add(ERROR_STYLE_CLASS);
            errorMessagePhone.setText(MESSAGE_INVALID_PHONE);
            userPhoneTextField.setText("");
            userPhoneTextField.setPromptText("Invalid phone number!");
        }

        if (!Email.isValidEmail(userEmailTextField.getText())) {
            styleClassEmail.add(ERROR_STYLE_CLASS);
            errorMessageEmail.setText(MESSAGE_INVALID_EMAIL);
            userEmailTextField.setText("");
            userEmailTextField.setPromptText("Invalid address!");
        }
    }

    /**
     * Automatically fills in the user details in respective field in enter user data window.
     */
    public void fillInUserDetails(User user) {
        userNameTextField.setText(user.getName().toString());
        userPhoneTextField.setText(user.getPhone().toString());
        userEmailTextField.setText(user.getEmail().toString());
    }

    /**
     * Represents a function that stores user data.
     */
    @FunctionalInterface
    public interface UserDataStorageManager {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#storeUserData(String, String, String)
         */
        void storeUserData(String name, String phone, String email) throws IllegalArgumentException, IOException;
    }

}
