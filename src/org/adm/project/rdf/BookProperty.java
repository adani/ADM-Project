package org.adm.project.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;

public class BookProperty {
	
	private static final String DC_NS = "http://purl.org/dc/terms/";
	private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";
	private static final String OM_NS = "http://purl.oreilly.com/ns/meta/";
	
	private final Property title;
	private final Property foafName;
	private final Property price;
	private final Property description;
	private final Property cover;
	private final Property creator;

	public BookProperty(Model rdfModel) {
		title = rdfModel.getProperty(DC_NS + "title");
		foafName = rdfModel.getProperty(FOAF_NS + "name"); //inside foaf:Person
		price = rdfModel.getProperty(OM_NS + "price");
		description = rdfModel.getProperty(DC_NS + "description");
		cover = rdfModel.getProperty(OM_NS + "cover");
		creator = rdfModel.getProperty(DC_NS + "creator");
	}

	public Property getTitle() {
		return title;
	}

	public Property getFoafName() {
		return foafName;
	}

	public Property getPrice() {
		return price;
	}

	public Property getDescription() {
		return description;
	}

	public Property getCover() {
		return cover;
	}
	
	public Property getCreator() {
		return creator;
	}
}
