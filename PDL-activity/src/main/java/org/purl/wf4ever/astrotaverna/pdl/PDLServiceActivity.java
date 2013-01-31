package org.purl.wf4ever.astrotaverna.pdl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
//comment from terminal
import org.apache.log4j.Logger;

import CommonsObjects.GeneralParameter;

//import uk.ac.starlink.ttools.Stilts;
import visitors.GeneralParameterVisitor;

import net.ivoa.parameter.model.ConditionalStatement;
import net.ivoa.parameter.model.ConstraintOnGroup;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupProcessor;
import net.ivoa.pdl.interpreter.utilities.UserMapper;
import net.ivoa.pdl.interpreter.utilities.Utilities;
import net.sf.taverna.t2.invocation.InvocationContext;
import net.sf.taverna.t2.reference.ReferenceService;
import net.sf.taverna.t2.reference.T2Reference;
import net.sf.taverna.t2.workflowmodel.processor.activity.AbstractAsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.ActivityConfigurationException;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivity;
import net.sf.taverna.t2.workflowmodel.processor.activity.AsynchronousActivityCallback;

public class PDLServiceActivity extends
		AbstractAsynchronousActivity<PDLServiceActivityConfigurationBean>
		implements AsynchronousActivity<PDLServiceActivityConfigurationBean> {

	/*
	 * Best practice: Keep port names as constants to avoid misspelling. This
	 * would not apply if port names are looked up dynamically from the service
	 * operation, like done for WSDL services.
	 */
	
	private static Logger logger = Logger.getLogger(PDLServiceActivity.class);
	
	//private static final String OUT_SIMPLE_OUTPUT = "outputFileOut";
	private static final String OUT_REPORT = "report";
	private static final String RESPONSE_BODY = "response_body";
	
	private PDLServiceActivityConfigurationBean configBean;
	
	
	private HashMap<String, SingleParameter> hashAllParameters;
	private HashMap<String, String> restrictionsOnGroups;
	private String serviceDescription;
	
	//pdl specific objects
//	final public String complete = "To complete";
//	final public String error = "With error";
//	final public String valid = "Valid";

	@Override
	public void configure(PDLServiceActivityConfigurationBean configBean)
			throws ActivityConfigurationException {

		// Any pre-config sanity checks

		
		//this method controls if the input is valid
		
		//service.getParameters().getParameter();
		
		this.configBean = configBean;

		// OPTIONAL: 
		// Do any server-side lookups and configuration, like resolving WSDLs

		// myClient = new MyClient(configBean.getExampleUri());
		// this.service = myClient.getService(configBean.getExampleString());

		
		// REQUIRED: (Re)create input/output ports depending on configuration
		configurePorts();
	}

	protected void configurePorts() throws ActivityConfigurationException {
		//GroupProcessor gp;
//		Service service;
		//ArrayList<List<SingleParameter>> paramsLists;
		//HashMap<String, Integer> dimensions;
		
		PDLServiceController pdlcontroller;
		
		removeInputs();
		removeOutputs();
		
		try{
			pdlcontroller = new PDLServiceController (this.configBean);
			pdlcontroller.prepareHashParametersInputs();
			hashAllParameters = pdlcontroller.getHashAllParameters();
			pdlcontroller.prepareRestrictions();
			restrictionsOnGroups = pdlcontroller.getRestrictionsOnGroups();
			serviceDescription = pdlcontroller.getServiceDescription();
			
//			service = buildService(configBean.getPdlDescriptionFile());
//			Utilities.getInstance().setService(service);
//			Utilities.getInstance().setMapper(new UserMapper());
			// In case we are being reconfigured - remove existing ports first
			// to avoid duplicates
			
	
			//service.getInputs().getConstraintOnGroup().getConditionalStatement().
//			List<SingleParameter> serviceParameters = service.getParameters().getParameter();
			
//			List<ParameterReference> inputParamRefs = getParameterRefeferences(service.getInputs());
//			List<ParameterReference> outputParamRefs = getParameterRefeferences(service.getOutputs());
			
//			ArrayList<SingleParameter> inputParameters = getSubsetOfSingleParameter(serviceParameters, inputParamRefs);
//			ArrayList<SingleParameter> outputParameters = getSubsetOfSingleParameter(serviceParameters, outputParamRefs);
			
//			//Input ports
//			hashParameters = new HashMap();
			
			//Input ports
			HashMap<String, SingleParameter> inputSingleParams = pdlcontroller.getHashInputParameters();
			for(String paramName : inputSingleParams.keySet()){
				addInput(paramName, 0, true, null, String.class);
			}
			
			//Output ports
			HashMap<String, SingleParameter> outputSingleParams = pdlcontroller.getHashOutputParameters();
			for(String paramName : outputSingleParams.keySet()){
				addOutput(paramName, 0);
			}
			addOutput(OUT_REPORT, 0);
			
//			for(SingleParameter param: inputParameters){
//				addInput(param.getName(), 0, true, null, String.class);
//				hashParameters.put(param.getName(), param);
//			}
			
//			//Output ports
//			for(SingleParameter param: outputParameters){
//				// Single value output port (depth 0)
//				addOutput(param.getName(), 0);
//				hashParameters.put(param.getName(), param);
//			}
	
			
			//This port is for testing
			// Single value output port (depth 0)
			
			
			
			
			//restrictions 
//			 HashMap<String, String> inputRestrictions = getRestrictionsOnGroup(service.getInputs());
//			 HashMap<String, String> outputRestrictions = getRestrictionsOnGroup(service.getOutputs());
//			 restrictionsOnGroups = new HashMap<String,String>();
//			 if(inputRestrictions!=null){
//				 restrictionsOnGroups.putAll(inputRestrictions);
//			 }
//			 if(outputRestrictions!=null){
//				 restrictionsOnGroups.putAll(outputRestrictions);
//			 }
//			 
//			 serviceDescription = service.getDescription();
			
			// FIXME: Replace with your input and output port definitions
			/*
			//The following commented code is a not efficient way to extract the inputParameters
			gp = new GroupProcessor(service);
			//System.out.println(service.getInputs().getParameterRef().get(0).getParameterName());
			gp.process();
			List<GroupHandlerHelper> groupsHandler = gp.getGroupsHandler();
			//paramsLists = new ArrayList();
			//dimensions = new HashMap();
			hashParameters = new HashMap();
			for(GroupHandlerHelper ghh : groupsHandler){
				List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
				for(SingleParameter param: paramsList){
					//The following code is commented to ignore the dimension value, due to 
					//taverna has a native way to handle grids/arrays/lists
					//int dimension = -1;
					//if(param.getDimension()!=null){
					//	try{
					//		String value = ExpressionParserFactory.getInstance()
					//		   .buildParser(param.getDimension()).parse().get(0).getValue();
					//		dimension = new Integer(value).intValue();
					//	} catch (Exception ex){
					//		logger.error("I couln't read the dimension value for "+ param.getName());
					//		dimension = -1;
					//	}
					//}
					//if(dimension > 1 ){
					//	addInput(param.getName(), 1, true, null, String.class);	
					//	//dimensions.put(param.getName(), new Integer(1));
					//}else{
					addInput(param.getName(), 0, true, null, String.class);
					//	//dimensions.put(param.getName(), new Integer(0));
					//}
					hashParameters.put(param.getName(), param);
				}
				//if(paramsList!=null && paramsLists.size()>0)
				//	paramsLists.add(paramsList);
					
			}
			*/
		}catch(ActivityConfigurationException ex){
			logger.warn("unexisting or invalid pdl description file: the service will not have inports");
		}

		// Single value output port (depth 0)
		//addOutput(OUT_SIMPLE_OUTPUT, 0);
		// Single value output port (depth 0)
		//addOutput(OUT_REPORT, 0);

	}
	
	
//	public String getServiceDescription() {
//		return serviceDescription;
//	}


	@SuppressWarnings("unchecked")
	@Override
	public void executeAsynch(final Map<String, T2Reference> inputs,
			final AsynchronousActivityCallback callback) {
		// Don't execute service directly now, request to be run ask to be run
		// from thread pool and return asynchronously
		callback.requestRun(new Runnable() {
		
//			GroupProcessor gp;
//			Service service;
			
			PDLServiceController pdlcontroller;
			
			//ArrayList<List<SingleParameter>> paramsLists;
			//HashMap<String, Integer> dimensions;
			
			/*
			 * Check if the mandatory inputs are not null
			 */
			public boolean areMandatoryInputsNotNull(){                  
				boolean validStatus = true;
            
				try{
//					List<GroupHandlerHelper> groupsHandler = gp.getGroupsHandler();
//					for(GroupHandlerHelper ghh : groupsHandler){
//						List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
					List<SingleParameter> paramsList = pdlcontroller.getSingleParametersOnGroups();
					for(SingleParameter param: paramsList){
						if(inputs.get(param.getName())==null)
							//if no dependency defined --> false
							//if no optional --> false
							if(!(param.getDependency()!=null && param.getDependency().compareTo("optional")==0))
								validStatus = false;
					}
//					}
				}catch(Exception ex){validStatus = false;}
				
				return validStatus;
			}
			
			private void checkInfo(){
				List<SingleParameter> paramList = Utilities.getInstance().getService()
						.getParameters().getParameter();
				
				for (int i = 0; i < paramList.size(); i++) {
					SingleParameter p = paramList.get(i);
					//System.out.println(p.getName());
					List<GeneralParameter> genparlist = Utilities.getInstance().getuserProvidedValuesForParameter(p);
					if(genparlist != null && genparlist.size()!=0){
						String value =Utilities.getInstance().getuserProvidedValuesForParameter(p).get(0).getValue();
						System.out.println(p.getName()+", "+ value);
					}else{
						System.out.println(p.getName()+", no value" );
					}
				}
			}
			
			
			
			
			/*
			public void run4TestNullInputs() {
				boolean callbackfails=false;
				
				InvocationContext context = callback
						.getContext();
				ReferenceService referenceService = context
						.getReferenceService();
			
				Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
				String message ="null inputs: ";
				
				try{
					service = buildService(configBean.getPdlDescriptionFile());

					Utilities.getInstance().setService(service);
					Utilities.getInstance().setMapper(new UserMapper());
					
					gp = new GroupProcessor(service);
					gp.process();
				}catch (ActivityConfigurationException e) {
					// TODO Auto-generated catch block
					callback.fail("Make sure that the service configuration has an url that points to a valid pdl description file");
					callbackfails = true;
				}
				
				List<GroupHandlerHelper> groupsHandler = gp.getGroupsHandler();
				for(GroupHandlerHelper ghh : groupsHandler){
					List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
					for(SingleParameter param: paramsList){
						if(inputs.get(param.getName())==null)
							message += param.getName()+", ";
					}
				}
				
				T2Reference simpleRef2 = referenceService.register(message,0, true, context); 
				outputs.put(OUT_REPORT, simpleRef2);
				callback.receiveResult(outputs, new int[0]);
			}
			*/
			
			public void run() {
				boolean callbackfails=false;
				
				InvocationContext context = callback
						.getContext();
				ReferenceService referenceService = context
						.getReferenceService();
				
				try {
					try{
						pdlcontroller = new PDLServiceController (configBean);
						pdlcontroller.prepareHashParametersInputs();
						hashAllParameters = pdlcontroller.getHashAllParameters();
						pdlcontroller.prepareRestrictions();
						restrictionsOnGroups = pdlcontroller.getRestrictionsOnGroups();
						pdlcontroller.prepareProcess();
						serviceDescription = pdlcontroller.getServiceDescription();
//						service = buildService(configBean.getPdlDescriptionFile());
//
//						Utilities.getInstance().setService(service);
//						Utilities.getInstance().setMapper(new UserMapper());
//						
//						gp = new GroupProcessor(service);
//						gp.process();
					}catch (ActivityConfigurationException e) {
						// TODO Auto-generated catch block
						callback.fail("Make sure that the service configuration has an url that points to a valid pdl description file"+"\n"+e.getMessage());
						logger.error("Make sure that the service configuration has an url that points to a valid pdl description file"+"\n"+e.getMessage());
						callbackfails = true;
					}
					if(!callbackfails && areMandatoryInputsNotNull()){
						
						GroupProcessor gp = pdlcontroller.getGroupProcessor();  
						
						// Resolve inputs
						List<GroupHandlerHelper> groupsHandler = gp.getGroupsHandler();
						for(GroupHandlerHelper ghh : groupsHandler){
							List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
							if(paramsList!=null && paramsList.size()>0)
								for(SingleParameter param : paramsList){
									if(inputs.get(param.getName())!=null){
										//dimension?
										int dimension= PDLServiceController.getDimension(param);
										//if depth is 0 && dimension==1 then generalParamList only has one element
										if(dimension==1){
											String value = (String) referenceService.renderIdentifier(inputs.get(param.getName()), 
													String.class, context);
											// put every input in the Mapper
											List<GeneralParameter> generalParamList = new ArrayList<GeneralParameter>();
											GeneralParameter gparam = new GeneralParameter(value, 
													param.getParameterType().toString(), param.getName(),
													new GeneralParameterVisitor());
											generalParamList.add(gparam);
											
											Utilities.getInstance().getMapper().getMap()
											  .put(param.getName(), generalParamList);
										}else{
											//if depth is 1 then generalParamList has several elements
											//and input port gets a list
											List<String> values = (List<String>) referenceService.renderIdentifier(inputs.get(param.getName()), 
													String.class, context);
											//TODO
											//check if values has the size than it is said in dimension??
											
											List<GeneralParameter> generalParamList = new ArrayList<GeneralParameter>();
											for(String value : values){
												// put every input in the Mapper
												
												GeneralParameter gparam = new GeneralParameter(value, 
														param.getParameterType().toString(), param.getName(),
														new GeneralParameterVisitor());
												generalParamList.add(gparam);
											}
											Utilities.getInstance().getMapper().getMap()
											  .put(param.getName(), generalParamList);
										}
									} // end if(inputs.get(param.getName())!=null){
							}//end for(List<SingleParameter> list : paramsLists){
						}
											
						//end of reading inputs
						//checkInfo();
						//Input values VALIDATION
						PDLServiceValidation pdlServiceValidation = new PDLServiceValidation(gp);
						
						// CALL THE SERVICE
						//create a rest activity
						MyDefaultServiceCaller caller = new MyDefaultServiceCaller();
						
						String serviceResult = caller.callService();
						//example call: http://www.vm-calc-lerma02.com:8081/broadening?mail=jgarrido@iaa.es&InitialLevel=1&FinalLevel=3&Temperature=15&Density=0.6666667
						
						
						//System.out.println("******is valid service???:  "+ pdlServiceValidation.isValid());
						//System.out.println("status:  "+ pdlServiceValidation.getStatus());
						
						if(!callbackfails && pdlServiceValidation.isValid()){
							Map<String, T2Reference> outputs = new HashMap<String, T2Reference>();
							//validation results
							T2Reference simpleRef2 = referenceService.register(PDLServiceController.getValidStatus(),0, true, context); 
							outputs.put(OUT_REPORT, simpleRef2);
							//response body
							simpleRef2 = referenceService.register(serviceResult,0, true, context); 
							outputs.put(RESPONSE_BODY, simpleRef2);
							callback.receiveResult(outputs, new int[0]);
						}else{
							logger.error("Invalid values for the input parameters, check the restrictions");
							callback.fail("Invalid values for the input parameters, check the restrictions");
						}
						
					}else{
						if(callbackfails==false)
							callback.fail("Mandatory inputs are null");
					}
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					logger.error("Problems in the run method. Is it correct the pdl-description file url?: "+ configBean.getPdlDescriptionFile()+". "+e.getMessage());
					callback.fail("Problems in the run method. Is it correct the pdl-description file url?: "+ configBean.getPdlDescriptionFile()+"\n"+e.getMessage());
				} 
			}
			
		});
	}

	@Override
	public PDLServiceActivityConfigurationBean getConfiguration() {
		return this.configBean;
	}


	public String getServiceDescription() {
		return serviceDescription;
	}
	
	public HashMap<String, SingleParameter> getHashAllParameters(){
		return this.hashAllParameters;
	}
	
	/**
	 * 
	 * @return It returns a hash with the restrictions in natural language for each group
	 */
	public HashMap<String, String> getRestrictionsOnGroups(){
		return this.restrictionsOnGroups;
	}

	
}
