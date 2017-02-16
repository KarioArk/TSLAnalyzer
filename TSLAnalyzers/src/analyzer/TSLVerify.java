package analyzer;

import java.util.List;

import parser.beans.ComponentInfo;
import parser.beans.TSLEntry;
import parser.beans.TaskStatus;

public class TSLVerify {

/*	public String verifyMandatoryFieldsForComponentInfo(TSLEntry tslEntry) {
		StringBuilder message = new StringBuilder();
		message = verifyComponentInfoFields(tslEntry.getComponentInfo(), message);
		message.append(verifyITLicenseRiskReview(tslEntry));
		return message.toString();
	}*/

	/*private StringBuilder verifyITLicenseRiskReview(TSLEntry tslEntry) {
		StringBuilder message = new StringBuilder();
		if (tslEntry.getComponentInfo().isFoss())
			if (tslEntry.getTaskStatuses() != null) {
				List<TaskStatus> taskStatuses = tslEntry.getTaskStatuses();
				for (TaskStatus status : taskStatuses) {
					if (!(status.isITLRTask())) {
						message.append(" IT License Risk Review is missing");
						break;
					}
				}
			}
		return message;
	}*/

	private String verifyComponentInfoFields(ComponentInfo componentInfo) {
		StringBuilder message = new StringBuilder();
		if (emptyCheck(componentInfo.getName()))
			message.append(" Name shouldn't be Empty");
		if (emptyCheck(componentInfo.getVersion()))
			message.append(" Version is Blank or Empty");
		if (emptyCheck(componentInfo.getUrl()))
			message.append(" URL is Blank or Empty");
		if (emptyCheck(componentInfo.getType()))
			message.append(" Type is Blank or Empty");
		if (emptyCheck(componentInfo.getVendor()))
			message.append(" Vendor is Blank or Empty");
		if (emptyCheck(componentInfo.getLicensingType()))
			message.append(" Licensing Type is Blank or Empty");
		if (emptyCheck(componentInfo.getMajorLayer()))
			message.append(" Major Layer is Blank or Empty");
		if (emptyCheck(componentInfo.getMinorLayer()))
			message.append(" Minor Layer is Blank or Empty");
		if (emptyCheck(componentInfo.getSubLayer()))
			message.append(" Sub Layer is Blank or Empty");
		
		return message.toString();
	}

	private boolean emptyCheck(String value) {
		return value == null || value.isEmpty();
	}

	public String verifyITLRForFOSS(TSLEntry tslEntry) {
		if(tslEntry.getComponentInfo().isFoss() )
			if(!hasITLRTask(tslEntry.getTaskStatuses())){
				return "ITLR Task is missing";
			}
		return "";
	}

	private boolean hasITLRTask(List<TaskStatus> taskStatuses) {
		for(TaskStatus taskStatus:taskStatuses){
			if(taskStatus.isITLRTask())
				return true;
		}
		return false;
	}

	public String verify(TSLEntry tslEntry) {
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append(verifyComponentInfoFields(tslEntry.getComponentInfo()))
					.append(",")
					.append(verifyITLRForFOSS(tslEntry));
		
		return errorMessage.toString();
	}

}
