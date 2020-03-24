package seedu.address.ui;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sun.javafx.charts.Legend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyWallet;
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

    private ObservableList<Transaction> walletTransactionList;

    private ReadOnlyWallet wallet;

    public WalletStatisticsPanel(ReadOnlyWallet wallet, ObservableList<Transaction> transactionList) {
        super(FXML);
        update(wallet, transactionList);
    }

    /**
     * Updates the statistics displayed with the modified {@code wallet} and {@code transactionList}.
     */
    public void update(ReadOnlyWallet wallet, ObservableList<Transaction> transactionList) {
        this.wallet = wallet;
        this.walletTransactionList = transactionList;

        resetStyles();
        setCurrentMonthYear();
        populatePieChart();
        updateBudgetRemaining();
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
            if (!tagList.contains(t.getTag())) {
                tagList.add(t.getTag());
            }
        }
        for (Tag tag : tagList) {
            FilteredList<Transaction> tempTagList =
                    walletTransactionList.filtered(t -> t instanceof Expense
                            && t.getTag().equals(tag)
                            && t.getDate().getMonth().equals(currDate.getMonth())
                            && t.getDate().getYear().equals(currDate.getYear()));

            double totalAmount = tempTagList.stream().mapToDouble(t -> t.getAmount().amount).sum();
            totalAmountList.add(totalAmount);
            String tagString = tag.toString().replaceAll("\\p{P}", "");

            PieChart.Data tempData = new PieChart.Data(tagString, totalAmount);

            pieChartData.add(tempData);
        }

        expenditurePieChart.setData(pieChartData);

        Legend legend = (Legend) expenditurePieChart.lookup(".chart-legend");
        for (int i = 0; i < legend.getItems().size(); i++) {
            Legend.LegendItem tempItem = legend.getItems().get(i);
            Tag tag = tagList.get(i);
            double totalAmount = totalAmountList.get(i);

            tempItem.setText(String.format("%s: $%.2f", tag.toString().replaceAll("\\p{P}", ""), totalAmount));
        }
    }

    /**
     * Updates the budget remaining after deducting the month's total expenditure.
     */
    private void updateBudgetRemaining() {
        Date currDate = Date.getDefault();
        Month currMonth = currDate.getMonth();
        Year currYear = currDate.getYear();

        Budget currBudget = wallet.getBudgetList().get(currMonth, currYear);

        if (currBudget.getAmount().amount == 0) {
            budgetRemainingLabel.setText(BUDGET_NOT_SET);
            budgetOverUnderLabel.setVisible(false);
        } else {
            double totalExpenditure =
                    wallet.getExpenseList()
                            .stream()
                            .filter(t -> t.getDate().getMonth().equals(currMonth)
                                    && t.getDate().getYear().equals(currYear))
                            .mapToDouble(t -> t.getAmount().amount)
                            .sum();
            double currBudgetAmount = currBudget.getAmount().amount;

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
}
