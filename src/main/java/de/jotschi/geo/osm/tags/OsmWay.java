package de.jotschi.geo.osm.tags;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OsmWay extends OsmObject {

	private ArrayList<OsmNd> nds = new ArrayList<OsmNd>();

	private boolean closed = false;

	public OsmWay(Node node) {
		super(node);

		addTags();
		addNds();

		checkIfWayIsClosed();
	}

	private void checkIfWayIsClosed() {
		long firstRef = nds.get(0).getRef();
		long lastRef = nds.get(nds.size()-1).getRef();
		if (firstRef == lastRef) {
			closed = true;
		}
	}

	private void addNds() {
		NodeList ndList = node.getChildNodes();

		int i = 0;
		while (i < ndList.getLength()) {
			if (ndList.item(i).getNodeName().equalsIgnoreCase("nd")) {
				nds.add(new OsmNd(ndList.item(i)));
			}

			i++;
		}

	}

	public ArrayList<OsmNd> getNds() {
		return this.nds;
	}

	public boolean isClosed() {
		return this.closed;
	}
}
