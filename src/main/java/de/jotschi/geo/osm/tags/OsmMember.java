package de.jotschi.geo.osm.tags;

import org.w3c.dom.Node;

public class OsmMember {
	String type, ref, role;

	public OsmMember(Node node) {

		ref = node.getAttributes().getNamedItem("ref").getNodeValue();
		role = node.getAttributes().getNamedItem("role").getNodeValue();
		type = node.getAttributes().getNamedItem("type").getNodeValue();
		
	}

	public String getType() {
		return type;
	}

	public String getRef() {
		return ref;
	}

	public String getRole() {
		return role;
	}
	
	
}
