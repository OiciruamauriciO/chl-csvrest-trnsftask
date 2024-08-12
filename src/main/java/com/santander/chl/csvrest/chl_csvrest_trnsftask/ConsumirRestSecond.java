package com.santander.chl.csvrest.chl_csvrest_trnsftask;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.santander.chl.csvrest.chl_csvrest_trnsftask.dto.CSVCTLFile;
import com.santander.chl.csvrest.chl_csvrest_trnsftask.process.ProcessDataResponseCSVCTLFile;

@Component
public class ConsumirRestSecond extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		errorHandler(defaultErrorHandler().maximumRedeliveries(0));

		BindyCsvDataFormat bind = new BindyCsvDataFormat(CSVCTLFile.class);
		JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(CSVCTLFile.class);
		jacksonDataFormat.useList();
		jacksonDataFormat.setUnmarshalType(CSVCTLFile.class);

		from("timer:simple?period=1000").to("direct:consumirRestCTL").end();

		from("direct:consumirRestCTL").setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.to("http://localhost:8084/export-csv-ctl-file").log("Data recibida").unmarshal(bind)
				.log("Body -> ${body}").marshal().json(JsonLibrary.Jackson, true).convertBodyTo(String.class)
				.to("direct:convert-process-csv-to-ctl-file").end();

		from("direct:convert-process-csv-to-ctl-file").log("Data consumida")
				.process(new ProcessDataResponseCSVCTLFile()).unmarshal(jacksonDataFormat).log("Body -> ${body}")
				.marshal().bindy(BindyType.Csv, CSVCTLFile.class).log("Body to Body -> ${body}")
				.to("file:files/output?fileName=newSampleDataToCTL.csv").log("Archivo nuevo csv creado exitosamente")
				.to("direct:exec-ctl-action").end();

		from("direct:exec-ctl-action").to(
				"exec:cmd?args=/C sqlldr supertestuser/123456@//localhost:1521/xe control='C:\\Users\\mauri\\Desktop\\ImageMaker Test\\Bitácora\\002_082024\\09082024\\loaddata09082024ctl.ctl' data='C:\\Users\\mauri\\Desktop\\ImageMaker Test\\Bitácora\\002_082024\\09082024\\tablapersonasenexcel.csv' log='C:\\Users\\mauri\\Desktop\\ImageMaker Test\\Bitácora\\002_082024\\09082024\\loaddata09082024fromapi001.log'")
				.end();
	}

}
