package com.santander.chl.csvrest.chl_csvrest_trnsftask;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import com.santander.chl.csvrest.chl_csvrest_trnsftask.dto.CSVFile;
import com.santander.chl.csvrest.chl_csvrest_trnsftask.process.ProcessDataResponseCSVFile;

import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;

@Component
public class ConsumirRestFirst extends RouteBuilder {

	public ConsumirRestFirst() {
	}

	@Override
	public void configure() throws Exception {

		errorHandler(defaultErrorHandler().maximumRedeliveries(0));
		
		BindyCsvDataFormat bind = new BindyCsvDataFormat(CSVFile.class);
		JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(CSVFile.class);
		jacksonDataFormat.useList();
		jacksonDataFormat.setUnmarshalType(CSVFile.class);

		from("timer:simple?period=1000").to("direct:consumirRest").end();

		from("direct:consumirRest")
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
			.to("http://localhost:8084/export-csv-file")
			.log("Data recibida")
			.unmarshal(bind)
			.log("Body -> ${body}")
			.marshal().json(JsonLibrary.Jackson, true).convertBodyTo(String.class)
			.to("direct:convert-process-csv-file")
		.end();
		
		from("direct:convert-process-csv-file")
			.log("Data consumida")
			.process(new ProcessDataResponseCSVFile()).unmarshal(jacksonDataFormat)
			.log("Body -> ${body}")
			.marshal().bindy(BindyType.Csv, CSVFile.class)
			.log("Body to Body -> ${body}")
			.to("file:files/output?fileName=newSampleData.csv")
			.log("Archivo nuevo csv creado exitosamente")
		.end();

	}

}
