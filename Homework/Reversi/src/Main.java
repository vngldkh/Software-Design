public class Main {
    public static void main(String[] args) {
        Game game = Game.Init();
        if (game != null) {
            game.play();
        }
    }
}