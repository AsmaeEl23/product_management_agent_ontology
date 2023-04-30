package ma.enset;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.schema.*;
import ma.enset.entities.*;

public class CatalogOntology extends Ontology implements CatalogVocabulary{
    //Le nom de l'ontologie
    public static final String ONTOLOGY_NAME="Catalog-Ontology";
    public static final Ontology CATALOG_ONTOLOGY=new CatalogOntology();

    public static Ontology getCatalogOntology() {
        return CATALOG_ONTOLOGY;
    }

    private CatalogOntology(){
        super(ONTOLOGY_NAME,BasicOntology.getInstance());
        try {
            add(new ConceptSchema(PRODUCT), Product.class);
            add(new ConceptSchema(COMPUTER), Computer.class);
            add(new ConceptSchema(USB),Usb.class);
            add(new AgentActionSchema(SELL),Sell.class);
            add(new PredicateSchema(DISPOSABLE),Disponible.class);

            ConceptSchema cs = (ConceptSchema)getSchema(PRODUCT);

            cs.add(PRODUCT_NAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(PRODUCT_PRICE,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(PRODUCT_DESCRIPTION,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);

            cs = (ConceptSchema) getSchema(COMPUTER);
            cs.addSuperSchema((ConceptSchema) getSchema(PRODUCT));
            cs.add(COMPUTER_RAM,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(COMPUTER_DISK,(PrimitiveSchema)getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(COMPUTER_PROCESSORS,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);

            cs = (ConceptSchema) getSchema(USB);
            cs.addSuperSchema((ConceptSchema) getSchema(PRODUCT));
            cs.add(USB_CAPACITY,(PrimitiveSchema)getSchema(BasicOntology.FLOAT));

            PredicateSchema ds = (PredicateSchema) getSchema(DISPOSABLE);
            ds.add(DISPOSABLE_PRODUCT,getSchema(PRODUCT));
            ds.add(DISPOSABLE_SELLER,getSchema(BasicOntology.AID));

            AgentActionSchema ss = (AgentActionSchema)getSchema(SELL);
            ss.add(SELL_PRODUCT,(ConceptSchema)getSchema(PRODUCT));
            ss.add(SELL_BUYER,(ConceptSchema)getSchema(BasicOntology.AID));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
