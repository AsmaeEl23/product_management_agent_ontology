package ma.enset.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ma.enset.CatalogOntology;
import ma.enset.Disponible;
import ma.enset.GUI.ClientGUI;
import ma.enset.entities.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientAgent extends GuiAgent {
    private ClientGUI clientGUI;
    private Ontology catalogOntology= CatalogOntology.getCatalogOntology();
    private Codec codec=new SLCodec();
    private List<Product> products=new ArrayList<>();
    @Override
    protected void setup() {
        clientGUI=(ClientGUI)getArguments()[0];
        clientGUI.setClientAgent(this);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                getContentManager().registerOntology(catalogOntology);
                getContentManager().registerLanguage(codec);
                MessageTemplate messageTemplate=MessageTemplate.and
                        (
                                MessageTemplate.MatchOntology(CatalogOntology.ONTOLOGY_NAME),
                                MessageTemplate.MatchLanguage(codec.getName()));

                ACLMessage receivedMessage = blockingReceive(messageTemplate);
                try {
                    Disponible disponible = (Disponible)getContentManager().extractContent(receivedMessage);
                    System.out.println(disponible.getProduct().getName()+disponible.getProduct().getPrice());

                    if(disponible!=null){
                        products.add(disponible.getProduct());
                        clientGUI.showData(disponible.getProduct());
                    }else {
                        block();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        Iterator<String> it=guiEvent.getAllParameter();
        String name=it.next();
        List<Product> prs=new ArrayList<>();
        for (Product pr:products) {
            if(pr.getName().contentEquals(name)){
                prs.add(pr);
            }
        }
    }
}
