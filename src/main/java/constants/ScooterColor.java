package constants;

// цвет самоката
public enum ScooterColor {
        BLACK("Черный"),
        GREY("Серый"),
        BOTH("Два цвета"),
        NO_COLOR("Без цвета");

        private String color;
        ScooterColor(String color) {
            this.color = color;
        }

        public String getColor()
        {
            return color;
        }
}
