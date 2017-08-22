/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openml.openml.mvn;

import com.thoughtworks.xstream.XStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

//import org.openml.apiconnector.io.HttpConnector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;
import org.openml.apiconnector.algorithms.Conversion;
import org.openml.apiconnector.algorithms.Hashing;
import org.openml.apiconnector.io.ApiException;
import org.openml.apiconnector.io.HttpConnector;
import org.openml.apiconnector.io.OpenmlConnector;
import org.openml.apiconnector.xml.Data;
import org.openml.apiconnector.xml.DataDelete;
import org.openml.apiconnector.xml.DataFeature;
import org.openml.apiconnector.xml.DataQuality;
import org.openml.apiconnector.xml.DataSetDescription;
import org.openml.apiconnector.xml.DataTag;
import org.openml.apiconnector.xml.DataUntag;
import org.openml.apiconnector.xml.TaskDelete;
import org.openml.apiconnector.xml.TaskTag;
import org.openml.apiconnector.xml.TaskUntag;
//import org.openml.apiconnector.xml.TaskInputs;
import org.openml.apiconnector.xml.UploadDataSet;
import org.openml.apiconnector.xml.UploadTask;
import org.openml.apiconnector.xml.Data.DataSet;
//import org.openml.apiconnector.xml.TaskInputs.Input;
import org.openml.apiconnector.xstream.XstreamXmlMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;








import com.thoughtworks.xstream.XStream;
import org.openml.apiconnector.xml.Task.Input;
/**
 *
 * @author Sami Ullah Chattha
 */
public class mainTest {
    
    
    private static final String data_file = "data/iris.arff";
    private static final int probe = 61;
    private static final String tag = "junittest";

    private static final String url = "https://test.openml.org/";
    private static final String key_read = "c1994bdb7ecb3c6f3c8f3b35f4b47f1f"; // mlr ..  sorry i borrowed it
    private static final OpenmlConnector client_write = new OpenmlConnector(url, "8baa83ecddfe44b561fd3d92442e3319");
    private static final OpenmlConnector client_read = new OpenmlConnector(url, key_read); 
    private static final XStream xstream = XstreamXmlMapping.getInstance();
    
    
    public mainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class main.
     */
    @org.junit.Test
    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] arg = null;
