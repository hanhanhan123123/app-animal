package data.address;

import com.google.gson.annotations.SerializedName;

public class AddressDocument {
	//원본과 다르게 적고싶다면 원본문서의 필드명을 적어주면 값을 받을 수 있다
	//다르게 만들어도 설정하는 방법
	@SerializedName("y")
	String lng; //경도
	
	@SerializedName("x")
	String lat; //위도
	
	@SerializedName("address_type")
	String type;
	
	@SerializedName("address_name")
	String name;

	public String getLng() {
		return lng;
	}

	public String getLat() {
		return lat;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
}
