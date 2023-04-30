package ma.enset.GUI;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.Disponible;
import ma.enset.agents.ServerAgent;
import ma.enset.entities.Computer;
import ma.enset.entities.Product;
import ma.enset.entities.Usb;

public class ServerGUI extends Application {
    private ServerAgent serverAgent;
    private ObservableList<Product> products= FXCollections.observableArrayList();
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private void startContainer() throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent("saller","ma.enset.agents.ServerAgent",new Object[]{this});
        agent.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        BorderPane root=new BorderPane();

        Label usbTitle =new Label("USB");        Label cmpTitle =new Label("COMPUTER");

        //---------------------------------------------
        GridPane usbGrp=new GridPane();         GridPane cmpGrp=new GridPane();
        TextField usbName=new TextField();
        usbGrp.add(new Label("Name"),0,0);
        usbGrp.add(usbName,1,0);
        TextField usbPrice=new TextField();
        usbGrp.add(new Label("Price"),0,1);
        usbGrp.add(usbPrice,1,1);
        TextField usbCapacity=new TextField();
        usbGrp.add(new Label("Capacity"),0,2);
        usbGrp.add(usbCapacity,1,2);
        TextField usbDescr=new TextField();
        usbGrp.add(new Label("Description"),0,3);
        usbGrp.add(usbDescr,1,3);

        //---------------------------------------------
        TextField cmpName=new TextField();
        cmpGrp.add(new Label("Name"),0,0);
        cmpGrp.add(cmpName,1,0);
        TextField cmpPrice=new TextField();
        cmpGrp.add(new Label("Price"),0,1);
        cmpGrp.add(cmpPrice,1,1);
        TextField cmpRam=new TextField();
        cmpGrp.add(new Label("RAM"),0,2);
        cmpGrp.add(cmpRam,1,2);
        TextField cmpDisk=new TextField();
        cmpGrp.add(new Label("DISK"),0,3);
        cmpGrp.add(cmpDisk,1,3);
        TextField cmpPro=new TextField();
        cmpGrp.add(new Label("Processor"),0,4);
        cmpGrp.add(cmpPro,1,4);
        TextField cmpDescr=new TextField();
        cmpGrp.add(new Label("Description"),0,5);
        cmpGrp.add(cmpDescr,1,5);

        Button sendComputer=new Button("SEND");
        Button sendUsb=new Button("SEND");
        VBox vBox1=new VBox(usbTitle,usbGrp,sendUsb);
        VBox vBox2=new VBox(cmpTitle,cmpGrp,sendComputer);
        HBox hBox1=new HBox(vBox1,vBox2);
        vBox1.setSpacing(10);
        vBox2.setSpacing(10);
        hBox1.setSpacing(20);
        ListView<Product> listView=new ListView<>(products);
        root.setCenter(hBox1);
        root.setBottom(listView);
        Scene scene=new Scene(root,600,700);
        primaryStage.setScene(scene);
        primaryStage.show();

        sendComputer.setOnAction((event)->{
            Product computer=new Computer(cmpName.getText(),cmpDescr.getText(),Float.parseFloat(cmpPrice.getText()),
                    Float.parseFloat(cmpRam.getText()),Float.parseFloat(cmpDisk.getText()),Integer.parseInt(cmpPro.getText()));
            Disponible disponible=new Disponible();
            disponible.setProduct(computer);
            GuiEvent guiEvent=new GuiEvent(this,1);
            guiEvent.addParameter(disponible);
            serverAgent.onGuiEvent(guiEvent);
            products.add(computer);
        });
        sendUsb.setOnAction((event)->{
            Product usb=new Usb(usbName.getText(),usbDescr.getText(),Float.parseFloat(usbPrice.getText()),Float.parseFloat(usbCapacity.getText()));
            Disponible disponible=new Disponible();
            disponible.setProduct(usb);
            GuiEvent guiEvent=new GuiEvent(disponible,1);
            guiEvent.addParameter(disponible);
            serverAgent.onGuiEvent(guiEvent);
            products.add(usb);
        });
    }

    public void setVendeurAgent(ServerAgent serverAgent){
        this.serverAgent=serverAgent;
    }

}
