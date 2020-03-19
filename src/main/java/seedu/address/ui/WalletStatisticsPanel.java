package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;


public class WalletStatisticsPanel extends UiPart<Region> {

    private static final String FXML = "WalletStatisticsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WalletStatisticsPanel.class);

    @FXML
    private PieChart expenditurePieChart;

    private ObservableList<Transaction> walletTransactionList;

    private ObservableList<PieChart.Data> pieChartData;

    public WalletStatisticsPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        update(transactionList);
    }

    public void update(ObservableList<Transaction> transactionList) {
        this.walletTransactionList = transactionList;

        populatePieChart();
    }

    public void populatePieChart() {
        pieChartData = FXCollections.observableArrayList();
        List<Tag> tagList = new ArrayList<Tag>();

        for (Transaction t : walletTransactionList) {
           if (!tagList.contains(t.getTag())) {
               tagList.add(t.getTag());
           }
        }

        for (Tag tag : tagList) {
            FilteredList<Transaction> tempList = walletTransactionList.filtered(t -> t.getTag().equals(tag));
            double totalAmount = tempList.stream().mapToDouble(t -> t.getAmount().amount).sum();
            PieChart.Data tempData = new PieChart.Data(tag.toString(), totalAmount);
            pieChartData.add(tempData);
        }

        expenditurePieChart.setData(pieChartData);
    }
}
