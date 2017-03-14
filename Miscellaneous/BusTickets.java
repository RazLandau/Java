package bus_tickets;

/**
* Prints summary of different bus ticket types
/*
public class Main {

	private final static float BASE_PRICE_PER_DAY = 2.0f;

	public enum BusTicket {
		DAILY(1, BASE_PRICE_PER_DAY, "Ticket valid for 24 hours"), WEEKLY(7,
				13.30, "Ticket valid for 7 days"), MONTHLY(30, 54.00,
						"Ticket valid for 30 days"), QUARTERLY(90, 144.00,
								"Ticket valid for 3 month"), YEARLY(365, 474.50,
										"Ticket valid for 1 year"),;
		private final int duration;
		private final double price;
		private final String description;

		BusTicket(int duration, double price, String description) {
			this.duration = duration;
			this.price = price;
			this.description = description;
		}

		public int duration() {
			return duration;
		}

		public double price() {
			return price;
		}

		public String description() {
			return description;
		}
	}

	public static void main(String[] args) {
		System.out.println("Avialable tickets:");
		for (BusTicket ticket : BusTicket.values()) {
			System.out.println(String.format(
					"Name: %10s \t Validity: %3d days "
							+ "\t Price: %3.2f NIS \t Description: %s",
					ticket, ticket.duration(), ticket.price(),
					ticket.description()));
		}
	}
}
