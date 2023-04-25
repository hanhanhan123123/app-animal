package data.animal;

public class AnimalResponse {
	AnimalHeader header;
	AnimalBody body;

	public AnimalHeader getHeader() {
		return header;
	}

	public AnimalBody getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "AnimalResponse [header=" + header + ", body=" + body + ", getHeader()=" + getHeader() + ", getBody()="
				+ getBody() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
