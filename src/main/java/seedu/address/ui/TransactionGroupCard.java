package seedu.address.ui;

import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Transaction;

public class TransactionGroupCard extends UiPart<Region> {

    private static final int LIST_CELL_HEIGHT = 32;
    private static final int LIST_OFFSET = 0;

    private static final String FXML = "TransactionGroupCard.fxml";

    private static final String POSITIVE_CLASS = "positive";
    private static final String NEGATIVE_CLASS = "negative";

    @FXML
    private Label transactionGroupLabel;

    @FXML
    private Label groupExpenditureLabel;

    @FXML
    private ListView<Transaction> transactionItemsList;

    public final FilteredList<Transaction> transactionsList;

    public TransactionGroupCard(FilteredList<Transaction> transactionsList) {
        super(FXML);
        this.transactionsList = transactionsList;

        transactionGroupLabel.setText(transactionsList.get(0).getDate().toString());

        setGroupExpenditure();

        transactionItemsList.setItems(transactionsList);
        transactionItemsList.setCellFactory(listView -> new TransactionListViewCell());
        transactionItemsList.setPrefHeight(transactionsList.size() * LIST_CELL_HEIGHT + LIST_OFFSET);
    }

    private void setGroupExpenditure() {
        double groupValue = transactionsList.stream().mapToDouble(t -> {
            if(t instanceof Expense) {
                return -t.getAmount().amount;
            } else {
                return t.getAmount().amount;
            }
        }).sum();

        if (groupValue < 0) {
            groupExpenditureLabel.setText("-$" + String.format("%.2f", -groupValue));
            groupExpenditureLabel.getStyleClass().add(NEGATIVE_CLASS);
        } else {
            groupExpenditureLabel.setText("+$" + String.format("%.2f", groupValue));
            groupExpenditureLabel.getStyleClass().add(POSITIVE_CLASS);
        }
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
}
