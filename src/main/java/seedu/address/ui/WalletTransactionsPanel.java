package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;

/**
 * Panel containing the list of transactions.
 */
public class WalletTransactionsPanel extends UiPart<Region> {
    private static final String FXML = "WalletTransactionsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WalletTransactionsPanel.class);

    @FXML
    private ListView<FilteredList<Transaction>> transactionGroupsListView;

    public WalletTransactionsPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        List<Date> dateList = new ArrayList<Date>();
        ObservableList<FilteredList<Transaction>> filteredTransactionsList = FXCollections.observableArrayList();

        for(Transaction t : transactionList) {
            if(!dateList.contains(t.getDate())) {
                dateList.add(t.getDate());
            }
        }

        for(Date d : dateList) {
            FilteredList<Transaction> tempList = transactionList.filtered(t -> t.getDate().equals(d));
            filteredTransactionsList.add(tempList);
        }

        transactionGroupsListView.setItems(filteredTransactionsList);
        transactionGroupsListView.setCellFactory(listView -> new TransactionGroupCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Transaction} using a {@code TransactionCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(transaction, getIndex() + 1).getRoot());
            }
        }
    }

    class TransactionGroupCell extends ListCell<FilteredList<Transaction>> {
        @Override
        protected void updateItem(FilteredList<Transaction> groupItems, boolean empty) {
            super.updateItem(groupItems, empty);

            if(empty || groupItems == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionGroupCard(groupItems).getRoot());
            }
        }
    }

}
