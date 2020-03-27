package seedu.address.ui;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Transaction;

/**
 * Ui panel containing the statistics of the Wallet.
 */
public class WalletStatisticsPanel extends UiPart<Region> {

    private static final String FXML = "WalletStatisticsPanel.fxml";

    private static final String BUDGET_NOT_SET = "No budget set!";
    private static final String UNDER_BUDGET = "You are $%.2f under your budget! Good job!";
    private static final String OVER_BUDGET = "You are $%.2f over your budget! Oh no!";

    private static final String OVER_BUDGET_CLASS = "budget-over";
    private static final String UNDER_BUDGET_CLASS = "budget-under";

    private final Logger logger = LogsCenter.getLogger(WalletStatisticsPanel.class);

    @FXML
    private PieChart expenditurePieChart;

    @FXML
    private Label currentMonthYearLabel;

    @FXML
    private Label budgetRemainingLabel;

    @FXML
    private Label budgetOverUnderLabel;

    @FXML
    private VBox walletStatisticsLayout;

    @FXML
    private VBox walletStatisticsPlaceholder;

    private Budget budget;
    private ObservableList<Transaction> walletTransactionList;

    public WalletStatisticsPanel(Budget budget, ObservableList<Transaction> transactionList) {
        super(FXML);
        this.budget = budget;
        update(budget, transactionList);
        setProperties();
    }

    /**
     * Updates the statistics displayed with the modified {@code wallet} and {@code transactionList}.
     */
    public void update(Budget budget, ObservableList<Transaction> transactionList) {
        this.budget = budget;
        this.walletTransactionList = transactionList;

        if (transactionList.isEmpty()) {
            walletStatisticsLayout.setVisible(false);
            walletStatisticsPlaceholder.setVisible(true);
        } else {
            resetStyles();
            setCurrentMonthYear();
            populatePieChart();
            updateBudgetRemaining();
            walletStatisticsLayout.setVisible(true);
            walletStatisticsPlaceholder.setVisible(false);
        }

    }

    /**
     * Updates the current month / year label to the current date.
     */
    private void setCurrentMonthYear() {
        Date currDate = Date.getDefault();
        currentMonthYearLabel.setText(String.format("%s %s", currDate.getMonth(), currDate.getYear()));
    }

    /**
     * Populates the pie chart with the data provided.
     */
    private void populatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        Date currDate = Date.getDefault();
        List<Tag> tagList = new ArrayList<Tag>();
        List<Double> totalAmountList = new ArrayList<Double>();

        for (Transaction t : walletTransactionList) {
            if (!tagList.contains(t.getTag())
                    && t instanceof Expense
                    && t.getDate().getMonth().equals(currDate.getMonth())
                    && t.getDate().getYear().equals(currDate.getYear())) {
                tagList.add(t.getTag());
            }
        }

        for (Tag tag : tagList) {
            FilteredList<Transaction> tempTransactionsOfTagList =
                    walletTransactionList.filtered(t -> t instanceof Expense
                            && t.getTag().equals(tag)
                            && t.getDate().getMonth().equals(currDate.getMonth())
                            && t.getDate().getYear().equals(currDate.getYear()));

            double totalAmount = tempTransactionsOfTagList.stream().mapToDouble(t -> t.getAmount().amount).sum();
            totalAmountList.add(totalAmount);
            String tagString = tag.toString().replaceAll("\\p{P}", "");

            PieChart.Data tempData = new PieChart.Data(tagString, totalAmount);

            expenditurePieChart.layout();
            pieChartData.add(tempData);
        }

        pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),
                String.format(" $%.2f", data.getPieValue()))));
        expenditurePieChart.setData(pieChartData);

    }

    /**
     * Updates the budget remaining after deducting the month's total expenditure.
     */
    private void updateBudgetRemaining() {
        Date currDate = Date.getDefault();
        Month currMonth = currDate.getMonth();
        Year currYear = currDate.getYear();

        System.out.println(budget);

        if (budget.getAmount().amount == 0) {
            budgetRemainingLabel.setText(BUDGET_NOT_SET);
            budgetOverUnderLabel.setVisible(false);
        } else {
            double totalExpenditure =
                    walletTransactionList
                            .stream()
                            .filter(t -> t instanceof Expense
                                    && t.getDate().getMonth().equals(currMonth)
                                    && t.getDate().getYear().equals(currYear))
                            .mapToDouble(t -> t.getAmount().amount)
                            .sum();
            double currBudgetAmount = budget.getAmount().amount;

            budgetRemainingLabel.setText(String.format("$%.2f / $%.2f", totalExpenditure, currBudgetAmount));

            if (currBudgetAmount > totalExpenditure) {
                budgetOverUnderLabel.setText(String.format(UNDER_BUDGET, currBudgetAmount - totalExpenditure));
                budgetRemainingLabel.getStyleClass().add(UNDER_BUDGET_CLASS);
            } else {
                budgetOverUnderLabel.setText(String.format(OVER_BUDGET, totalExpenditure - currBudgetAmount));
                budgetRemainingLabel.getStyleClass().add(OVER_BUDGET_CLASS);
            }

            budgetOverUnderLabel.setVisible(true);
        }
    }

    private void resetStyles() {
        ObservableList<String> styleClass = budgetRemainingLabel.getStyleClass();
        styleClass.remove(OVER_BUDGET_CLASS);
        styleClass.remove(UNDER_BUDGET_CLASS);
    }

    private void setProperties() {
        walletStatisticsPlaceholder.managedProperty().bind(walletStatisticsPlaceholder.visibleProperty());
        walletStatisticsLayout.managedProperty().bind(walletStatisticsLayout.visibleProperty());
    }
}
