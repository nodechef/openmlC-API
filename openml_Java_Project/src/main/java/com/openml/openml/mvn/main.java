/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openml.openml.mvn;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.openml.apiconnector.io.OpenmlConnector;
import org.openml.apiconnector.xml.DataFeature;
import org.openml.apiconnector.xml.DataSetDescription;
import org.openml.apiconnector.xml.EvaluationScore;
import org.openml.apiconnector.xml.Run;
import org.openml.apiconnector.xml.Run.Parameter_setting;
import org.openml.apiconnector.xml.Task;
import org.openml.apiconnector.xml.Task.Input;
import org.openml.apiconnector.xml.Task.Output;
import org.openml.apiconnector.algorithms.Conversion;
import org.openml.apiconnector.xml.Data;
import org.openml.apiconnector.xml.Data.DataSet;
import org.openml.apiconnector.xml.Flow;
import org.openml.apiconnector.xml.Flow.Parameter;
import org.openml.apiconnector.xml.UploadDataSet;
import org.openml.apiconnector.xml.FlowDelete;
import org.openml.apiconnector.xml.FlowExists;
import org.openml.apiconnector.xml.FlowOwned;
import org.openml.apiconnector.xml.RunDelete;
import org.openml.apiconnector.xml.Tasks;
import org.openml.apiconnector.xml.UploadFlow;
import org.openml.apiconnector.xml.UploadRun;
import org.openml.apiconnector.xstream.XstreamXmlMapping;

/**
 *
 * @author Sami Ullah Chattha
 */
public class main {
     private static final OpenmlConnector openml = new OpenmlConnector("https://www.openml.org/", "5ff389fda327b06847db93efd0cbc1ed");
     private static final Scanner input = new Scanner(System.in);
     private static final XStream xstream = XstreamXmlMapping.getInstance();
    
    public static void main(String[] arg) throws Exception{
        System.out.println("Testing 1 2 3 ");
//        dataSetList();
    }
    
    
    public static void dataSetList() throws Exception {
        System.out.println("Data being downloaded. Please wait......");
         Data data = openml.dataList("");
          for (DataSet d : data.getData()) {
              System.out.println("ID : "+d.getDid()+" | Name : "+ d.getName() +" | Status  : "+ d.getStatus()+"  | Version  : "+ d.getVersion());
          }
    }
      
      
    public static void download(int id) throws Exception{
            DataSetDescription data = openml.dataGet(id);
            //Retrieves the description of a specified data set.
            String name = data.getName();
            String version = data.getVersion();
            String description = data.getDescription();
            String url = data.getUrl();
            System.out.println("Name :" + name);
            System.out.println("Version :" + version);
            System.out.println("Description :" + description);
           
    }
    public static void datasetDownload(int id) throws Exception{
        
        URL myURL = new URL("https://www.openml.org/data/download/"+id+"/?api_key=5ff389fda327b06847db93efd0cbc1ed");
        HttpConnector.getFileFromUrl(myURL, "C:/Users/Sami Ullah Chattha/Documents/Visual Studio 2015/Projects/Testing2/Testing2/classes/openml/dataSet_"+id+"/",true );
        System.out.println("\n Data has been successfully downloaded to /openml/ :");
        
    }
    
    public static void datasetFeatures(int id) throws Exception{
        DataFeature reponse = openml.dataFeatures(id);
        DataFeature.Feature[] features = reponse.getFeatures();
        String feature_name = features[0].getName();
        String type = features[0].getDataType();
        boolean	isTarget = features[0].getIs_target();
    }
    
    
    
    public static void upload(String dataSetName,String dataSetDescription,String filename) throws Exception{
        DataSetDescription dataset = new DataSetDescription( dataSetName, dataSetDescription, "arff", "class");
        File description = Conversion.stringToTempFile(xstream.toXML(dataset), filename, "xml");
        String workingDirectory = System.getProperty("user.dir")+"/openml/uploads";
        File datasetFile = new File(workingDirectory, filename);
        //File datasetFile = new File( DATASET_PATH );
        UploadDataSet data = openml.dataUpload( description, datasetFile);
        int data_id = data.getId();
        System.out.println("Here's the ID of uploaded dataset : "+ data_id);
    }
    public static void uploadURL(String dataSetName,String dataSetDescription,String dataUrl ) throws Exception{
        //Registers an existing dataset (hosted elsewhere). The description needs to include the url of the data set. Throws an exception if the upload failed, see openml.data.upload for error codes.
            //String dataUrl = "http://storm.cis.fordham.edu/~gweiss/data-mining/weka-data/cpu.arff";
        DataSetDescription dsd = new DataSetDescription(dataSetName, dataSetDescription, "arff", dataUrl, "class");
        String dsdXML = xstream.toXML(dsd);
        File description = Conversion.stringToTempFile(dsdXML, "test-data", "arff");
        UploadDataSet ud = openml.dataUpload(description, null);
        int data_id = ud.getId();
        System.out.println("Here's the ID of uploaded dataset : "+ data_id);
    }
    
