package de.jotschi.geo.osm.tags;

import java.util.Properties;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OsmObject {

	Properties tags = new Properties();

	String version, changeset, user, uid, visible, timestamp;
	long id;

	Node node;

	public OsmObject(Node node) {
		this.node = node;

		id = Long.parseLong(node.getAttributes().getNamedItem("id")
				.getNodeValue());

		version = node.getAttributes().getNamedItem("version").getNodeValue();
		changeset = node.getAttributes().getNamedItem("changeset")
				.getNodeValue();
		user = node.getAttributes().getNamedItem("user").getNodeValue();
		uid = node.getAttributes().getNamedItem("uid").getNodeValue();
		visible = node.getAttributes().getNamedItem("visible").getNodeValue();
		timestamp = node.getAttributes().getNamedItem("timestamp")
				.getNodeValue();

	}

	public long getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public String getChangeset() {
		return changeset;
	}

	public String getUser() {
		return user;
	}

	public String getUid() {
		return uid;
	}

	public String getVisible() {
		return visible;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Properties getTags() {
		return this.tags;
	}

	protected void addTags() {
		NodeList ndList = node.getChildNodes();

		int i = 0;
		while (i < ndList.getLength()) {
			if (ndList.item(i).getNodeName().equalsIgnoreCase("tag")) {

				String k = ndList.item(i).getAttributes().getNamedItem("k")
						.getNodeValue();
				String v = ndList.item(i).getAttributes().getNamedItem("v")
						.getNodeValue();
				tags.setProperty(k, v);

			}

			i++;
		}
	}

}
