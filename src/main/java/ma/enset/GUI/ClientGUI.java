package ma.enset.GUI;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.agents.ClientAgent;
import ma.enset.entities.Computer;
import ma.enset.entities.Product;
import ma.enset.entities.Usb;

import java.util.List;

public class ClientGUI extends Application {
    private ClientAgent clientAgent;
    private ObservableList<String> data= FXCollections.observableArrayList();
    private ObservableList<Product> computers= FXCollections.observableArrayList();
    private ObservableList<Product> usb= FXCollections.observableArrayList();


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        startCotainer();
        BorderPane root=new BorderPane();
        TextField searchField=new TextField();
        Button searchBtn=new Button("Search");
        HBox hBox=new HBox(searchField,searchBtn);
        ListView computerListView=new ListView<Computer>();
        computerListView.setItems(computers);
        ListView usbListView=new ListView<Usb>();
        usbListView.setItems(usb);
        HBox hboxData=new HBox(computerListView,usbListView);
        hboxData.setSpacing(10);

        root.setTop(hBox);
        root.setCenter(hboxData);
        Scene scene=new Scene(root,700,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        searchBtn.setOnAction(event->{
            String name=searchField.getText();
            GuiEvent guiEvent=new GuiEvent(name,1);
            guiEvent.addParameter(name);
            clientAgent.onGuiEvent(guiEvent);
        });

    }
    private void startCotainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agentClient1=container.createNewAgent("buyer","ma.enset.agents.ClientAgent",new Object[]{this});
        agentClient1.start();
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }

    public void showData(Product pr) {
        Platform.runLater(()-> {
            if (pr.getClass().equals(Computer.class)) {
                computers.add(pr);
            } else if (pr.getClass().equals(Usb.class)) {
                usb.add(pr);
            }
        });
    }
}
