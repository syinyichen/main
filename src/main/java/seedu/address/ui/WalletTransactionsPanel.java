package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of transactions.
 */
public class WalletTransactionsPanel extends UiPart<Region> {
    private static final int LIST_CELL_HEIGHT = 160;

    private static final String FXML = "WalletTransactionsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WalletTransactionsPanel.class);

    @FXML
    private Label transactionCountLabel;

    @FXML
    private ListView<FilteredList<Transaction>> transactionGroupsListView;

    private ObservableList<Transaction> walletTransactionList;

    private SortedList<Transaction> sortedTransactionList;

    private ObservableList<FilteredList<Transaction>> filteredGroupedList = FXCollections.observableArrayList();

    public WalletTransactionsPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        this.walletTransactionList = transactionList;

        update(transactionList);
    }

    public void update(ObservableList<Transaction> newTransactionList) {
        this.walletTransactionList = newTransactionList;

        this.sortedTransactionList = walletTransactionList.sorted(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return -o1.getDate().compareTo(o2.getDate());
            }
        });

        for (Transaction t : sortedTransactionList) {
            System.out.println(t);
        }

        populateGroupedList();
        updateTransactionCount();
    }

    private void updateTransactionCount() {
        transactionCountLabel.setText(String.format("%d transactions", sortedTransactionList.size()));
    }

    /**
     * Filters the items in the transaction list into {@code FilteredList<Transaction>}, and populates the given
     * {@code ObservableList}.
     */
    private void populateGroupedList() {
        if (sortedTransactionList.isEmpty()) {
            filteredGroupedList = FXCollections.observableArrayList();
            transactionGroupsListView.setItems(filteredGroupedList);
            return;
        }

        filteredGroupedList = FXCollections.observableArrayList();

        int i = 0;
        while (i < sortedTransactionList.size()) {
            Date currDate = sortedTransactionList.get(i).getDate();
            FilteredList<Transaction> tempList = sortedTransactionList.filtered(t -> t.getDate().equals(currDate));
            filteredGroupedList.add(tempList);

            i += tempList.size();
        }

        transactionGroupsListView.setItems(filteredGroupedList);
        transactionGroupsListView.setCellFactory(listView -> new TransactionGroupCell());
    }

    /**
     * Custom {@code ListCell} that displays a group of {@code Transaction} using {@code TransactionCard}.
     */
    class TransactionGroupCell extends ListCell<FilteredList<Transaction>> {
        @Override
        protected void updateItem(FilteredList<Transaction> groupItems, boolean empty) {
            super.updateItem(groupItems, empty);

            if (empty || groupItems == null) {
                setGraphic(null);
                setText(null);
            } else {
                int startIndex = sortedTransactionList.indexOf(groupItems.get(0));
                setGraphic(new TransactionGroupCard(groupItems, startIndex).getRoot());
            }
        }
    }

}
