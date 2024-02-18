package no.miles.switchex;


public class SwitchRecordPattern {

    public void recordMatching(Point point) {
        switch (point) {
            case Point(int x, int y) when x == 0 -> System.out.println("Langs y aksen y = " + y);
            case Point(int x, int y) when y == 0 -> System.out.println("Langs x aksen x = " + x);
            case Point p -> System.out.println("X = " + p.x);
           // default -> System.out.println("Ikke ett gyldig punkt dette nei");
        }
    }

    void textBlock(){}
    String text = """
            
            """;
    record Point(int x, int y) {
        // no body
    }

//    class Point {
//        private int x;
//        private int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }



}