//        main.main(arg);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of dataSetList method, of class main.
     */
    @org.junit.Test
    public void testDataSetList() throws Exception {
        
//        Map<String, String> filters = new TreeMap<String, String>();
//        filters.put("tag", "study_14");
        //This is how it should be but needs extra work
//        Data datasets = client_read.dataList(filters);
//        Data datasets = client_read.dataList("");
//        assertTrue(datasets.getData().length > 20);
//        for (DataSet dataset : datasets.getData()) {
//                assertTrue(dataset.getQualities().length > 5);
//        }
        
//        System.out.println("dataSetList");
//        main.dataSetList();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of download method, of class main.
     */
    @org.junit.Test
    public void testDownload() throws Exception {
//        
//        System.out.println("download");
//        int id = 0;
//        main.download(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of datasetDownload method, of class main.
     */
    @org.junit.Test
    public void testDatasetDownload() throws Exception {
        
        
//        DataSetDescription dsd = client_read.dataGet(probe);
//		DataFeature features = client_read.dataFeatures(probe);
//		DataQuality qualities = client_read.dataQualities(probe);
//		
//		File tempDsd = Conversion.stringToTempFile(xstream.toXML(dsd), "data", "xml");
//		File tempXsd = client_read.getXSD("openml.data.upload");
//		
//		String url = client_read.getApiUrl() + "data/" + probe;
//                String workingDirectory = System.getProperty("user.dir")+"/openml/downloads/";
//                int id = 61;
//                URL myURL = new URL("https://www.openml.org/data/download/"+id+"/?api_key=5ff389fda327b06847db93efd0cbc1ed");
//		String raw = HttpConnector.getFileFromUrl(myURL, workingDirectory+"dataSet_"+id+"/",true );
//		
//		assertTrue(Conversion.validateXML(tempDsd, tempXsd));
//		
//		
//		String dsdFromOpenml = toPrettyString(raw, 0);
//		String dsdFromConnector = toPrettyString(xstream.toXML(dsd), 0);
//		
//		if (!dsdFromOpenml.equals(dsdFromConnector)) {
//			System.out.println("===== OBTAINED FROM OPENML: =====");
//			System.out.println(dsdFromOpenml);
//			System.out.println("===== LOCALLY AVILABLE: =====");
//			System.out.println(dsdFromConnector);
//		}
//		
//		assertTrue(dsdFromOpenml.equals(dsdFromConnector));
//		
//		// very easy checks, should all pass
//		assertTrue(dsd.getId() == probe);
//		assertTrue(features.getFeatures().length > 0);
//		assertTrue(qualities.getQualities().length > 0);

//
//        System.out.println("datasetDownload");
//        int id = 0;
//        main.datasetDownload(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    /**
     * Test of upload method, of class main.
     */
    @org.junit.Test
    public void testUpload() throws Exception {
//        client_write.setVerboseLevel(1);
//        DataSetDescription dsd = new DataSetDescription("test", "Unit test should be deleted", "arff", "class");
//        String dsdXML = xstream.toXML(dsd);
//        System.out.println(dsdXML);
//        File description = Conversion.stringToTempFile(dsdXML, "test-data", "arff");
//        File toUpload = new File(data_file);
//        UploadDataSet ud = client_write.dataUpload(description, toUpload);
//        DataTag dt = client_write.dataTag(ud.getId(), tag);
//        assertTrue(Arrays.asList(dt.getTags()).contains(tag));
//
//        // Download dataset and check md5 thingy
//        DataSetDescription dsd_downloaded = client_read.dataGet(ud.getId());
//        File dataset = dsd_downloaded.getDataset(key_read);
//        assertEquals(Hashing.md5(dataset), Hashing.md5(toUpload));
//
//        // create task upon it
//        Input estimation_procedure = new Input("estimation_procedure", "1");
//        Input data_set = new Input("source_data", "" + ud.getId());
//        Input target_feature = new Input("target_feature", "class");
//        Input[] inputs = {estimation_procedure, data_set, target_feature};
//        UploadTask ut = client_write.taskUpload(inputsToTaskFile(inputs, 1));
//
//        TaskTag tt = client_write.taskTag(ut.getId(), tag);
//        assertTrue(Arrays.asList(tt.getTags()).contains(tag));
//        TaskUntag tu = client_write.taskUntag(ut.getId(), tag);
//        assertTrue(tu.getTags() == null);
//
//        try {
//                client_write.dataDelete(ud.getId());
//                // this SHOULD fail, we should not be allowed to delete data that contains tasks.
//                fail("Problem with API. Dataset ("+ud.getId()+") was deleted while it contains a task ("+ut.getId()+"). ");
//        } catch(ApiException ae) {}
//
//
//        // delete the task
//        TaskDelete td = client_write.taskDelete(ut.getId());
//        assertTrue(td.get_id().equals(ut.getId()));
//
//        // and delete the data
//        DataUntag du = client_write.dataUntag(ud.getId(), tag);
//        assertTrue(du.getTags() == null);
//
//        DataDelete dd = client_write.dataDelete(ud.getId());
//        assertTrue(ud.getId() == dd.get_id());
                
                
                
//        System.out.println("upload");
//        String dataSetName = "";
//        String dataSetDescription = "";
//        String filename = "";
//        main.upload(dataSetName, dataSetDescription, filename);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of uploadURL method, of class main.
     */
    @org.junit.Test
    public void testUploadURL() throws Exception {
        
//        String dataUrl = "http://storm.cis.fordham.edu/~gweiss/data-mining/weka-data/cpu.arff";
//	client_write.setVerboseLevel(1);
//        DataSetDescription dsd = new DataSetDescription("anneal", "Unit test should be deleted", "arff", dataUrl, "class");
//        String dsdXML = xstream.toXML(dsd);
//        File description = Conversion.stringToTempFile(dsdXML, "test-data", "arff");
//
//        UploadDataSet ud = client_write.dataUpload(description, null);
//        DataTag dt = client_write.dataTag(ud.getId(), tag);
//        assertTrue(Arrays.asList(dt.getTags()).contains(tag));

        // Download dataset and check md5 thingy
//        DataSetDescription dsd_downloaded = client_read.dataGet(ud.getId());
//        File dataset = dsd_downloaded.getDataset(key_read);
//
//        assertEquals(Hashing.md5(dataset), Hashing.md5(HttpConnector.getStringFromUrl(new URL(dataUrl), false)));
                
                
                
                
//        System.out.println("uploadURL");
//        String dataSetName = "";
//        String dataSetDescription = "";
//        String dataUrl = "";
//        main.uploadURL(dataSetName, dataSetDescription, dataUrl);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of flowDescription method, of class main.
     */
    @org.junit.Test
    public void testFlowDescription() throws Exception {
//        System.out.println("flowDescription");
//        int id = 0;
//        main.flowDescription(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of flowsOwned method, of class main.
     */
    @org.junit.Test
    public void testFlowsOwned() throws Exception {
//        System.out.println("flowsOwned");
//        main.flowsOwned();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of flowsExists method, of class main.
     */
    @org.junit.Test
    public void testFlowsExists() throws Exception {
//        System.out.println("flowsExists");
//        String checkName = "";
//        String checkversion = "";
//        main.flowsExists(checkName, checkversion);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Removeflow method, of class main.
     */
    @org.junit.Test
    public void testRemoveflow() throws Exception {
//        System.out.println("Removeflow");
//        int id = 0;
//        main.Removeflow(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of Uploadflow method, of class main.
     */
    @org.junit.Test
    public void testUploadflow() throws Exception {
//        System.out.println("Uploadflow");
//        String descriptionFlow = "";
//        String codeJar = "";
//        String sourceZip = "";
//        main.Uploadflow(descriptionFlow, codeJar, sourceZip);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of taskDescription method, of class main.
     */
    @org.junit.Test
    public void testTaskDescription() throws Exception {
//        System.out.println("taskDescription");
//        int id = 0;
//        main.taskDescription(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of taskEvaluations method, of class main.
     */
    @org.junit.Test
    public void testTaskEvaluations() throws Exception {
//        System.out.println("taskEvaluations");
//        main.taskEvaluations();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of runDescription method, of class main.
     */
    @org.junit.Test
    public void testRunDescription() throws Exception {
//        System.out.println("runDescription");
//        int id = 0;
//        main.runDescription(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of runDelete method, of class main.
     */
    @org.junit.Test
    public void testRunDelete() throws Exception {
//        System.out.println("runDelete");
//        int id = 0;
//        main.runDelete(id);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of runUpload method, of class main.
     */
    @org.junit.Test
    public void testRunUpload() throws Exception {
//        System.out.println("runUpload");
//        String tag = "";
//        String tag2 = "";
//        String fileName = "";
//        main.runUpload(tag, tag2, fileName);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
//    public static String toPrettyString(String xml, int indent) throws Exception {
//        // Turn xml string into a document
//        Document document = DocumentBuilderFactory.newInstance()
//                .newDocumentBuilder()
//                .parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
//
//        // Remove whitespaces outside tags
//        XPath xPath = XPathFactory.newInstance().newXPath();
//        NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
//                                                      document,
//                                                      XPathConstants.NODESET);
//
//        for (int i = 0; i < nodeList.getLength(); ++i) {
//            Node node = nodeList.item(i);
//            node.getParentNode().removeChild(node);
//        }
//
//        // Setup pretty print options
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        transformerFactory.setAttribute("indent-number", indent);
//        Transformer transformer = transformerFactory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//        // Return pretty print xml string
//        StringWriter stringWriter = new StringWriter();
//        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
//	        return stringWriter.toString();
//	}
	
//	public static File inputsToTaskFile(Input[] inputs, int ttid) throws IOException {
//		TaskInputs task = new TaskInputs(null, ttid, inputs, null);
//		File taskFile = Conversion.stringToTempFile(xstream.toXML(task), "task", "xml");
//		return taskFile;
//	}
}
