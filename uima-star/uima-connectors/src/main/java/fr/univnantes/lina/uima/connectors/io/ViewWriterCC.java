/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreemnets.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.univnantes.lina.uima.connectors.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.uima.UimaContext;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.ProcessTrace;

/**
 * view writer 
 * <p>
 * Mandatory parameters
 * <table border=1>
 *   <tr><td>String</td> <td>ViewNames</td> <td>The names of the view to export</td></tr>
 *   <tr><th>String</th> <th>OutputDir</th> <th>Directory where to export the view</th></tr>
 * </table>
 * 	
 */
public final class ViewWriterCC extends CasConsumer_ImplBase {

	/*
	 * PARAMETERS NAMES
	 */

	/**
	 * Name of the Parameter of the view names to export
	 */
	public static final String PARAM_VIEW_NAMES_TO_EXPORT = "ViewNames";

	/**
	 * Name of the Parameter of the view names to export
	 */
	public static final String PARAM_OUTPUT_DIR = "OutputDirectory";

	
	/*
	 * PARAMETERS DEFAULT VALUES
	 */
	/**
	 * Name of the default value
	 */
	// public static final String DEFAULT_ = ""

	/*
	 * LOCAL VARIABLES
	 */
	private UimaContext mContext;

	private String[] viewNames;
	
	/**
	 * Initializes the current instance.
	 */
	public void initialize() throws ResourceInitializationException {

		super.initialize();

		mContext = getUimaContext();


	}

	/**
	 * Initializes the current instance with the given type system.
	 */
	public void typeSystemInit(TypeSystem typeSystem)
	throws ResourceInitializationException {


	}

	/**
	 * Process the given CAS object.
	 */
	public void processCas(CAS cas) {

		try {
			JCas jcas = cas.getJCas();
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * Called if the processing is finished, 
	 */
	public void collectionProcessComplete(ProcessTrace trace)
	throws ResourceProcessException, IOException {
		
	}



	/**
	 * Releases allocated resources.
	 */
	public void destroy() {
		// dereference to allow garbage collection
		viewNames = null;
	}
}