package de.jotschi.geo.osm;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.jotschi.geo.osm.tags.OsmNode;
import de.jotschi.geo.osm.tags.OsmRelation;
import de.jotschi.geo.osm.tags.OsmWay;


/**
 * Simple small osm api 0.6 read only implementation:
 * @see http://wiki.openstreetmap.org/wiki/Data_Primitives
 * @author jotschi
 *
 */
public class OSM {

	protected Document apiXMLDoc;

	private Map<Long, OsmNode> osmNodes = new HashMap<Long, OsmNode>();
	private ArrayList<OsmWay> osmWays = new ArrayList<OsmWay>();
	private ArrayList<OsmRelation> osmRelations = new ArrayList<OsmRelation>();

	public OSM(URL url) throws Exception {

		InputStream in = getXMLSource(url);
		initParser(in);
	}

	public OSM(double startLong, double startLat, double stopLong,
			double stopLat) throws Exception {

		URL url = new URL("http://www.openstreetmap.org/api/0.6/map?bbox="
				+ startLong + "," + startLat + "," + stopLong + "," + stopLat);
		System.out.println("Url: " + url);
		InputStream in = getXMLSource(url);
		initParser(in);

	}

	private InputStream getXMLSource(URL url) throws IOException {

		URLConnection con = url.openConnection();
		con.connect();
		return con.getInputStream();
	}

	public void initParser(InputStream source)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		apiXMLDoc = db.parse(source);

		NodeList ndNodeList = apiXMLDoc.getElementsByTagName("node");
		NodeList ndWayList = apiXMLDoc.getElementsByTagName("way");
		NodeList ndRelationList = apiXMLDoc.getElementsByTagName("relation");

		getNodes(ndNodeList);
		getWays(ndWayList);
		getRelations(ndRelationList);

	}

	private void getNodes(NodeList list) {
		int i = 0;
		while (i < list.getLength()) {
			OsmNode node = new OsmNode(list.item(i));
			osmNodes.put(new Long(node.getId()), node);
			i++;
		}
	}

	private void getWays(NodeList list) {
		int i = 0;
		while (i < list.getLength()) {
			osmWays.add(new OsmWay(list.item(i)));
			i++;
		}
	}

	private void getRelations(NodeList list) {
		int i = 0;
		while (i < list.getLength()) {
			osmRelations.add(new OsmRelation(list.item(i)));
			i++;
		}
	}

	public Map<Long, OsmNode> getNodes() {
		return this.osmNodes;

	}

	public ArrayList<OsmRelation> getRelations() {
		return this.osmRelations;
	}

	public ArrayList<OsmWay> getWays() {
		return this.osmWays;

	}
}
