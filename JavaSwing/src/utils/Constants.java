package utils;

public class Constants {
    public static class Directions{
        public static final int NOT_MOVING = -1;
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int SPACE = 2;
    }

    public static class PlayerConstants{
        public static final int ALIVE = 0;
        public static final int DEAD = 1;
        public static final int EGG = 2;

        //retorna a quant de sprites q a acao tem, pois podem ter acoes com mais ou menos sprites na imagem
        public static int GetSpriteAmount(int player_action){
            switch(player_action)
            {
                //quanto retorna o mesmo resultado, pode deixar os cases juntos
                case ALIVE:
                case EGG:
                case DEAD:
                    return 3;

                case 9:
                default:
                    return 2;
            }
        }
    }

    public static class SETS{
        public static final int UPS_SET = 120;
        public static final int FPS_SET = 120;
    }
}