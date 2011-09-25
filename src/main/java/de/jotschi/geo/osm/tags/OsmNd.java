package de.jotschi.geo.osm.tags;

import org.w3c.dom.Node;

public class OsmNd {

	long ref;

	public OsmNd(Node node) {
		ref = Long.parseLong(node.getAttributes().getNamedItem("ref")
				.getNodeValue());
	}

	public long getRef() {
		return ref;
	}
}
