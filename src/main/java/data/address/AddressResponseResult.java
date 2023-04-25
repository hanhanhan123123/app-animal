package data.address;

public class AddressResponseResult {
 //전체설계. 필요없는 건 뺐음. 도큐먼트, 메타
 //도큐먼트가 배열. 메타는 안 쓸 것 같아서 뺐음.
	//설계했으면 응답받는곳으로 이동. addressAPI
	AddressDocument[] documents;

	public AddressDocument[] getDocuments() {
		return documents;
	}
	
}
