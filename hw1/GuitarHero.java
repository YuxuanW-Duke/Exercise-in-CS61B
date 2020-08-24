//import edu.princeton.cs.algs4.StdAudio;
//import es.datastructur.synthesizer.GuitarString;
//import edu.princeton.cs.algs4.StdDraw;
//
//public class GuitarHero {
//    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
//
//    public static void main(String[] args) {
//        while (true) {
//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                if (!keyboard.contains(key)) {
//                    throw new RuntimeException("The key you typed is invalid.");
//                } else {
//                    GuitarString keyToPlay = new GuitarString(440 * Math.pow(2, keyboard.indexOf(key) / 12.0));
//                    keyToPlay.pluck();
//
//                }
//
//            }
//            StdAudio.play(keyToPlay.sample());
//        }
//    }
//}
