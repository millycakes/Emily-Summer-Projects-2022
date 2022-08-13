package uno;

public enum Color {
	
	RED ("red"),
	GREEN ("green"),
	YELLOW ("yellow"),
	BLUE ("blue"),
	ALL ("all");
	private String Color;
	
	private Color (String c) {
		this.Color = c;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}
	
	
}
