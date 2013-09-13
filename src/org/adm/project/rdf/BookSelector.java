package org.adm.project.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;

public class BookSelector extends SimpleSelector {

	public BookSelector(Resource res, Property prop, RDFNode node) {
		super(res, prop, node);
	}

	@Override
	public boolean selects(Statement s) {
		Resource subj = s.getSubject();
		if (subj.toString().startsWith("urn:x-domain:oreilly.com:product:")
				&& super.selects(s)) {
			return true;
		}
		return false;
	}
}