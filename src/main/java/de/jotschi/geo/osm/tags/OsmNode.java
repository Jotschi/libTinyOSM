package de.jotschi.geo.osm.tags;

import org.w3c.dom.Node;

public class OsmNode extends OsmObject {

	double lat, lon;

	public OsmNode(Node node) {
		super(node);

		if (node.getAttributes().getNamedItem("lat") != null) {
			lat = Double.parseDouble(node.getAttributes().getNamedItem("lat")
					.getNodeValue());
		}

		if (node.getAttributes().getNamedItem("lon") != null) {
			lon = Double.parseDouble(node.getAttributes().getNamedItem("lon")
					.getNodeValue());
		}

		addTags();
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

}
