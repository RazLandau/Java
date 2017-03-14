package il.ac.tau.cs.sw1.ip;

public class IPAddressString implements IPAddress {

	private String address;

	IPAddressString(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return getOctet(0) + "." + getOctet(1) + "." + getOctet(2) + "." + getOctet(3);
	}

	@Override
	public boolean equals(IPAddress other) {
		for (int i = 0; i < 4; i++) {
			if (getOctet(i) != other.getOctet(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getOctet(int index) {
		return Integer.parseInt(address.split(".")[index]);
	}

	@Override
	public boolean isPrivateNetwork() {
		int octet0 = getOctet(0);
		int octet1 = getOctet(1);
		boolean result = false;
		switch (octet0) {
		case (10):
			result = true;
			break;
		case (172):
			if (octet1 >= 16 && octet1 <= 31) {
				result = true;
				break;
			}
		case (192):
			if (octet1 == 168) {
				result = true;
				break;
			}
		case (169):
			if (octet1 == 254) {
				result = true;
				break;
			}
		}
		return result;
	}

}
