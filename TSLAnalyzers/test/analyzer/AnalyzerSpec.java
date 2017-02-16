package analyzer;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import parser.TSLParser;
import parser.beans.TSLEntry;

public class AnalyzerSpec {

	
	/*@Test
	public void invocationSpec() throws Exception {
		TSLParser tslParser = new TSLParser("kdarunn", "jan2017");
		TSLEntry tslEntry = tslParser.parse("7010481");
		TSLVerify tslVerify = new TSLVerify();
		String verifyMandatoryFields = tslVerify.verifyMandatoryFieldsForComponentInfo(tslEntry);
		System.out.println(verifyMandatoryFields);
		assertNotNull(verifyMandatoryFields);
	}
	*/
	
	@Test
	public void checkForITLRTaskWhenTheComponentisFOSS() throws Exception {
		//Given
		TSLParser tslParser = new TSLParser("kdarunn", "jan2017");
		TSLEntry tslEntry = tslParser.parse("7010481");
		TSLVerify tslVerify = new TSLVerify();
		//When
		String errorMessage = tslVerify.verifyITLRForFOSS(tslEntry);
		//Then
		assertThat(errorMessage).isEqualTo("ITLR Task is missing");
	}
	
	
	@Test
	public void validateCompleteEntry() throws Exception {
		TSLParser tslParser = new TSLParser("kdarunn", "jan2017");
		TSLEntry tslEntry = tslParser.parse("7010481");
		TSLVerify tslVerify = new TSLVerify();
		//When
		String errorMessage = tslVerify.verify(tslEntry);
		//Then
		assertThat(errorMessage).isEqualTo(" URL is Blank or Empty,ITLR Task is missing");

	}
	
	
}
