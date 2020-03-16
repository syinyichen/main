package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the table of transactions.
 */
public class PersonTablePanel<T extends Transaction> extends UiPart<Region> {

    private static final String FXML = "PersonTablePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(PersonTablePanel.class);

    @FXML
    private TableView<T> transactionTableView;
    @FXML
    private TableColumn<T, String> indexTableColumn;
    @FXML
    private TableColumn<T, Description> descriptionTableColumn;
    @FXML
    private TableColumn<T, Amount> amountTableColumn;
    @FXML
    private TableColumn<T, LocalDate> dateTableColumn;

    public PersonTablePanel(ObservableList<T> transactionList) {
        super(FXML);
        indexTableColumn.setCellValueFactory(data -> {
            T transaction = data.getValue();
            int index = transactionTableView.getItems().indexOf(transaction) + 1;
            return new SimpleStringProperty(Integer.toString(index));
        });
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<T, Description>("description"));
        amountTableColumn.setCellValueFactory(new PropertyValueFactory<T, Amount>("amount"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<T, LocalDate>("date"));
        transactionTableView.setItems(transactionList);
    }

}
