package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.Transaction;

public class TransactionGroupCard extends UiPart<Region> {
    private static final String FXML = "TransactionGroupCard.fxml";

    @FXML
    private Label transactionGroupLabel;

    @FXML
    private ListView<Transaction> transactionItemsList;

    public final FilteredList<Transaction> transactionsList;

    public TransactionGroupCard(FilteredList<Transaction> transactionsList) {
        super(FXML);
        this.transactionsList = transactionsList;

        transactionGroupLabel.setText(transactionsList.get(0).getDate().toString());
        transactionItemsList.setItems(transactionsList);
    }
}
