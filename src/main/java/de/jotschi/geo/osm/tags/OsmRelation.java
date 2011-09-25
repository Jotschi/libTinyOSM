package de.jotschi.geo.osm.tags;

import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OsmRelation extends OsmObject {

	Vector<OsmMember> members = new Vector<OsmMember>();

	public OsmRelation(Node node) {
		super(node);

		addTags();
		addMembers();
	}

	private void addMembers() {
		NodeList ndList = node.getChildNodes();

		int i = 0;
		while (i < ndList.getLength()) {
			if (ndList.item(i).getNodeName().equalsIgnoreCase("member")) {
				members.add(new OsmMember(ndList.item(i)));
			}

			i++;
		}

	}

	public Vector<OsmMember> getMembers() {
		return this.members;
	}

}
