package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class TabDisplay extends UiPart<Region> {
    private static final String FXML = "TabDisplay.fxml";

    @FXML
    private Tab peopleTab;

    @FXML
    private Tab walletTab;

    @FXML
    private TabPane sharkieTabPane;


    public TabDisplay() {
        super(FXML);

        //sharkieTabPane = new TabPane();
//
//        peopleTab = new Tab("Planes", new Label("Show all planes available"));
//        walletTab = new Tab("Cars"  , new Label("Show all cars available"));
////
//        sharkieTabPane.getTabs().add(peopleTab);
//        sharkieTabPane.getTabs().add(walletTab);

//        TabPane tabPane = new TabPane();
//        Tab tab = new Tab();
//        tab.setText("new tab");
//        tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
//        tabPane.getTabs().add(tab);
    }
}
