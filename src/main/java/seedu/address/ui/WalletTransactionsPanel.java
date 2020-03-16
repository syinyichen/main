package seedu.address.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

/**
 * Panel containing the list of transactions.
 */
public class WalletTransactionsPanel extends UiPart<Region> {
    private static final int LIST_CELL_HEIGHT = 160;

    private static final String FXML = "WalletTransactionsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WalletTransactionsPanel.class);

    @FXML
    private ListView<FilteredList<Transaction>> transactionGroupsListView;

    private final ObservableList<Transaction> transactionList;

    public WalletTransactionsPanel(ObservableList<Transaction> transactionList) {
        super(FXML);
        this.transactionList = transactionList;

        ObservableList<FilteredList<Transaction>> filteredGroupedList = FXCollections.observableArrayList();
        populateGroupedList(filteredGroupedList);

        transactionGroupsListView.setItems(filteredGroupedList);
        transactionGroupsListView.setCellFactory(listView -> new TransactionGroupCell());
    }

    /**
     * Filters the items in the transaction list into {@code FilteredList<Transaction>}, and populates the given
     * {@code ObservableList}.
     */
    private void populateGroupedList(ObservableList<FilteredList<Transaction>> filteredGroupedList) {

        List<Date> dateList = new ArrayList<Date>();
        for (Transaction t : transactionList) {
            if (!dateList.contains(t.getDate())) {
                dateList.add(t.getDate());
            }
        }
        for (Date d : dateList) {
            FilteredList<Transaction> tempList = transactionList.filtered(t -> t.getDate().equals(d));
            filteredGroupedList.add(tempList);
        }
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
                int startIndex = transactionList.indexOf(groupItems.get(0));
                setGraphic(new TransactionGroupCard(transactionList, groupItems, startIndex).getRoot());
            }
        }
    }

}
