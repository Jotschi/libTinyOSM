package de.jotschi.geo.osm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import de.jotschi.geo.osm.tags.OsmNd;
import de.jotschi.geo.osm.tags.OsmNode;
import de.jotschi.geo.osm.tags.OsmWay;

public class OSMTest {

	public static void main(String[] args) throws Exception {

		// URL url = new
		// URL("http://www.openstreetmap.org/api/0.6/map?bbox=16.353718,48.201566,16.363395,48.205227");
		// OSM osm = new OSM(url);
		OSM osm = new OSM(16.353718, 48.201566, 16.363395, 48.205227);

		Map<Long, OsmNode> nodes = osm.getNodes();

		/*
		 * // Show all nodes for (OsmNode node : nodes.values()) {
		 * System.out.println("\nNodeID: " + node.getId());
		 * 
		 * Properties tags = node.getTags();
		 * 
		 * Iterator it = tags.keySet().iterator(); while (it.hasNext()) { String
		 * key = (String) it.next(); System.out.println(" - Tag: " + key + "=" +
		 * tags.getProperty(key)); } }
		 */

		// Show all ways
		ArrayList<OsmWay> ways = osm.getWays();
		for (OsmWay way : ways) {

			/* Show only closed ways (areas) */
			if (way.isClosed()) {

				Properties tags = way.getTags();

				/* Only show buildings */
				if (tags.getProperty("building") != null
						&& (tags.getProperty("building")
								.equalsIgnoreCase("yes") || tags.getProperty(
								"building").equalsIgnoreCase("true"))) {
					ArrayList<OsmNd> nds = way.getNds();

					System.out.println("\nWayId: " + way.getId());
					System.out.println("Closed: " + way.isClosed());

					Iterator it = tags.keySet().iterator();
					while (it.hasNext()) {
						String key = (String) it.next();
						System.out.println(" - Tag: " + key + "="
								+ tags.getProperty(key));
					}

					for (OsmNd nd : nds) {
						OsmNode refNode = nodes.get(nd.getRef());
						System.out.println(" - Ref NodID: " + refNode.getId()
								+ " -  Lat: " + refNode.getLat() + " -  Long: "
								+ refNode.getLon());
					}

				}

			}
		}

		/*
		 * Show all relations ArrayList<OsmRelation> relations =
		 * osm.getRelations(); for (OsmRelation relation : relations) {
		 * System.out.println("\nRelation: " + relation.getId());
		 * Vector<OsmMember> members = relation.getMembers(); Vector<OsmTag>
		 * tags = relation.getTags();
		 * 
		 * for (OsmTag tag : tags) { System.out.println(" - Tag: " +
		 * tag.getKey() + "=" + tag.getValue()); }
		 * 
		 * for (OsmMember member : members) {
		 * System.out.println(" - MemberRef: " + member.getRef()); }
		 * 
		 * }
		 */

	}
}
