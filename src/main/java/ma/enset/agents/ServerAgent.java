package ma.enset.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.application.Application;
import ma.enset.CatalogOntology;
import ma.enset.Disponible;
import ma.enset.GUI.ServerGUI;
import ma.enset.entities.Computer;
import ma.enset.entities.Product;
import ma.enset.entities.Usb;

import java.util.Iterator;
import java.util.Random;

public class ServerAgent extends GuiAgent {
    private ServerGUI serverGUI;
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec codec=new SLCodec();
    private Disponible disponible;
    @Override
    protected void setup() {
        serverGUI=(ServerGUI)getArguments()[0];
        serverGUI.setVendeurAgent(this);
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent){
        Iterator<Disponible> it=guiEvent.getAllParameter();
        while(it.hasNext()){
            this.disponible=it.next();
            System.out.println(disponible);
        }
        getContentManager().registerOntology(catalogOntology);
        getContentManager().registerLanguage(codec);
        System.out.println(disponible+" is it null ou no");
        if(disponible!=null){
            disponible.setSeller(new AID(getLocalName(),AID.ISLOCALNAME));

            ACLMessage message=new ACLMessage(ACLMessage.QUERY_IF);
            message.addReceiver(new AID("buyer",AID.ISLOCALNAME));
            message.setOntology(catalogOntology.getName());
            message.setLanguage(codec.getName());
            try {
                getContentManager().fillContent(message,disponible);
                System.out.println("the message not sent : "+message);
                send(message);
                System.out.println("the message is sent : "+message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