    public static void flowDescription(int id) throws Exception{
        Flow implementation = openml.flowGet(id);
        String name = implementation.getName();
        String version = implementation.getVersion();
        String description = implementation.getDescription();
        String binary_url = implementation.getBinary_url();
        String source_url = implementation.getSource_url();
        Parameter[] parameters = implementation.getParameter();
        System.out.println("Flow testing : "+ name+" version : " +version+ "    Description : " +description+ " Binary : "+binary_url+" Source : "+source_url+" parameters :"+parameters);
        
    }
    
    public static void flowsOwned() throws Exception{
        //Retrieves an array of id's of all flows/implementations owned by you.
        FlowOwned  response = openml.flowOwned();
        Integer[] ids = response.getIds();
        System.out.println("Array of all the ID's : "+ ids);

    }
    public static void flowsExists(String checkName, String checkversion) throws Exception{
        
        //Checks whether an implementation with the given name and version is already registered on OpenML.
        FlowExists checkFlow = openml.flowExists(checkName, checkversion);
        boolean exists = checkFlow.exists();
        int flow_id = checkFlow.getId();
        System.out.println("Does the flow exits : " + exists );
        System.out.println("Flow ID : " + flow_id );
        
    }
    public static void Removeflow(int id) throws Exception{
       //Removes the flow with the given id (if you are its owner).
        int flowID;
        System.out.println("Enter the flow ID to delete : " );                        
        flowID = input.nextInt();
        FlowDelete  response2 = openml.flowDelete(flowID);
        
    }
    
     public static void Uploadflow(String descriptionFlow,String codeJar,String sourceZip ) throws Exception{
        // Uploads implementation files (binary and/or source) to OpenML given a description.
        Flow flow = new Flow("weka.J48", "3.7.12", descriptionFlow, "Java", "WEKA 3.7.12");
        File description3 = Conversion.stringToTempFile(xstream.toXML(flow), "some_name", "xml");
        UploadFlow response3 = openml.flowUpload( description3, new File(codeJar), new File(sourceZip));
        int flow_id3 = response3.getId();
        System.out.println("Here's the ID of uploaded flow : "+ flow_id3);
     }
     
    public static void taskDescription(int id) throws Exception{
       //Retrieves the description of the task with the given id.
        Task task = openml.taskGet(id);
        String task_type = task.getTask_type();
        Input[] inputs = task.getInputs();
        Output[] outputs = task.getOutputs();
        System.out.println("task_type : "+ task_type+" version : " +inputs+ "Description : " +outputs);
    }
    
    public static void taskEvaluations() throws Exception{
        //Retrieves all evaluations for the task with the given id.
        Tasks tasks = openml.taskList("");
        for(org.openml.apiconnector.xml.Tasks.Task t : tasks.getTask() ){
            System.out.println("ID : "+t.getDid() +" | Name : "+t.getName()+" | Status : " +t.getStatus() + " | Task Type "+ t.getTask_type());
        }
    }
    public static void runDescription(int id) throws Exception{
         //Retrieves the description of the run with the given id.
         
        Run run = openml.runGet(id);
        int task_id = run.getTask_id();
        int flow_id = run.getFlow_id();
        Parameter_setting[] settings = run.getParameter_settings();
        EvaluationScore[] scores = run.getOutputEvaluation();
        System.out.println("Run ID  : "+ task_id+" Flow ID : " +flow_id+ " Settings : " +Arrays.toString(settings) + " Scores : " +Arrays.toString(scores) );
        
    }
    
    public static void runDelete(int id) throws Exception{
         //Deletes the run with the given id (if you are its owner).
        RunDelete response = openml.runDelete(id);
    }
    
    public static void runUpload(String tag,String tag2,String fileName) throws Exception{
        //        Uploads a run to OpenML, including a description and a set of output files depending on the task type.
        String[] tags = new String[2];                    
        tags[0] = tag;
        tags[1] = tag2;
        Run r = new Run(1, null, 10, null, null, tags);
        String runXML = xstream.toXML(r);
        File runFile = Conversion.stringToTempFile(runXML, "runtest",  "xml");
        File predictions = new File("uplaods/"+fileName); 
        Map<String,File> output_files = new HashMap<String, File>();
        output_files.put("predictions", predictions);
        UploadRun ur = openml.runUpload(runFile, output_files);
        System.out.println("Here's the ID of uploaded Run : " + ur.getRun_id());
        
    }
}
