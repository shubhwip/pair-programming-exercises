/**
 * StateCodeMapping class defines mapping of state code to state names
 */
package com.pp.tollplaza.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SJain
 * @version 1.0
 */
public enum StateCodeMapping {

	/** 
	 * Mapping of state code to state name
	 */
	ANDMAN_AND_NICOBAR("AN", "andman and nicobar islands"),
	ANDHRA_PRADESH("AP", "andhra pradesh"),
	ARUNACHAL_PRADESH("AR", "arunachal pradesh"),
	ASSAM("AS", "assam"),
	BIHAR("BR", "bihar"),
	CHHATISGARH("CG", "chhatisgarh"),
	CHANDIGARH("CH", "chandigarh"),
	DAMAN_AND_DIU("DD", "daman and diu"),
	DELHI("DL", "delhi"),
	DADRA_NAGAR_HAVELI("DN", "dadra and nagar haveli"),
	GOA("GA", "goa"),
	GUJRAT("GJ", "gujrat"),
	HARYANA("HR", "haryana"),
	HIMACHAL_PRADESH("HP", "himachal pradesh"),
	JHARKHAND("JH", "jharkhand"),
	JAMMU_AND_KASHMIR("JK", "jammu and kashmir"),
	KARNATAKA("KA", "karnataka"),
	KERALA("KL", "kerala"),
	LAKSHADWEEP("LD", "lakshdweep"),
	MAHARASHTRA("MH", "maharashtra"),
	MANIPUR("MN", "manipur"),
	MADHAYA_PRADESH("MP", "madhaya pradesh"),
	MIZORAM("MZ", "mizoram"),
	NAGALAND("NL", "nagaland"),
	ODISHA("OD", "odisha"),
	PUNJAB("PB", "punjab"),
	PUDUCHERRY("PY", "puducherry"),
	RAJASTHAN("RJ", "rajasthan"),
	SIKKIM("SK", "sikkim"),
	TAMILNADU("TN", "tamilnadu"),
	TRIPURA("TR", "tripura"),
	TELANGANA("TS", "telangana"),
	UTTARAKHAND("UK", "uttarakhand"),
	UTTAR_PRADESH("UP", "uttar pradesh"),
	WEST_BANGAL("WB", "west bangal");
	
	/** 
	 * state code
	 */
	private String code;
	
	/**
	 * name of state
	 */
	private String stateName;
	
	/**
	 * Map to store state code and state name mapping
	 */
	private static Map<String,String> stateCodeMap;
	
	/**
	 * Constructor to initialize the state code mapping
	 * @param code
	 * @param stateName
	 */
	private StateCodeMapping(String code, String stateName) {
		this.code = code;
		this.stateName = stateName;
	}
	
	/**
	 * static block to initialize state code to state name mapping
	 */
	static {
		stateCodeMap = new HashMap<>();
		stateCodeMap.put(ANDMAN_AND_NICOBAR.getCode(), ANDMAN_AND_NICOBAR.getStateName());
		stateCodeMap.put(ANDHRA_PRADESH.getCode(), ANDHRA_PRADESH.getStateName());
		stateCodeMap.put(ARUNACHAL_PRADESH.getCode(), ARUNACHAL_PRADESH.getStateName());
		stateCodeMap.put(ASSAM.getCode(), ASSAM.getStateName());
		stateCodeMap.put(BIHAR.getCode(), BIHAR.getStateName());
		stateCodeMap.put(CHHATISGARH.getCode(), CHHATISGARH.getStateName());
		stateCodeMap.put(CHANDIGARH.getCode(), CHANDIGARH.getStateName());
		stateCodeMap.put(DAMAN_AND_DIU.getCode(), DAMAN_AND_DIU.getStateName());
		stateCodeMap.put(DELHI.getCode(), DELHI.getStateName());
		stateCodeMap.put(DADRA_NAGAR_HAVELI.getCode(),DADRA_NAGAR_HAVELI.getStateName());
		stateCodeMap.put(GOA.getCode(), GOA.getStateName());
		stateCodeMap.put(GUJRAT.getCode(), GUJRAT.getStateName());
		stateCodeMap.put(HARYANA.getCode(), HARYANA.getStateName());
		stateCodeMap.put(HIMACHAL_PRADESH.getCode(), HIMACHAL_PRADESH.getStateName());
		stateCodeMap.put(JHARKHAND.getCode(), JHARKHAND.getStateName());
		stateCodeMap.put(JAMMU_AND_KASHMIR.getCode(), JAMMU_AND_KASHMIR.getStateName());
		stateCodeMap.put(KARNATAKA.getCode(), KARNATAKA.getStateName());
		stateCodeMap.put(KERALA.getCode(), KERALA.getStateName());
		stateCodeMap.put(LAKSHADWEEP.getCode(), LAKSHADWEEP.getStateName());
		stateCodeMap.put(MAHARASHTRA.getCode(), MAHARASHTRA.getStateName());
		stateCodeMap.put(MANIPUR.getCode(), MANIPUR.getStateName());
		stateCodeMap.put(MADHAYA_PRADESH.getCode(), MADHAYA_PRADESH.getStateName());
		stateCodeMap.put(MIZORAM.getCode(), MIZORAM.getStateName());
		stateCodeMap.put(NAGALAND.getCode(), NAGALAND.getStateName());
		stateCodeMap.put(ODISHA.getCode(), ODISHA.getStateName());
		stateCodeMap.put(PUNJAB.getCode(), PUNJAB.getStateName());
		stateCodeMap.put(PUDUCHERRY.getCode(), PUDUCHERRY.getStateName());
		stateCodeMap.put(RAJASTHAN.getCode(), RAJASTHAN.getStateName());
		stateCodeMap.put(SIKKIM.getCode(), SIKKIM.getStateName());
		stateCodeMap.put(TAMILNADU.getCode(), TAMILNADU.getStateName());
		stateCodeMap.put(TRIPURA.getCode(), TRIPURA.getStateName());
		stateCodeMap.put(TELANGANA.getCode(), TELANGANA.getStateName());
		stateCodeMap.put(UTTARAKHAND.getCode(),UTTARAKHAND.getStateName());
		stateCodeMap.put(UTTAR_PRADESH.getCode(), UTTAR_PRADESH.getStateName());
		stateCodeMap.put(WEST_BANGAL.getCode(), WEST_BANGAL.getStateName());		
	}
	
	/**
	 * getStateName method returns the state name corresponding to state code.
	 * @param code
	 * @return
	 */
	public static String getStateName(String code) {
		return stateCodeMap.get(code);
	}
	
	/**
	 * @return the code
	 */
	private String getCode() {
		return code;
	}

	/**
	 * @return the stateName
	 */
	private String getStateName() {
		return stateName;
	}
	
}
