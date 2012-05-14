package org.purl.wf4ever.astrotaverna.tjoin.ui.serviceprovider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.servicedescriptions.ServiceDescriptionProvider;

public class StiltsServiceProvider implements ServiceDescriptionProvider {
	
	//OJO!!!!!!!!!!!!!!!!!!!!
	//write down a real URI
	private static final URI providerId = URI
		.create("http://www.iaa.es/service-provider/tjoin");
	
	/**
	 * Do the actual search for services. Return using the callBack parameter.
	 */
	@SuppressWarnings("unchecked")
	public void findServiceDescriptionsAsync(
			FindServiceDescriptionsCallBack callBack) {
		// Use callback.status() for long-running searches
		// callBack.status("Resolving example services");

		List<ServiceDescription> results = new ArrayList<ServiceDescription>();

		// FIXME: Implement the actual service search/lookup instead
		// of dummy for-loop
		//for (int i = 1; i <= 5; i++) {
		//	StiltsServiceDesc service = new StiltsServiceDesc();
		//	// Populate the service description bean
		//	service.setExampleString("Example " + i);
		//	service.setExampleUri(URI.create("http://localhost:8192/service"));

		//	// Optional: set description
		//	service.setDescription("Service example number " + i);
		//	results.add(service);
		//}
		
		StiltsServiceDesc service = new StiltsServiceDesc();
		service.setCmd("");
		service.setInputFormat("votable");
		service.setNumberOfTables(2);
		service.setDescription("tjoin between tables");
		
		results.add(service);
		
		//ServiceDescription
		SelectColumnsServiceDesc service2 = new SelectColumnsServiceDesc();
		service2.setTypeOfInput("String");
		service2.setTypeOfFilter("Column names");
		service2.setDescription("Columns selection in a table");
		
		results.add(service2);
		
		SelectRowsServiceDesc service3 = new SelectRowsServiceDesc();
		service3.setTypeOfInput("String");
		//service3.setTypeOfFilter("Column names");
		service3.setDescription("Rows selection in a table");
		
		results.add(service3);
		
		//Put here additional descriptions for other services
		//............
		//............
		//............
		//............
		

		// partialResults() can also be called several times from inside
		// for-loop if the full search takes a long time
		callBack.partialResults(results);

		// No more results will be coming
		callBack.finished();
	}

	/**
	 * Icon for service provider
	 */
	public Icon getIcon() {
		return StiltsServiceIcon.getIcon();
	}

	/**
	 * Name of service provider, appears in right click for 'Remove service
	 * provider'
	 */
	public String getName() {
		return "My stilts local services";
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getId() {
		return providerId.toASCIIString();
	}

}
