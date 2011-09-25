package de.jotschi.geo.osm;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import de.jotschi.geo.osm.tags.OsmNd;
import de.jotschi.geo.osm.tags.OsmNode;
import de.jotschi.geo.osm.tags.OsmWay;
import de.jotschi.geoconvert.GeoConvert;

/**
 * This example will convert the lon/lat data that has been retrieved by the openstreetmap api into UTM data and display them.
 * The displayed area is the http://www.mqw.at/ in vienna.
 * @author jotschi
 *
 */
@SuppressWarnings("serial")
public class OSMGuiExample extends Frame {

	OSM osm;
	Map<Long, OsmNode> nodes;
	int width = 400;
	int height = (int) (width * 1.2809295736078916);

	//double[] xy1 = { 48.201566, 16.353718 };
	//double[] xy2 = { 48.205227, 16.363395 };

	double[] xy1 = {48.20279,16.3571 };
	double[] xy2 = {48.20385,16.35846};
	
	public OSMGuiExample() throws Exception {
		System.out.println("OSM Init");
		osm = new OSM(xy1[1], xy1[0], xy2[1], xy2[0]);

		System.out.println("OSM Init DONE");
		nodes = osm.getNodes();

		System.out.println("Nodes: " + nodes.size());
		setSize(height, width);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		
		ArrayList<OsmWay> ways = osm.getWays();

		for (OsmWay way : ways) {

//			if(way.getId()!=8095326)
//			{
//				continue;
//			}
			/* Show only closed ways (areas) */
			if (way.isClosed()) {

				//Properties tags = way.getTags();
				/* Only show buildings */
//				if (tags.getProperty("building") != null
//						&& (tags.getProperty("building")
//								.equalsIgnoreCase("yes") || tags.getProperty(
//								"building").equalsIgnoreCase("true"))) {
					ArrayList<OsmNd> nds = way.getNds();

					System.out.println("\nWayId: " + way.getId());
					System.out.println("Closed: " + way.isClosed());

//					Iterator it = tags.keySet().iterator();
//					while (it.hasNext()) {
//						String key = (String) it.next();
//						System.out.println(" - Tag: " + key + "="
//								+ tags.getProperty(key));
//					}

					System.out.println("Nodes:" + nds.size());
					System.out.println(nds.size());
					int i = 0;
					while (i < nds.size()) {

						OsmNd ndA = nds.get(i);

						int b = i + 1;
						if (b >= nds.size()) {
							b = 0;
						}
						OsmNd ndB = nds.get(b);

						OsmNode refNodeA = nodes.get(ndA.getRef());
						OsmNode refNodeB = nodes.get(ndB.getRef());
						
						try {
							double[] utmPoint1 = GeoConvert.toUtm(refNodeA
									.getLat(), refNodeA.getLon());

							double[] utmPoint2 = GeoConvert.toUtm(refNodeB
									.getLat(), refNodeB.getLon());

							System.out.println("\n - Ref NodID: "
									+ refNodeA.getId() + " -  Lat: "
									+ refNodeA.getLat() + " -  Long: "
									+ refNodeA.getLon() + " -  X:"
									+ utmPoint1[0] + " Y: " + utmPoint1[1]);
							

							System.out.println(" - Ref NodID: "
									+ refNodeB.getId() + " -  Lat: "
									+ refNodeB.getLat() + " -  Long: "
									+ refNodeB.getLon() + " -  X:"
									+ utmPoint2[0] + " Y: " + utmPoint2[1]);
				

							int[] xyA = GeoConvert.getPos(refNodeA.getLon(), refNodeA
									.getLat(),xy1,xy2,height,width);
							int[] xyB = GeoConvert.getPos(refNodeB.getLon(), refNodeB
									.getLat(),xy1,xy2,height,width);
							g.drawLine(xyA[0], xyA[1], xyB[0], xyB[1]);

						} catch (Exception e) {
							System.out.println("Error while parsing lon lat");
						}
						i++;
					}

				}

			}
		}

//	}



	public static void main(String[] args) throws Exception {
		new OSMGuiExample().setVisible(true);
	}
}