package com.santander.chl.csvrest.chl_csvrest_trnsftask.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDataResponseCSVCTLFile implements Processor {

	private static Logger log = LoggerFactory.getLogger(ProcessDataResponseCSVCTLFile.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {

		String dataStringCSVFile = exchange.getIn().getBody(String.class);

		if (dataStringCSVFile != null) {
			log.info("CSV está lista");
			log.info(dataStringCSVFile);

		} else {
			log.info(" CSV está no lista (null)");
		}
		
	}

}
