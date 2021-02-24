package Uno;

/**
 * The UnoCard class represents each UnoCard.
 * Each UnoCard contains two attributes which are the Color and the Values it represents.
 **/
public class UnoCard {

    /**
     * the enum class for the color which defines all the possible colors for the UnoCard
     */
    public enum Color {
        Red("Red"), Blue("Blue"), Green("Green"), Yellow("Yellow"), Black("Black"), None("None");
        private final String color;

        /**
         * method for transferring enum to String.
         * @param color
         */
        Color(String color) {
            this.color = color;
        }
        public String getValue() {
            return color;
        }
    }

    /**
     *  the enum class for the content which defines all the possible content for the UnoCard
     */
    public enum Content {
        zero(0), one(1), two(2), three(3), four(4), five(5), six(6), seven(7), eight(8), nine(9), Draw(10), Skip(11), Reverse(12), Wild(13), WildF(14);
        private final int value;
        Content(int value) {
            this.value = value;
        }

        /**
         * method for transferring enum to integer.
         */
        public int getValue() {
            return value;
        }

    }

    /**
     * A Color type variable to keep track of the color of current UnoCard
     */
    private final Color curr_color;
    /**
     * A Number type variable to keep track of the number type of current UnoCard
     */
    private final Content curr_content;

    /**
     * constructor for the UnoCard class
     * @param color the color for the UnoCard
     * @param content the content for the UnoCard
     */
    public UnoCard(final Color color, final Content content) {
        this.curr_color = color;
        this.curr_content = content;
    }

    /**
     * @return return the color of the UnoCard
     */
    public Color getColor() {
        return curr_color;
    }

    /**
     * @return return the content of the UnoCard
     */
    public Content getContent() {
        return curr_content;
    }

    /**
     * method for printing out the information of the UnoCard
     */
    public void printCardInfo() {
        if (getColor() == UnoCard.Color.Red) {
            System.out.print("Red ");
        }
        if (getColor() == UnoCard.Color.Blue) {
            System.out.print("Blue ");
        }
        if (getColor() == UnoCard.Color.Green) {
            System.out.print("Green ");
        }
        if (getColor() == UnoCard.Color.Yellow) {
            System.out.print("Yellow ");
        }
        if (getColor() == UnoCard.Color.Black) {
            System.out.print("Black ");
        }

        if (getContent() == UnoCard.Content.zero) {
            System.out.println("zero");
        }
        if (getContent() == UnoCard.Content.two) {
            System.out.println("two");
        }
        if (getContent() == UnoCard.Content.three) {
            System.out.println("three");
        }
        if (getContent() == UnoCard.Content.four) {
            System.out.println("four");
        }
        if (getContent() == UnoCard.Content.five) {
            System.out.println("five");
        }
        if (getContent() == UnoCard.Content.six) {
            System.out.println("six");
        }
        if (getContent() == UnoCard.Content.seven) {
            System.out.println("seven");
        }
        if (getContent() == UnoCard.Content.eight) {
            System.out.println("eight");
        }
        if (getContent() == UnoCard.Content.nine) {
            System.out.println("nine");
        }
        if (getContent() == UnoCard.Content.Draw) {
            System.out.println("Draw");
        }
        if (getContent() == UnoCard.Content.Skip) {
            System.out.println("Skip");
        }
        if (getContent() == UnoCard.Content.Reverse) {
            System.out.println("Reverse");
        }
        if (getContent() == UnoCard.Content.Wild) {
            System.out.println("Wild");
        }
        if (getContent() == UnoCard.Content.WildF) {
            System.out.println("WildF");
        }
    }
}
